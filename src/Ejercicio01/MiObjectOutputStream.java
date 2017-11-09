package Ejercicio01;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Esta clase sobreescribe a ObjectOutputStream para que el método
 * writeStreamHeader no haga nada
 * 
 * @author Joaquin Alonso Perianez
 *
 */
public class MiObjectOutputStream extends ObjectOutputStream {

	public MiObjectOutputStream(OutputStream out) throws IOException {
		super(out);
		// TODO Auto-generated constructor stub
	}

	public MiObjectOutputStream() throws SecurityException, IOException {
		super();
	}

	@Override
	protected void writeStreamHeader() throws IOException {
		// Nada
	}

}

