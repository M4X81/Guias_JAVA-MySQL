/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import tienda.entity.Fabricante;
import tienda.entity.Producto;
import tienda.service.FabricanteService;

/**
 *
 * @author Max
 */
public final class ProductoDao extends DAO {

    //private final FabricanteService fabricanteService;
    /*
    public ProductoDao() {
        this.fabricanteService = new FabricanteService();
    }
     */
    private final FabricanteService fabricanteService;

    public ProductoDao() {
        this.fabricanteService = new FabricanteService();
    }

    public void guardarProducto(Producto producto) throws Exception {
        try {
            
            if (producto == null) {
                throw new Exception("Debe indicar el producto");
            }
            
            String sql = "INSERT INTO Producto (nombre, precio, codigo_fabricante) "
                    + "VALUES ('" +  "', '" + producto.getNombre() + "', " + producto.getPrecio() + ", " + producto.getCodigoFabricante()
                    + producto.getFabricante().getCodigo() + ");";

            System.out.println(sql);
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

   public void modificarProducto(Producto producto) throws Exception {
    try {
        if (producto == null) {
            throw new Exception("Debe indicar el producto que desea modificar");
        }
        String sql = "UPDATE Producto SET "
                + "nombre = '" + producto.getNombre() + "', precio = " + producto.getPrecio()
                + ", codigo_fabricante = " + producto.getCodigoFabricante()
                + " WHERE codigo = " + producto.getCodigo();
        insertarModificarEliminar(sql);
    } catch (Exception e) {
        throw e;
    } finally {
        desconectarBase();
    }
}


    public void eliminarProducto(int codigo) throws Exception {
        try {
            String sql = "DELETE FROM Producto WHERE codigo = " + codigo + "";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public boolean existeProducto(int codigoProducto) throws Exception {
        Connection connection = DAO.getConnection();
    boolean existe = false;
    try {
        String sql = "SELECT COUNT(*) FROM Producto WHERE codigo = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, codigoProducto);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            existe = count > 0;
        }
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        throw e;
    }
    return existe;
}

    /*
    public Producto buscarProductoPorCodigo(int codigo) throws Exception {
        try {
            String sql = "SELECT * FROM Producto WHERE codigo = " + codigo + "";
            consultarBase(sql);
            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                //producto.setCodigoFabricante(resultado.getInt(4)); 
                Integer codigo_fab = resultado.getInt(4);
                Fabricante fabricante = fabricanteDao.buscarFabricantePorCodigo(codigo_fab);
                producto.setFabricante(fabricante);
            }
            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
     */
    // esto es con un join como sugiere el video
    public Producto buscarProductoPorCodigo(int codigo) throws Exception {
        try {
            String sql = "SELECT p.*, f.* FROM Producto p JOIN Fabricante f ON p.codigo_fabricante = f.codigo WHERE p.codigo = " + codigo;
            consultarBase(sql);
            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));

                Fabricante fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt("codigo_fabricante"));
                fabricante.setNombre(resultado.getString("nombre_fabricante"));

                producto.setFabricante(fabricante);
            }
            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

public Collection<Producto> listarProductos() throws Exception {
    Collection<Producto> productos = new ArrayList();

    try {
        String sql = "SELECT * FROM Producto ";
        consultarBase(sql);
        Producto producto = null;

        while (resultado.next()) {
            producto = new Producto();
            producto.setCodigo(resultado.getInt(1));
            producto.setNombre(resultado.getString(2));
            producto.setPrecio(resultado.getDouble(3));
            producto.setCodigoFabricante(resultado.getInt(4));
            Fabricante fabricante = fabricanteService.buscarFabricantePorCodigo(0);
            producto.setFabricante(fabricante);
            productos.add(producto);
        }

        desconectarBase();
    } catch (Exception e) {
        e.printStackTrace();
        desconectarBase();
        throw e;
    }

    for (Producto producto : productos) {
        System.out.println(producto.getCodigo() + " - " + producto.getNombre() + " - " + producto.getPrecio());
    }

    return productos;
}


}
