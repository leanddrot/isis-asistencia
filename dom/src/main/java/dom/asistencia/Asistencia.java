package dom.asistencia;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Title;


@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

public class Asistencia {
	
	// {{ Descripcion (property)
	private String descripcion;

	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Title
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}
	// }}


	
	
	// {{ AsistenciasDia (Collection)
	
	@Element(column = "AsistenciasDia", dependent = "True")
	private SortedSet<AsistenciaDia> asistenciasDia = new TreeSet<AsistenciaDia>();

	@MemberOrder(sequence = "1")
	public SortedSet<AsistenciaDia> getAsistenciasDia() {
		return asistenciasDia;
	}

	public void setAsistenciasDia(final SortedSet<AsistenciaDia> asistenciasDia) {
		this.asistenciasDia = asistenciasDia;
	}
	// }}
	
	

}
