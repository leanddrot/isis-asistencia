package dom.asistencia;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ 
	@javax.jdo.annotations.Query(name = "alumnosSinCurso", 
			language = "JDOQL", 
			value = "SELECT FROM dom.asistencia.Alumno"
					+" WHERE this.curso == null"),
	@javax.jdo.annotations.Query(name = "alumnosDeUnCurso", 
			language = "JDOQL", 
			value = "SELECT FROM dom.asistencia.Alumno"
					+" WHERE this.curso.anio == :anio"
					+" && this.curso.division == :division"),
	@javax.jdo.annotations.Query(name = "alumnosOrdenadosPorCurso", 
			language = "JDOQL", 
			value = "SELECT FROM dom.asistencia.Alumno"
					+" order by this.curso.anio asc, this.curso.division asc")

	})
@ObjectType("alumno")
@Bounded
public class Alumno {

	// {{ Nombre (property)
	private String nombre;

	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	// }}

	// {{ Apellido (property)
	private String apellido;

	@MemberOrder(sequence = "2")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getApellido() {
		return apellido;
	}

	public void setApellido(final String apellido) {
		this.apellido = apellido;
	}

	// }}

	// {{ Dni (property)
	private String dni;

	@MemberOrder(sequence = "3")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public String getDni() {
		return dni;
	}

	public void setDni(final String dni) {
		this.dni = dni;
	}

	// }}

	public String title() {
		return this.getNombre() + " " + this.getApellido();
	}

	// {{ Curso (property)
	private Curso curso;

	@MemberOrder(sequence = "4")
	@javax.jdo.annotations.Column(allowsNull = "true")
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(final Curso curso) {
		this.curso = curso;
	}
	// }}

}
