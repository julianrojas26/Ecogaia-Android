package com.example.Ecogaia.Servicios;

import com.example.Ecogaia.Entidades.Comprador;
import com.example.Ecogaia.Entidades.Producto;
import com.example.Ecogaia.conexion;

import java.sql.*;
import java.util.ArrayList;

public class ServicioComprador {
    ArrayList<Comprador> datos = new ArrayList<Comprador>();
    conexion con = new conexion();
    Connection conect = null;
    Statement st = null;
    ResultSet res = null;
    PreparedStatement ps = null;

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

    public Boolean IngresarComprador(Comprador c) {
        Boolean res = false;
        try {
            conect = con.conecta();
            String sql = "insert into comprador (Comp_Nombre, Comp_Telefono, Comp_Direccion, Comp_Correo, Comp_Contraseña) values (?,?,?,?,?)";
            ps = conect.prepareStatement(sql);
            ps.setString(1, c.getComp_Nombre());
            ps.setString(2, c.getComp_Telefono());
            ps.setString(3, c.getComp_Direccion());
            ps.setString(4, c.getComp_Correo());
            ps.setString(5, c.getComp_Contrasenia());
            res = ps.executeUpdate() > 0;

        }catch (SQLException ex) {
            System.out.println("Error al ingresar comprador " + ex);
        }
         return  res;
    }
}
