/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package dom.asistencia;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;

@Named("TomarAsistenciaView")
@Bookmarkable
@MemberGroupLayout(columnSpans = { 5, 0, 0, 7 })
public class TomarAsistenciaView extends AbstractViewModel {

	@Hidden
	public String getId() {
		return viewModelMemento();
	}

	private String title;

	// region > identification in the UI
	public String title() {
		return title;
	}

	public String iconName() {
		return "SimpleObject";
	}

	// endregion

	// region > ViewModel contract
	private String memento;

	@Override
	public String viewModelMemento() {
		return memento;
	}

	@Override
	public void viewModelInit(String memento) {
		this.memento = memento;

		String[] parametros = memento.split(",");

		title = parametros[0];
		setAsistencia(parametros[1]);
		setFecha(parametros[2]);
		setAnio(parametros[3]);
		setDivision(parametros[4]);
		try {
			inicializarListaAlumnos(asistencia, anio, division, fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		inicializarAlumnoActivo();

	}
	
	
	@Programmatic
	private void inicializarListaAlumnos(	String asistencia,
											String anio, 
											String division,
											String fecha) throws ParseException {
		int anioInt = Integer.parseInt(anio); 
		Date fechaDate = TraductorServicio.stringToDate(fecha);
		setAsistenciAlumnos(TomarAsistenciaService.queryAsistenciaAlumnoPorCursoPorDia(fechaDate, anioInt, division));
	}
	
	@Programmatic
	private void inicializarAlumnoActivo(){
		setAlumnoActivo(getAsistenciAlumnos().get(0));
	}
	
	// {{ Asistencia (property)
	private String asistencia;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(final String asistencia) {
		this.asistencia = asistencia;
	}
	// }}


	
	// {{ Fecha (property)
	private String fecha;

	@MemberOrder(sequence = "1.3")
	@Column(allowsNull = "true")
	public String getFecha() {
		return fecha;
	}

	public void setFecha(final String fecha) {
		this.fecha = fecha;
	}

	// }}

	// {{ Anio (property)
	private String anio;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	@Named("Año")
	public String getAnio() {
		return anio;
	}

	public void setAnio(final String anio) {
		this.anio = anio;
	}

	// }}

	// {{ Division (property)
	private String division;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public String getDivision() {
		return division;
	}

	public void setDivision(final String division) {
		this.division = division;
	}

	// }}

	// {{ AlumnoActivo (property)
	private AsistenciaAlumno alumnoActivo;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
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
	@MemberOrder(sequence = "2")
	public List<AsistenciaAlumno> getAsistenciAlumnos() {
		return asistenciaAlumnoList;
	}

	public void setAsistenciAlumnos(
			final List<AsistenciaAlumno> asistenciaAlumnoList) {
		this.asistenciaAlumnoList = asistenciaAlumnoList;
	}

	// }} (end region)
	// //////////////////////////////////////

	// {{ cambiarActivo (action)
	@MemberOrder(sequence = "1", name = "alumnoActivo")
	@PublishedAction
	public TomarAsistenciaView cambiarMemento(final String memento) {

		return container.newViewModelInstance(TomarAsistenciaView.class,
				memento);
	}

	// }}

	// {{ cambiarFecha (action)
	@MemberOrder(sequence = "2", name = "alumnoActivo")
	@PublishedAction
	public TomarAsistenciaView cambiarFecha(final Date fecha) {

		String fechaString = TraductorServicio.DateToString(fecha);

		String mementoString = 	title() + "," + 
								getAsistencia() + "," +
								fechaString + "," + 
								getAnio() + "," + 
								getDivision();

		return container.newViewModelInstance(TomarAsistenciaView.class,
				mementoString);
	}

	// }}

	// region > injected services
	@javax.inject.Inject
	private DomainObjectContainer container;

	// endregion

}
