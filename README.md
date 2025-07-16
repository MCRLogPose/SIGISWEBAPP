# 📌 Sistema de Gestión de Incidencias - UTP SJL

Este proyecto tiene como objetivo implementar un sistema web para la gestión de incidencias dentro de la sede de **UTP San Juan de Lurigancho (SJL)**. Permitirá a estudiantes, personal administrativo y técnico registrar, visualizar y dar seguimiento a incidencias relacionadas con infraestructura, equipamiento o servicios TI.

---

## 🚀 Tecnologías Utilizadas

- Java 21
- Spring Boot
- Spring Security + JWT
- Maven
- MySQL / PostgreSQL
- Docker (opcional)

---

## 📥 Requisitos Previos

### ☕ Java 21 JDK

- [Descargar Java 21 JDK (Oracle)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Descargar Java 21 OpenJDK (Adoptium)](https://adoptium.net/en-GB/temurin/releases/?version=21)

### 🧪 Maven

- [Descargar Apache Maven](https://maven.apache.org/download.cgi)

Verifica la instalación ejecutando:

```bash
mvn -version
```

---

## ⚙️ Instalación del Proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/tuusuario/sistema-incidencias-utp-sjl.git
cd sistema-incidencias-utp-sjl
```

### 2. Compilar el proyecto e instalar dependencias

```bash
mvn clean install
```

---

## 🔐 Autenticación con JWT

Este sistema usa JWT para autenticación. Al iniciar sesión, recibirás un token que debe ser enviado en cada solicitud protegida en el header:

```http
Authorization: Bearer <jwt_token>
```

---

## ⚙️ Configuración de `application.properties`

Configura los siguientes valores en `src/main/resources/application.properties`:

```properties
# Puerto del servidor
server.port=8080

# Configuración de base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/incidencias_utp
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update

# JWT
jwt.secret=claveSuperSecreta123
jwt.expiration=86400000
```

---

## 📂 Estructura del Proyecto (Backend)

```
src/
├── main/
│   ├── java/
│   │   └── com.utp.incidencias
│   │       ├── controller
│   │       ├── model
│   │       ├── repository
│   │       ├── service
│   │       └── security
│   └── resources/
│       └── application.properties
└── test/
```

---

## 🧪 Endpoints API - Ejemplos

| Método | Endpoint            | Descripción                  |
|--------|---------------------|------------------------------|
| POST   | `/api/auth/login`   | Inicia sesión y retorna JWT |
| POST   | `/api/incidencias`  | Crea una nueva incidencia    |
| GET    | `/api/incidencias`  | Lista todas las incidencias |

---

## 🛠️ Empaquetado y ejecución

Empaqueta el proyecto:

```bash
mvn clean package
```

Ejecuta el `.jar` generado:

```bash
java -jar target/incidencias-utp.jar
```

---

## ✅ Funcionalidades principales

- Registro de incidencias con estado y prioridad
- Autenticación con JWT
- Roles: administrador, técnico, usuario
- Gestión de usuarios
- API REST segura

---

## 📈 Mejoras Futuras

- Notificaciones por correo
- Reportes en PDF o Excel
- Dashboard administrativo
- Soporte multisesión y logs

---

