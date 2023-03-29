package com.example.Ecogaia.Entidades;

import java.sql.Date;
import java.sql.Time;

public class Distribuir {
    private int Codigo_Dis;
    private int id_Repartidor;
    private Time Dis_Tiempo;
    private String Dis_Estado;
    private Date Dis_Fecha;

    public Distribuir(int codigo_Dis, int id_Repartidor, Time dis_Tiempo, String dis_Estado, Date dis_Fecha) {
        Codigo_Dis = codigo_Dis;
        this.id_Repartidor = id_Repartidor;
        Dis_Tiempo = dis_Tiempo;
        Dis_Estado = dis_Estado;
        Dis_Fecha = dis_Fecha;
    }

    public Distribuir() {
    }

    public int getCodigo_Dis() {
        return Codigo_Dis;
    }

    public void setCodigo_Dis(int codigo_Dis) {
        Codigo_Dis = codigo_Dis;
    }

    public int getId_Repartidor() {
        return id_Repartidor;
    }

    public void setId_Repartidor(int id_Repartidor) {
        this.id_Repartidor = id_Repartidor;
    }

    public Time getDis_Tiempo() {
        return Dis_Tiempo;
    }

    public void setDis_Tiempo(Time dis_Tiempo) {
        Dis_Tiempo = dis_Tiempo;
    }

    public String getDis_Estado() {
        return Dis_Estado;
    }

    public void setDis_Estado(String dis_Estado) {
        Dis_Estado = dis_Estado;
    }

    public Date getDis_Fecha() {
        return Dis_Fecha;
    }

    public void setDis_Fecha(Date dis_Fecha) {
        Dis_Fecha = dis_Fecha;
    }

    @Override
    public String toString() {
        return "Distribuir{" +
                "Codigo_Dis=" + Codigo_Dis +
                ", id_Repartidor=" + id_Repartidor +
                ", Dis_Tiempo=" + Dis_Tiempo +
                ", Dis_Estado='" + Dis_Estado + '\'' +
                ", Dis_Fecha=" + Dis_Fecha +
                '}';
    }
}
