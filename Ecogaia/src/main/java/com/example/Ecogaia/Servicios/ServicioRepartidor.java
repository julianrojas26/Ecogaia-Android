package com.example.Ecogaia.Servicios;

import com.example.Ecogaia.Entidades.Producto;
import com.example.Ecogaia.Entidades.Repartidor;
import com.example.Ecogaia.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServicioRepartidor {
    ArrayList<Repartidor> datos = new ArrayList<Repartidor>();
    conexion con = new conexion();
    Connection conect = null;
    Statement st = null;
    ResultSet res = null;

    public ArrayList<Repartidor> BuscarRepartidor() {
        try{
            conect = con.conecta();
            String sql = "select * from repartidor";
            st = conect.createStatement();
            res = st.executeQuery(sql);
            while(res.next()){
                Repartidor r = new Repartidor(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));
                datos.add(r);
            }
        }catch (SQLException ex){
            System.out.println("Error al buscar repartidores " + ex);
        }
        return datos;
    }
}
