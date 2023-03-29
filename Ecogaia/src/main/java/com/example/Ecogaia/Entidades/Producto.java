package com.example.Ecogaia.Entidades;

public class Producto {
    private int Prod_Codigo;
    private int Prod_Precio;
    private String Prod_Nombre;
    private int Prod_Cantidad;

    public Producto(int prod_Codigo, int prod_Precio, String prod_Nombre, int prod_Cantidad) {
        Prod_Codigo = prod_Codigo;
        Prod_Precio = prod_Precio;
        Prod_Nombre = prod_Nombre;
        Prod_Cantidad = prod_Cantidad;
    }

    public Producto() {
    }

    public int getProd_Codigo() {
        return Prod_Codigo;
    }

    public void setProd_Codigo(int prod_Codigo) {
        Prod_Codigo = prod_Codigo;
    }

    public int getProd_Precio() {
        return Prod_Precio;
    }

    public void setProd_Precio(int prod_Precio) {
        Prod_Precio = prod_Precio;
    }

    public String getProd_Nombre() {
        return Prod_Nombre;
    }

    public void setProd_Nombre(String prod_Nombre) {
        Prod_Nombre = prod_Nombre;
    }

    public int getProd_Cantidad() {
        return Prod_Cantidad;
    }

    public void setProd_Cantidad(int prod_Cantidad) {
        Prod_Cantidad = prod_Cantidad;
    }

    @Override
    public String toString() {
        return  "Prod_Codigo = " + Prod_Codigo +
                ", Prod_Precio = " + Prod_Precio +
                ", Prod_Nombre = " + Prod_Nombre +
                ", Prod_Cantidad = " + Prod_Cantidad + '\n';
    }
}
