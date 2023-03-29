package com.example.Ecogaia.Entidades;

import java.sql.Date;

public class Venta {
    private int Venta_Codigo;
    private int Codigo_Prod;
    private int id_Comprador;
    private int Dis_Codigo;
    private int Venta_Cantidad;
    private int Venta_Valor;
    private Date Venta_Fecha;
    private String Venta_Estado;

    public Venta(int venta_Codigo, int codigo_Prod, int id_Comprador, int dis_Codigo, int venta_Cantidad, int venta_Valor, Date venta_Fecha, String venta_Estado) {
        Venta_Codigo = venta_Codigo;
        Codigo_Prod = codigo_Prod;
        this.id_Comprador = id_Comprador;
        Dis_Codigo = dis_Codigo;
        Venta_Cantidad = venta_Cantidad;
        Venta_Valor = venta_Valor;
        Venta_Fecha = venta_Fecha;
        Venta_Estado = venta_Estado;
    }

    public Venta() {
    }

    public int getVenta_Codigo() {
        return Venta_Codigo;
    }

    public void setVenta_Codigo(int venta_Codigo) {
        Venta_Codigo = venta_Codigo;
    }

    public int getCodigo_Prod() {
        return Codigo_Prod;
    }

    public void setCodigo_Prod(int codigo_Prod) {
        Codigo_Prod = codigo_Prod;
    }

    public int getId_Comprador() {
        return id_Comprador;
    }

    public void setId_Comprador(int id_Comprador) {
        this.id_Comprador = id_Comprador;
    }

    public int getDis_Codigo() {
        return Dis_Codigo;
    }

    public void setDis_Codigo(int dis_Codigo) {
        Dis_Codigo = dis_Codigo;
    }

    public int getVenta_Cantidad() {
        return Venta_Cantidad;
    }

    public void setVenta_Cantidad(int venta_Cantidad) {
        Venta_Cantidad = venta_Cantidad;
    }

    public int getVenta_Valor() {
        return Venta_Valor;
    }

    public void setVenta_Valor(int venta_Valor) {
        Venta_Valor = venta_Valor;
    }

    public Date getVenta_Fecha() {
        return Venta_Fecha;
    }

    public void setVenta_Fecha(Date venta_Fecha) {
        Venta_Fecha = venta_Fecha;
    }

    public String getVenta_Estado() {
        return Venta_Estado;
    }

    public void setVenta_Estado(String venta_Estado) {
        Venta_Estado = venta_Estado;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "Venta_Codigo=" + Venta_Codigo +
                ", Codigo_Prod=" + Codigo_Prod +
                ", id_Comprador=" + id_Comprador +
                ", Dis_Codigo=" + Dis_Codigo +
                ", Venta_Cantidad=" + Venta_Cantidad +
                ", Venta_Valor=" + Venta_Valor +
                ", Venta_Fecha=" + Venta_Fecha +
                ", Venta_Estado='" + Venta_Estado + '\'' +
                '}';
    }
}
