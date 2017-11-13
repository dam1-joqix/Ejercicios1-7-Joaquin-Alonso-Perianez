package Utilidades;

import java.io.Serializable;

/**
 * Esta clase sirve para guardar la informacion de un libro
 * 
 * @author Joaquin Alonso Perianez
 *
 */
public class Libro implements Serializable {

	private static final long serialVersionUID = -1993349967265800892L;
	public String isbn;
	public String titulo;
	public String autor;
	public String editorial;

	public Libro(String isbn, String titulo, String autor, String editorial) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
	}

	@Override
	public String toString() {
		return "ISBN: " + isbn + " T�tulo: " + titulo + " Autor: " + autor + " Editorial: " + editorial;
	}

}
