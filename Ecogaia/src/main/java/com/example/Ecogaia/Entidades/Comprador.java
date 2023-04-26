package com.example.Ecogaia.Entidades;

public class Comprador {
    private int id_comprador;
    private String Comp_Nombre;
    private String Comp_Telefono;
    private String Comp_Direccion;
    private String Comp_Correo;
    private String Comp_Contrasenia;

    public Comprador(int id_comprador, String comp_Nombre, String comp_Telefono, String comp_Direccion, String comp_Correo, String comp_Contrasenia) {
        this.id_comprador = id_comprador;
        Comp_Nombre = comp_Nombre;
        Comp_Telefono = comp_Telefono;
        Comp_Direccion = comp_Direccion;
        Comp_Correo = comp_Correo;
        Comp_Contrasenia = comp_Contrasenia;
    }

    public Comprador() {
    }

    public int getId_comprador() {
        return id_comprador;
    }

    public void setId_comprador(int id_comprador) {
        this.id_comprador = id_comprador;
    }

    public String getComp_Nombre() {
        return Comp_Nombre;
    }

    public void setComp_Nombre(String comp_Nombre) {
        Comp_Nombre = comp_Nombre;
    }

    public String getComp_Telefono() {
        return Comp_Telefono;
    }

    public void setComp_Telefono(String comp_Telefono) {
        Comp_Telefono = comp_Telefono;
    }

    public String getComp_Direccion() {
        return Comp_Direccion;
    }

    public void setComp_Direccion(String comp_Direccion) {
        Comp_Direccion = comp_Direccion;
    }

    public String getComp_Correo() {
        return Comp_Correo;
    }

    public void setComp_Correo(String comp_Correo) {
        Comp_Correo = comp_Correo;
    }

    public String getComp_Contrasenia() {
        return Comp_Contrasenia;
    }

    public void setComp_Contrasenia(String comp_Contrasenia) {
        Comp_Contrasenia = comp_Contrasenia;
    }

    @Override
    public String toString() {
        return "Comprador{" +
                "id_comprador=" + id_comprador +
                ", Comp_Nombre='" + Comp_Nombre + '\'' +
                ", Comp_Telefono='" + Comp_Telefono + '\'' +
                ", Comp_Direccion='" + Comp_Direccion + '\'' +
                ", Comp_Correo='" + Comp_Correo + '\'' +
                ", Comp_Contraseña='" + Comp_Contrasenia + '\'' +
                '}';
    }
}
