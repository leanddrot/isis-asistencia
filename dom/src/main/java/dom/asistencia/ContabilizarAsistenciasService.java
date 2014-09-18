package dom.asistencia;

import java.util.Date;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.PublishedObject;

@Named("Contabilizar Asistencia")
@DomainService(menuOrder = "90")
public class ContabilizarAsistenciasService {

	// region > identification in the UI
	@Hidden
	public String getId() {
		return "contarAsistencia";
	}

	public String title() {
		return "Contabilizar Asistencia";
	}

	public String iconName() {
		return "SimpleObject";
	}

	// endregion

	@MemberOrder(sequence = "2")
	@PublishedAction
	public ContabilizarAsistenciasView contarAsistenciasCurso(
			@Named("Esquema") Asistencia esquema, @Named("Curso") Curso curso,
			@Named("Desde") Date desde, @Named("Hasta") Date hasta) {

		// memento: esquema, anio, division, desde, hasta

		String mementoString = esquema.getDescripcion() + "," + curso.getAnio()
				+ "," + curso.getDivision() + ","
				+ TraductorServicio.DateToString(desde) + ","
				+ TraductorServicio.DateToString(hasta) + ",-1";

		return container.newViewModelInstance(
				ContabilizarAsistenciasView.class, mementoString);
	}

	public List<Asistencia> choices0ContarAsistenciasCurso() {

		return container.allInstances(Asistencia.class);
	}

	public Asistencia default0ContarAsistenciasCurso() {

		return choices0ContarAsistenciasCurso().get(0);
	}

	public List<Curso> choices1ContarAsistenciasCurso() {

		return CursoRepositorio.querylistAll();
	}

	public Curso default1ContarAsistenciasCurso() {

		return choices1ContarAsistenciasCurso().get(0);
	}

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	static DomainObjectContainer container;

	// endregion
}
