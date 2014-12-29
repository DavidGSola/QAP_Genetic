import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Datos 
{
	private String fichero;
	private int tam;
	
	ArrayList<ArrayList<Integer>> flujos = new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> distancias = new ArrayList<ArrayList<Integer>>();
	
	public Datos(String fichero)
	{
		this.fichero = fichero;
	}
	
	public int getTam()
	{
		return tam;
	}
	
	public void leerArchivo() throws IOException
	{
		try 
		{
			Scanner sc = new Scanner(new File(fichero));
			
			// Leemos el tamaño
			if (sc.hasNextInt())
				tam = sc.nextInt();
			
			// Leemos las distancias
			for (int i = 0; i < tam; i++)
			{
				flujos.add(new ArrayList<Integer>());
				for (int j = 0; j < tam; j++)
					flujos.get(i).add(sc.nextInt());
			}
			
			// Leemos los pesos
			for (int i = 0; i < tam; i++) 
			{
				distancias.add(new ArrayList<Integer>());
				for (int j = 0; j < tam; j++)
					distancias.get(i).add(sc.nextInt());
			}
			
			sc.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
}
