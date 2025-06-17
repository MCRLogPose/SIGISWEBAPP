// com/sigis/prueba/config/OpenApiConfig.java
package com.sigis.prueba.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "SIGIS - API Gestión de Incidencias",
                version = "1.0",
                description = "Documentación técnica de la API de soporte y gestión de incidencias"
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Servidor local de desarrollo")
        },
        tags = {
                @Tag(name = "Incidencias", description = "Gestión de incidencias"),
                @Tag(name = "Asignaciones", description = "Manejo de asignaciones de técnicos"),
                @Tag(name = "Usuarios", description = "Autenticación y perfiles")
        }
)
public class OpenApiConfig {
}
