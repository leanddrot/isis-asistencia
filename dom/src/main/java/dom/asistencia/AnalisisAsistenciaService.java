package dom.asistencia;

import org.apache.isis.applib.DomainObjectContainer;

public class AnalisisAsistenciaService {

	public static String analizarUnAlumno(String memento) {

		return "ddddddd" + memento;
	}

	// region > injected services
	@javax.inject.Inject
	private static DomainObjectContainer container;

	// endregion

}
