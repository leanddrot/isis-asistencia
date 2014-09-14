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

		title = parametros[0];
		setAnio(parametros[1]);
		setDivision(parametros[2]);
		setDesde(parametros[3]);
		setHasta(parametros[4]);
		setIndice(Integer.parseInt(parametros[5]));
		setprueba(container.newViewModelInstance(
				AnalisisAsistenciaView.class, AnalisisAsistenciaService.analizarUnAlumno("w")));
		llenarListaAnalisis();

	}

	@Programmatic
	private void llenarListaAnalisis() {

		List<AnalisisAsistenciaView> listaAnalisis = new ArrayList<AnalisisAsistenciaView>();
		listaAnalisis.add(container.newViewModelInstance(AnalisisAsistenciaView.class, "m"));
		listaAnalisis.add(container.newViewModelInstance(AnalisisAsistenciaView.class, "n"));
		listaAnalisis.add(container.newViewModelInstance(AnalisisAsistenciaView.class, "o"));
		listaAnalisis.add(container.newViewModelInstance(AnalisisAsistenciaView.class, "p"));
		listaAnalisis.add(container.newViewModelInstance(AnalisisAsistenciaView.class, "q"));
		listaAnalisis.add(container.newViewModelInstance(AnalisisAsistenciaView.class, "r"));
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

	@MemberOrder(sequence = "4", name = "Intervalo")
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


	// {{ prueba (property)
	private AnalisisAsistenciaView prueba;

	@MemberOrder(sequence = "4", name = "Curso")
	@Column(allowsNull = "true")
	public AnalisisAsistenciaView getprueba() {
		return prueba;
	}

	public void setprueba(final AnalisisAsistenciaView prueba) {
		this.prueba = prueba;
	}
	// }}


	
	

	// region > injected services
	@javax.inject.Inject
	private DomainObjectContainer container;
	
	// endregion
	
}
