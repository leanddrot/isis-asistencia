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
    public TomarAsistencia porCurso(Curso curso, Date fecha){
    	int anio= curso.getAnio();
		String division = curso.getDivision();
		
		List<AsistenciaAlumno> lista = container.allMatches(new QueryDefault<AsistenciaAlumno>(
				AsistenciaAlumno.class, "asistenciaAlumno_asistenciaDiaCurso", 
				"anio", anio, 
				"division", division,
				"fecha", fecha
				));
		
		TomarAsistencia ta = container.newTransientInstance(TomarAsistencia.class);
		ta.setAlumnoActivo(lista.get(0));
		ta.setAsistenciAlumnos(lista);
		container.persistIfNotAlready(ta);
		
		return ta;
    }
    
    public List<Curso> choices0PorCurso (){
		return container.allMatches(new QueryDefault<Curso>(
				Curso.class, "todosLosCursos"));
	}
    
    public Curso default0PorCurso (){
		return container.allMatches(new QueryDefault<Curso>(
				Curso.class, "todosLosCursos")).get(0);
	}
    
    
    
    
 // region > injected services
	// //////////////////////////////////////
    
	@javax.inject.Inject
	DomainObjectContainer container;

	
	
	// endregion
}
