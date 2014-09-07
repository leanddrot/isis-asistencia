package dom.asistencia;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries( {
@javax.jdo.annotations.Query(
name="asistenciaAlumno_contarAusentes", language="JDOQL",
value="SELECT FROM "),

@javax.jdo.annotations.Query(name = "asistenciaAlumno_asistenciaDiaCurso", language = "JDOQL", 
value = "SELECT FROM dom.asistencia.AsistenciaAlumno"
		+ " WHERE this.alumno.curso.anio == :anio "
		+ "&& this.alumno.curso.division == :division"
		+ "&& this.fecha == :fecha") 
})

@Bounded
public class AsistenciaAlumno {

	
	// {{ Fecha (property)
	private Date date;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public Date getFecha() {
		return date;
	}

	public void setFecha(final Date date) {
		this.date = date;
	}
	// }}

	
	// {{ Alumno (property)
	private Alumno alumno;

	@MemberOrder(sequence = "1.5")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(final Alumno alumno) {
		this.alumno = alumno;
	}

	// }}

	// {{ EstaPresente (property)
	private boolean estaPresente;

	@MemberOrder(sequence = "2")
	@javax.jdo.annotations.Column(allowsNull = "false", defaultValue = "false")
	public boolean getEstaPresente() {
		return estaPresente;
	}

	public void setEstaPresente(final boolean estaPresente) {
		this.estaPresente = estaPresente;
	}

	// }}

	// {{ LlegoTarde (property)
	private boolean llegoTarde;

	@MemberOrder(sequence = "3")
	@javax.jdo.annotations.Column(allowsNull = "false", defaultValue = "false")
	public boolean getLlegoTarde() {
		return llegoTarde;
	}

	public void setLlegoTarde(final boolean llegoTarde) {
		this.llegoTarde = llegoTarde;
	}

	// }}

	public String title() {
		return getAlumno().getNombre() + " " + getAlumno().getApellido();
	}

}
