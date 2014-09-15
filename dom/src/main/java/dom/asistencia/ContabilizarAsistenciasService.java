package dom.asistencia;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.PublishedObject;

@Named("Contar Asistencia")
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

	// //Accion
	// //////////

	@MemberOrder(sequence = "1")
	@PublishedAction
	public ContabilizarAsistenciasView contarAsistenciasAlumno() {

		// memento: asistencia, anio, division, desde, hasta, indexAlumno
		//hardcode
		String mementoString = "Esquema1,1,A,01-03-2014,31-12-2014,6";

		return container.newViewModelInstance(
				ContabilizarAsistenciasView.class, mementoString);
	}

	// //Accion
	// //////////

	@MemberOrder(sequence = "2")
	@PublishedAction
	public ContabilizarAsistenciasView contarAsistenciasCurso() {

		// memento: titulo, anio, division, desde, hasta, indexAlumno

		String mementoString = "Esquema1,1,A,01-03-2014,31-12-2014,6";

		return container.newViewModelInstance(
				ContabilizarAsistenciasView.class, mementoString);
	}

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	static DomainObjectContainer container;

	// endregion
}
