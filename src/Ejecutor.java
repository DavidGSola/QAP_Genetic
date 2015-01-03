import java.io.IOException;

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
		
		// Leemos el fichero
		Datos datos = new Datos(fichero);
		try 
		{
			datos.leerArchivo();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		AlgoritmoGenetico ag = new AlgoritmoGenetico(datos);
		ag.ejecutarAlgoritmoBasico(nPoblacion);
		ag.ejecutarAlgoritmoBaldwiniano(nPoblacion);
		ag.ejecutarAlgoritmoLamarkiano(nPoblacion);
	}

}
