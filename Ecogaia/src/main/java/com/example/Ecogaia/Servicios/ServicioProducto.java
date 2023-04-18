package com.example.Ecogaia.Servicios;

import com.example.Ecogaia.Entidades.Producto;
import com.example.Ecogaia.conexion;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ServicioProducto {
    ArrayList<Producto> datos = new ArrayList<Producto>();
    conexion con = new conexion();
    Connection conect = null;
    Statement st = null;
    ResultSet res = null;
    PreparedStatement ps = null;

    public ArrayList<Producto> BuscarProducto() {
        try {
            conect = con.conecta();
            String sql = "select * from producto";
            st = conect.createStatement();
            datos.clear();
            res = st.executeQuery(sql);
            while (res.next()) {
                Producto p = new Producto(res.getInt(1), res.getInt(2), res.getString(3), res.getInt(4),res.getString(5));
                datos.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar productos " + ex);
        }
        return datos;
    }

    public Boolean IngresarProducto(Producto p) {
        Boolean res = false;
        try {
            conect = con.conecta();
            String sql = "insert into producto (Prod_Precio, Prod_Nombre, Prod_Cantidad, Prod_Categoria) values (?,?,?,?)";
            ps = conect.prepareStatement(sql);
            ps.setInt(1, p.getProd_Precio());
            ps.setString(2,p.getProd_Nombre());
            ps.setInt(3,p.getProd_Cantidad());
            ps.setString(4, p.getProd_Categoria());
            res = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al ingresar el producto" + ex);
        }
        return res;
    }

}

