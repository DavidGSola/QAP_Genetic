import java.util.ArrayList;

/**
 * Cromosoma que representa una solución del problema utilizando una permutación.
 * @author DavidGSola
 *
 */
public class Cromosoma implements Comparable<Cromosoma>
{
	/**
	 * Permutación en forma de lista que representa la solución dada por este cromosoma.
	 */
	protected ArrayList<Integer> solucion;
	
	/**
	 * Permutación en forma de lista que representa la solución dada por este cromosoma.
	 */
	protected ArrayList<Integer> herencia;
	
	/**
	 * Fitness del cromosoma calculado a partir de su solución
	 */
	protected int mFitness;

	/**
	 * Constructor por defecto que le asocia una solución
	 * @param solucion
	 */
	public Cromosoma(ArrayList<Integer> solucion, Datos datos)
	{
		this.solucion = (ArrayList<Integer>)solucion.clone();
		calcularFitness(datos);
	}
	
	/**
	 * Devuelve la actual solución del cromosoma
	 * @return solución actual del cromosoma
	 */
	public ArrayList<Integer> getSolucion() 
	{
		return solucion;
	}

	/**
	 * Setea la solución del cromosoma a una nueva
	 * @param solucion Nueva solución del cromosoma
	 */
	public void setSolucion(ArrayList<Integer> solucion) 
	{
		this.solucion = solucion;
	}

	/**
	 * Devuelve el valor de fitness del cromosoma
	 * @return valor de fitness
	 */
	public int getFitness() 
	{
		return mFitness;
	}

	/**
	 * Setea el valor de fitness del cromosoma
	 * @param mFitness Nuevo falor de fitness
	 */
	public void setFitness(int mFitness) 
	{
		this.mFitness = mFitness;
	}
	
	/**
	 * Devuelve el array que heredará su hijo
	 * @return Solución que heredará el hijo
	 */
	public ArrayList<Integer> getHerencia()
	{
		return herencia;
	}

	/**
	 * Setea el array que herederán igual que el array solución.
	 * Esto es para que el algoritmo básico y larmarkiano funcionen.
	 */
	public void setHerenciaASolucion()
	{
		herencia = (ArrayList<Integer>)solucion.clone();
	}
	
	@Override
	public int compareTo(Cromosoma o) 
	{
		return this.mFitness - o.mFitness;
	}

	/**
	 * Calcula el fitness del Cromosoma
	 * @param datos Datos del problema
	 */
	public void calcularFitness(Datos datos)
	{
		mFitness = Fitness.CalcularFitness(solucion, datos.flujos, datos.distancias);
	}
}