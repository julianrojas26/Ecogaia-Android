package com.example.Ecogaia.Servicios;

import com.example.Ecogaia.Entidades.Cotizacion;
import com.example.Ecogaia.Entidades.Distribuir;
import com.example.Ecogaia.conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServicioDistribuir {
    ArrayList<Distribuir> datos = new ArrayList<Distribuir>();
    conexion con = new conexion();
    Connection conect = null;
    Statement st = null;
    ResultSet res = null;

    public ArrayList<Distribuir> BuscarDistribuir() {
        try {
            conect = con.conecta();
            String sql = "select * from distribuir";
            st = conect.createStatement();
            res = st.executeQuery(sql);
            while(res.next()){
                Distribuir d = new Distribuir(res.getInt(1),res.getInt(2),res.getTime(3),res.getString(4),res.getDate(5));
                datos.add(d);
            }
        }catch (SQLException ex){
            System.out.println("Error al buscar distribucion " + ex);
        }
        return datos;
    }
}
