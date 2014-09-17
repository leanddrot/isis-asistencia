package dom.asistencia;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.query.QueryDefault;

@DomainService(menuOrder = "40", repositoryFor = Alumno.class)
@Named("Alumnos")
public class AlumnoRepositorio {

	// region > identification in the UI
	// //////////////////////////////////////

	public String getId() {
		return "alumno";
	}

	public String iconName() {
		return "SimpleObject";
	}

	// endregion

	// region > listAll (action)
	// //////////////////////////////////////

	@Bookmarkable
	@ActionSemantics(Of.SAFE)
	@MemberOrder(sequence = "1")
	@Named("Listar Alumnos")
	public List<Alumno> listAll() {
		return container.allInstances(Alumno.class);
	}

	// endregion

	// region > create (action)
	// //////////////////////////////////////

	@MemberOrder(sequence = "2")
	@Named("Crear Alumno")
	@Hidden(where = Where.OBJECT_FORMS)
	public Alumno create(final @Named("Nombre") String nombre,
			final @Named("Apellido") String apellido,
			final @Named("Dni") String dni) {
		final Alumno obj = container.newTransientInstance(Alumno.class);

		obj.setNombre(nombre);
		obj.setApellido(apellido);
		obj.setDni(dni);
		container.persistIfNotAlready(obj);
		return obj;
	}

	// endregion

	// region > remove User (action)
	// //////////////////////////////////////

	@ActionSemantics(Of.NON_IDEMPOTENT)
	@MemberOrder(sequence = "10")
	@Named("Borrar Alumno")
	public String borrarAlumno(@Named("User") Alumno alumno) {
		String nombre = alumno.title();
		container.remove(alumno);
		return "El alumno " + nombre + " ha sido eliminado";
	}

	// endregion

	// region > list Alumnos sin curso (action)
	// //////////////////////////////////////

	@Bookmarkable
	@ActionSemantics(Of.SAFE)
	@MemberOrder(sequence = "1.5")
	@Named("Listar Alumnos Sin Curso")
	public List<Alumno> listSinCurso() {
		return container.allMatches(new QueryDefault<Alumno>(Alumno.class,
				"alumnosSinCurso"));

	}

	// endregion

	// region > list Alumnos de un curso (action)
	// //////////////////////////////////////

	@Bookmarkable
	@ActionSemantics(Of.SAFE)
	@MemberOrder(sequence = "2")
	@Named("Listar Alumnos de un Curso")
	public List<Alumno> listAlumnosDeUnCurso(final @Named("Curso") Curso curso) {
		int anio = curso.getAnio();
		String division = curso.getDivision();
		return queryListarAlumnosDeUnCurso(anio, division);
	}

	// {{ queryListarAlumnosDeUnCurso (action)
	@MemberOrder(sequence = "1")
	public static List<Alumno> queryListarAlumnosDeUnCurso(final int anio, final String division) {
		return container.allMatches(new QueryDefault<Alumno>(Alumno.class,
				"alumnosDeUnCurso", "anio", anio, "division", division));
	}
	// }}


	
	
	// endregion

	// region > list Alumnos sin curso (action)
	// //////////////////////////////////////

	@Bookmarkable
	@ActionSemantics(Of.SAFE)
	@MemberOrder(sequence = "3")
	@Named("Listar Ordenados por Curso")
	public List<Alumno> listOrdenadosPorCurso() {
		return queryOrdenadosPorCurso();
	}

	// endregion

	@Programmatic
	public static List<Alumno> queryOrdenadosPorCurso() {
		return container.allMatches(new QueryDefault<Alumno>(Alumno.class,
				"alumnosOrdenadosPorCurso"));
	}

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	static DomainObjectContainer container;

	// endregion

}
