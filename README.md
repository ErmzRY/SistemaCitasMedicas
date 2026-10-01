# Sistema de Administración de Citas Médicas

## Descripción

Sistema desarrollado en Java para la administración básica de citas médicas.

El sistema permite registrar y consultar información de doctores, pacientes y citas médicas. También cuenta con un sistema de acceso mediante administrador y almacenamiento de información en archivos CSV.

## Funcionalidades

* Registro de doctores.
* Registro de pacientes.
* Registro de citas médicas.
* Consulta de información.
* Validación de identificadores únicos.
* Relación entre doctores, pacientes y citas.
* Validación de fecha y hora de las citas.
* Acceso mediante administrador y contraseña.
* Persistencia de información mediante archivos CSV.
* Creación automática de la carpeta `db` y de los archivos de datos cuando son necesarios.
* Manejo de errores durante la lectura y escritura de archivos.
* Ejecución mediante un archivo JAR ejecutable.

## Tecnologías utilizadas

* Java 27
* Maven
* IntelliJ IDEA
* Git
* GitHub
* Archivos CSV

## Requisitos

Para ejecutar el proyecto se necesita:

* Java JDK 27 o superior.
* IntelliJ IDEA para trabajar con el código fuente.
* Maven, si se desea realizar la compilación desde línea de comandos.

## Instalación y configuración

1. Descargar o clonar el proyecto.
2. Abrir el proyecto en IntelliJ IDEA.
3. Verificar que el proyecto utilice un JDK compatible.
4. Esperar a que Maven cargue la configuración del proyecto.
5. Ejecutar la clase:

```text
src/main/java/com/sistemacitas/Principal.java
```

La aplicación crea automáticamente la carpeta `db` cuando es necesario.

## Ejecución mediante JAR

El proyecto utiliza Maven Shade Plugin para generar un JAR ejecutable que incluye la configuración necesaria para iniciar la aplicación.

Para generar el JAR desde IntelliJ:

1. Abrir el panel **Maven**.
2. Seleccionar el proyecto.
3. Ejecutar `clean`.
4. Ejecutar `package`.
5. El archivo generado se encuentra en:

```text
target/SistemaCitasMedicas-1.0-SNAPSHOT.jar
```

Para ejecutarlo desde una terminal con Java instalado:

```text
java -jar target/SistemaCitasMedicas-1.0-SNAPSHOT.jar
```

## Uso del sistema

Al iniciar la aplicación se solicita el acceso del administrador.

El administrador inicial utilizado por el sistema es:

```text
Identificador: admin01
Contraseña: 12345
```

Después de iniciar sesión se muestra el menú principal.

Desde el menú se pueden realizar las operaciones disponibles para:

* Registrar doctores.
* Registrar pacientes.
* Registrar citas.
* Consultar información.
* Salir del sistema.

### Registro de doctores

Para registrar un doctor se solicita:

* Identificador.
* Nombre completo.
* Especialidad.

El identificador debe ser único.

### Registro de pacientes

Para registrar un paciente se solicita:

* Identificador.
* Nombre completo.

El identificador debe ser único.

### Registro de citas

Para registrar una cita se solicita:

* Identificador de la cita.
* Fecha y hora.
* Identificador del doctor.
* Identificador del paciente.
* Motivo de la consulta.

El doctor y el paciente deben existir previamente en el sistema.

## Persistencia de datos

La información se almacena localmente en la carpeta:

```text
db/
```

Los archivos utilizados son:

```text
doctores.csv
pacientes.csv
citas.csv
administradores.csv
```

Estos archivos son generados y utilizados localmente por la aplicación.

Por configuración del proyecto, los archivos de datos no se incluyen en el repositorio de Git.

## Estructura del proyecto

```text
SistemaCitasMedicas/
│
├── db/
│   └── .gitignore
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── sistemacitas/
│                   ├── Administrador.java
│                   ├── Cita.java
│                   ├── Doctor.java
│                   ├── GestorArchivos.java
│                   ├── Paciente.java
│                   ├── Principal.java
│                   └── SistemaCitas.java
│
├── .gitignore
├── pom.xml
└── README.md
```

## Control de versiones

El proyecto utiliza Git para el control de versiones.

Las principales ramas utilizadas son:

* `master`: versión estable del proyecto.
* `develop`: rama principal de desarrollo.
* `crear_doctor`: desarrollo relacionado con doctores.
* `crear_paciente`: desarrollo relacionado con pacientes.
* `crear_cita`: desarrollo relacionado con citas.

Las ramas de funcionalidades se mantienen como parte del historial del proyecto.

## Créditos

Proyecto desarrollado como actividad académica para la implementación de un sistema de administración de citas médicas utilizando programación orientada a objetos en Java.

## Licencia

Este proyecto fue desarrollado con fines académicos y educativos.
