package dom.asistencia;

import java.util.List;
import java.util.ArrayList;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.query.QueryDefault;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "todosLosCursos", language = "JDOQL", 
			value = "SELECT FROM dom.asistencia.Curso"
			+ " order by anio asc, division asc"
			) })

@Bounded
public class Curso {

	// {{ Anio (property)
	private int anio;
	@Named("Año")
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
	@Persistent(mappedBy = "curso", dependentElement = "false")
	private List<Alumno> alumnosList = new ArrayList<Alumno>();
	@Render(Type.EAGERLY)
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
	
		
	// region > add Alumno (action)
	// //////////////////////////////////////

	@ActionSemantics(Of.NON_IDEMPOTENT)
	@MemberOrder(sequence = "6")
	@Named("Agregar Alumno")
	public Curso agregarAlumno(@Named("Alumno") Alumno alumno) {
		
		getAlumnosList().add(alumno);
		
		return this;
	}

	public List<Alumno> choices0AgregarAlumno (){
		return container.allMatches(new QueryDefault<Alumno>(Alumno.class,
				"alumnosSinCurso"));
		}
		
	// endregion

	
	// region > add Alumno (action)
		// //////////////////////////////////////
	@ActionSemantics(Of.NON_IDEMPOTENT)
	@MemberOrder(sequence = "7")
	@Named("Quitar Alumno")
	public Curso quitarAlumno(@Named("Alumno") Alumno alumno) {
		
		getAlumnosList().remove(alumno);
		
		return this;
	}
	
	public List<Alumno> choices0QuitarAlumno (){
		return this.alumnosList;
	}
	// endregion
	
	
	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;

	// endregion

}
