import java.util.ArrayList;


public class Cromosoma implements Comparable<Cromosoma>
{
	private ArrayList<Integer> solucion;
	private int mFitness;

	public Cromosoma(ArrayList<Integer> solucion)
	{
		this.solucion = solucion;
		mFitness = 0;
	}
	
	public ArrayList<Integer> getSolucion() 
	{
		return solucion;
	}

	public void setSolucion(ArrayList<Integer> solucion) 
	{
		this.solucion = solucion;
	}

	public int getFitness() 
	{
		return mFitness;
	}

	public void setFitness(int mFitness) 
	{
		this.mFitness = mFitness;
	}

	@Override
	public int compareTo(Cromosoma o) 
	{
		return this.mFitness - o.mFitness;
	}
	
	@Override
	public int hashCode()
	{
		return -1;
	}
}