package dom.asistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberGroups;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.query.QueryDefault;


@Named("TomarAsistencia")
@DomainService(menuOrder = "80")
public class TomarAsistenciaService {

	


    //region > identification in the UI
    @Hidden
	public String getId() {
        return "tomarAsistencia";
    }

    public String title(){
    	return "Tomar Asistencia";
    }
    
    public String iconName() {
        return "SimpleObject";
    }
    //endregion
 
    
    @Named("Tomar Asistencia")
    @Bookmarkable
    @ActionSemantics(Of.SAFE)
    @MemberOrder(sequence = "2") 
    public TomarAsistencia porCurso(@Named("Esquema") final Asistencia asistencia, 
    								@Named("Curso") Curso curso, 
    								@Named("Fecha") Date fecha){
    	int anio= curso.getAnio();
		String division = curso.getDivision();
				
		List<AsistenciaAlumno> lista = queryAsistenciaAlumnoPorCursoPorDia(fecha, anio, division);
		
		TomarAsistencia ta = container.newTransientInstance(TomarAsistencia.class);
		ta.setAlumnoActivo(lista.get(0));
		ta.setAsistenciAlumnos(lista);
		container.persistIfNotAlready(ta);
		
		return ta;
    }

	public static List<AsistenciaAlumno> queryAsistenciaAlumnoPorCursoPorDia(
			Date fecha, int anio, String division) {
		return container.allMatches(new QueryDefault<AsistenciaAlumno>(
				AsistenciaAlumno.class, "asistenciaAlumno_asistenciaDiaCurso", 
				"anio", anio, 
				"division", division,
				"fecha", fecha
				));
	}
    
    public List<Curso> choices1PorCurso (){
		return container.allMatches(new QueryDefault<Curso>(
				Curso.class, "todosLosCursos"));
	}
    
    public Curso default1PorCurso (){
		return container.allMatches(new QueryDefault<Curso>(
				Curso.class, "todosLosCursos")).get(0);
	}

    public List<Asistencia> choices0PorCurso (){
		return container.allInstances(Asistencia.class);
	}
    
    public Asistencia default0PorCurso (){
		return choices0PorCurso().get(0);
	}
    
    public String validatePorCurso(Asistencia asistencia, Curso curso, Date fecha){
    	List<AsistenciaDia> asistenciaDiaList = container.allMatches(
				new QueryDefault<AsistenciaDia>(AsistenciaDia.class,
				"BuscarAsistenciDiaPorFechaParaUnEsquema", 
				"fecha", fecha,
				"descripcion",asistencia.getDescripcion()));
		
		if (!asistenciaDiaList.isEmpty()){
			return null;
		}
				
		return "No existe asistencia creada para ese dia en este esquema de asistencia";
    }
    
    @Named("Prueba ViewModel")
    @Bookmarkable
    @ActionSemantics(Of.SAFE)
    @MemberOrder(sequence = "2") 
    public TomarAsistenciaView porCurso(){
    	
    	final TomarAsistenciaView pizarra = container.newViewModelInstance(TomarAsistenciaView.class, "Prueba,Esta es la descripcion de la prueba");
    	
    	List<AsistenciaAlumno> lista = container.allInstances(AsistenciaAlumno.class);
    	pizarra.setAlumnoActivo(lista.get(0));
    	pizarra.setAsistenciAlumnos(lista);

    	return pizarra;
    	
    }
    
    
 // region > injected services
	// //////////////////////////////////////
    
	@javax.inject.Inject
	static DomainObjectContainer container;
	
	
	// endregion
}
