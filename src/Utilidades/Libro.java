package Utilidades;

import java.io.Serializable;

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
		return "ISBN: " + isbn + " Título: " + titulo + " Autor: " + autor + " Editorial: " + editorial;
	}

}
