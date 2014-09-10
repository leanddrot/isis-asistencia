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

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.annotation.Render.Type;

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
	
	@Join
	@Element(dependent = "True")
	
	private List<AsistenciaDia> asistenciasDia = new ArrayList<AsistenciaDia>();
	
	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1")
	public List<AsistenciaDia> getAsistenciasDia() {
		return asistenciasDia;
	}

	public void setAsistenciasDia(final List<AsistenciaDia> asistenciasDia) {
		this.asistenciasDia = asistenciasDia;
	}

	// }}

	// region > create (action)
	// //////////////////////////////////////

	@MemberOrder(sequence = "6")
	@Named("Crear Asistencia del dia")
	
	public AsistenciaDia createAsitenciDia(final @Named("Fecha") Date fecha) {

		AsistenciaDia asistenciaDia = new AsistenciaDia();
		List<Alumno> todosLosAlumnos = container.allInstances(Alumno.class);
		List<AsistenciaAlumno> asistenciaAlumnoList = new ArrayList<AsistenciaAlumno>();
		
		for (Alumno unAlumno : todosLosAlumnos){
			AsistenciaAlumno asistenciaAlumno = new AsistenciaAlumno();
			asistenciaAlumno.setAlumno(unAlumno);
			asistenciaAlumno.setFecha(fecha);
			asistenciaAlumnoList.add(asistenciaAlumno);
		}
		
		asistenciaDia.setFecha(fecha);
		asistenciaDia.setAsistenciaAlumnoList(asistenciaAlumnoList);
		this.asistenciasDia.add(asistenciaDia);
		return asistenciaDia;
	}
	
	

	// endregion

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;

	// endregion

}
