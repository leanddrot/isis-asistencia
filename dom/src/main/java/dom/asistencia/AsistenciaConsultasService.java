package dom.asistencia;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;



public class AsistenciaConsultasService 
				extends AbstractViewModel 
				implements Comparable<AsistenciaConsultasService>{

	@Override
	public int compareTo(AsistenciaConsultasService o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String viewModelMemento() {
		// TODO Auto-generated method stub
		return getDescripcion();
	}

	@Override
	public void viewModelInit(String memento) {
		// TODO Auto-generated method stub
		
	}

	
	// {{ Descripcion (property)
	private String descripcion;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}
	// }}


	
	
	
	
}
