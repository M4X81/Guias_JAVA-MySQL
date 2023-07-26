package libreria.services;

import java.util.List;
import java.util.Scanner;
import libreria.JPA_DAO.Autor_DAO;
import libreria.JPA_DAO.Editorial_DAO;
import libreria.JPA_DAO.Libro_DAO;

import libreria.entities.Autor;
import libreria.entities.Editorial;
import libreria.entities.Libro;

/**
 *
 * @author Max
 */
public class ServicioPrincipal {

    Scanner input = new Scanner(System.in).useDelimiter("\n");
    LibroService ls = new LibroService();
    AutorService as = new AutorService();
    EditorialService es = new EditorialService();
    Autor_DAO adao = new Autor_DAO();
    Editorial_DAO edao = new Editorial_DAO();
    Libro_DAO ldao = new Libro_DAO();

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
            }
        } while (!optionA.equalsIgnoreCase("x"));
    }

    public void menuEditorial() {
        String optionE = " ";

        do {
            System.out.println(" ------------------------------------");
            System.out.println(" ");
            System.out.println(" ----------Menú Editorial--------");
            System.out.println(" ------------------------------------");
            System.out.println("1- Nuevo ingreso");
            System.out.println("2- Modificar datos");
            System.out.println("3- Ver editorial");
            System.out.println("4- Buscar por nombre");
            System.out.println("5- Buscar por Id");
            System.out.println("6- Eliminar editorial(baja)");
            System.out.println("X- Volver al menú principal ");
            System.out.println("Ingrese una opcion...");
            System.out.println(" -------------------------------------");
            optionE = input.nextLine();

            switch (optionE) {
                case "1":
                    System.out.println("Ingrese nombre de la editorial:");
                    String nombreEditorial = input.nextLine();
                    es.crearEditorial(nombreEditorial);
                    break;

                case "2":
                    System.out.println("Ingrese el número de Id de la editorial que desea modificar");
                    int idEditorialModificar;
                    while (true) {
                        try {
                            idEditorialModificar = Integer.parseInt(input.next());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Ingrese un número válido para el Id de la editorial:");
                        }
                    }
                    input.nextLine(); //limpio buffer sino no me toma el proximo dato

                    Editorial editorialAModificar = es.encontrarId(idEditorialModificar);

                    if (editorialAModificar != null) {
                        System.out.println("Ingrese nuevo nombre");
                        String nombreEdit = input.nextLine();
                        es.modificarEditorial(idEditorialModificar, nombreEdit);
                    } else {
                        System.out.println("El autor con Id " + idEditorialModificar + " no existe.");
                    }
                    break;

                case "3":
                    mostrarListaEd();
                    break;

                case "4":
                    System.out.println("Ingrese editorial a buscar");
                    String nombre = input.nextLine();
                    mostrarEditorial(null, nombre);
                    break;

                case "5":
                    System.out.println("Ingrese Id para buscar");
                    Integer idBuscar = input.nextInt();
                    mostrarEditorial(idBuscar, null);
                    break;

                case "6":
                    System.out.println("Ingrese el Id de la editorial que desea borrar(dar de baja)");
                    idBuscar = input.nextInt();
                    es.borrarEditorial(idBuscar);
                    break;
            }
        } while (!optionE.equalsIgnoreCase("x"));
    }

    public void menuLibros() {
        String optionL = " ";
        String titulo;
        do {
            System.out.println(" ------------------------------------");
            System.out.println(" ");
            System.out.println(" ----------Menú Libros--------");
            System.out.println(" ------------------------------------");
            System.out.println("1- Nuevo ingreso");
            System.out.println("2- Modificar datos");
            System.out.println("3- Ver libros");
            System.out.println("4- Buscar por título");
            System.out.println("5- Buscar por ISBN");
            System.out.println("6- Buscar por autor");
            System.out.println("7- Buscar por editorial");
            System.out.println("8- Eliminar libro(baja)");
            System.out.println("X- Volver al menú principal ");
            System.out.println("Ingrese una opcion...");
            System.out.println(" -------------------------------------");
            optionL = input.nextLine();

            switch (optionL) {
                case "1":
                    ls.crearLibro();
                    break;

                case "2":
                    /*
                    aca solo dejo modificar titulo, cantidades y alta/baja. despues vere si agrego para modificar
                    autor y editorial. da muchos errores de asignacion
                    */
                     System.out.println("Ingrese el ISBN del libro que desea modificar:");
                Long ISBNModificar;
                while (true) {
                    try {
                        ISBNModificar = Long.parseLong(input.next());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Ingrese un número válido para el ISBN del libro:");
                    }
                }
                input.nextLine(); //limpio buffer
                ls.modificarLibro(ISBNModificar);
                break;

                case "3":
                    mostrarListaLib();
                    break;

                case "4":
                    System.out.println("Ingrese título a buscar");
                    titulo = input.nextLine(); 
                    mostrarTitulos(titulo); 
                    break;

                case "5":
                    //cambiar despues
                    System.out.println("Ingrese Id para buscar");
                    Integer idBuscar = input.nextInt();
                    mostrarEditorial(idBuscar, null);
                    break;

                case "6":
                     //cambiar despues
                    System.out.println("Ingrese el Id de la editorial que desea borrar(dar de baja)");
                    idBuscar = input.nextInt();
                    es.borrarEditorial(idBuscar);
                    break;
                   //agregar case 7 y 8... 
            }

        } while (!optionL.equalsIgnoreCase("x"));
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
        List<Autor> autores = (List<Autor>) as.mostrarAutor(id, nombre);
        if (autores != null && !autores.isEmpty()) {
            for (Autor autor : autores) {
                System.out.println("ID: " + autor.getId() + ", Nombre: " + autor.getNombre() + ", Activo: " + autor.getAlta());
            }
        } else {
            System.out.println("No se encontró el autor.");
        }
    }

    public void mostrarListaEd() {
        System.out.println(" ----- Lista de Editoriales -----");

        List<Editorial> editoriales = es.mostrarListaEd();

        if (editoriales != null && !editoriales.isEmpty()) {
            for (Editorial editorial : editoriales) {
                System.out.println("ID: " + editorial.getId() + ", Nombre: " + editorial.getNombre() + ", Activo: " + editorial.getAlta());
            }
        } else {
            System.out.println("No se encontraron editoriales.");
        }
    }

    public void mostrarEditorial(Integer id, String nombre) {
        System.out.println(" ---------Editorial--------");
        List<Editorial> editoriales = es.mostrarEditorial(id, nombre);
        if (editoriales != null && !editoriales.isEmpty()) {
            for (Editorial editorial : editoriales) {
                System.out.println("ID: " + editorial.getId() + ", Nombre: " + editorial.getNombre() + ", Activo: " + editorial.getAlta());
            }
        } else {
            System.out.println("No se encontró el editorial.");
        }
    }

