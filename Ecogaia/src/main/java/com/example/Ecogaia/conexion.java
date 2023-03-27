package com.example.Ecogaia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    private String db = "ecogaia";
    private String user = "root";
    private String pass = "123456";
    private String url = "jdbc:mysql://localhost:3306/" + db;

    Connection conect = null;

    public Connection conecta() {
        try {
            conect = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            System.out.println("Error al conectar " + ex);
        }
        return conect;
    }


}