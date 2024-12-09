/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestorbiblioteca;

/**
 *
 * @author zakar
 */
public class Libro {

    private String titulo;
    private String autor;
    private String isbn; //unico
    private boolean estaPrestado;

    //constructor
    public Libro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.estaPrestado = false;

    }

    // Metodos get : 
    public boolean isEstaPrestado() {
        return this.estaPrestado;
    }
    
    public String getIsbn() {
        return isbn;
    }

    
    //Marca el libro como prestado.
    public boolean prestar() {
        this.estaPrestado = true;
        return this.estaPrestado;
    }

    //Marca el libro como disponible
    public boolean devolver() {
        this.estaPrestado = false;
        return this.estaPrestado;
    }

    //Devuelve la información del libro en un formato legible.
    @Override
    public String toString() {
        String str = this.titulo + "//" + this.autor + "//" + this.isbn;
        if (this.estaPrestado) {
            str+="//Si";
        } else {
            str+="//No";
        }

        return str;
    }

}
