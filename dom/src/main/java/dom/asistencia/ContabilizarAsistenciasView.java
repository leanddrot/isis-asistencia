package dom.asistencia;

import java.io.ObjectInputStream.GetField;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberGroups;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.PublishedObject;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.annotation.TypicalLength;

@Named("Contabilizar Asistencias View")
@Bookmarkable
@MemberGroupLayout(columnSpans = {6, 0, 6, 12 }, right = "Intervalo", left = "Curso")
public class ContabilizarAsistenciasView extends AbstractViewModel {

	String memento;
	String title;

	public String title() {
		return title;
	}

	@Override
	public String viewModelMemento() {
		return memento;
	}

	@Override
	public void viewModelInit(String memento) {
		this.memento = memento;

		String[] parametros = memento.split(",");

		title = "Estadísticas de Asistencia";
		setAsistencia(parametros[0]);
		setAnio(Integer.parseInt(parametros[1]));
		setDivision(parametros[2]);
		try {
			setDesde(TraductorServicio.stringToDate(parametros[3]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			setHasta(TraductorServicio.stringToDate(parametros[4]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		setIndice(Integer.parseInt(parametros[5]));
		llenarAsistenciaAlumnoList();
		llenarListaAnalisis();

	}

	@Programmatic
	private void llenarListaAnalisis() {

		//memento asistencia, anio, curso, dni, nombre, apellido, fechadesde, fechahasta
		List<AnalisisAsistenciaView> listaAnalisis = new ArrayList<AnalisisAsistenciaView>();
		
		String mementoAnalisis; 

		for (Alumno alumno: getAlumnoList()){
			
			mementoAnalisis = 	getAsistencia() + "," +
								getAnio() + "," +
								getDivision()  + "," +
								alumno.getDni() + "," +
								alumno.getNombre() + "," +
								alumno.getApellido() + "," +
								TraductorServicio.DateToString(getDesde()) + "," +
								TraductorServicio.DateToString(getHasta());
			
			
			
			listaAnalisis.add(
					AnalisisAsistenciaService.analizarIntervaloAsistenciaAlumno(
							mementoAnalisis));	
					
		}
		
		setAnalisisAsistenciaView(listaAnalisis);
	}
	
	
	@Programmatic
	private void llenarAsistenciaAlumnoList() {
		
		setAlumnoList(AlumnoRepositorio.queryListarAlumnosDeUnCurso(getAnio(), getDivision()));
	}
	

	// {{ Desde (property)
	private Date desde;

	
	@MemberOrder(sequence = "1", name = "Intervalo" )
	@Column(allowsNull = "true")
	public Date getDesde() {
		return desde;
	}

	public void setDesde(final Date desde) {
		this.desde = desde;
	}

	// }}

	// {{ Hasta (property)
	private Date hasta;

	@MemberOrder(sequence = "2", name = "Intervalo")
	@Column(allowsNull = "true")
	public Date getHasta() {
		return hasta;
	}

	public void setHasta(final Date hasta) {
		this.hasta = hasta;
	}

	// }}

	// {{ Anio (property)
	private int anio;

	@MemberOrder(sequence = "2", name = "Curso")
	@Column(allowsNull = "true")
	@TypicalLength(value = 5)
	@Named("Año")
	public int getAnio() {
		return anio;
	}

	public void setAnio(final int anio) {
		this.anio = anio;
	}

	// }}

	// {{ Division (property)
	private String division;

	@MemberOrder(sequence = "3", name = "Curso")
	@Column(allowsNull = "true")
	@TypicalLength(value = 5)
	public String getDivision() {
		return division;
	}

	public void setDivision(final String division) {
		this.division = division;
	}

	// }}

	// {{ Indice (property)
	private int indice;

	@Hidden
	@Column(allowsNull = "true")
	public int getIndice() {
		return indice;
	}

	public void setIndice(final int indice) {
		this.indice = indice;
	}

	// }}

	
	// {{ Asistencia (property)
	private String asistencia;

	@MemberOrder(sequence = "1", name = "Curso")
	@Column(allowsNull = "true")
	public String getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(final String asistencia) {
		this.asistencia = asistencia;
	}
	// }}


	
	
	// {{ AnalisisAsistenciaView (Collection Property)
	// //////////////////////////////////////////

	@Join
	@Element(dependent = "true")
	private List<AnalisisAsistenciaView> analisisAsistenciaList = new ArrayList<AnalisisAsistenciaView>();

	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1")
	@Named("Analisis de Asistencia por Alumno")
	public List<AnalisisAsistenciaView> getAnalisisAsistenciaView() {
		return analisisAsistenciaList;
	}

	public void setAnalisisAsistenciaView(
			final List<AnalisisAsistenciaView> analisisAsistenciaList) {
		this.analisisAsistenciaList = analisisAsistenciaList;
	}

	// }} (end region)
	// //////////////////////////////////////
	
	
	// {{ AsistenciaAlumnoList (Collection Property)
	// //////////////////////////////////////////
	
	
	@Element(dependent = "false")
	private List<Alumno> alumnoList = new ArrayList<Alumno>();

	@Hidden
	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "10")
	public List<Alumno> getAlumnoList() {
		return alumnoList;
	}

	public void setAlumnoList(final List<Alumno> alumnoList) {
		this.alumnoList = alumnoList;
	}

	// }} (end region)
	// //////////////////////////////////////
	
	

	
	// region > injected services
	@javax.inject.Inject
	private DomainObjectContainer container;
	
	// endregion
	
}
