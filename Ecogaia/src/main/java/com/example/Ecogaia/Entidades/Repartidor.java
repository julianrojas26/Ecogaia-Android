package com.example.Ecogaia.Entidades;

public class Repartidor {
    private int  id_Rep;
    private String Rep_Nombre;
    private String Telefono;
    private String Direccion;
    private String Rep_Disponibilidad;

    public Repartidor(int id_Rep, String rep_Nombre, String telefono, String direccion, String rep_Disponibilidad) {
        this.id_Rep = id_Rep;
        Rep_Nombre = rep_Nombre;
        Telefono = telefono;
        Direccion = direccion;
        Rep_Disponibilidad = rep_Disponibilidad;
    }

    public Repartidor() {
    }

    public int getId_Rep() {
        return id_Rep;
    }

    public void setId_Rep(int id_Rep) {
        this.id_Rep = id_Rep;
    }

    public String getRep_Nombre() {
        return Rep_Nombre;
    }

    public void setRep_Nombre(String rep_Nombre) {
        Rep_Nombre = rep_Nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getRep_Disponibilidad() {
        return Rep_Disponibilidad;
    }

    public void setRep_Disponibilidad(String rep_Disponibilidad) {
        Rep_Disponibilidad = rep_Disponibilidad;
    }

    @Override
    public String toString() {
        return "Repartidor{" +
                "id_Rep=" + id_Rep +
                ", Rep_Nombre='" + Rep_Nombre + '\'' +
                ", Telefono=" + Telefono +
                ", Direccion='" + Direccion + '\'' +
                ", Rep_Disponibilidad='" + Rep_Disponibilidad + '\'' +
                '}';
    }
}