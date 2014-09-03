package dom.asistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Render.Type;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

public class AsistenciaDia {
	
		
	// {{ Fecha (property)
	private Date fecha;

	@Title
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
	private List<AsistenciaAlumno> asistenciaAlumnoList = new ArrayList<AsistenciaAlumno>();

	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1")
	public List<AsistenciaAlumno> getAsistenciaAlumnoList() {
		return asistenciaAlumnoList;
	}

	public void setAsistenciaAlumnoList(final List<AsistenciaAlumno> alumnoList) {
		this.asistenciaAlumnoList = alumnoList;
	}
	// }}

	// {{ prueba (action)
	@MemberOrder(sequence = "6")
	
	public AsistenciaDia prueba(final @Named("Alumno") AsistenciaAlumno asistenciaAlumno,
								final @Named("Presente?") boolean presente,
								final @Named("Tarde?")boolean tarde) {
		
		asistenciaAlumno.setEstaPresente(presente);
		asistenciaAlumno.setLlegoTarde(tarde);
				
		return this; 
	}
	// }}

	public List<AsistenciaAlumno> choices0Prueba(){
		
		return this.getAsistenciaAlumnoList();
			
	}
	
	
	
	
}
