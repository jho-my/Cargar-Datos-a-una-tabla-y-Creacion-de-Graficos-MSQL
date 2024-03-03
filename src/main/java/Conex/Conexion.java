package Conex;

import java.sql.*;

public class Conexion {

    private static Connection connexion;
    private static Conexion instancia;

    private static final String URL = "jdbc:mysql://localhost:3306/ejercicios";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public Connection ConectarBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connexion = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conectado");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en Conectar => " + e.getMessage());
        }

        return connexion;
    }

    public void DesconectarBD() throws SQLException {
        try {
            connexion.close();
            System.out.println("Desconectado");
        } catch (SQLException e) {
            System.out.println("Error al desconecta => " + e.getMessage());
            connexion.close();
        } finally {
            connexion.close();
        }
    }

    //PATRON SINGLETON
    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }
}
