/*

 */
package estancia.persistence;

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

    private final String USER = "root";
    private final String PASSWORD = "M4xi198!";
    private final String DATABASE = "estancias_exterior";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    public static Connection getConnection() throws SQLException {
    String url = "jdbc:mysql://localhost:3306/estancias_exterior?useSSL=false";
    String username = "root";
    String password = "M4xi198!";
    JOptionPane.showMessageDialog(null, "Conexión exitosa");
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
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }

}
