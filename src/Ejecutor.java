import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase main que genera una población inicial y ejecuta un algoritmo genético básico,
 * otro con una variante baldwiniana y otro con una variante lamarkiana
 * @author DavidGSola
 *
 */
public class Ejecutor 
{	
	public static void main(String[] args) 
	{
		// Comprobamos que los argumentos son correctos
		if(args.length != 2)
		{
			System.err.println("Número de argumentos incorrectos: <fichero> <tamaño población>");
			System.exit(-1);
		}
		
		String fichero = args[0];
		int nPoblacion = Integer.parseInt(args[1]);
		ArrayList<Cromosoma> poblacion = new ArrayList<Cromosoma>();
		
		// Leemos el fichero
		Datos datos = new Datos(fichero);
		try 
		{
			datos.leerArchivo();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	
		// Creamos la población
		for(int i=0; i<nPoblacion; i++)
			poblacion.add(new Cromosoma(Utils.crearPermutacion(0, datos.getTam(), datos.getTam()), datos));

		AlgoritmoGenetico ag = new AlgoritmoGenetico(datos, poblacion);
		ag.ejecutarAlgoritmo();
	}

}
