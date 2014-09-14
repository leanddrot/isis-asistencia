package dom.asistencia;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;



public class AnalisisAsistenciaView extends AbstractViewModel{

	String memento;
	
	@Override
	public String viewModelMemento() {
		return memento;
	}

	@Override
	public void viewModelInit(String memento) {
		this.memento = memento;
		setNombre(memento);
		
	}
	
	public String title(){
		return memento;
	}
	
	// {{ Nombre (property)
	private String nombre;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}
	// }}

	// {{ Apellido (property)
	private String apellido;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public String getApellido() {
		return apellido;
	}

	public void setApellido(final String apellido) {
		this.apellido = apellido;
	}
	// }}

	// {{ Total (property)
	private int totalAsistencias;

	@Named("Asistencia")
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public int getTotal() {
		return totalAsistencias;
	}

	public void setTotal(final int totalAsistencias) {
		this.totalAsistencias = totalAsistencias;
	}
	// }}

	
	// {{ Presente (property)
	private int presente;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	public int getPresente() {
		return presente;
	}

	public void setPresente(final int presente) {
		this.presente = presente;
	}
	// }}


	
	
	// {{ Tarde (property)
	private int tarde;

	@MemberOrder(sequence = "5")
	@Column(allowsNull = "true")
	public int getTarde() {
		return tarde;
	}

	public void setTarde(final int tarde) {
		this.tarde = tarde;
	}
	// }}

	
	// {{ Ausente (property)
	private int ausente;

	@MemberOrder(sequence = "6")
	@Column(allowsNull = "true")
	public int getAusente() {
		return ausente;
	}

	public void setAusente(final int ausente) {
		this.ausente = ausente;
	}
	// }}


	// {{ Porcentaje_tarde (property)
	private double porcentajeTarde;

	@Named("Tarde %")
	@MemberOrder(sequence = "7")
	@Column(allowsNull = "true")
	public double getPorcentajeTarde() {
		return porcentajeTarde;
	}

	public void setPorcentajeTarde(final double porcentajeTarde) {
		this.porcentajeTarde = porcentajeTarde;
	}
	// }}

	// {{ Porcentaje_ausente (property)
	private double porcentajeAusente;

	@Named("Ausente %")
	@MemberOrder(sequence = "8")
	@Column(allowsNull = "true")
	public double getPorcentaje_ausente() {
		return porcentajeAusente;
	}

	public void setPorcentaje_ausente(final double porcentajeAusente) {
		this.porcentajeAusente = porcentajeAusente;
	}
	// }}
	
	// {{ TotalFaltas (property)
	private double totalFaltas;

	@Named("Total Faltas")
	@MemberOrder(sequence = "9")
	@Column(allowsNull = "true")
	public double getTotalFaltas() {
		return totalFaltas;
	}

	public void setTotalFaltas(final double totalFaltas) {
		this.totalFaltas = totalFaltas;
	}
	// }}



	




}
