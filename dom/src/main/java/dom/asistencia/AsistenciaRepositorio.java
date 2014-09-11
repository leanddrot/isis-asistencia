package dom.asistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Date;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.query.QueryDefault;

@DomainService(menuOrder = "50", repositoryFor = Asistencia.class)
@Named("Asistencias")
public class AsistenciaRepositorio {

	// region > identification in the UI
	// //////////////////////////////////////

	public String getId() {
		return "asistencia";
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
	@Named("Esquemas de Asistencias")
	public List<Asistencia> listAll() {
		return container.allInstances(Asistencia.class);
	}

	// endregion

	// region > create (action)
	// //////////////////////////////////////

	@MemberOrder(sequence = "2")
	@Named("Crear Esquema Asistencia")
	@Hidden(where = Where.OBJECT_FORMS)
	public Asistencia create(final @Named("Descripcion") String descripcion) {
		final Asistencia obj = container.newTransientInstance(Asistencia.class);
		
		List<AsistenciaDia> asistenciasDia = new ArrayList<AsistenciaDia>();
		obj.setDescripcion(descripcion);
		obj.setAsistenciasDiaList(asistenciasDia);
		
		container.persistIfNotAlready(obj);
		return obj;
	}

	// endregion

	// region > remove (action)
	// //////////////////////////////////////

	@ActionSemantics(Of.NON_IDEMPOTENT)
	@MemberOrder(sequence = "4")
	@Named("Eliminar Esquema Asistencia")
	public String borrarAlumno(@Named("Asistencia") Asistencia asistencia) {
		String descripcion = asistencia.getDescripcion();
		container.remove(asistencia);
		return "La Asistencia " + descripcion + " ha sido eliminada";
	}
	
	
	
	// Crear la asistencia para un dia (action)
	// ///////////////////////////////////////////////
	
	@MemberOrder(sequence = "6")
	@Named("Crear Asistencia Diaria")
	
	public Asistencia createAsistenciaDia(final @Named("Esquema") Asistencia asistencia,
											final @Named("Fecha") Date fecha) {

		AsistenciaDia asistenciaDia = new AsistenciaDia();
		List<Alumno> todosLosAlumnos = container.allInstances(Alumno.class);
		List<AsistenciaAlumno> asistenciaAlumnoList = new ArrayList<AsistenciaAlumno>();
		
		for (Alumno unAlumno : todosLosAlumnos){
			AsistenciaAlumno asistenciaAlumno = new AsistenciaAlumno();
			asistenciaAlumno.setAlumno(unAlumno);
			asistenciaAlumno.setFecha(fecha);
			asistenciaAlumnoList.add(asistenciaAlumno);
		}
		
		asistenciaDia.setFecha(fecha);
		asistenciaDia.setAsistenciaAlumnoList(asistenciaAlumnoList);
		asistencia.getAsistenciasDiaList().add(asistenciaDia);
		return asistencia;
	}
	
	public List<Asistencia> choices0CreateAsistenciaDia(){
		return container.allMatches(new QueryDefault<Asistencia>(Asistencia.class,
				"todosLosEsquemasAlfabeticamente"));
	}
	
	public Asistencia default0CreateAsistenciaDia(){
		return choices0CreateAsistenciaDia().get(0);
	}

	
	public String validateCreateAsistenciaDia(Asistencia asistencia, Date fecha){
		List<AsistenciaDia> asistenciaDiaList = container.allMatches(
				new QueryDefault<AsistenciaDia>(AsistenciaDia.class,
				"BuscarAsistenciDiaPorFechaParaUnEsquema", "fecha", fecha));
		
		if (asistenciaDiaList.isEmpty()){
			return null;
		}
				
		return "Ya existe asistencia para ese dia";
	}
	
	
	
	//todosLosEsquemasAlfabeticamente
	
	// endregion
	// /////////////////////////////////////////////////////////
	

		

	
	
		

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;
	
	// endregion
	
	
}
