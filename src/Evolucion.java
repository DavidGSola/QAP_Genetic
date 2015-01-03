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
		ArrayList<Integer> solucionHijo1 = cruzar(tamSolucion, puntoCorte1, puntoCorte2, padre.getSolucion(), madre.getSolucion());
		// En el segundo hijo se prioriza a la madre
		ArrayList<Integer> solucionHijo2 = cruzar(tamSolucion, puntoCorte1, puntoCorte2, madre.getSolucion(), padre.getSolucion());

		Cromosoma hijo1 = new Cromosoma(solucionHijo1, datos);
		Cromosoma hijo2 = new Cromosoma(solucionHijo2, datos);
		
		poblacion.add(hijo1);
		poblacion.add(hijo2);
	}
	
	/**
	 * Lleva a cabo un cruce entre dos cromosomas siguiendo la teoria baldwinana en el que se heredan 
	 * unos alelos diferentes a los mejorados. Se eligen dos puntos de corte y genera dos hijos diferentes
	 * priorizando en uno al padre y en otro a la madre. Finalmente se añaden los hijos a la población.
	 * 
	 * @param poblacion Población del problema
	 * @param datos Datos del problema
	 * @param padre Cromosoma padre en el cruce
	 * @param madre Cromosoma madre en el cruce
	 */
	public void operadorCruceBaldwiniano(ArrayList<Cromosoma> poblacion,Datos datos, Cromosoma padre, Cromosoma madre)
	{
		int tamSolucion = padre.getSolucion().size();
		Random r = new Random();
		
		// Seleccionamos los dos puntos de corte diferentes
		int puntoCorte1 = r.nextInt(tamSolucion - 1);
		int puntoCorte2 = r.nextInt(tamSolucion - puntoCorte1) + puntoCorte1;
		
		if(puntoCorte1 == puntoCorte2)
			puntoCorte2++;

		// En el primer hijo se prioriza al padre
		ArrayList<Integer> solucionHijo1 = cruzar(tamSolucion, puntoCorte1, puntoCorte2, padre.getHerencia(), madre.getHerencia());
		// En el segundo hijo se prioriza a la madre
		ArrayList<Integer> solucionHijo2 = cruzar(tamSolucion, puntoCorte1, puntoCorte2, madre.getHerencia(), padre.getHerencia());

		Cromosoma hijo1 = new Cromosoma(solucionHijo1, datos);
		Cromosoma hijo2 = new Cromosoma(solucionHijo2, datos);

		hijo1.setHerenciaASolucion();
		hijo2.setHerenciaASolucion();
		
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
		
		c.setHerenciaASolucion();
		
		// Se recalcula su fitness
		c.calcularFitness(datos);
	}
	
	/**
	 * Mejora un cromosoma utilizando un algoritmo greedy basado en 2-opt
	 * @param datos
	 * @param cromosoma
	 */
	public void greedy(Datos datos, Cromosoma cromosoma)
	{
		double best_distance = cromosoma.getFitness();
	 
        for ( int i = 0; i < cromosoma.getSolucion().size() - 1; i++ ) 
        {
            for (int k=i+1; k < cromosoma.getSolucion().size(); k++) 
            {
            	// Creamos el nuevo cromosoma a partir del cromosoma actual mejorado
            	Cromosoma c1 = new Cromosoma((ArrayList<Integer>) cromosoma.getSolucion().clone(), datos);
    		
            	// Intercambiamos los valores
            	int valueAtIndex1 = cromosoma.getSolucion().get(i);
        		int valueAtIndex2 = cromosoma.getSolucion().get(k);
        		
        		c1.getSolucion().set(i, valueAtIndex2);
        		c1.getSolucion().set(k, valueAtIndex1);
        		c1.calcularFitness(datos);

        		double new_distance = c1.getFitness();
 
        		// Si el nuevo fitness es menor que el mejor sustituimos el cromosoma
        		// original por el mejorado
                if ( new_distance < best_distance ) 
                {
                    cromosoma.setSolucion((ArrayList<Integer>) c1.getSolucion().clone());
                    cromosoma.calcularFitness(datos);
                    best_distance = new_distance;
                }
            }
        }
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
	private ArrayList<Integer> cruzar(int tamSolucion, int puntoCorte1, int puntoCorte2, ArrayList<Integer> p1, ArrayList<Integer> p2)
	{
		ArrayList<Integer> solucionHijo1 = new ArrayList<Integer>();
		
		// Se inicializa el hijo
		for(int i=0; i<tamSolucion; i++)
			solucionHijo1.add(-1);
		
		// Se copian los alelos del cromosoma p1 del intervalo en el hijo
		for(int i=puntoCorte1; i<=puntoCorte2; i++)
			solucionHijo1.set(i, p1.get(i));
		
		int aux = (puntoCorte2+1)%tamSolucion;
		int aux2 = (puntoCorte2+1)%tamSolucion;
		
		// Se copian en el hijo los alelos en el orden en el que aparecen
		// en el cromosoma p2 teniendo en cuenta que no se pueden repetir
		// valores en el hijo
		while(aux != puntoCorte1)
		{
			if(!solucionHijo1.contains(p2.get(aux2)) )
			{
				solucionHijo1.set(aux, p2.get(aux2));
				aux = (aux +1)%tamSolucion;
			}
			
			aux2 = (aux2 + 1)%tamSolucion;
		}
		
		return solucionHijo1;
	}
}
