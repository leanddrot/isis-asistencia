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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	// region > identification in the UI
	public String title() {
		return viewModelMemento().split(",")[0];
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
	}

	
	//	fecha = TraductorServicio.stringToDate(viewModelMemento().split(",")[1]);
	
	
	// {{ Descripcion (property)
	private String descripcion;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	@MultiLine(numberOfLines=3)
	public String getDescripcion() {
		return viewModelMemento().split(",")[1];
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}
	// }}


	
	// {{ Fecha (property)
	private Date fecha;

	@MemberOrder(sequence = "1.3")
	@Column(allowsNull = "true")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(final Date fecha) {
		this.fecha = fecha;
	}
	// }}


	
	
	

	// {{ AlumnoActivo (property)
	private AsistenciaAlumno alumnoActivo;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public AsistenciaAlumno getAlumnoActivo() {
		return alumnoActivo;
		// return getAsistenciAlumnos().get(0);
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
		// return container.allInstances(AsistenciaAlumno.class);
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

		return container.newViewModelInstance(TomarAsistenciaView.class, memento);
	}

	// }}

	// {{ cambiarFecha (action)
		@MemberOrder(sequence = "2", name = "alumnoActivo")
		@PublishedAction
		public TomarAsistenciaView cambiarFecha(final Date fecha,
											final String descripcion) {

			String fechaString = "11-09-2014";//TraductorServicio.DateToString(fecha);
			
			
			String mementoString = descripcion + "," + fechaString;
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(mementoString);
			System.out.println(" ");
			System.out.println(" ");
			return container.newViewModelInstance(TomarAsistenciaView.class, mementoString);
		}

		// }}
	
	
	
	
	
	// region > injected services
	@javax.inject.Inject
	private DomainObjectContainer container;
	

	// endregion

}
