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
	 * Población del algoritmo genético
	 */
	private ArrayList<Cromosoma> poblacion = new ArrayList<Cromosoma>();
	
	/**
	 * Tamaño máximo de la población
	 */
	private int nPoblacion;
	
	/**
	 * Constructor por defecto que inicializa los datos y la población
	 * @param datos Datos del problema
	 * @param pob Población inicial
	 */
	public AlgoritmoGenetico(Datos datos, ArrayList<Cromosoma> pob)
	{
		this.datos = datos;
		this.poblacion = (ArrayList<Cromosoma>)pob.clone();
		nPoblacion = poblacion.size();
	}
	
	/**
	 * Ejecuta el algoritmo completo hasta cumplir la condición de parada
	 */
	public void ejecutarAlgoritmo()
	{
		// Ordenamos la solución respecto a su fitness
		Collections.sort(poblacion);
		Utils.imprimirPoblacion(poblacion);
	
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
			for(int i=0; i<poblacionAnterior; i++)
			{
				if(i != poblacionAnterior-1)
					ev.operadorCruce(poblacion, datos, poblacion.get(i), poblacion.get(i+1));
			}

			// Mutamos el 10% de los cromosomas
			for(Cromosoma c : poblacion)
			{
				double probabilidad = Math.random();
				
				if(probabilidad>0.9)
					ev.mutar(c, datos);
			}
			
			for(Cromosoma c : poblacion)
				ev.greedy(datos, c);
			
			// Orndenamos la población respecto a su fitness y 
			// eliminamos los peores hasta llegar a una población
			// con el mismo número de cromosomas que la inicial
			Collections.sort(poblacion);
			
			while(poblacion.size() != nPoblacion)
				poblacion.remove(nPoblacion);

//			System.out.println("NUEVA");
//			Utils.imprimirPoblacion(poblacion);
//			
			if(poblacion.get(0).getFitness() == mejorFitness)
				fin = true;
			
		}
		
		System.out.println("Numero de generaciones: " + generaciones);
		System.out.println("Fitness: " + poblacion.get(0).getFitness());
		Utils.imprimirSolucion(poblacion.get(0).getSolucion());
	}
}
