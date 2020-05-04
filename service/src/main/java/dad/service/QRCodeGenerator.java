package dad.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.TimeoutException;

import javax.mail.*;
import javax.mail.internet.*;

import org.apache.commons.lang3.SerializationUtils;

import javax.activation.*;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import dad.hoottickets.database.Message_Service;

public class QRCodeGenerator {
	private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";

	private static void generateQRCodeImage(String text, int width, int height, String filePath)
			throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

		Path path = FileSystems.getDefault().getPath(filePath);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	}

	public static void main(String[] args) throws DocumentException, IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("queue");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare("cola", false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				Message_Service object = SerializationUtils.deserialize(body);
				try {

					generateQRCodeImage(object.getTicket_id(), 200, 200, QR_CODE_IMAGE_PATH);
					Document document = new Document();
					PdfWriter.getInstance(document, new FileOutputStream("entrada.pdf"));

					document.open();
					Font fontTitle = FontFactory.getFont(FontFactory.COURIER_BOLD, 24, BaseColor.BLACK);
					Chunk titleChunk = new Chunk("Hoot Tickets", fontTitle);
					document.add(new Paragraph(titleChunk));
					document.add(Chunk.NEWLINE);
					
					Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
					Chunk eventTextChunk = new Chunk("Event: " + object.getEvent(), font);
					document.add(new Paragraph(eventTextChunk));
					document.add(Chunk.NEWLINE);
					
					Chunk showingTextChunk = new Chunk("Place: " + object.getShowing(), font);
					document.add(new Paragraph(showingTextChunk));
					document.add(Chunk.NEWLINE);

					Image img = Image.getInstance("./MyQRCode.png");
					document.add(img);
					document.close();
				} catch (WriterException e) {
					System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
				} catch (IOException e) {
					System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				final String username = "hoot.tickets.entradas@gmail.com";
				final String password = "h00t/tickets";

				Properties props = System.getProperties();
				props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
				props.put("mail.smtp.user", username);
				props.put("mail.smtp.clave", password); // La clave de la cuenta
				props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
				props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
				props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

				try {
					Session session = Session.getDefaultInstance(props);
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("hootTickets"));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(object.getEmail()));
					message.setSubject("Hoot Tickets: Entrada para el evento " + object.getEvent());

					// Se incluye el cuerpo del mensaje
					message.setText("Este es el mensaje");
					MimeBodyPart messageBodyPart = new MimeBodyPart();

					Multipart multipart = new MimeMultipart();

					messageBodyPart = new MimeBodyPart();
					String file = "./entrada.pdf";
					String fileName = "entradas.pdf";
					DataSource source = new FileDataSource(file);
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(fileName);
					multipart.addBodyPart(messageBodyPart);

					message.setContent(multipart);
					Transport transport = session.getTransport("smtp");
					transport.connect("smtp.gmail.com", username, password);
					transport.sendMessage(message, message.getAllRecipients());
					transport.close();

				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		};
		channel.basicConsume("cola", true, consumer);

	}
}