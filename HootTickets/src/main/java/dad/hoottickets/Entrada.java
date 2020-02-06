package dad.hoottickets;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Entrada {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int identrada;
	@ManyToOne
	@JoinColumn(name = "idsesion")
	private Sesion sesion;
	private String codigo;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Sesion getSesion() {
		return sesion;
	}
	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}
	
	


}
