package com.sistemacitas;

import java.util.ArrayList;
import java.util.List;

public class SistemaCitas {

    private List<Doctor> doctores;
    private List<Paciente> pacientes;
    private List<Cita> citas;
    private List<Administrador> administradores;

    public SistemaCitas() {
        doctores = new ArrayList<>();
        pacientes = new ArrayList<>();
        citas = new ArrayList<>();
        administradores = new ArrayList<>();
    }

    // Agregar doctor
    public void agregarDoctor(Doctor doctor) {
        doctores.add(doctor);
    }

    // Agregar paciente
    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    // Agregar cita
    public void agregarCita(Cita cita) {
        citas.add(cita);
    }

    // Agregar administrador
    public void agregarAdministrador(Administrador administrador) {
        administradores.add(administrador);
    }

    // Buscar doctor por ID
    public Doctor buscarDoctor(String idDoctor) {

        for (Doctor doctor : doctores) {

            if (doctor.getIdDoctor().equals(idDoctor)) {
                return doctor;
            }
        }

        return null;
    }

    // Buscar paciente por ID
    public Paciente buscarPaciente(String idPaciente) {

        for (Paciente paciente : pacientes) {

            if (paciente.getIdPaciente().equals(idPaciente)) {
                return paciente;
            }
        }

        return null;
    }

    // Buscar cita por ID
    public Cita buscarCita(String idCita) {

        for (Cita cita : citas) {

            if (cita.getIdCita().equals(idCita)) {
                return cita;
            }
        }

        return null;
    }

    // Validar administrador
    public boolean validarAdministrador(String id, String contrasena) {

        for (Administrador administrador : administradores) {

            if (administrador.validarAcceso(id, contrasena)) {
                return true;
            }
        }

        return false;
    }

    // Obtener listas
    public List<Doctor> getDoctores() {
        return doctores;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public List<Administrador> getAdministradores() {
        return administradores;
    }
}