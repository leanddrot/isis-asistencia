package dom.asistencia;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;
import org.apache.isis.applib.annotation.MemberOrder;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

public class AsistenciaAlumno {
	
	// {{ Alumno (property)
	private Alumno alumno;

	@MemberOrder(sequence = "1")
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


}
