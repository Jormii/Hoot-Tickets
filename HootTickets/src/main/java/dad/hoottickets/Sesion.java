package dad.hoottickets;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Sesion {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idsesion;
	@ManyToOne
	@JoinColumn(name = "idevento")
	private Evento evento;
	private Date fecha;
	private double precio;
	@OneToMany(mappedBy = "sesion")
	private List<Entrada> entradas;
public Sesion() {
		
	}
	public Sesion(Date fecha, double precio) {
		this.fecha = fecha;
		this.precio = precio;
	}
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
