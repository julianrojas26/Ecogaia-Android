package com.example.Ecogaia.Entidades;

import java.sql.Date;

public class Cotizacion {
    private int Codigo;
    private int id_Comprador;
    private int Codigo_Prod;
    private int Cant_Prod;
    private Date Cot_Fecha;

    public Cotizacion(int codigo, int id_Comprador, int codigo_Prod, int cant_Prod, Date cot_Fecha) {
        Codigo = codigo;
        this.id_Comprador = id_Comprador;
        Codigo_Prod = codigo_Prod;
        Cant_Prod = cant_Prod;
        Cot_Fecha = cot_Fecha;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public int getId_Comprador() {
        return id_Comprador;
    }

    public void setId_Comprador(int id_Comprador) {
        this.id_Comprador = id_Comprador;
    }

    public int getCodigo_Prod() {
        return Codigo_Prod;
    }

    public void setCodigo_Prod(int codigo_Prod) {
        Codigo_Prod = codigo_Prod;
    }

    public int getCant_Prod() {
        return Cant_Prod;
    }

    public void setCant_Prod(int cant_Prod) {
        Cant_Prod = cant_Prod;
    }

    public Date getCot_Fecha() {
        return Cot_Fecha;
    }

    public void setCot_Fecha(Date cot_Fecha) {
        Cot_Fecha = cot_Fecha;
    }

    @Override
    public String toString() {
        return "Cotizacion{" +
                "Codigo=" + Codigo +
                ", id_Comprador=" + id_Comprador +
                ", Codigo_Prod=" + Codigo_Prod +
                ", Cant_Prod=" + Cant_Prod +
                ", Cot_Fecha=" + Cot_Fecha +
                '}';
    }
}
