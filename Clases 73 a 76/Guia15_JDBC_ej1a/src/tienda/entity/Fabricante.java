/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.entity;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import tienda.persistence.DAO;

/**
 *
 * @author Max
 */
public class Fabricante {
    private int codigo;
    private String nombre;

    public Fabricante() {
    }

    public Fabricante(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
     // esto es para usar la interfaz tabla
    
      public void insertarFabricante(JTextField codigoField, JTextField nombField) throws SQLException{
          setCodigo(Integer.parseInt(codigoField.getText()));
          setNombre(nombField.getText());
          Connection connection = DAO.getConnection();
          
          String consulta = "INSERT INTO Fabricante (codigo, nombre) VALUES (?,?);";
          
          try {
              CallableStatement cs = connection.prepareCall(consulta);
              cs.setInt(1, getCodigo());
              cs.setString(2, getNombre());
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
          modelo.addColumn(codigo);
          modelo.addColumn(nombre);
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
