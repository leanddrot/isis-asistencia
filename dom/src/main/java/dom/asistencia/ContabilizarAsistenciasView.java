package dom.asistencia;

import java.util.ArrayList;
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
import org.apache.isis.applib.annotation.Render.Type;

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
		setAnio(parametros[1]);
		setDivision(parametros[2]);
		setDesde(parametros[3]);
		setHasta(parametros[4]);
		setIndice(Integer.parseInt(parametros[5]));
		llenarListaAnalisis();

	}

	@Programmatic
	private void llenarListaAnalisis() {

		//memento asistencia, anio, curso, dni, fechadesde, fechahasta
		
		String mementoAnalisis ="Esquema1,1,B,15468659,01-03-2014,31-12-2014"; 

		
		List<AnalisisAsistenciaView> listaAnalisis = new ArrayList<AnalisisAsistenciaView>();
			listaAnalisis.add(AnalisisAsistenciaService.analizarIntervaloAsistenciaAlumno(mementoAnalisis));		
		
		
		
		setAnalisisAsistenciaView(listaAnalisis);
	}

	// {{ Desde (property)
	private String desde;

	
	@MemberOrder(sequence = "1", name = "Intervalo" )
	@Column(allowsNull = "true")
	public String getDesde() {
		return desde;
	}

	public void setDesde(final String desde) {
		this.desde = desde;
	}

	// }}

	// {{ Hasta (property)
	private String hasta;

	@MemberOrder(sequence = "2", name = "Intervalo")
	@Column(allowsNull = "true")
	public String getHasta() {
		return hasta;
	}

	public void setHasta(final String hasta) {
		this.hasta = hasta;
	}

	// }}

	// {{ Anio (property)
	private String anio;

	@MemberOrder(sequence = "2", name = "Curso")
	@Column(allowsNull = "true")
	public String getAnio() {
		return anio;
	}

	public void setAnio(final String anio) {
		this.anio = anio;
	}

	// }}

	// {{ Division (property)
	private String division;

	@MemberOrder(sequence = "3", name = "Curso")
	@Column(allowsNull = "true")
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
	public List<AnalisisAsistenciaView> getAnalisisAsistenciaView() {
		return analisisAsistenciaList;
	}

	public void setAnalisisAsistenciaView(
			final List<AnalisisAsistenciaView> analisisAsistenciaList) {
		this.analisisAsistenciaList = analisisAsistenciaList;
	}

	// }} (end region)
	// //////////////////////////////////////
	

	
	// region > injected services
	@javax.inject.Inject
	private DomainObjectContainer container;
	
	// endregion
	
}
