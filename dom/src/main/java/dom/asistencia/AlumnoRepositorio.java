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
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.annotation.ActionSemantics.Of;


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
		@MemberOrder(sequence = "4")
		@Named("Borrar Alumno")
		public String borrarAlumno(@Named("User") Alumno alumno) {
			String nombre = alumno.title();
			container.remove(alumno);
			return "El alumno " + nombre + " ha sido eliminado";
		}

		// endregion


		// region > injected services
		// //////////////////////////////////////

		@javax.inject.Inject
		DomainObjectContainer container;

		// endregion
		
}
