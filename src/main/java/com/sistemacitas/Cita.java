package com.sistemacitas;

import java.time.LocalDateTime;

public class Cita {

    private String idCita;
    private LocalDateTime fechaHora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(String idCita, LocalDateTime fechaHora, String motivo,
                Doctor doctor, Paciente paciente) {

        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public String getIdCita() {
        return idCita;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    @Override
    public String toString() {
        return idCita + ","
                + fechaHora + ","
                + motivo + ","
                + doctor.getIdDoctor() + ","
                + paciente.getIdPaciente();
    }
}