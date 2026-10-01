package com.sistemacitas;
import java.io.IOException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {

            GestorArchivos.prepararCarpetaDB();

        } catch (Exception e) {

            System.out.println(
                    "ERROR: No se pudo preparar la carpeta db."
            );

            System.out.println(
                    "Detalle: " + e.getMessage()
            );

            scanner.close();
            return;
        }

        SistemaCitas sistema = new SistemaCitas();

        try {

            GestorArchivos.cargarDoctores(
                    sistema.getDoctores()
            );

        } catch (IOException e) {

            System.out.println(
                    "ERROR: No se pudieron cargar los doctores."
            );

            System.out.println(
                    "Detalle: " + e.getMessage()
            );
        }

        try {

            GestorArchivos.cargarPacientes(
                    sistema.getPacientes()
            );

        } catch (IOException e) {

            System.out.println(
                    "ERROR: No se pudieron cargar los pacientes."
            );

            System.out.println(
                    "Detalle: " + e.getMessage()
            );
        }
        try {

            GestorArchivos.cargarCitas(
                    sistema.getCitas(),
                    sistema.getDoctores(),
                    sistema.getPacientes()
            );

        } catch (IOException e) {

            System.out.println(
                    "ERROR: No se pudieron cargar las citas."
            );

            System.out.println(
                    "Detalle: " + e.getMessage()
            );
        }
