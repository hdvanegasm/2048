package vista;

import modelo.MatrixModel;
import java.util.Scanner;
/**
 * Clase para mostrar el juego.
 * @author Lemark
 *
 */
public class ConsoleOutput {
	
	private static ConsoleOutput instance = null;
	
	/**
	 * Metodo singleton de la consola.
	 * @return Retorna la referencia al objeto unico.
	 */
	public static ConsoleOutput getInstance() {
		if(instance == null) {
			return new ConsoleOutput();
		} else {
			return instance;
		}
	}
	
	/**
	 * Muestra por consola el estado de juego.
	 * @param model Modelo a mostrar.
	 */
	public void printMatrix(MatrixModel model) {
		System.out.println(model);
	}
	
	/**
	 * Captura la opcion de movimiento digitada por el usuario.
	 * @return Retorna la opcion ingresada.
	 */
	public String readKeyboard() {
		Scanner in = new Scanner(System.in);
		System.out.println("Digite el movimiento: ");
		return in.next();
	}
}
