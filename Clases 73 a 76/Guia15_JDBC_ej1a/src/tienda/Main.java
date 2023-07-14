
/*

Paquete persistencia
En este paquete estará la clase DAO encarga de conectarse con la base de datos y de 
comunicarse con la base de datos para obtener sus datos. Además, estará las clases de 
EntidadDaoExt para cada entidad / tabla de nuestro proyecto.
Es importante tener la conexión creada a la base de datos, como lo explica el Instructivo en la 
pestaña de Services en Netbeans.
Paquete entidades:
Dentro de este paquete se deben crear todas las clases necesarias que vamos a usar de la 
base de datos. Por ejemplo, una de las clases a crear dentro de este paquete es la clase 
“Producto.java” con los siguientes atributos:
• private int codigo;
• private String nombre;
• private double precio;
• private int codigoFabricante;
Agregar a cada clase el/los constructores necesarios y los métodos públicos getters y setters 
para poder acceder a los atributos privados de la clase. La llave foránea se pondrá como dato 
nada más, no como objeto.
Paquete servicios:
En este paquete se almacenarán aquellas clases que llevarán adelante lógica del negocio. En 
general se crea un servicio para administrar cada una de las entidades y algunos servicios 
para manejar operaciones muy específicas como las estadísticas.
Realizar un menú en Java a través del cual se permita elegir qué consulta se desea realizar. 
Las consultas a realizar sobre la BD son las siguientes:
a) Lista el nombre de todos los productos que hay en la tabla producto. 
b) Lista los nombres y los precios de todos los productos de la tabla producto. 
c) Listar aquellos productos que su precio esté entre 120 y 202. 
d) Buscar y listar todos los Portátiles de la tabla producto. 
e) Listar el nombre y el precio del producto más barato. 
f) Ingresar un producto a la base de datos.
g) Ingresar un fabricante a la base de datos
h) Editar un producto con datos a elección.
 */
package tienda;

import tienda.persistence.ProductoDao;
import tienda.service.FabricanteService;
import tienda.service.ProductoService;

/**
 *
 * @author Max
 */
public class Main {

    public static void main(String[] args) throws Exception {

        FabricanteService fS = new FabricanteService();
        ProductoService pS = new ProductoService();
        ProductoDao pd = new ProductoDao();
        pS.listarProducto();
      
    
   
/*
        try {
            //Creamos  usuarios
            usuarioService.crearUsuario("fiorde@hotmail.com", "fiorde14");
            usuarioService.crearUsuario("tincho@hotmail.com", "eggprogramacion");
            usuarioService.imprimirUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }

        try {
            //Modificamos un usuario
            usuarioService.modificarClaveUsuario("fiorde@hotmail.com", "fiorde14", "fiorde15");
            usuarioService.imprimirUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }

        try {
            //Eliminamos un usuario
            usuarioService.eliminarUsuario("fiorde@hotmail.com");
            usuarioService.imprimirUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }

        try {
            //Buscamos el Usuario por correo
            Usuario usuario = usuarioService.buscarUsuarioPorCorreoElectronico("tincho@hotmail.com");
            //Creamos Mascota con el Usuario anterior
            mascotaService.crearMascota("Chiquito", "Beagle", usuario);

            //Mostramos Mascota con su Usuario
            mascotaService.imprimirMascotas();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error del sistema por \n" + e.getMessage());
        }
    */
    }

}
