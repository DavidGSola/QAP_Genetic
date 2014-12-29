import java.util.ArrayList;
import java.util.Random;


public class Utils 
{

	public static ArrayList<Integer> crearPermutacion(int min, int max, int size)
	{
		Random r = new Random();
		
		ArrayList<Integer> permutacion = new ArrayList<Integer>();
		for(int i=0; i<size; i++){
			int next = r.nextInt(max-min) + min;
			while(permutacion.contains(next)){
				next = r.nextInt(max-min) + min;
			}
			permutacion.add(next);
		}

		return permutacion;
	}
}
