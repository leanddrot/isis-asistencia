package dom.asistencia;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.query.QueryDefault;

@DomainService
public class AnalisisAsistenciaService {

	public static AnalisisAsistenciaView analizarIntervaloAsistenciaAlumno(String memento) {

		// memento asistencia, anio, division, dni, fechadesde, fechahasta

		String[] parametros = memento.split(",");

		String asistencia = parametros[0];
		String anio = (parametros[1]);

		int anioInt = Integer.parseInt(anio);
		String division = (parametros[2]);
		String dni = (parametros[3]);

		String desde = parametros[4];
		Date desdeDate = null;
		try {
			desdeDate = TraductorServicio.stringToDate(desde);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String hasta = parametros[5];
		Date hastaDate = null;
		try {
			hastaDate = TraductorServicio.stringToDate(hasta);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		
		List<AsistenciaAlumno> tempList = AsistenciaAlumnoRepositorio
		.queryAsistenciaAlumnoPorCursoEnUnIntervalo(	asistencia, 
										anioInt, 
										division, 
										desdeDate, 
										hastaDate, 
										dni);
		

		int totalAsistencias = tempList.size();

		String mementoAnalisis= "nombre" + totalAsistencias;
		
		return container.newViewModelInstance(AnalisisAsistenciaView.class, mementoAnalisis);
	}

	// region > injected services
	@javax.inject.Inject
	private static DomainObjectContainer container;

	// endregion

}
