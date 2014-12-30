import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


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

		System.out.println("ANTIGUA");
		// Ordenamos la población de acuerdo al fitness calculado anteriormente
		Collections.sort(poblacion);
		int j=0;
		for(Cromosoma c : poblacion)
			System.out.println(j++ + " " +  c.getFitness());
	
		for(int k=0; k<40; k++)
		{
			Evolucion ev = new Evolucion();
			int poblacionAnterior = poblacion.size();
			for(int i=0; i<poblacionAnterior; i=i+2)
			{
				ev.operadorCruce(poblacion, datos, poblacion.get(i), poblacion.get(i+1));
			}
			
			System.out.println("NUEVA");
			Collections.sort(poblacion);
			while(poblacion.size() != nPoblacion)
			{
				poblacion.remove(nPoblacion);
			}
			int i=0;
			for(Cromosoma c : poblacion)
				System.out.println(i++ + " " +  c.getFitness());
		}
	}

}
