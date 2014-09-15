package dom.asistencia;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.query.QueryDefault;

public class AnalisisAsistenciaService {

	public static String analizarIntervaloAsistenciaAlumno(String memento) {

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

		System.out.println("");
		System.out.println("");
		System.out.println("desde = " + desdeDate + "  hasta = " + hastaDate);
		System.out.println("anio= " + anioInt + "   division = " + division);
		System.out.println("assitencia = " + asistencia + "   dni = " + dni);
		System.out.println("");
		System.out.println("");

		
//		List<AsistenciaAlumno> tempList = container.allMatches(new
//		QueryDefault<AsistenciaAlumno>( AsistenciaAlumno.class,
//		"asistenciaAlumno_ContarAsistencias", "anio", anio, "division",
//		division, "desde", desdeDate, "hasta", hastaDate, "asistencia",
//		asistencia, "dni", dni ));
//		 
//
//		List<AsistenciaAlumno> tempList = container.allMatches(new QueryDefault<AsistenciaAlumno>(
//				AsistenciaAlumno.class, "asistenciaAlumno_asistenciaDiaCurso", 
//				"anio", anioInt, 
//				"division", division,
//				"fecha", desdeDate,
//				"asistencia", asistencia
//				));

		
		List<AsistenciaAlumno> tempList = TomarAsistenciaService
		.queryAsistenciaAlumnoPorCursoPorDia(desdeDate, anioInt, division,
		asistencia);
		

		int totalAsistencias = tempList.size();

		// int totalAsistencias = 0;

		return "total " + totalAsistencias;
	}

	// region > injected services
	@javax.inject.Inject
	private static DomainObjectContainer container;

	// endregion

}
