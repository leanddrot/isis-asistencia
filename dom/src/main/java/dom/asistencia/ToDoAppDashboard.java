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

import java.util.List;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;

@MemberGroupLayout(columnSpans = {12,0,0,12})
public class ToDoAppDashboard extends AbstractViewModel {

    //region > identification in the UI
    public String title() {
        return "Pizarra";
    }
    
    public String iconName() {
        return "SimpleObject";
    }
    //endregion

    //region > ViewModel contract
    private String memento;
    
    @Override
    public String viewModelMemento() {
        return memento;
    }

    @Override
    public void viewModelInit(String memento) {
        this.memento = memento;
    }

    //endregion

    //region > Listado de Cursos (collection)
    @Named("Listado de Cursos")
    @Render(Type.EAGERLY)
    @Disabled
    @MemberOrder(sequence = "1")
    public List<Curso> getListadoDeCursos() {
    	return container.allInstances(Curso.class);
    }
    //endregion

    //region > getAnalysisByDateRange (collection)
    @Named("Todos los Alumnos")
    @Render(Type.EAGERLY)
    @Disabled
    @MemberOrder(sequence = "2")
    public List<Alumno> getListadoDeAlumnos() {
        return container.allInstances(Alumno.class);
    }
    //endregion

    //region > injected services
    @javax.inject.Inject
    private DomainObjectContainer container;

    //endregion

}
