/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestorbiblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author zakar
 */
public class Biblioteca {

    private List<Libro> catalogo;
    private List<Usuario> usuarios;

    Scanner sc = new Scanner(System.in);

    //construct
    public Biblioteca() {
        this.catalogo = new ArrayList();
        this.usuarios = new ArrayList();
    }

    //Metodos get :
    /*metodo getter de un libro desde catalogo 
    mediante su isbn*/
    private Libro getLibro(String isbn) {
        for (Libro libro : this.catalogo) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }

    /*metodo getter de usuario mediante su id*/
    private Usuario getUsuario(String id) {
        for (Usuario user : this.usuarios) {
            if (user.getIdUsuario().equals(id)) {
                return user;
            }
        }
        return null;
    }

    /*metodo para agregar nuevo usuario
    debido a que el id de cada usuario tiene que ser unico ,
    no puede haber IDs duplicado
     */
    public void registrarUsuario() {
        String nombre, id;

        System.out.println("Elige un nombre del usuario: ");
        nombre = sc.nextLine();

        System.out.println("Ingrese el ID del usuario: ");
        id = sc.nextLine();

        //verificacion
        while (this.buscarUsuario(id)) {
            System.out.println("Error : Este id Ya existe !!");
            System.out.println("Intenta con otro:");
            id = sc.nextLine();
        }

        Usuario usuario = new Usuario(nombre, id);
        usuarios.add(usuario);
        System.out.println("Usuario registrado con éxito.");

    }


    /*metodo de agregación de nuevos libros en el catalogo de 
    la biblioteca*/
    public void agregarLibro() {
        String autor, nombre, isbn;

        System.out.println("Ingrese el título del libro:");
        nombre = sc.nextLine();
        System.out.println("Ingrese el autor:");
        autor = sc.nextLine();
        System.out.println("Ingrese el ISBN:");
        isbn = sc.nextLine();

        //verificacion de isbn
        while (this.buscarLibro(isbn)) {
            System.out.println("Error : ISBN no válido o de otor Libro");
            System.out.println("Vuelve a ingresarlo");
            isbn = sc.nextLine();
        }

        Libro libro = new Libro(nombre, autor, isbn);
        catalogo.add(libro);

        System.out.println("Libro agregado con éxito.");
    }

    // metodo de prestacion 
    public String prestarLibro() {
        String isbn, idUsuario;

        System.out.println("Ingrese el ISBN del libro:");
        isbn = sc.nextLine();

        // verificacion de existencia de libro en el catalogo
        if (!this.buscarLibro(isbn)) {
            return "Libro no encontrado";
        }

        System.out.println("Ingrese el ID del usuario:");
        idUsuario = sc.nextLine();

        // verificacion de que el usuario esta registrado
        // si no se le pide registrarse con el metodo
        if (!this.buscarUsuario(idUsuario)) {
            return "ID usuario no válido o Usuario no existe !!";
        }

        /*tras las verificaciones recuperamos los datos de tanto
        el libro como el usuario con los metodos getters*/
        Libro libro = getLibro(isbn);
        Usuario usuario = getUsuario(idUsuario);

        // verificacion de disponibilidad del libro a prestar
        if (libro.isEstaPrestado()) {
            return "Libro ya está prestado";
        }

        // verificacion de cantidad de libros prestados
        if (usuario.getNumLibrosPrestados() > 3) {
            return "Te has pasado del numeros de libros prestados";
        }

        /* todos los requisitos de prestacion están cumplidos
        asignamos el libro al usuario cambiamos su estado
        y devolvemos el mensaje*/
        libro.prestar();
        usuario.agregarLibro(libro);

        return "Libro prestado con exito (●'◡'●)";
    }

    //metodo de devolucion
    public void devolverLibro() {
        String isbn, idUsuario;

        System.out.println("Ingrese el ISBN del libro:");
        isbn = sc.nextLine();
        System.out.println("Ingrese el ID del usuario:");
        idUsuario = sc.nextLine();

        Libro libro = getLibro(isbn);
        Usuario usuario = getUsuario(idUsuario);

        usuario.devolverLibro(libro);

    }

    /* metodo para poder buscar un libro por el 
    criterio que sea :su nombre, su autor o bien su isbn
     */
    private boolean buscarLibroPorCriterio(String criterio) {
        for (Libro libro : this.catalogo) {
            String informacion = libro.toString();
            if (informacion.contains("//" + criterio + "//")) {
                return true;//encontrado
            }
        }
        return false;//no encontrado
    }

    /* metodo para buscar un usuario por su id */
    private boolean buscarUsuario(String idUsuario) {
        for (Usuario user : this.usuarios) {
            if (user.getIdUsuario().equals(idUsuario)) {
                return true;
            }
        }
        return false;
    }

    /* metodo para buscar un libro por su isbn */
    private boolean buscarLibro(String isbn) {
        for (Libro libro : this.catalogo) {
            if (libro.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    
    //metodo mostrar todos los libros del catalogo
    public void mostrarCatalogo() {
        System.out.println( setSpace(30,"Titulo") + setSpace(30,"Autor")  + setSpace(30,"ISBN") + setSpace(30,"Prestado?"));
        for (Libro libro : this.catalogo) {
            String[] infoLibro = splitString(libro.toString(), "//");
            for (String infoLibro1 : infoLibro) {
                System.out.print(setSpace(30,infoLibro1));
            }
            System.out.println("\n");
        }
    }

    //metodo mostrar todos los usuarios
    public void mostrarUsuarios() {
        System.out.println("Nombre" + "\t\t" + "ID" + "\t\t" + "Libros Prestados");
        for (Usuario usuario : this.usuarios) {
            usuario.mostrarUsuario();
        }
    }

    
    // metodos para el formato
    public static String[] splitString(String cadena, String patron) {
        return cadena.split(patron);
    }

    public static String tab(int n) {
        String tabs = "";
        for (int i = 0; i < n; i++) {
            tabs+="\t";
        }
        return tabs;
    }
    
    public static String setSpace(int space,String cadena){
        StringBuilder cadenaEnEspacio=new StringBuilder();
        cadenaEnEspacio.append(cadena);
        for (int i = 1; i <= (space-cadena.length()); i++) {
            cadenaEnEspacio.append(" ");
        }
        return cadenaEnEspacio.toString();
    }

    
    //los servicios que ofrece la biblioteca
    public void ofrecerServicio(int n){
        switch(n){
            case 1:
                this.registrarUsuario();
                break;
            case 2:
                this.agregarLibro();
                break;
            case 3:
                System.out.println(this.prestarLibro());
                break;
            case 4:
                this.devolverLibro();
                break;
            case 5:
                this.mostrarCatalogo();
                break;
            case 6:
                this.mostrarUsuarios();
                break;
        }
    }
    
    
}
