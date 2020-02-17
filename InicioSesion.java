/**
 * 
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CrearSession")

public class CrearSession extends HttpServlet {

private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	HttpSession misession= request.getSession(true);

	User usuario = new Producto(userUsername,userEmail,userName,userSurname,userPassword);

	misession.setAttribute("User",usuario);

	PrintWriter pw= response.getWriter();

	pw.println("<html><body> Inicio sesion </body></html>");

	pw.close();
}