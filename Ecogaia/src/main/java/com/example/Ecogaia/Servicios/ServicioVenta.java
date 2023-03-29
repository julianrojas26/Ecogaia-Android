package com.example.Ecogaia.Servicios;

import com.example.Ecogaia.Entidades.Producto;
import com.example.Ecogaia.Entidades.Venta;
import com.example.Ecogaia.conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServicioVenta {
    ArrayList<Venta> datos = new ArrayList<Venta>();
    conexion con = new conexion();
    Connection conect = null;
    Statement st = null;
    ResultSet res = null;

    public ArrayList<Venta> BuscarVenta() {
        try {
            conect = con.conecta();
            String sql = "select * from venta";
            st = conect.createStatement();
            res = st.executeQuery(sql);
            while (res.next()) {
                Venta v = new Venta(res.getInt(1),res.getInt(2),res.getInt(3),res.getInt(4),res.getInt(5),res.getInt(6),res.getDate(7),res.getString(8));
                datos.add(v);
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar ventas " + ex);
        }
        return datos;
    }
}
