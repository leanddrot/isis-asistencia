package dom.asistencia;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
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

@DomainService(menuOrder = "50", repositoryFor = Asistencia.class)
@Named("Asistencia")
public class AsistenciaRepositorio {

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
	@Named("Asistencias Disponibles")
	public List<Asistencia> listAll() {
		return container.allInstances(Asistencia.class);
	}

	// endregion

	// region > create (action)
	// //////////////////////////////////////

	@MemberOrder(sequence = "2")
	@Named("Crear Asistencia")
	@Hidden(where = Where.OBJECT_FORMS)
	public Asistencia create(final @Named("Descripcion") String descripcion) {
		final Asistencia obj = container.newTransientInstance(Asistencia.class);
		
		SortedSet<AsistenciaDia> asistenciasDia = new TreeSet<AsistenciaDia>();
		obj.setDescripcion(descripcion);
		obj.setAsistenciasDia(asistenciasDia);
		
		container.persistIfNotAlready(obj);
		return obj;
	}

	// endregion

	// region > remove User (action)
	// //////////////////////////////////////

	@ActionSemantics(Of.NON_IDEMPOTENT)
	@MemberOrder(sequence = "4")
	@Named("Eliminar Asistencia")
	public String borrarAlumno(@Named("Asistencia") Asistencia asistencia) {
		String descripcion = asistencia.getDescripcion();
		container.remove(asistencia);
		return "La Asistencia " + descripcion + " ha sido eliminada";
	}

	// endregion


	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;

	// endregion
	
	
}