public void mostrarListaLib() {
    System.out.println(" ----- Lista de Libros -----");

    List<Libro> librox = ls.mostrarLista();

    if (librox != null && !librox.isEmpty()) {
        for (Libro libro : librox) {
            System.out.println("ID: " + libro.getISBN() + ", Titulo: " + libro.getTitulo() + ", Activo: " + libro.getAlta()
                    + " , Cantidad de ejemplares " + libro.getEjemplares() + " , Prestados : " + libro.getEjemplaresPrestados()
                    + " , Restantes : " + libro.getEjemplaresRestantes() + " , Autor : " + libro.getAutor()
                    + " , Editorial : " + libro.getEditorial().getNombre()); 
        }
    } else {
        System.out.println("No se encontraron libros ");
    }
}


    public void mostrarTitulos(String tituloBuscado) {
        List<Libro> libros = ldao.findTitle(tituloBuscado);

        if (libros != null && !libros.isEmpty()) {
            System.out.println("Libros encontrados con el título '" + tituloBuscado + "':");
            for (Libro libro : libros) {
                System.out.println("ISBN: " + libro.getISBN() + ", Título: " + libro.getTitulo() + ", Activo: " + libro.getAlta() + ", Cantidad de ejemplares: " + libro.getEjemplares()
                        + ", Prestados: " + libro.getEjemplaresPrestados() + ", Restantes: " + libro.getEjemplaresRestantes() + ", Autor: " + libro.getAutor()
                        + ", Editorial: " + libro.getEditorial().getNombre());
            }
        } else {
            System.out.println("No se encontró ningún libro con ese título.");
        }
    }
}
