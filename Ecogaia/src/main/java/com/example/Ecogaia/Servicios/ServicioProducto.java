package com.example.Ecogaia.Servicios;

import com.example.Ecogaia.Entidades.Producto;
import com.example.Ecogaia.conexion;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ServicioProducto {
<<<<<<< HEAD
    ArrayList<Producto> datos = new ArrayList<Producto>();
    conexion con = new conexion();
    Connection conect = null;
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet res = null;

    public ArrayList<Producto> BuscarProducto() {
        try {
            conect = con.conecta();
            String sql = "select * from producto";
            st = conect.createStatement();
            datos.clear();
            res = st.executeQuery(sql);
            while (res.next()) {
                Producto p = new Producto(res.getInt(1), res.getInt(2), res.getString(3), res.getInt(4));
                datos.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar productos " + ex);
        }
        return datos;
    }
=======
>>>>>>> 22fdc24ebea927fc2f64d80720171192b6ec487a

}

