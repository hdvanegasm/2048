package vista;

import modelo.MatrixModel;
import java.util.Scanner;

public class ConsoleOutput {
	
	private static ConsoleOutput instance = null;
	
	public static ConsoleOutput getInstance() {
		if(instance == null) {
			return new ConsoleOutput();
		} else {
			return instance;
		}
	}
	
	public void printMatrix(MatrixModel model) {
		System.out.println(model);
	}
	
	public String readKeyboard() {
		Scanner in = new Scanner(System.in);
		System.out.println("Digite el movimiento: ");
		return in.next();
	}
}
