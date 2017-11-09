package Utilidades;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
/**
 * Esta clase se usa como sustituta a ObjectOutputStream para que no escriba cabeceras
 * @author Joaquin Alonso Periánez
 *
 */
public class MiObjectOutputStream extends ObjectOutputStream{

	/**
	 * Constructor de la superclase
	 * @param out
	 * @throws IOException
	 */
	public MiObjectOutputStream(OutputStream out) throws IOException {
		super(out);
		
	}
	/**
	 * Constructor de la superclase
	 * @throws IOException
	 * @throws SecurityException
	 */
	protected MiObjectOutputStream() throws IOException, SecurityException {
		super();
		
	}
	
	@Override
	/**
	 * Se sobreescribe el método para que no haga nada,
	 *así no tendremos problemas de cabeceras duplicadas al escribir varias veces en el mismo fichero
	 */
	protected void writeStreamHeader() throws IOException {
		// NADA
		
	}

	

}
