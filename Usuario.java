/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestorbiblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zakar
 */
public class Usuario {

    private String nombre;
    private String idUsuario;

    private List<Libro> librosPrestados;

    //constructor
    public Usuario(String nombre, String idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.librosPrestados = new ArrayList();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void agregarLibro(Libro libro) {
        if (this.librosPrestados.size() < 3) {
            this.librosPrestados.add(libro);
            libro.prestar();
        }
    }

    public void devolverLibro(Libro libro) {
        this.librosPrestados.remove(libro);
        libro.devolver();
    }

    public void mostrarUsuario() {
        System.out.println(this.nombre + "\t\t" + this.idUsuario + "\t\t" + this.librosPrestados.size());
    }

    public String getIdUsuario() {
        return idUsuario;
    }
    
    public int getNumLibrosPrestados(){
        return this.librosPrestados.size();
    }

}
