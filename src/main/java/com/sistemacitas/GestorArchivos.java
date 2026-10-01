package com.sistemacitas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GestorArchivos {

    private static final String CARPETA_DB = "db";

    private static final String ARCHIVO_DOCTORES =
            "db/doctores.csv";
    private static final String ARCHIVO_PACIENTES =
            "db/pacientes.csv";
    private static final String ARCHIVO_CITAS =
            "db/citas.csv";
    private static final String ARCHIVO_ADMINISTRADORES =
            "db/administradores.csv";
    public static void prepararCarpetaDB() throws IOException {

        Path carpeta = Paths.get(CARPETA_DB);

        if (!Files.exists(carpeta)) {
            Files.createDirectories(carpeta);
        }
    }

    public static void guardarDoctores(
            List<Doctor> doctores) throws IOException {

        prepararCarpetaDB();

        Path archivo = Paths.get(ARCHIVO_DOCTORES);

        List<String> lineas = new java.util.ArrayList<>();

        for (Doctor doctor : doctores) {
            lineas.add(doctor.toString());
        }

        Files.write(archivo, lineas);
    }
    public static void guardarPacientes(
            List<Paciente> pacientes) throws IOException {

        prepararCarpetaDB();

        Path archivo = Paths.get(ARCHIVO_PACIENTES);

        List<String> lineas = new java.util.ArrayList<>();

        for (Paciente paciente : pacientes) {
            lineas.add(paciente.toString());
        }

        Files.write(archivo, lineas);
    }
    public static void guardarCitas(
            List<Cita> citas) throws IOException {

        prepararCarpetaDB();

        Path archivo = Paths.get(ARCHIVO_CITAS);

        List<String> lineas = new java.util.ArrayList<>();

        for (Cita cita : citas) {
            lineas.add(cita.toString());
        }

        Files.write(archivo, lineas);
    }
    public static void guardarAdministradores(
            List<Administrador> administradores) throws IOException {

        prepararCarpetaDB();

        Path archivo = Paths.get(ARCHIVO_ADMINISTRADORES);

        List<String> lineas = new java.util.ArrayList<>();

        for (Administrador administrador : administradores) {
            lineas.add(administrador.toString());
        }

        Files.write(archivo, lineas);
    }
    public static void cargarAdministradores(
            List<Administrador> administradores) throws IOException {

        prepararCarpetaDB();

        Path archivo = Paths.get(ARCHIVO_ADMINISTRADORES);

        if (!Files.exists(archivo)) {
            Files.createFile(archivo);
            return;
        }

        List<String> lineas = Files.readAllLines(archivo);

        for (String linea : lineas) {

            if (linea.trim().isEmpty()) {
                continue;
            }

            String[] datos = linea.split(",", -1);

            if (datos.length != 2) {

                System.out.println(
                        "ADVERTENCIA: Registro de administrador inválido: "
                                + linea
                );

                continue;
            }

            Administrador administrador =
                    new Administrador(
                            datos[0],
                            datos[1]
                    );

            if (buscarAdministradorEnLista(
                    administradores,
                    datos[0]) == null) {

                administradores.add(administrador);
            }
        }
    }
    public static void cargarCitas(
            List<Cita> citas,
            List<Doctor> doctores,
            List<Paciente> pacientes) throws IOException {

        prepararCarpetaDB();

        Path archivo = Paths.get(ARCHIVO_CITAS);

        if (!Files.exists(archivo)) {
            Files.createFile(archivo);
            return;
        }

        List<String> lineas = Files.readAllLines(archivo);

        for (String linea : lineas) {

            if (linea.trim().isEmpty()) {
                continue;
            }

            String[] datos = linea.split(",", -1);

            if (datos.length != 5) {

                System.out.println(
                        "ADVERTENCIA: Registro de cita inválido: "
                                + linea
                );

                continue;
            }

            try {

                String idCita = datos[0];

                java.time.LocalDateTime fechaHora =
                        java.time.LocalDateTime.parse(datos[1]);

                String motivo = datos[2];

                String idDoctor = datos[3];

                String idPaciente = datos[4];

                Doctor doctor =
                        buscarDoctorEnLista(doctores, idDoctor);

                Paciente paciente =
                        buscarPacienteEnLista(pacientes, idPaciente);

                if (doctor == null) {

                    System.out.println(
                            "ADVERTENCIA: No se encontró el doctor "
                                    + idDoctor
                                    + " para la cita "
                                    + idCita
                    );

                    continue;
                }

                if (paciente == null) {

                    System.out.println(
                            "ADVERTENCIA: No se encontró el paciente "
                                    + idPaciente
                                    + " para la cita "
                                    + idCita
                    );

                    continue;
                }

                if (buscarCitaEnLista(citas, idCita) != null) {
                    continue;
                }

                Cita cita = new Cita(
                        idCita,
                        fechaHora,
                        motivo,
                        doctor,
                        paciente
                );

                citas.add(cita);

            } catch (Exception e) {

                System.out.println(
                        "ADVERTENCIA: No se pudo cargar la cita: "
                                + linea
                );
            }
        }
    }
    public static void cargarPacientes(
            List<Paciente> pacientes) throws IOException {

        prepararCarpetaDB();

        Path archivo = Paths.get(ARCHIVO_PACIENTES);

        if (!Files.exists(archivo)) {
            Files.createFile(archivo);
            return;
        }

        List<String> lineas = Files.readAllLines(archivo);

        for (String linea : lineas) {

            if (linea.trim().isEmpty()) {
                continue;
            }

            String[] datos = linea.split(",", -1);

            if (datos.length != 2) {
                System.out.println(
                        "ADVERTENCIA: Registro de paciente inválido: "
                                + linea
                );
                continue;
            }

            Paciente paciente = new Paciente(
                    datos[0],
                    datos[1]
            );

            if (buscarPacienteEnLista(
                    pacientes,
                    datos[0]) == null) {

                pacientes.add(paciente);
            }
        }
    }
    private static Paciente buscarPacienteEnLista(
            List<Paciente> pacientes,
            String idPaciente) {

        for (Paciente paciente : pacientes) {

            if (paciente.getIdPaciente().equals(idPaciente)) {
                return paciente;
            }
        }

        return null;
    }
    public static void cargarDoctores(
            List<Doctor> doctores) throws IOException {

        prepararCarpetaDB();

        Path archivo = Paths.get(ARCHIVO_DOCTORES);

        if (!Files.exists(archivo)) {
            Files.createFile(archivo);
            return;
        }

        List<String> lineas = Files.readAllLines(archivo);

        for (String linea : lineas) {

            if (linea.trim().isEmpty()) {
                continue;
            }

            String[] datos = linea.split(",", -1);

            if (datos.length != 3) {
                System.out.println(
                        "ADVERTENCIA: Registro de doctor inválido: "
                                + linea
                );
                continue;
            }

            Doctor doctor = new Doctor(
                    datos[0],
                    datos[1],
                    datos[2]
            );

            if (buscarDoctorEnLista(doctores, datos[0]) == null) {
                doctores.add(doctor);
            }
        }
    }

    private static Doctor buscarDoctorEnLista(
            List<Doctor> doctores,
            String idDoctor) {

        for (Doctor doctor : doctores) {

            if (doctor.getIdDoctor().equals(idDoctor)) {
                return doctor;
            }
        }

        return null;
    }
    private static Cita buscarCitaEnLista(
            List<Cita> citas,
            String idCita) {

        for (Cita cita : citas) {

            if (cita.getIdCita().equals(idCita)) {
                return cita;
            }
        }

        return null;
    }
    private static Administrador buscarAdministradorEnLista(
            List<Administrador> administradores,
            String idAdministrador) {

        for (Administrador administrador : administradores) {

            if (administrador.getIdAdministrador()
                    .equals(idAdministrador)) {

                return administrador;
            }
        }

        return null;
    }
}