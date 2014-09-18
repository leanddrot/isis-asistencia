package dom.asistencia;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.query.QueryDefault;

@DomainService
public class AnalisisAsistenciaService {

	public static AnalisisAsistenciaView analizarIntervaloAsistenciaAlumno(String memento) {

		// memento asistencia, anio, division, dni, , nombre, apellido, fechadesde, fechahasta

		String[] parametros = memento.split(",");

		String asistencia = parametros[0];
		String anio = (parametros[1]);

		int anioInt = Integer.parseInt(anio);
		String division = (parametros[2]);
		String dni = (parametros[3]);
		String nombre = parametros[4];
		String apellido = parametros[5];
		String desde = parametros[6];
		Date desdeDate = null;
		try {
			desdeDate = TraductorServicio.stringToDate(desde);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String hasta = parametros[7];
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
		
		// total asistencias
		
		int totalAsistencias = tempList.size();
		
		// total presente ausente tarde
		
		int presente = 0;
		int ausente = 0; 
		int tarde = 0;
				
		for (AsistenciaAlumno asistenciaAlumno : tempList){
			if (asistenciaAlumno.getEstaPresente()){
				presente++;
			}
			
			if (!asistenciaAlumno.getEstaPresente() && !asistenciaAlumno.getLlegoTarde()){
				ausente++;
			}
			
			if (asistenciaAlumno.getLlegoTarde()){
				tarde++;
			}
		}
		
		// porcentaje tarde ausente
	
		BigDecimal porcentajeTarde;
		BigDecimal porcentajeAusente;
		BigDecimal tardeBD=new BigDecimal(tarde);
		BigDecimal ausenteBD=new BigDecimal (ausente);
		
		if (totalAsistencias != 0){
			
			porcentajeTarde= tardeBD.multiply(new BigDecimal(100)).divide(new BigDecimal(totalAsistencias),2,BigDecimal.ROUND_HALF_UP);
			porcentajeAusente= ausenteBD.multiply(new BigDecimal(100)).divide(new BigDecimal(totalAsistencias),2,BigDecimal.ROUND_HALF_UP);
		}
		else{
			porcentajeTarde= new BigDecimal(0);
			porcentajeAusente=  new BigDecimal(0);
		}
		
//		double porcentajeTarde = tarde * 100 / totalAsistencias;
//		double porcentajeAusente = ausente * 100 / totalAsistencias;
		
		//total inasistencias 
		
		BigDecimal mediaFalta = tardeBD.divide(new BigDecimal(2),1,BigDecimal.ROUND_HALF_UP);	
		BigDecimal totalInasistencias = new BigDecimal(ausente).add(mediaFalta);
		
		
		
		
		// 	memento= nombre,apellido,cantidadAsistencia,presente,tarde,ausente,
		//	porcTarde,porcAusente,totalInasistencias
		
		String mementoAnalisis= nombre + "," + 
								apellido  + "," + 
								totalAsistencias  + "," +
								presente  + "," +
								tarde  + "," +
								ausente  + "," +
								porcentajeTarde  + "," +
								porcentajeAusente  + "," +
								totalInasistencias;
								
		return container.newViewModelInstance(AnalisisAsistenciaView.class, mementoAnalisis);
	}

	// region > injected services
	@javax.inject.Inject
	private static DomainObjectContainer container;

	// endregion

}
