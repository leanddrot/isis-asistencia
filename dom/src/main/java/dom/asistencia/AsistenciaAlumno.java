package dom.asistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.HomePage;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "asistenciaAlumno_contarAusentes", language = "JDOQL", value = "SELECT FROM "),

		@javax.jdo.annotations.Query(name = "asistenciaAlumno_asistenciaDiaCurso", language = "JDOQL", value = "SELECT FROM dom.asistencia.AsistenciaAlumno"
				+ " WHERE this.alumno.curso.anio == :anio "
				+ "&& this.alumno.curso.division == :division"
				+ "&& this.fecha == :fecha") })
@Bounded
public class AsistenciaAlumno {

	// {{ Fecha (property)
	private Date date;

	@Disabled
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

	@Disabled
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

	// {{ marcarPresente (action)
	@MemberOrder(sequence = "1", name = "estaPresente")
	public AsistenciaAlumno marcarPresente() {
		setEstaPresente(true);

		return this;
	}

	// }}

	// {{ marcarTarde (action)
	@MemberOrder(sequence = "1", name = "llegoTarde")
	public AsistenciaAlumno marcarTarde() {
		setEstaPresente(true);
		setLlegoTarde(true);

		return this;
	}

	// }}

	// {{ volver (action)
	@MemberOrder(sequence = "10")
	public List<AsistenciaAlumno> volverALaLista() {

		int anio = this.getAlumno().getCurso().getAnio();
		String division = this.getAlumno().getCurso().getDivision();
		Date fecha = this.getFecha();

		return container.allMatches(new QueryDefault<AsistenciaAlumno>(
				AsistenciaAlumno.class, "asistenciaAlumno_asistenciaDiaCurso",
				"anio", anio, "division", division, "fecha", fecha));
	}

	// }}

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;

	// endregion

}
