package dad.hoottickets;

import java.util.ArrayList;
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
public class Evento {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idevento;
	private String nombre;
	@ManyToOne
	@JoinColumn(name = "idusuario")
	private Vendedor vendedor;
	private Date fecha;
	private String lugar;
	@OneToMany(mappedBy = "evento")
	private List<Sesion> sesiones = new ArrayList<Sesion>();
	private String info;
public  Evento() {
		
	}
	public Evento(String nombre, Date fecha, String lugar, String info) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.lugar = lugar;
		this.info = info;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public List<Sesion> getSesiones() {
		return sesiones;
	}
	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}
	public void addSesion(Sesion add) {
		sesiones.add(add);
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	


}
