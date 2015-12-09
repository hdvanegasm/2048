package controlador;

import modelo.MatrixModel;
import vista.ConsoleOutput;

public class Game {
	
	MatrixModel matrixModel;
	
	/**
	 * Metodo constructor del juego
	 */
	public Game() {
		matrixModel = new MatrixModel();
	}
	
	/**
	 * Ciclo del juego
	 */
	public void play() {
		
		do {
			ConsoleOutput.getInstance().printMatrix(matrixModel);
			String option = ConsoleOutput.getInstance().readKeyboard();
			matrixModel.move(option);
		} while(matrixModel.continuePlaying());
	}
	
	/**
	 * Metodo principal.
	 * @param args Argumentos de consola.
	 */
	public static void main(String[] args) {
		Game juego = new Game();
		juego.play();
	}
}
