package dom.asistencia;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Default;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bulk;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.query.QueryDefault;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@Bookmarkable
@MemberGroupLayout(columnSpans = {12,0,0,12})
public class AsistenciaDia {

	// {{ Fecha (property)
	private Date fecha;
	@Disabled
	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(final Date fecha) {
		this.fecha = fecha;
	}

	// }}

	// {{ Alumnos (Collection)
	@Join
	@Element(dependent = "false")
	
	
	private List<AsistenciaAlumno> asistenciaAlumnoList = new ArrayList<AsistenciaAlumno>();
	@ActionSemantics(Of.SAFE)
	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1")
	@Disabled
	public List<AsistenciaAlumno> getAsistenciaAlumnoList() {
		return asistenciaAlumnoList;
	}

	public void setAsistenciaAlumnoList(final List<AsistenciaAlumno> alumnoList) {
		this.asistenciaAlumnoList = alumnoList;
	}

	// }}

	// {{ prueba (action)
	@MemberOrder(name="asistenciaAlumnoList", sequence = "1")
	public AsistenciaDia prueba(
			final @Named("Alumno") AsistenciaAlumno asistenciaAlumno,
			final @Named("Presente?") boolean presente,
			final @Named("Tarde?") boolean tarde) {

		asistenciaAlumno.setEstaPresente(presente);
		asistenciaAlumno.setLlegoTarde(tarde);

		return this;
	}

	public String validatePrueba(final AsistenciaAlumno asistenciaAlumno,
			final boolean presente, final boolean tarde) {
		if (presente == false && tarde == true) {
			return "La asistencia no puede estar marcada como 'tarde' si no está 'presente'";
		}
		return null;
	}

	public AsistenciaAlumno default0Prueba() {
		return choices0Prueba().get(0);
	}

	// }}

	public List<AsistenciaAlumno> choices0Prueba() {

		return this.getAsistenciaAlumnoList();

	}

	public String title() {

		Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		String s = formatter.format(getFecha());

		return s;
	}

	
	
	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;

}
