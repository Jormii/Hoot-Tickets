package dad.hoottickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Vendedor extends Usuario{
	@OneToMany(mappedBy = "vendedor")
	List<Evento> eventos;
	String organizacion;
	public Vendedor(String n, String c,String o) {
		super(n, c);
		eventos=new ArrayList<Evento>();
		this.organizacion=o;
	}
	public List<Evento> getEventosFecha(){
		List<Evento> porFecha = new ArrayList<Evento>();
		porFecha.addAll(eventos);
		Collections.sort(porFecha,new Comparator<Evento>() {
			@Override
			public int compare(Evento e1, Evento e2) {
				return(e1.getFecha().compareTo(e2.getFecha()));
			}
		});
		return porFecha;
	}
	public List<Evento> getEventosNombre(){
		List<Evento> porNombre = new ArrayList<Evento>();
		porNombre.addAll(eventos);
		Collections.sort(porNombre,new Comparator<Evento>() {
			@Override
			public int compare(Evento e1, Evento e2) {
				return e1.getNombre().compareToIgnoreCase(e2.getNombre());
			}
		});
		return porNombre;
	}
	public Evento buscarNombre(String n) {
		for(Evento e : eventos) {
			if (n.equals(e.getNombre())){
				return e;
			}
		}
		return null;
	}
}
