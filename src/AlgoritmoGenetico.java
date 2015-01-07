import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que representa un algoritmo genético básico.
 * @author DavidGSola
 *
 */
public class AlgoritmoGenetico 
{
	/**
	 * Datos del problema
	 */
	private Datos datos;
	
	/**
	 * Constructor por defecto que inicializa los datos y la población
	 * @param datos Datos del problema
	 * @param pob Población inicial
	 */
	public AlgoritmoGenetico(Datos datos)
	{
		this.datos = datos;
	}
	
	/**
	 * Ejecuta el algoritmo evolutivo básico
	 * hasta cumplir la condición de parada
	 * 
	 * @param nPoblacion Tamaño de la población
	 */
	public void ejecutarAlgoritmoBasico(int nPoblacion)
	{
		System.out.println();
		System.out.println();
		System.out.println("COMIENZO DEL ALGORITMO BÁSICO");
		
		ArrayList<Cromosoma> poblacion = new ArrayList<Cromosoma>();
		
		// Creamos la población
		for(int i=0; i<nPoblacion; i++)
		{
			poblacion.add(new Cromosoma(Utils.crearPermutacion(0, datos.getTam(), datos.getTam()), datos));
			poblacion.get(i).setHerenciaASolucion();
		}

		nPoblacion = poblacion.size();
		
		// Ordenamos la solución respecto a su fitness
		Collections.sort(poblacion);
	
		Evolucion ev = new Evolucion();
		
		boolean fin = false;
		int generaciones = 0;
		while(!fin)
		{
			generaciones++;
			// Fijamos el tamaño de la población
			int poblacionAnterior = poblacion.size();

			int mejorFitness = poblacion.get(0).getFitness();
			
			// Cruzamos los mejores con los mejores
			for(int i=0; i<poblacionAnterior-1; i++)
				ev.operadorCruce(poblacion, datos, poblacion.get(i), poblacion.get(i+1));

			// Mutamos el 10% de los cromosomas
			for(int i=100; i<poblacion.size(); i++)
			{
				double probabilidad = Math.random();
				if(probabilidad>0.2)
					ev.mutar(poblacion.get(i), datos);
			}
			
			// Ordenamos la población respecto a su fitness y 
			// eliminamos los peores hasta llegar a una población
			// con el mismo número de cromosomas que la inicial
			Collections.sort(poblacion);
			
			while(poblacion.size() != nPoblacion)
				poblacion.remove(nPoblacion);
			
			if(poblacion.get(0).getFitness() >= mejorFitness)
				fin = true;
			
			System.out.println();
			System.out.println("Generación: " + generaciones);
			System.out.println("Fitness: " + poblacion.get(0).getFitness());
			Utils.imprimirSolucion(poblacion.get(0).getSolucion());
		}

		System.out.println();
		System.out.println();
		System.out.println("RESULTADOS PARA EL ALGORITMO BÁSICO:");
		System.out.println("Generación: " + generaciones);
		System.out.println("Fitness: " + poblacion.get(0).getFitness());
		Utils.imprimirSolucion(poblacion.get(0).getSolucion());
	}
	
