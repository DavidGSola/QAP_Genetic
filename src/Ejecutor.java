import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Ejecutor 
{	
	public static void main(String[] args) 
	{
		if(args.length != 2)
		{
			System.err.println("Número de argumentos incorrectos: <fichero> <tamaño población>");
			System.exit(-1);
		}

		String fichero = args[0];
		int nPoblacion = Integer.parseInt(args[1]);
		ArrayList<Cromosoma> poblacion = new ArrayList<Cromosoma>();
		
		Datos datos = new Datos(fichero);
		try 
		{
			datos.leerArchivo();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	
		for(int i=0; i<nPoblacion; i++)
			poblacion.add(new Cromosoma(Utils.crearPermutacion(0, datos.getTam(), datos.getTam())));
		
		for(Cromosoma c : poblacion)
			Fitness.CalcularFitness(c, datos.flujos, datos.distancias);
		
		//
		Collections.sort(poblacion);
		for(Cromosoma c : poblacion)
			Fitness.CalcularFitness(c, datos.flujos, datos.distancias);
		
	
	}

}
