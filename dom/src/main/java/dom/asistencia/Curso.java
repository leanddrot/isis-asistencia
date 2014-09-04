package dom.asistencia;

import java.util.List;
import java.util.ArrayList;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.VersionStrategy;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@Bounded
public class Curso {
	
	// {{ Anio (property)
	private int anio;

	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public int getAnio() {
		return anio;
	}

	public void setAnio(final int anio) {
		this.anio = anio;
	}
	// }}

	// {{ Division (property)
	private String division;

	@MemberOrder(sequence = "2")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getDivision() {
		return division;
	}

	public void setDivision(final String division) {
		this.division = division;
	}
	// }}
	
	// {{ AlumnosList (Collection)
	@Join
	@Element(dependent = "false")
	private List<Alumno> alumnosList = new ArrayList<Alumno>();

	@MemberOrder(sequence = "3")
	public List<Alumno> getAlumnosList() {
		return alumnosList;
	}

	public void setAlumnosList(final List<Alumno> alumnosList) {
		this.alumnosList = alumnosList;
	}
	
	public String title() {
		String titulo = getAnio() + "º " + getDivision();
		return titulo;
	}
	
	
	
	// }}
	


}
