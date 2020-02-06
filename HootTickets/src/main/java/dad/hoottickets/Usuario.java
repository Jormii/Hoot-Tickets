package dad.hoottickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idusuario;
	private String nombre;
	private String contraseña;
	@OneToMany
	private List<Entrada> entradas;
	private String icono;
	public Usuario(String n,String c) {
		this.nombre=n;
		this.contraseña=c;
		this.entradas=new ArrayList<Entrada>();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	public List<Entrada> getEntradas() {
		return entradas;
	}
	public List<Entrada> getEntradasFecha(){
		List<Entrada> porFecha = new ArrayList<Entrada>();
		porFecha.addAll(entradas);
		Collections.sort(porFecha,new Comparator<Entrada>() {
			@Override
			public int compare(Entrada e1, Entrada e2) {
				return(int)(e1.getSesion().getFecha().compareTo(e2.getSesion().getFecha()));
			}
		});
		return porFecha;
	}
	public List<Entrada> getEntradasNombre(){
		List<Entrada> porNombre = new ArrayList<Entrada>();
		porNombre.addAll(entradas);
		Collections.sort(porNombre,new Comparator<Entrada>() {
			@Override
			public int compare(Entrada e1, Entrada e2) {
				return e1.getSesion().getEvento().getNombre().compareToIgnoreCase(e2.getSesion().getEvento().getNombre());
			}
		});
		return porNombre;
	}
	
}
