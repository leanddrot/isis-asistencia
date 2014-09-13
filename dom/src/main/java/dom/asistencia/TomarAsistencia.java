package dom.asistencia;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@ObjectType("TomarAsistencia")
public class TomarAsistencia {

	public String title() {

		Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		String fecha = formatter.format(getAlumnoActivo().getAsistenciaDia().getFecha());

		return "Asistencia de "
				+ this.getAlumnoActivo().getAlumno().getCurso().getAnio()
				+ "° "
				+ this.getAlumnoActivo().getAlumno().getCurso().getDivision()
				+ " del el día " + fecha;
	}

	// {{ AlumnoActivo (property)
	private AsistenciaAlumno alumnoActivo;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public AsistenciaAlumno getAlumnoActivo() {
		return alumnoActivo;
	}

	public void setAlumnoActivo(final AsistenciaAlumno alumnoActivo) {
		this.alumnoActivo = alumnoActivo;
	}

	// }}

	// {{ AsistenciAlumnos (Collection Property)
	// //////////////////////////////////////////

	@Join
	@Element(dependent = "false")
	private List<AsistenciaAlumno> asistenciaAlumnoList = new ArrayList<AsistenciaAlumno>();

	@Disabled
	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1")
	public List<AsistenciaAlumno> getAsistenciAlumnos() {
		return asistenciaAlumnoList;
	}

	public void setAsistenciAlumnos(
			final List<AsistenciaAlumno> asistenciaAlumnoList) {
		this.asistenciaAlumnoList = asistenciaAlumnoList;
	}

	// }} (end region)
	// //////////////////////////////////////

	// {{ Presente (action)
	@MemberOrder(sequence = "1", name = "AlumnoActivo")
	public TomarAsistencia presente() {

		getAlumnoActivo().setEstaPresente(true);
		getAlumnoActivo().setLlegoTarde(false);
		cambiarAlSiguienteAlumno();

		return this;
	}

	// }}

	// {{ Tarde (action)
	@MemberOrder(sequence = "2", name = "AlumnoActivo")
	public TomarAsistencia tarde() {

		getAlumnoActivo().setEstaPresente(true);
		getAlumnoActivo().setLlegoTarde(true);
		cambiarAlSiguienteAlumno();

		return this;
	}

	// }}

	// {{ Ausente (action)
	@MemberOrder(sequence = "3", name = "AlumnoActivo")
	public TomarAsistencia ausente() {

		getAlumnoActivo().setEstaPresente(false);
		getAlumnoActivo().setLlegoTarde(false);
		cambiarAlSiguienteAlumno();

		return this;
	}

	// }}
	
	
	// {{ Ausente (action)
	
	@MemberOrder(sequence = "4", name = "AlumnoActivo")
	public TomarAsistencia siguiente() {
				
		cambiarAlSiguienteAlumno();

		return this;
	}
	// }}
	
	@Programmatic
	public void cambiarAlSiguienteAlumno() {
		
		int activo = this.getAsistenciAlumnos().indexOf(this.getAlumnoActivo());
		System.out.println(activo);
		int siguiente = activo + 1;
		if (siguiente == this.getAsistenciAlumnos().size()) {
			siguiente = 0;
		}
		System.out.println(siguiente);
		this.setAlumnoActivo(this.getAsistenciAlumnos().get(siguiente));
	}

}
