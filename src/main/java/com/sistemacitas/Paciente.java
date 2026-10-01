package com.sistemacitas;

public class Paciente {

    private String idPaciente;
    private String nombreCompleto;

    public Paciente(String idPaciente, String nombreCompleto) {
        this.idPaciente = idPaciente;
        this.nombreCompleto = nombreCompleto;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    @Override
    public String toString() {
        return idPaciente + "," + nombreCompleto;
    }
}
