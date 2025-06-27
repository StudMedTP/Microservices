## Description
Este repositorio contiene los microservicios del proyecto StudMed. 
StudMed es un sistema movil utilizando una arquitectura de microservicios, 
diseñada para gestionar diversos aspectos de un sistema de asistencia de estudiantes de medicina durante sus practicas clinicas. 
Este repositorio contiene todos los microservicios que forman parte del proyecto.


## Team StudMed
* Alessandro Alex Vega Paico - u201910225
* Tomas Ortiz Fajardo - U201910146

## Project Architecture
El proyecto sigue una arquitectura de microservicios, lo que significa que cada componente del sistema 
es un servicio independiente que se comunica con los demás a través de un API Gateway. Los servicios 
están desacoplados, lo que proporciona mayor flexibilidad, escalabilidad y facilidad de mantenimiento.

## Microservices

1. Spring Boot Admin

   Supervisa y monitorea los microservicios registrados, mostrando información en tiempo real sobre su estado, métricas, logs y configuraciones mediante Spring Boot Admin.

2. API Gateway

   El API Gateway actúa como un único punto de entrada para todas las solicitudes a los microservicios. Se encarga del enrutamiento, la autenticación y la coordinación de las solicitudes a servicios como orden, carrito y producto.

2. Config Data

   Contiene las configuraciones centralizadas para todos los microservicios en formato YAML. Cada microservicio tiene su propio archivo de configuración (por ejemplo, api-gateway.yml, cart.yml, etc.), que es utilizado por el servicio de configuración.

3. Config Service

   Este servicio proporciona gestión de configuración centralizada a cada microservicio de forma dinámica. Permite actualizar configuraciones sin necesidad de reiniciar los servicios.

4. Eureka Service
   
   Implementa el descubrimiento de servicios utilizando Netflix Eureka. Todos los microservicios se registran en este servicio para permitir su descubrimiento y comunicación dinámica entre ellos.

5. Evaluation Microservice

   Gestiona toda la lógica de las evaluaciones de los estudiantes

6. Notification Microservice

   Gestiona las notificaciones del sistema.

7. User Microservice

   Gestiona toda la lógica relacionada con los usuarios, incluyendo registro, autenticación y administración de datos personales.

8. Attendance Microservice

   Gestion toda la lógica relacionada con el registro de la asistencia de los estudiantes de medicina.

9. Support Microservice

   Gestiona toda la logica relacionada con la administracion de los tickets de soporte tecnico relacionados a la ayuda de los estudiantes y docentes.

## Technologies Used

* Java con Spring Boot para el desarrollo de microservicios.

* Netflix Eureka para el descubrimiento de servicios.

* Spring Cloud Config para la gestión centralizada de configuraciones.

* Gradle como herramienta de construcción.

* API Gateway para la gestión del enrutamiento de solicitudes.

## How to Run the Project
1. Clona este repositorio.

2. Configura tu entorno con Java y Gradle.

3. Inicia los microservicios en el siguiente orden:

   - Config Service
   - Eureka Service
   - Cada Microservicios 
   - Api Gateway
   - Spring Boot Admin

4. Asegúrate de que el servicio Eureka esté en ejecución para permitir el registro de los microservicios.
