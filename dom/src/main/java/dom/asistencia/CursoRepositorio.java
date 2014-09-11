package dom.asistencia;

import java.util.ArrayList;
import java.util.List;

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

@DomainService(menuOrder = "45", repositoryFor = Curso.class)
@Named("Cursos")
public class CursoRepositorio {
	
	// region > identification in the UI
		// //////////////////////////////////////

		public String getId() {
			return "curso";
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
		@Named("Listar Cursos")
		public List<Curso> listAll() {
			return querylistAll();
		}

		// endregion

		// region > create (action)
		// //////////////////////////////////////

		@MemberOrder(sequence = "2")
		@Named("Crear Curso")
		@Hidden(where = Where.OBJECT_FORMS)
		public Curso create(final @Named("Año") int anio,
							final @Named("División") String division) {
			final Curso obj = container.newTransientInstance(Curso.class);

			obj.setAnio(anio);
			obj.setDivision(division);
			
			container.persistIfNotAlready(obj);
			return obj;
		}

		// endregion

		// region > remove (action)
		// //////////////////////////////////////

		@ActionSemantics(Of.NON_IDEMPOTENT)
		@MemberOrder(sequence = "4")
		@Named("Eliminar Curso")
		@Hidden(where = Where.OBJECT_FORMS)
		public String borrarCurso(@Named("Curso") Curso curso) {
			String descripcion = curso.getAnio()+"º "+curso.getDivision();
			container.remove(curso);
			return "El Curso " + descripcion + " ha sido eliminado";
		}

		// endregion

		@Programmatic
		public static List<Curso> querylistAll() {
			return container.allMatches(new QueryDefault<Curso>(
					Curso.class, "todosLosCursos"));
		}

		// region > injected services
		// //////////////////////////////////////

		@javax.inject.Inject
		static DomainObjectContainer container;

		// endregion
		
		

}
