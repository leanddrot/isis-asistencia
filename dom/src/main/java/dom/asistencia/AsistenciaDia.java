package dom.asistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.MemberOrder;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

public class AsistenciaDia {
	
	// {{ Fecha (property)
	private Date fecha;

	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(final Date fecha) {
		this.fecha = fecha;
	}
	// }}


	// {{ Alumnos (Collection)
	@Join
	@Element(dependent = "false")
	private List<AsistenciaAlumno> alumnoList = new ArrayList<AsistenciaAlumno>();

	@MemberOrder(sequence = "1")
	public List<AsistenciaAlumno> getAlumnos() {
		return alumnoList;
	}

	public void setAlumnos(final List<AsistenciaAlumno> alumnoList) {
		this.alumnoList = alumnoList;
	}
	// }}

}
