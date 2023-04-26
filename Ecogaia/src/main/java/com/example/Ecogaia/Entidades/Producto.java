package com.example.Ecogaia.Entidades;

import jakarta.persistence.*;


@Entity
@Table (name = "Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Prod_Codigo;
    @Column(name = "Prod_Precio", length = 100, nullable = false)
    private String Prod_Precio;
    @Column(name = "Prod_Nombre", length = 100, nullable = false)
    private String Prod_Nombre;
    @Column(name = "Prod_Cantidad", length = 100, nullable = false)
    private int Prod_Cantidad;
    @Column(name = "Prod_Categoria", length = 100, nullable = false)
    private String Prod_Categoria;

    public Producto(String prod_Precio, String prod_Nombre, int prod_Cantidad,String prod_Categoria) {
        Prod_Codigo = null;
        Prod_Precio = prod_Precio;
        Prod_Nombre = prod_Nombre;
        Prod_Cantidad = prod_Cantidad;
        Prod_Categoria = prod_Categoria;
    }

    public Producto() {
    }

    public int getProd_Codigo() {
        return Prod_Codigo;
    }

    public void setProd_Codigo(int prod_Codigo) {
        Prod_Codigo = prod_Codigo;
    }

    public String getProd_Precio() {
        return Prod_Precio;
    }

    public void setProd_Precio(String prod_Precio) {
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

    public String getProd_Categoria() {
        return Prod_Categoria;
    }

    public void setProd_Categoria(String prod_Categoria) {
        Prod_Categoria = prod_Categoria;
    }

    @Override
    public String toString() {
        return  "Prod_Codigo = " + Prod_Codigo +
                ", Prod_Precio = " + Prod_Precio +
                ", Prod_Nombre = " + Prod_Nombre +
                ", Prod_Cantidad = " + Prod_Cantidad + '\n';
    }
}