// Cargar administradores
        try {

            GestorArchivos.cargarAdministradores(
                    sistema.getAdministradores()
            );

        } catch (IOException e) {

            System.out.println(
                    "ERROR: No se pudieron cargar los administradores."
            );

            System.out.println(
                    "Detalle: " + e.getMessage()
            );
        }
        // Crear administrador inicial si no existe ninguno
        if (sistema.getAdministradores().isEmpty()) {

            Administrador administrador =
                    new Administrador("admin01", "12345");

            sistema.agregarAdministrador(administrador);

            try {

                GestorArchivos.guardarAdministradores(
                        sistema.getAdministradores()
                );

            } catch (IOException e) {

                System.out.println(
                        "ERROR: No se pudo crear el administrador inicial."
                );

                System.out.println(
                        "Detalle: " + e.getMessage()
                );
            }
        }

        // Inicio de sesión
        System.out.println("================================");
        System.out.println("   SISTEMA DE CITAS MEDICAS");
        System.out.println("================================");

        System.out.println();
        System.out.println("Inicio de sesión");

        System.out.print("Identificador: ");
        String id = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        if (!sistema.validarAdministrador(id, contrasena)) {

            System.out.println();
            System.out.println("ERROR: Identificador o contraseña incorrectos.");

            scanner.close();
            return;
        }

        System.out.println();
        System.out.println("Acceso autorizado.");
        System.out.println("Bienvenido al sistema.");

        // Menú principal
        int opcion = 0;

        while (opcion != 5) {

            System.out.println();
            System.out.println("================================");
            System.out.println("        MENU PRINCIPAL");
            System.out.println("================================");
            System.out.println("1. Registrar doctor");
            System.out.println("2. Registrar paciente");
            System.out.println("3. Registrar cita");
            System.out.println("4. Consultar información");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {

                opcion = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println();
                System.out.println(
                        "ERROR: Debe introducir un número."
                );

                continue;
            }

            switch (opcion) {

                case 1:
                    registrarDoctor(scanner, sistema);
                    break;

                case 2:
                    registrarPaciente(scanner, sistema);
                    break;

                case 3:
                    registrarCita(scanner, sistema);
                    break;

                case 4:
                    consultarInformacion(scanner, sistema);
                    break;

                case 5:
                    System.out.println();
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println();
                    System.out.println("ERROR: Opción no válida.");
            }
        }

        scanner.close();
    }
    private static void registrarDoctor(
            Scanner scanner,
            SistemaCitas sistema) {

        System.out.println();
        System.out.println("================================");
        System.out.println("       REGISTRAR DOCTOR");
        System.out.println("================================");

        System.out.print("ID del doctor: ");
        String idDoctor = scanner.nextLine();

        if (idDoctor.trim().isEmpty()) {

            System.out.println(
                    "ERROR: El ID del doctor es obligatorio."
            );

            return;
        }

// Verificar si el ID ya existe
        if (sistema.buscarDoctor(idDoctor) != null) {

            System.out.println(
                    "ERROR: El ID ya está registrado."
            );

            return;
        }

        System.out.print("Nombre completo: ");
        String nombreCompleto = scanner.nextLine();

        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();

        // Verificar campos obligatorios
        if (nombreCompleto.trim().isEmpty()
                || especialidad.trim().isEmpty()) {

            System.out.println(
                    "ERROR: Todos los campos son obligatorios."
            );

            return;
        }

        // Crear doctor
        Doctor doctor = new Doctor(
                idDoctor,
                nombreCompleto,
                especialidad
        );

        // Agregar doctor al sistema
        sistema.agregarDoctor(doctor);

        try {

            GestorArchivos.guardarDoctores(
                    sistema.getDoctores()
            );

        } catch (IOException e) {

            System.out.println(
                    "ERROR: No se pudo guardar el doctor."
            );

            System.out.println(
                    "Detalle: " + e.getMessage()
            );

            return;
        }
    }
    private static void registrarPaciente(
            Scanner scanner,
            SistemaCitas sistema) {

        System.out.println();
        System.out.println("================================");
        System.out.println("      REGISTRAR PACIENTE");
        System.out.println("================================");

        System.out.print("ID del paciente: ");
        String idPaciente = scanner.nextLine();

        // Verificar que el ID no esté vacío
        if (idPaciente.trim().isEmpty()) {

            System.out.println(
                    "ERROR: El ID del paciente es obligatorio."
            );

            return;
        }

        // Verificar si el ID ya existe
        if (sistema.buscarPaciente(idPaciente) != null) {

            System.out.println(
                    "ERROR: El ID ya está registrado."
            );

            return;
        }

        System.out.print("Nombre completo: ");
        String nombreCompleto = scanner.nextLine();

        // Verificar que el nombre no esté vacío
        if (nombreCompleto.trim().isEmpty()) {

            System.out.println(
                    "ERROR: El nombre es obligatorio."
            );

            return;
        }

        // Crear paciente
        Paciente paciente = new Paciente(
                idPaciente,
                nombreCompleto
        );

        // Agregar paciente al sistema
        sistema.agregarPaciente(paciente);

        try {

            GestorArchivos.guardarPacientes(
                    sistema.getPacientes()
            );

        } catch (IOException e) {

            System.out.println(
                    "ERROR: No se pudo guardar el paciente."
            );

            System.out.println(
                    "Detalle: " + e.getMessage()
            );

            return;
        }

        System.out.println();
        System.out.println(
                "Paciente registrado correctamente."
        );
    }

