import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase encargada de leer los datos de entrada y almanacenarlos para su posterior uso
 * @author DavidGSola
 *
 */
public class Datos 
{
	/**
	 * Nombre del fichero
	 */
	private String fichero;
	
	/**
	 * Tamaño del problema
	 */
	private int tam;
	
	/**
	 * Matriz de flujos
	 */
	ArrayList<ArrayList<Integer>> flujos = new ArrayList<ArrayList<Integer>>();
	
	/**
	 * Matriz de distancias
	 */
	ArrayList<ArrayList<Integer>> distancias = new ArrayList<ArrayList<Integer>>();
	
	/**
	 * Constructor por defecto con el nombre del archivo
	 * @param fichero Nombre del archivo donde se encuentran los datos
	 */
	public Datos(String fichero)
	{
		this.fichero = fichero;
	}
	
	/**
	 * Get de la variable tam
	 * @return tamaño de la población
	 */
	public int getTam()
	{
		return tam;
	}
	
	/**
	 * Lee el archivo, inicializa la variable tam y almacena los datos en las matrices.
	 * @throws IOException
	 */
	public void leerArchivo() throws IOException
	{
		try 
		{
			Scanner sc = new Scanner(new File(fichero));
			
			// Leemos el tamaño
			if (sc.hasNextInt())
				tam = sc.nextInt();
			
			// Leemos las flujos
			for (int i = 0; i < tam; i++)
			{
				flujos.add(new ArrayList<Integer>());
				for (int j = 0; j < tam; j++)
					flujos.get(i).add(sc.nextInt());
			}
			
			// Leemos los distancias
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
