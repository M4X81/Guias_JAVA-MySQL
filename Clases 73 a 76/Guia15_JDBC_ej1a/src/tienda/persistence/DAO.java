/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Max
 */
public abstract class DAO {
    protected Connection conexion = null;
    protected ResultSet resultado = null;
    protected Statement sentencia = null;
    
    private final String USER ="root";
    private final String PASSWORD = "root";
    private final String DATABASE = "Guia15_JDBC_extra_ej1_tienda";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    /* este metodo lo creo para poder probar el form*/
    
    public static Connection getConnection() throws SQLException {
    String url = "jdbc:mysql://localhost:3306/Guia15_JDBC_extra_ej1_tienda?useSSL=false";
    String username = "root";
    String password = "root";
    return DriverManager.getConnection(url, username, password);
}

    
    protected void conectarBase() throws ClassNotFoundException, SQLException{
        try {
            
            Class.forName(DRIVER);
            String urlBaseDeDatos = "jdbc:mysql://localhost:3306/"+ DATABASE +"?useSSL=false";
            conexion= DriverManager.getConnection(urlBaseDeDatos, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos: " + e.toString());
            throw e;
        }
    }
    
    protected void desconectarBase() throws Exception{
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    protected void insertarModificarEliminar(String sql) throws Exception{
        try {
            
            conectarBase();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
            
        } catch (SQLException | ClassNotFoundException e) {
            //conexion.rollback();
            /* descomentar la linea rollback para poder usar
            y correr este script en workbench
            SET autocommit=1;
            COMMIT;
            */
            throw e;
        }finally {
            desconectarBase();
        }
    }
    
    protected void consultarBase(String sql) throws Exception{
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        } catch (Exception e) {
            throw e;
        }
    }
}
