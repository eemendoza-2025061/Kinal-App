KinalApp Control Comercial
Software para el manejo de transacciones comerciales creado mediante Spring Boot y Thymeleaf. Facilita el control de compradores, artículos, cuentas, operaciones y desgloses de facturación mediante un entorno digital y servicios API, empleando validación de ingresos por sesión.

Herramientas del Proyecto
Java 21: Código fuente
Spring Boot 3.2.0: Entorno de trabajo (web, persistencia, vistas)
Spring Data JPA 3.2.0: Consulta de información con Hibernate
Hibernate ORM 6.3.1: Vinculación de datos a objetos
Thymeleaf 3.1.2: Generador de interfaces desde el servidor
MySQL 8.x: Almacenamiento de datos
HikariCP 5.0.1: Administrador de enlaces de datos
Maven 3.x: Organización de librerías y construcción

Elementos Necesarios
Es indispensable contar con lo siguiente para el funcionamiento:

JDK 21 o versiones más recientes

Maven 3.x o superior

Servidor MySQL 8.x disponible

IntelliJ IDEA u otro entorno de desarrollo similar

Pasos para el Montaje
Obtener los archivos
Use el comando git clone seguido del enlace del proyecto y entre a la carpeta KinalApp.

Ajustar el almacenamiento
Modifique el archivo src/main/resources/application.properties colocando sus datos de acceso:

spring.datasource.url=jdbc:mysql://localhost:3306/dbKinalApp?createDatabaseIfNotExist=true
spring.datasource.username=NOMBRE_USUARIO
spring.datasource.password=CLAVE_PERSONAL

La instrucción createDatabaseIfNotExist permite que el sistema genere el esquema por sí solo. Por su parte, la propiedad ddl-auto=update se encarga de estructurar las tablas.

Iniciar el programa
En su editor, ejecute el archivo KinalappApplication.java. También puede usar la consola con la instrucción mvn spring-boot:run.

Ingreso al portal
Utilice la siguiente dirección en su buscador:
http://localhost:8500/login