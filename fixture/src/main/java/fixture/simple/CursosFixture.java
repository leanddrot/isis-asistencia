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
import dom.asistencia.Curso;
import dom.asistencia.CursoRepositorio;
import dom.simple.SimpleObject;
import dom.simple.SimpleObjects;

import org.apache.isis.applib.fixturescripts.FixtureScript;

public class CursosFixture extends FixtureScript {

    public CursosFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    @Override
    protected void execute(ExecutionContext executionContext) {

        // prereqs
        execute(new AlumnosTearDownFixture(), executionContext);

        // create
        create(1, "A", executionContext);
        create(1, "B", executionContext);
        create(2, "A", executionContext);
        create(2, "B", executionContext);
        create(3, "A", executionContext);
        create(3, "B", executionContext);
        create(4, "A", executionContext);
        create(4, "B", executionContext);
        create(5, "A", executionContext);
        create(5, "B", executionContext);
        
        
    }

    // //////////////////////////////////////

    private Curso create(final int anio, String division, ExecutionContext executionContext) {
        return executionContext.add(this, cursos.create(anio, division));
    }

    // //////////////////////////////////////

    @javax.inject.Inject
    private CursoRepositorio cursos;

}
