package com.example.Ecogaia.Servicios;

import com.example.Ecogaia.Entidades.Comprador;
import com.example.Ecogaia.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServicioComprador {
    ArrayList<Comprador> datos = new ArrayList<Comprador>();
    conexion con = new conexion();
    Connection conect = null;
    Statement st = null;
    ResultSet res = null;

    public ArrayList<Comprador> BuscarComprador() {
        try {
            conect = con.conecta();
            String sql = "select * from comprador";
            st = conect.createStatement();
            res = st.executeQuery(sql);
            datos.clear();
            while (res.next()) {
                Comprador comp = new Comprador(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6));
                datos.add(comp);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar comprador " + ex);
        }
        return datos;
    }
}
