import java.util.ArrayList;
import java.util.Random;

/**
 * Clase Utils con métodos que ayudan al resto de clases
 * @author DavidGSola
 *
 */
public class Utils 
{
	/**
	 * Genera una permutación de un tamaño dado dentro de un rango.
	 * @param min Valor mínimo dentro de la permutación (incluido)
	 * @param max Valor máximo dentro de la permutación (no incluido)
	 * @param size Tamaño de la permutación
	 * @return Permutación generada
	 */
	public static ArrayList<Integer> crearPermutacion(int min, int max, int size)
	{
		Random r = new Random();
		ArrayList<Integer> permutacion = new ArrayList<Integer>();
		for(int i=0; i<size; i++)
		{
			int next = r.nextInt(max-min) + min;
			
			while(permutacion.contains(next))
				next = r.nextInt(max-min) + min;
			System.out.println(next);
			permutacion.add(next);
		}

		return permutacion;
	}
	
	/**
	 * Imprime una solución
	 * @param s Solución a imprimir
	 */
	public static void imprimirSolucion(ArrayList<Integer> s)
	{
		for(Integer i : s)
			System.out.print(i + "\t");
	}
	
	/**
	 * Imprime el fitness de cada uno de los cromosomas de una
	 * población
	 * @param poblacion Población a imprimir
	 */
	public static void imprimirPoblacion(ArrayList<Cromosoma> poblacion)
	{
		int i=0;
		for(Cromosoma c : poblacion)
			System.out.println(i++ + " " +  c.getFitness());
	}
}