	/**
	 * Ejecuta el algoritmo de la variante Baldwiniana 
	 * hasta cumplir la condición de parada
	 * 
	 * @param nPoblacion Tamaño de la población
	 */
	public void ejecutarAlgoritmoBaldwiniano(int nPoblacion)
	{
		System.out.println();
		System.out.println();
		System.out.println("COMIENZO DE LA VARIANTE BALDWINIANA");
		
		// Creamos la población
		ArrayList<Cromosoma> poblacion = new ArrayList<Cromosoma>();
		for(int i=0; i<nPoblacion; i++)
		{
			poblacion.add(new Cromosoma(Utils.crearPermutacion(0, datos.getTam(), datos.getTam()), datos));
			poblacion.get(i).setHerenciaASolucion();
		}
		
		// Ordenamos la solución respecto a su fitness
		Collections.sort(poblacion);
	
		Evolucion ev = new Evolucion();
		
		boolean fin = false;
		int generaciones = 0;
		while(!fin)
		{
			generaciones++;
			// Fijamos el tamaño de la población
			int poblacionAnterior = poblacion.size();

			int mejorFitness = poblacion.get(0).getFitness();
			
			// Cruzamos los mejores con los mejores
			for(int i=0; i<poblacionAnterior-1; i++)
				ev.operadorCruce(poblacion, datos, poblacion.get(i), poblacion.get(i+1));

			// Mutamos el 10% de los cromosomas
			for(int i=100; i<poblacion.size(); i++)
			{
				double probabilidad = Math.random();
				if(probabilidad>0.2)
					ev.mutar(poblacion.get(i), datos);
			}
			
			// Realizamos Greedy sobre toda la población
			for(Cromosoma c : poblacion)
				ev.greedy(datos, c);
			
			
			// Ordenamos la población respecto a su fitness y 
			// eliminamos los peores hasta llegar a una población
			// con el mismo número de cromosomas que la inicial
			Collections.sort(poblacion);
			
			while(poblacion.size() != nPoblacion)
				poblacion.remove(nPoblacion);
			
			if(poblacion.get(0).getFitness() >= mejorFitness)
				fin = true;
			
			System.out.println();
			System.out.println("Generación: " + generaciones);
			System.out.println("Fitness: " + poblacion.get(0).getFitness());
			Utils.imprimirSolucion(poblacion.get(0).getSolucion());
		}

		System.out.println();
		System.out.println();
		System.out.println("RESULTADOS PARA LA VARIANTE BALDWINIANA");
		System.out.println("Generación: " + generaciones);
		System.out.println("Fitness: " + poblacion.get(0).getFitness());
		Utils.imprimirSolucion(poblacion.get(0).getSolucion());
	}
	
	/**
	 * Ejecuta el algoritmo de la variante lamarciana 
	 * hasta cumplir la condición de parada
	 * 
	 * @param nPoblacion Tamaño de la población
	 */
	public void ejecutarAlgoritmoLamarckiano(int nPoblacion)
	{
		System.out.println();
		System.out.println();
		System.out.println("COMIENZO DE LA VARIANTE LAMARCKIANA");
		
		// Creamos la población
		ArrayList<Cromosoma> poblacion = new ArrayList<Cromosoma>();
		for(int i=0; i<nPoblacion; i++)
		{
			poblacion.add(new Cromosoma(Utils.crearPermutacion(0, datos.getTam(), datos.getTam()), datos));
			poblacion.get(i).setHerenciaASolucion();
		}

		nPoblacion = poblacion.size();
		
		// Ordenamos la solución respecto a su fitness
		Collections.sort(poblacion);
	
		Evolucion ev = new Evolucion();
		
		boolean fin = false;
		int generaciones = 0;
		while(!fin)
		{
			generaciones++;
			
			// Fijamos el tamaño de la población
			int poblacionAnterior = poblacion.size();

			int mejorFitness = poblacion.get(0).getFitness();
			
			// Cruzamos los mejores con los mejores
			for(int i=0; i<poblacionAnterior-1; i++)
				ev.operadorCruce(poblacion, datos, poblacion.get(i), poblacion.get(i+1));

			// Mutamos el 0% de los cromosomas
			for(int i=100; i<poblacion.size(); i++)
			{
				double probabilidad = Math.random();
				if(probabilidad>0.2)
					ev.mutar(poblacion.get(i), datos);
			}

			// Realizamos Greedy sobre toda la población
			for(Cromosoma c : poblacion)
				ev.greedy(datos, c);
			
			// Ordenamos la población respecto a su fitness y 
			// eliminamos los peores hasta llegar a una población
			// con el mismo número de cromosomas que la inicial
			Collections.sort(poblacion);
			
			while(poblacion.size() != nPoblacion)
				poblacion.remove(nPoblacion);
			
			if(poblacion.get(0).getFitness() >= mejorFitness)
				fin = true;
			
			System.out.println();
			System.out.println("Generación: " + generaciones);
			System.out.println("Fitness: " + poblacion.get(0).getFitness());
			Utils.imprimirSolucion(poblacion.get(0).getSolucion());
		}
		
		System.out.println();
		System.out.println();
		System.out.println("RESULTADOS PARA LA VARIANTE LAMARCKIANA:");
		System.out.println("Generación: " + generaciones);
		System.out.println("Fitness: " + poblacion.get(0).getFitness());
		Utils.imprimirSolucion(poblacion.get(0).getSolucion());
	}
}
