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

package fixture.simple;

import dom.asistencia.Alumno;
import dom.asistencia.AlumnoRepositorio;
import dom.simple.SimpleObject;
import dom.simple.SimpleObjects;

import org.apache.isis.applib.fixturescripts.FixtureScript;

public class AlumnosFixture extends FixtureScript {

    public AlumnosFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    @Override
    protected void execute(ExecutionContext executionContext) {

        // prereqs
        execute(new AlumnosTearDownFixture(), executionContext);

        // create
        create("Ricardo", "Dawkins","15468659", executionContext);
        create("Ernesto", "Guevara","35695847",executionContext);
        create("Nelson", "Mandela","65984825",executionContext);
        create("Luisa", "Pasteur","32648755",executionContext);
        create("Marcela", "Midori","19678648",executionContext);
        create("Ana", "Frank","64918237",executionContext);
        create("Roberto", "Gomez","32649754",executionContext);
        create("Florinda", "Mesa","29763488",executionContext);
        create("Martin", "Pasculli","58545125",executionContext);
        create("Federica", "Ramirez","69321548",executionContext);
        create("Emilio", "Estevez","26498731",executionContext);
    }

    // //////////////////////////////////////

    private Alumno create(final String nombre, String apellido, String dni, ExecutionContext executionContext) {
        return executionContext.add(this, alumnos.create(nombre, apellido, dni));
    }

    // //////////////////////////////////////

    @javax.inject.Inject
    private AlumnoRepositorio alumnos;

}
