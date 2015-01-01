import java.util.ArrayList;


/**
 * Clase encarga de calcular el fitness.
 * @author DavidGSola
 *
 */
public class Fitness 
{
	/**
	 * Calcula y setea el fitness de un cromosoma dado utilizando las matrices de flujos y distancias
	 * @param cromosoma Cromosoma del cual se quiere calcular el fitness
	 * @param flujos Matriz de flujos
	 * @param distancias Matriz de distancias
	 */
	public static int CalcularFitness(ArrayList<Integer>  solucion, ArrayList<ArrayList<Integer>> flujos, ArrayList<ArrayList<Integer>> distancias)
	{
		int fitness = 0;
		
//		for(int i=0; i<solucion.size()-1; i++)
//		{
//			int fabrica1 = solucion.get(i);
//			int fabrica2 = solucion.get(i+1);
//			
//			int flujo = flujos.get(fabrica1).get(fabrica2);
//			int distancia = distancias.get(i).get(i+1);
//			
//			fitness += flujo*distancia;
//		}
		
		for (int i = 0; i < solucion.size(); i++) 
			for (int l = 0; l < solucion.size(); l++) 
				if (i != l) 
				{
					int fabrica1 = solucion.get(i);
					int fabrica2 = solucion.get(l);
					fitness += (distancias.get(i).get(l) * flujos.get(fabrica1).get(fabrica2));
				}

		return fitness;
	}
	
	
}
