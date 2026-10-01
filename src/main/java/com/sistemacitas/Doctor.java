package com.sistemacitas;

public class Doctor {

    private String idDoctor;
    private String nombreCompleto;
    private String especialidad;

    public Doctor(String idDoctor, String nombreCompleto, String especialidad) {
        this.idDoctor = idDoctor;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public String toString() {
        return idDoctor + "," + nombreCompleto + "," + especialidad;
    }
}
