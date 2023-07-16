/*

 */
package tienda.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import tienda.entity.Fabricante;

/**
 *
 * @author Max
 */
public final class FabricanteDao extends DAO {

    public void guardarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("Debe ingresar un fabricante");

            }
            String sql = " INSERT INTO Fabricante (codigo,nombre)"
                    + " VALUES  ('" + fabricante.getCodigo() + "' , '" + fabricante.getNombre()
                    + "' ); ";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        }
    }
    
        public boolean existeFabricante(int codigo) throws Exception {
    try {
        String sql = "SELECT COUNT(*) FROM Fabricante WHERE codigo = ?";
        PreparedStatement pstmt = DAO.getConnection().prepareStatement(sql);
        pstmt.setInt(1, codigo);
        ResultSet resultSet = pstmt.executeQuery();
        
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count > 0;
        }
        
        return false;
    } catch (Exception e) {
        throw e;
    }
}
    
    public void modificarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("Debe ingresar un fabricante para modificar");
            }
            String sql = "UPDATE Fabricante SET"
                    + " nombre ='" + fabricante.getNombre() + "' WHERE codigo = '" + fabricante.getCodigo() + "'";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }


public void eliminarFabricante(int codigo) throws Exception {
    try {
        if (existeFabricante(codigo)) {
            String sql = "DELETE FROM Fabricante WHERE codigo = ?";
            PreparedStatement pstmt = DAO.getConnection().prepareStatement(sql);
            pstmt.setInt(1, codigo);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminado correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "El fabricante no existe en la base de datos");
        }
    } catch (Exception e) {
        System.err.println(e);
        throw e;
    } finally {
        desconectarBase();
    }
}


    public Fabricante buscarFabricantePorCodigo(int codigo) throws Exception {
        try {

            String sql = "SELECT * FROM Fabricante "
                    + " WHERE codigo = '" + codigo + "'";

            consultarBase(sql);

            Fabricante fabricante = null;
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));

            }
            desconectarBase();
            return fabricante;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    
    public Fabricante buscarFabricantePorNombre(String nombre) throws Exception {
        try {

            String sql = "SELECT * FROM Fabricante "
                    + " WHERE id = '" + nombre + "'";

            consultarBase(sql);

            Fabricante fabricante = null;
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
                
            }
            desconectarBase();
            return fabricante;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    
    public Collection<Fabricante> listarFabricantes() throws Exception {
         Collection<Fabricante> fabricantes = new ArrayList();
         
        try {
            String sql = "SELECT codigo, nombre FROM Fabricante ";

            consultarBase(sql);

            Fabricante fabricante = null;
           
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
                fabricantes.add(fabricante);
            }
            desconectarBase();
           
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
        for (Fabricante fabricante : fabricantes) {
            System.out.println(fabricante.getCodigo() + " _ " + fabricante.getNombre());
        }
        
        return fabricantes;
    }
    
      // esto es para usar la interfaz tabla
    
      public void insertarFabricante(JTextField codigoField, JTextField nombField) throws SQLException{
          Fabricante f = new Fabricante();
          f.setCodigo(Integer.parseInt(codigoField.getText()));
          f.setNombre(nombField.getText());
          Connection connection = DAO.getConnection();
          
          String consulta = "INSERT INTO Fabricante (codigo, nombre) VALUES (?,?);";
          
          try {
              CallableStatement cs = connection.prepareCall(consulta);
              cs.setInt(1, f.getCodigo());
              cs.setString(2, f.getNombre());
              cs.execute();
              JOptionPane.showMessageDialog(null, "Se agrego correctamente el Fabricante");
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "No se agrego correctamente el Fabricante, error: " + e.toString());
          }
    }
      
      public void mostrarFabricantes(JTable fabricantesTable) throws SQLException{
          Connection connection = DAO.getConnection();
          DefaultTableModel modelo = new DefaultTableModel();
          TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(modelo);
          fabricantesTable.setRowSorter(ordenarTabla);
          String sql = "";
          modelo.addColumn("codigo");
          modelo.addColumn("nombre");
          fabricantesTable.setModel(modelo);
          sql = "SELECT * FROM Fabricante;";
          String[] datos = new String[2];
          Statement st;
          try {
              st = connection.createStatement();
              ResultSet rs = st.executeQuery(sql);
              while (rs.next()) { 
                  datos[0]= rs.getString(1);
                  datos[1]= rs.getString(2);
                  modelo.addRow(datos);                 
              }
              fabricantesTable.setModel(modelo);
          } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
          }
          
      }
}

