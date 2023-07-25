package libreria.services;

import java.util.List;
import java.util.Scanner;
import libreria.JPA_DAO.Autor_DAO;
import libreria.entities.Autor;

/**
 *
 * @author Max
 */
public class ServicioPrincipal {

    Scanner input = new Scanner(System.in).useDelimiter("\n");
    LibroService ls = new LibroService();
    AutorService as = new AutorService();
    Autor_DAO adao = new Autor_DAO();

    public void menuGeneral() {
        String option = " ";
        do {
            System.out.println(" ------------------------------------");
            System.out.println(" Bienvenido al sistema de librerias ");
            System.out.println(" ------------------------------------");
            System.out.println(" ---------------Menú-----------------");
            System.out.println(" ");
            System.out.println(" Seleccione que desea ingresar/revisar");
            System.out.println("1- Autores");
            System.out.println("2- Editoriales");
            System.out.println("3- Libros");
            System.out.println("X- Finalizar");
            System.out.println(" ///////////////////////////////////");
            option = input.nextLine();

            switch (option) {
                case "1":
                    menuAutor();
                    break;
                case "2":
                    menuEditorial();
                    break;
                case "3":
                    menuLibros();
                    break;
                case "x":
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    throw new AssertionError();
            }
        } while (!option.equalsIgnoreCase("x"));

    }

    public void menuAutor() {
        String optionA = " ";

        do {
            System.out.println(" ------------------------------------");
            System.out.println(" ");
            System.out.println(" ----------Menú Autores--------");
            System.out.println(" ------------------------------------");
            System.out.println("1- Nuevo ingreso");
            System.out.println("2- Modificar datos");
            System.out.println("3- Ver autores");
            System.out.println("4- Buscar por nombre");
            System.out.println("5- Buscar por Id");
            System.out.println("6- Eliminar autor(baja)");
            System.out.println("X- Volver al menú principal ");
            System.out.println("Ingrese una opcion...");
            System.out.println(" -------------------------------------");
            optionA = input.nextLine();

            switch (optionA) {
                case "1":
                    System.out.println("Ingrese nombre del autor:");
                    String nombreAutor = input.nextLine();
                    as.crearAutor(nombreAutor);
                    break;
                case "2":
                    System.out.println("Ingrese el número de Id del autor que desea modificar");
                    int idAutorModificar;
                    while (true) {
                        try {
                            idAutorModificar = Integer.parseInt(input.next());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Ingrese un número válido para el Id del autor:");
                        }
                    }
                    input.nextLine(); //limpio buffer sino no me toma el proximo dato

                    Autor autorAModificar = as.encontrarId(idAutorModificar);

                    if (autorAModificar != null) {
                        System.out.println("Ingrese nuevo nombre");
                        String nombreAut = input.nextLine();
                        as.modificarAutor(autorAModificar, nombreAut);
                    } else {
                        System.out.println("El autor con Id " + idAutorModificar + " no existe.");
                    }
                    break;

                case "3":
                    mostrarLista();
                    break;
                case "4":
                    System.out.println("Ingrese autor a buscar");
                    String nombre = input.nextLine();
                    mostrarAutor(null, nombre);
                    break;

                case "5":
                    System.out.println("Ingrese Id para buscar");
                    Integer idBuscar = input.nextInt();
                    mostrarAutor(idBuscar, null);
                    break;
                case "6":
                    System.out.println("Ingrese el Id del autor que desea borrar(dar de baja)");
                    idBuscar = input.nextInt();
                    as.eliminarAutor(idBuscar);
                    break;
                case "x":

                    break;

            }

        } while (!optionA.equalsIgnoreCase("x"));

    }

    public void menuLibros() {
        System.out.println("opcion 3");

    }

    public void menuEditorial() {
        System.out.println("opcion 2");
    }

    public void mostrarLista() {
        System.out.println(" ----- Lista de Autores -----");

        List<Autor> autores = as.mostrarLista();

        if (autores != null && !autores.isEmpty()) {
            for (Autor autor : autores) {
                System.out.println("ID: " + autor.getId() + ", Nombre: " + autor.getNombre() + ", Activo: " + autor.getAlta());
            }
        } else {
            System.out.println("No se encontraron autores.");
        }
    }

    public void mostrarAutor(Integer id, String nombre) {
        System.out.println(" ---------Autor--------");
        List<Autor> autores = as.mostrarAutor(id, nombre);
        if (autores != null && !autores.isEmpty()) {
            for (Autor autor : autores) {
                System.out.println("ID: " + autor.getId() + ", Nombre: " + autor.getNombre() + ", Activo: " + autor.getAlta());
            }
        } else {
            System.out.println("No se encontró el autor.");
        }
    }

}
