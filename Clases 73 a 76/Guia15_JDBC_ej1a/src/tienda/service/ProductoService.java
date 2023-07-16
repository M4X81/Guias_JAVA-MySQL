/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.service;

import java.util.Collection;
import tienda.entity.Fabricante;
import tienda.entity.Producto;
import tienda.persistence.ProductoDao;

/**
 *
 * @author Max
 */
public class ProductoService {
    
    private ProductoDao dao;
  
    public ProductoService() {
        this.dao = new ProductoDao();
    }
 
   

    public void crearProducto(int codigo, String nombre, double precio,int codigoFabricante, Fabricante fabricante) throws Exception {

        try {

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }

            if (precio == 0) {
                throw new Exception("Debe indicar el precio");
            }

            if (codigoFabricante == 0) {
                throw new Exception("Debe indicar codigo de fabricante");
            }

            //Creamos el producto
            Producto producto = new Producto();
            producto.setCodigo(codigo);
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCodigoFabricante(codigoFabricante);
            dao.guardarProducto(producto);

        } catch (Exception e) {
            throw e;
        }
    }
/*
    public void modificarClaveMascota(int id, String apodo, String raza, int idUsuario) throws Exception {

        try {

            //Validamos
            if (id > 0) {
                throw new Exception("Debe indicar el id");
            }

            if (apodo == null || apodo.trim().isEmpty()) {
                throw new Exception("Debe indicar el apodo");
            }

            if (raza == null || raza.trim().isEmpty()) {
                throw new Exception("Debe indicar la raza");
            }

            if (idUsuario < 0) {
                throw new Exception("Debe indicar el Usuario");
            }

            //Buscamos
            Producto mascota = buscarMascotaPorId(id);

            dao.modificarMascota(mascota);
        } catch (Exception e) {
            throw e;
        }
    }
*/

    public void eliminarProducto(int codigo) throws Exception {

        try {

            if (codigo < 0) {
                throw new Exception("Debe indicar el Id");
            }
            dao.eliminarProducto(codigo);
        } catch (Exception e) {
            throw e;
        }
    }

    public Producto buscarProductoPorCodigo(int codigo) throws Exception {

        try {

            if (codigo < 0) {
                throw new Exception("Debe indicar el id");
            }
            Producto producto = dao.buscarProductoPorCodigo(codigo);
        
            if (producto == null) {
                throw new Exception("No se econtró mascota para el correo electrónico indicado");
            }

            return producto;
        } catch (Exception e) {
            throw e;
        }
    }
    
    

    public Collection<Producto> listarProducto() throws Exception {

        try {
            Collection<Producto> producto = dao.listarProductos();
            return producto;
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirProductos() throws Exception {

        try {
            Collection<Producto> producto = listarProducto();

            if (producto.isEmpty()) {
                throw new Exception("No existen productos para imprimir");
            } else {
                for (Producto u : producto) {
                    System.out.println(u.toString());
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
}
