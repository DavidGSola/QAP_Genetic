import java.util.ArrayList;


public class Fitness 
{
	public static void CalcularFitness(Cromosoma cromosoma, ArrayList<ArrayList<Integer>> flujos, ArrayList<ArrayList<Integer>> distancias)
	{
		ArrayList<Integer> solucion = cromosoma.getSolucion();
		int fitness = 0;
		
		for(int i=0; i<solucion.size()-1; i++)
		{
			int fabrica1 = solucion.get(i);
			int fabrica2 = solucion.get(i+1);
			
			int flujo = flujos.get(fabrica1).get(fabrica2);
			int distancia = distancias.get(fabrica1).get(fabrica2);
			
			fitness += flujo*distancia;
		}
		
		System.out.println(fitness);
		
		cromosoma.setFitness(fitness);
	}
	
	
}
