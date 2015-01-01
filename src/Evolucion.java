import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que representa la evolución de una población. Permite realizar
 * cruce entre cromosomas y mutar un cromosoma.
 * 
 * @author DavidGSola
 *
 */
public class Evolucion 
{
	/**
	 * Constructor por defecto
	 */
	public Evolucion()
	{
		
	}
	
	/**
	 * Lleva a cabo un cruce entre dos cromosomas. Se eligen dos puntos de corte y genera dos hijos diferentes
	 * priorizando en uno al padre y en otro a la madre. Finalmente se añaden los hijos a la población.
	 * 
	 * @param poblacion Población del problema
	 * @param datos Datos del problema
	 * @param padre Cromosoma padre en el cruce
	 * @param madre Cromosoma madre en el cruce
	 */
	public void operadorCruce(ArrayList<Cromosoma> poblacion,Datos datos, Cromosoma padre, Cromosoma madre)
	{
		int tamSolucion = padre.getSolucion().size();
		Random r = new Random();
		
		// Seleccionamos los dos puntos de corte diferentes
		int puntoCorte1 = r.nextInt(tamSolucion - 1);
		int puntoCorte2 = r.nextInt(tamSolucion - puntoCorte1) + puntoCorte1;
		
		if(puntoCorte1 == puntoCorte2)
			puntoCorte2++;

		// En el primer hijo se prioriza al padre
		ArrayList<Integer> solucionHijo1 = cruzar(tamSolucion, puntoCorte1, puntoCorte2, padre, madre);
		// En el segundo hijo se prioriza a la madre
		ArrayList<Integer> solucionHijo2 = cruzar(tamSolucion, puntoCorte1, puntoCorte2, madre, padre);
		
		Cromosoma hijo1 = new Cromosoma(solucionHijo1, datos);
		Cromosoma hijo2 = new Cromosoma(solucionHijo2, datos);
		
		poblacion.add(hijo1);
		poblacion.add(hijo2);
	}
	
	/**
	 * Muta un cromosoma intercambiando el valor de dos alelos aleatorios
	 * de su solución
	 * @param c Cromosoma a mutar
	 * @param datos Datos del problema
	 */
	public void mutar(Cromosoma c, Datos datos)
	{
		Random r = new Random();
		int tamSolucion = c.getSolucion().size();
		
		// Se seleccionan los dos alelos a intercambiar
		int index1 =  r.nextInt(tamSolucion - 1);
		int index2 =  r.nextInt(tamSolucion - 1);

		int valueAtIndex1 = c.getSolucion().get(index1);
		int valueAtIndex2 = c.getSolucion().get(index2);
		
		// Se intercambian
		c.getSolucion().set(index1, valueAtIndex2);
		c.getSolucion().set(index2, valueAtIndex1);
		
		// Se recalcula su fitness
		c.calcularFitness(datos);
	}
	
	/**
	 * Genera la solución de cruzar dos cromosomas respecto a dos puntos de corte. El hijo hereda los alelos
	 * que se encuentran en el intervalo entre los dos puntos de corte de p1. El resto de alelos se colocan
	 * en orden en el que aparecen en p2 a partir del segundo punto de corte teniendo en cuenta que al ser
	 * una permutación no se pueden repetir valores.
	 * 
	 * @param tamSolucion Tamaño de la solución
	 * @param puntoCorte1 Punto de corte 1
	 * @param puntoCorte2 Punto de corte 2
	 * @param p1 Cromosoma principal
	 * @param p2 Cromosoma secundario
	 * 
	 * @return Solución generada a partir del cruce
	 */
	private ArrayList<Integer> cruzar(int tamSolucion, int puntoCorte1, int puntoCorte2, Cromosoma p1, Cromosoma p2)
	{
		ArrayList<Integer> solucionHijo1 = new ArrayList<Integer>();
		
		// Se inicializa el hijo
		for(int i=0; i<tamSolucion; i++)
			solucionHijo1.add(-1);
		
		// Se copian los alelos del cromosoma p1 del intervalo en el hijo
		for(int i=puntoCorte1; i<=puntoCorte2; i++)
			solucionHijo1.set(i, p1.getSolucion().get(i));
		
		int aux = (puntoCorte2+1)%tamSolucion;
		int aux2 = (puntoCorte2+1)%tamSolucion;
		
		// Se copian en el hijo los alelos en el orden en el que aparecen
		// en el cromosoma p2 teniendo en cuenta que no se pueden repetir
		// valores en el hijo
		while(aux != puntoCorte1)
		{
			if(!solucionHijo1.contains(p2.getSolucion().get(aux2)) )
			{
				solucionHijo1.set(aux, p2.getSolucion().get(aux2));
				aux = (aux +1)%tamSolucion;
			}
			
			aux2 = (aux2 + 1)%tamSolucion;
		}
		
		return solucionHijo1;
	}
}
