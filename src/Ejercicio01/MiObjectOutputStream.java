package Ejercicio01;

import java.io.*;

/**
 * Esta clase sobreescribe a ObjectOutputStream para que el m�todo
 * writeStreamHeader no haga nada
 * 
 * @author Joaquin Alonso Perianez
 *
 */
public class MiObjectOutputStream extends ObjectOutputStream {

	public MiObjectOutputStream(OutputStream out) throws IOException {
		super(out);
		
	}

	public MiObjectOutputStream() throws SecurityException, IOException {
		super();
	}

	@Override
	protected void writeStreamHeader() throws IOException {
		// Nada
	}

}

