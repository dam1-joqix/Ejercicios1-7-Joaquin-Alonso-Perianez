package Ejercicio01;
import java.io.Serializable;

/**
 * La clase guarda los datos de un contacto que son su nombre, apellidos, email
 * y telefono la calse implementa la interfaz serializable para poder guardar
 * objetos contacto en un fichero
 * 
 * @author Joaquin Alonso Perianez
 *
 */
public class Contacto implements Serializable {

	private static final long serialVersionUID = 9060709644266410802L;

	private String nombre;
	private String apellidos;
	private String email;
	private int telefono;

	/**
	 * Constructor que establece los parámetros de la clase
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param telefono
	 */
	public Contacto(String nombre, String apellidos, String email, int telefono) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
	}

	/**
	 * Sobreescribe el método toString para mostrar la información de un contacto
	 */
	@Override
	public String toString() {

		return "Nombre: " + nombre + " Apellidos: " + apellidos + " e-mail: " + email + " Teléfono: " + telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

}
