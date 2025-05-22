package com.sigis.prueba.auth;

import com.sigis.prueba.auth.model.ModuloModel;
import com.sigis.prueba.auth.model.RolModel;
import com.sigis.prueba.auth.repository.ModuloRepository;
import com.sigis.prueba.auth.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ModuloRepository moduloRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public void run(String... args) throws Exception {
            ModuloModel crearIncidencia = new ModuloModel();
            crearIncidencia.setNombre("Crear Incidencia");
            crearIncidencia.setDescripcion("Permite crear nuevas incidencias");
            moduloRepository.save(crearIncidencia);

            ModuloModel aceptarIncidencia = new ModuloModel();
            aceptarIncidencia.setNombre("Aceptar Incidencia");
            aceptarIncidencia.setDescripcion("Permite aceptar tareas asignadas");
            moduloRepository.save(aceptarIncidencia);

            RolModel usuario = new RolModel();
            usuario.setTipoRol("usuario");
            usuario.setModulos(List.of(crearIncidencia));
            rolRepository.save(usuario);

            RolModel operario = new RolModel();
            operario.setTipoRol("operario");
            operario.setModulos(List.of(crearIncidencia, aceptarIncidencia));
            rolRepository.save(operario);

    }
}