private static void registrarCita(
        Scanner scanner,
        SistemaCitas sistema) {

    System.out.println();
    System.out.println("================================");
    System.out.println("        REGISTRAR CITA");
    System.out.println("================================");

    // ID de la cita
    System.out.print("ID de la cita: ");
    String idCita = scanner.nextLine();

    // Verificar que el ID no esté vacío
    if (idCita.trim().isEmpty()) {

        System.out.println(
                "ERROR: El ID de la cita es obligatorio."
        );

        return;
    }

    // Verificar ID duplicado
    if (sistema.buscarCita(idCita) != null) {

        System.out.println(
                "ERROR: El ID de la cita ya existe."
        );

        return;
    }

    // ID del doctor
    System.out.print("ID del doctor: ");
    String idDoctor = scanner.nextLine();

    if (idDoctor.trim().isEmpty()) {

        System.out.println(
                "ERROR: El ID del doctor es obligatorio."
        );

        return;
    }

    Doctor doctor = sistema.buscarDoctor(idDoctor);

    // Verificar que exista el doctor
    if (doctor == null) {

        System.out.println(
                "ERROR: Doctor no encontrado."
        );

        return;
    }

    // ID del paciente
    System.out.print("ID del paciente: ");
    String idPaciente = scanner.nextLine();

    if (idPaciente.trim().isEmpty()) {

        System.out.println(
                "ERROR: El ID del paciente es obligatorio."
        );

        return;
    }

    Paciente paciente = sistema.buscarPaciente(idPaciente);

    // Verificar que exista el paciente
    if (paciente == null) {

        System.out.println(
                "ERROR: Paciente no encontrado."
        );

        return;
    }

    // Fecha y hora
    System.out.print(
            "Fecha y hora (AAAA-MM-DD HH:MM): "
    );

    String fechaHoraTexto = scanner.nextLine();

    java.time.LocalDateTime fechaHora;

    try {

        fechaHora = java.time.LocalDateTime.parse(
                fechaHoraTexto.replace(" ", "T")
        );

    } catch (Exception e) {

        System.out.println(
                "ERROR: Formato de fecha y hora incorrecto."
        );

        return;
    }

    // Verificar que la fecha no sea pasada
    if (fechaHora.isBefore(
            java.time.LocalDateTime.now())) {

        System.out.println(
                "ERROR: No se pueden agendar citas en fechas pasadas."
        );

        return;
    }

    // Motivo
    System.out.print("Motivo de la cita: ");
    String motivo = scanner.nextLine();

    if (motivo.trim().isEmpty()) {

        System.out.println(
                "ERROR: El motivo de la cita es obligatorio."
        );

        return;
    }

    // Crear cita
    Cita cita = new Cita(
            idCita,
            fechaHora,
            motivo,
            doctor,
            paciente
    );

    // Agregar cita al sistema
    sistema.agregarCita(cita);

    try {

        GestorArchivos.guardarCitas(
                sistema.getCitas()
        );

    } catch (IOException e) {

        System.out.println(
                "ERROR: No se pudo guardar la cita."
        );

        System.out.println(
                "Detalle: " + e.getMessage()
        );

        return;
    }

    System.out.println();
    System.out.println(
            "Cita registrada exitosamente."
    );
}

    private static void consultarInformacion(
            Scanner scanner,
            SistemaCitas sistema) {

        int opcion = 0;

        while (opcion != 4) {

            System.out.println();
            System.out.println("================================");
            System.out.println("     CONSULTAR INFORMACION");
            System.out.println("================================");
            System.out.println("1. Consultar doctores");
            System.out.println("2. Consultar pacientes");
            System.out.println("3. Consultar citas");
            System.out.println("4. Regresar al menú principal");
            System.out.print("Seleccione una opción: ");

            try {

                opcion = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("ERROR: Debe introducir un número.");
                continue;
            }

            switch (opcion) {

                case 1:

                    System.out.println();
                    System.out.println("========== DOCTORES ==========");

                    if (sistema.getDoctores().isEmpty()) {

                        System.out.println("No hay doctores registrados.");

                    } else {

                        for (Doctor doctor : sistema.getDoctores()) {
                            System.out.println(doctor);
                        }
                    }

                    break;

                case 2:

                    System.out.println();
                    System.out.println("========= PACIENTES =========");

                    if (sistema.getPacientes().isEmpty()) {

                        System.out.println("No hay pacientes registrados.");

                    } else {

                        for (Paciente paciente : sistema.getPacientes()) {
                            System.out.println(paciente);
                        }
                    }

                    break;

                case 3:

                    System.out.println();
                    System.out.println("=========== CITAS ===========");

                    if (sistema.getCitas().isEmpty()) {

                        System.out.println("No hay citas registradas.");

                    } else {

                        for (Cita cita : sistema.getCitas()) {
                            System.out.println(cita);
                        }
                    }

                    break;

                case 4:

                    System.out.println("Regresando al menú principal...");
                    break;

                default:

                    System.out.println("ERROR: Opción no válida.");
            }
        }
    }
}