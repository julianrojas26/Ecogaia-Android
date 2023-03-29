package com.example.Ecogaia.Servicios;

import com.example.Ecogaia.Entidades.Cotizacion;
import com.example.Ecogaia.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServicioCotizacion {
    ArrayList<Cotizacion> datos = new ArrayList<Cotizacion>();
    conexion con = new conexion();
    Connection conect = null;
    Statement st = null;
    ResultSet res = null;

    public ArrayList<Cotizacion> BuscarCotizacion() {
        try {
            conect = con.conecta();
            String sql = "select * from cotizacion";
            st = conect.createStatement();
            res = st.executeQuery(sql);
            while(res.next()){
                Cotizacion c = new Cotizacion(res.getInt(1),res.getInt(2),res.getInt(3),res.getInt(4),res.getDate(5));
                datos.add(c);
            }
        }catch (SQLException ex){
            System.out.println("Error al buscar cotizacion " + ex);
        }
        return datos;
    }
}
