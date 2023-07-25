
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import tienda.entity.Producto;
import tienda.persistence.DAO;
import tienda.persistence.ProductoDao;
import tienda.service.FabricanteService;
import tienda.service.ProductoService;

/**
 *
 * @author Max
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in).useDelimiter("\n");       
        ProductoDao p1 = new ProductoDao();
        //este es para iniciar el formulario
       formFabricante_Producto fp = new formFabricante_Producto();
        //----------------------------------
        Connection connection = DAO.getConnection();
        FabricanteService fs = new FabricanteService();
        ProductoService ps = new ProductoService();
        JOptionPane.showMessageDialog(null, "La conexión se ha realizado exitosamente...");

        try {
            String opcion;

            do {
                System.out.println(" ");
                System.out.println("     Bienvenido a MercadoBarrani  ");
                System.out.println("--------------------------------------");
                System.out.println("                 MENÚ                 ");
                System.out.println("--------------------------------------");
                System.out.println("1-Lista el nombre de todos los productos que hay en la tabla producto. ");
                System.out.println("2-Lista los nombres y los precios de todos los productos de la tabla producto. ");
                System.out.println("3-Listar aquellos productos que su precio esté entre 120 y 202. ");
                System.out.println("4-Buscar y listar todos los Portátiles de la tabla producto. ");
                System.out.println("5-Listar el nombre y el precio del producto más barato.");
                System.out.println("6-Ingresar un producto a la base de datos.");
                System.out.println("7-Ingresar un fabricante a la base de datos.");
                System.out.println("8-Editar un producto con datos a elección.");
                System.out.println("9-Mostrar productos.");
                System.out.println("10-Mostrar fabricantes.");
                System.out.println("11-Mostrar formulario(Tocar como ultima opcion xq no se como volver al menú...");
                System.out.println("0-Salir");
                System.out.println("Ingrese su consulta");
                System.out.println(" ");
                System.out.println("--------------------------------------");

                opcion = input.nextLine();

                switch (opcion) {
                    case "1":
                    try {
                        String sql = "SELECT nombre FROM Producto;";
                        Statement st = connection.createStatement();
                        ResultSet resultSet = st.executeQuery(sql);

                        while (resultSet.next()) {
                            String nombre = resultSet.getString("nombre");
                            System.out.println(nombre);
                        }

                        resultSet.close();
                        st.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println(" ");
                    break;
                    case "2":
                        try {
                        String sql = "SELECT nombre, precio FROM Producto;";
                        Statement st = connection.createStatement();
                        ResultSet resultSet = st.executeQuery(sql);

                        while (resultSet.next()) {
                            String nombre = resultSet.getString("nombre");
                            double precio = resultSet.getDouble("precio");
                            System.out.println("Nombre: " + nombre + ", Precio: " + precio);
                        }

                        resultSet.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(" ");
                    break;
                    case "3":
                            try {
                        String sql = "SELECT nombre, precio FROM Producto where precio between 120 and 202;";
                        Statement st = connection.createStatement();
                        ResultSet resultSet = st.executeQuery(sql);

                        while (resultSet.next()) {
                            String nombre = resultSet.getString("nombre");
                            double precio = resultSet.getDouble("precio");
                            System.out.println("Nombre: " + nombre + ", Precio: " + precio);
                        }

                        resultSet.close();
                        st.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(" ");
                    break;
                    case "4":
                                try {
                        String sql = "SELECT nombre, precio FROM Producto where nombre like ('%portatil%');";
                        Statement st = connection.createStatement();
                        ResultSet resultSet = st.executeQuery(sql);

                        while (resultSet.next()) {
                            String nombre = resultSet.getString("nombre");
                            double precio = resultSet.getDouble("precio");
                            System.out.println("Nombre: " + nombre + ", Precio: " + precio);
                        }

                        resultSet.close();
                        st.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(" ");
                    break;
                    case "5":
                            try {
                        String sql = "SELECT nombre, precio FROM Producto order by precio\n"
                                + "limit 1;";
                        Statement st = connection.createStatement();
                        ResultSet resultSet = st.executeQuery(sql);

                        while (resultSet.next()) {
                            String nombre = resultSet.getString("nombre");
                            double precio = resultSet.getDouble("precio");
                            System.out.println("Nombre: " + nombre + ", Precio: " + precio);
                        }

                        resultSet.close();
                        st.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(" ");
                    break;
                    case "6":

                        System.out.println("Ingrese el nombre del producto: ");
                        String nombre = input.next();
                        System.out.println("Ingrese el precio del producto: ");
                        double precio = input.nextDouble();
                        System.out.println("Ingrese el código del fabricante: ");
                        int codigoFabricante = input.nextInt();

                        try {
                            String sql = "INSERT INTO Producto (nombre, precio, codigo_fabricante) VALUES (?, ?, ?)";
                            PreparedStatement pstmt = connection.prepareStatement(sql);
                            pstmt.setString(1, nombre);
                            pstmt.setDouble(2, precio);
                            pstmt.setInt(3, codigoFabricante);
                            pstmt.executeUpdate();

                            System.out.println("El producto ha sido guardado exitosamente.");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println(" ");
                        break;

                    case "7":
                        System.out.println("Ingrese el nombre del fabricante: ");
                        String nombreF = input.next();

                        try {
                            String sql = "INSERT INTO Fabricante (nombre) VALUES (?)";
                            PreparedStatement pstmt = connection.prepareStatement(sql);
                            pstmt.setString(1, nombreF);
                            pstmt.executeUpdate();

                            System.out.println("El fabricante ha sido guardado exitosamente.");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println(" ");
                        break;

                    case "8":
                            try {
                        System.out.println("Ingrese el código del producto a modificar: ");
                        int codigoProducto = input.nextInt();
                        input.nextLine();

                        if (!p1.existeProducto(codigoProducto)) {
                            System.out.println("El producto no existe en la base de datos.");
                            break;
                        }

                        System.out.println("Ingrese el nuevo nombre del producto");
                        String nuevoNombre = input.nextLine().trim();

                        System.out.println("Ingrese el nuevo precio del producto");
                        double nuevoPrecio = input.nextDouble();
                        input.nextLine();

                        System.out.println("Ingrese el nuevo código del fabricante ");
                        int nuevoCodigoFabricante = input.nextInt();
                        input.nextLine();

                        Producto productoModificado = new Producto();
                        productoModificado.setCodigo(codigoProducto);

                        if (!nuevoNombre.isEmpty()) {
                            productoModificado.setNombre(nuevoNombre);
                        }

                        if (nuevoPrecio != 0) {
                            productoModificado.setPrecio(nuevoPrecio);
                        }

                        if (nuevoCodigoFabricante != 0) {
                            productoModificado.setCodigoFabricante(nuevoCodigoFabricante);
                        }

                        p1.modificarProducto(productoModificado);
                        System.out.println("El producto ha sido modificado exitosamente.");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(" ");
                    break;

                    case "9":
                        ps.listarProducto();
                        break;
                    case "10":
                        fs.listarFabricante();
                        break;
                    case "11":
                        p1.ejecutarFormulario();
                        break;
                }
               
            } while (!opcion.equals("0"));

            //estas lineas son para cerrar la ejecucion del formulario que queda en segundo plano
            
            SwingUtilities.invokeLater(() -> {
                fp.dispose();         
                System.exit(0);
            });

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
