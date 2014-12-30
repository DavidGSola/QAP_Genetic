import java.util.ArrayList;
import java.util.Random;


public class Evolucion 
{
	public Evolucion()
	{
		
	}
	
	public void operadorCruce(ArrayList<Cromosoma> poblacion,Datos datos, Cromosoma padre, Cromosoma madre)
	{
		int tamSolucion = padre.getSolucion().size();
		Random r = new Random();
		
		int puntoCorte1 = r.nextInt(tamSolucion - 1);
		int puntoCorte2 = r.nextInt(tamSolucion - puntoCorte1) + puntoCorte1;
		
		if(puntoCorte1 == puntoCorte2)
			puntoCorte2++;

		ArrayList<Integer> solucionHijo1 = cruzar(tamSolucion, puntoCorte1, puntoCorte2, padre, madre);
		ArrayList<Integer> solucionHijo2 = cruzar(tamSolucion, puntoCorte1, puntoCorte2, madre, padre);
		
		Cromosoma hijo1 = new Cromosoma(solucionHijo1, datos);
		Cromosoma hijo2 = new Cromosoma(solucionHijo2, datos);

//		System.out.println("Padre");
//		System.out.println(padre.getFitness());
//		System.out.println("Madre");
//		System.out.println(madre.getFitness());
//		System.out.println("Hijo 1");
//		System.out.println(hijo1.getFitness());
//		System.out.println("Hijo 2");
//		System.out.println(hijo2.getFitness());	
		
		poblacion.add(hijo1);
		poblacion.add(hijo2);
	}
	
	private ArrayList<Integer> cruzar(int tamSolucion, int puntoCorte1, int puntoCorte2, Cromosoma p1, Cromosoma p2)
	{
		ArrayList<Integer> solucionHijo1 = new ArrayList<Integer>();
		
		for(int i=0; i<tamSolucion; i++)
			solucionHijo1.add(-1);
		
		for(int i=puntoCorte1; i<=puntoCorte2; i++)
		{
			solucionHijo1.set(i, p1.getSolucion().get(i));
		}
		
		int aux = (puntoCorte2+1)%tamSolucion;
		int aux2 = (puntoCorte2+1)%tamSolucion;
		
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
