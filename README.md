# Servicio Web de Autenticación

## Descripción
Servicio REST para registro de usuarios e inicio de sesión con Spring Boot y MySQL.

## Tecnologías
- Java 8
- Spring Boot
- Spring Data JPA
- MySQL
- BCrypt para proteger las contraseñas
- Postman para pruebas

## Endpoints

### Registro
- Método: POST
- Ruta: /api/auth/registro
- 201: usuario registrado correctamente.
- 400: datos faltantes o inválidos.
- 409: el nombre de usuario ya existe.

### Inicio de sesión
- Método: POST
- Ruta: /api/auth/login
- 200: autenticación satisfactoria.
- 400: datos faltantes o inválidos.
- 401: error en la autenticación.

## Arquitectura
El controlador recibe las solicitudes, el servicio valida las credenciales y el repositorio interactúa con MySQL.

## Seguridad
Las contraseñas se almacenan mediante BCrypt. La contraseña de MySQL se configura mediante la variable de entorno DB_PASSWORD.
