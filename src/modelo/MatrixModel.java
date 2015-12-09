package modelo;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Clase que modela el juego, toma en cuenta tanto el "score" como el estado de la matriz
 * @author Lemark
 *
 */
public class MatrixModel {

	private int[][] matrix;
	private int score;

	private int[] randomArray = { 2, 4 };

	/**
	 * Metodo constructor del modelo de juego. Escoge dos posiciones aleatorias
	 * y las llena con numeros potencia de dos.
	 */
	public MatrixModel() {
		score = 0;
		matrix = new int[4][4];

		// Construccion de las potencias de 2 aleatorias
		int potencia1 = (int) (Math.random() + 1);
		int potencia2 = (int) (Math.random() + 1);

		int i1 = (int) (Math.random() * 4);
		int j1 = (int) (Math.random() * 4);
		int i2 = (int) (Math.random() * 4);
		int j2 = (int) (Math.random() * 4);

		while (i1 == i2 && j1 == j2) {
			i1 = (int) (Math.random() * 4);
			j1 = (int) (Math.random() * 4);
			i2 = (int) (Math.random() * 4);
			j2 = (int) (Math.random() * 4);
		}

		matrix[i1][j1] = (int) (Math.pow(2, (double) potencia1));
		matrix[i2][j2] = (int) (Math.pow(2, (double) potencia2));
	}

	/**
	 * Metodo que modifica a la matriz.
	 * @param matrix Matriz nueva.
	 */
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	/**
	 * Metodo para obtener la matriz
	 * @return Retorna la matriz en su estado actual.
	 */
	public int[][] getMatrix() {
		return this.matrix;
	}

	/**
	 * Cambia el puntaje del juego.
	 * @param newScore Nuevo puntaje para modificar.
	 */
	public void setScore(int newScore) {
		score = newScore;
	}

	/**
	 * Obtiene el puntaje actual del juego.
	 * @return Retorna el puntaje del juego.
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Metodo que evalua la matriz para ver si hay movimientos posibles.
	 * 
	 * @return Retorna "true" cuando hay al menos una jugada valida, de lo
	 *         contrario retorna "false".
	 */
	public boolean continuePlaying() {
		for (int i = 0; i < 3; i++) {
			if (matrix[i][0] == 0 || matrix[i][1] == 0 || matrix[i][0] == matrix[i][1]) {
				return true;
			}
		}
		for (int j = 2; j <= 3; j++) {
			for (int i = 0; i <= 2; i++) {
				if (matrix[i][j - 1] == 0 || matrix[i][j] == 0 || matrix[i][j - 1] == matrix[i][j]) {
					return true;
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			if (matrix[3][i] == matrix[3][i + 1] || matrix[i][3] == matrix[i + 1][3]) {
				return true;
			}
		}
		if(matrix[3][3] == 0){
			return true;
		}
		return false;
	}

	/**
	 * Metodo que realiza un movimiento dentro de la matriz. W - arriba, D -
	 * derecha, S - abajo, A - izquierda. Tambien genera un 2 o un 4 en una posicion aleatoria vacia.
	 * 
	 * @param option
	 *            Opcion de entrada por teclado.
	 */
	public void move(String option) {
		switch (option) {
		case "a":
			movimientoIzquierda();
			break;
		case "s":
			movimientoAbajo();
			break;
		case "d":
			movimientoDerecha();
			break;
		case "w":
			movimientoArriba();
			break;
		}
		Pair emptyPosition = getRandomEmptyPosition();

		int randomValue = randomArray[(int) (Math.random())];

		matrix[emptyPosition.getI()][emptyPosition.getJ()] = randomValue;

	}

	/**
	 * Metodo que retorna una posicion vacia dentro de la matriz de manera aleatoria.
	 * @return Coordenadas de la celda vacia.
	 */
	private Pair getRandomEmptyPosition() {
		ArrayList<Pair> emptyPositions = new ArrayList();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (matrix[i][j] == 0) {
					Pair emptyPosition = new Pair(i, j);
					emptyPositions.add(emptyPosition);
				}
			}
		}

		int randomPosition = (int) (Math.random() * (emptyPositions.size() - 1));
		return emptyPositions.get(randomPosition);
	}

	/**
	 * Movimiento arriba en el juego.
	 */
	private void movimientoArriba() {
		for (int i = 0; i < 4; i++) {
			int posicion = 0;
			int comparador = 1;
			int cursor = 0;
			while (posicion <= 3 && comparador < 4) {
				if (posicion < 3 && matrix[posicion][i] == matrix[comparador][i] && matrix[posicion][i] != 0
						&& matrix[comparador][i] != 0) {
					int suma = matrix[comparador][i] + matrix[posicion][i];
					matrix[posicion][i] = 0;
					matrix[comparador][i] = 0;
					matrix[cursor][i] = suma;
					cursor++;
					posicion = comparador + 1;
					comparador += 2;
					setScore(getScore() + suma);
				} else if (matrix[posicion][i] == 0) {
					posicion++;
					comparador++;
				} else if (matrix[comparador][i] == 0) {
					comparador++;
				} else { // Ambos son diferentes de cero y diferentes entre
							// si
					int aux = matrix[posicion][i];
					matrix[posicion][i] = 0;
					matrix[cursor][i] = aux;
					cursor++;
					posicion = comparador;
					comparador++;
				}
			}
			if (posicion <= 3) {
				int aux = matrix[posicion][i];
				matrix[posicion][i] = 0;
				matrix[cursor][i] = aux;

			}
		}
	}

	/**
	 * Movimiento abajo en el juego.
	 */
	private void movimientoIzquierda() {
		for (int i = 0; i < 4; i++) {
			int posicion = 0;
			int comparador = 1;
			int cursor = 0;
			while (posicion <= 3 && comparador < 4) {
				if (posicion < 3 && matrix[i][posicion] == matrix[i][comparador] && matrix[i][posicion] != 0
						&& matrix[i][comparador] != 0) {
					int suma = matrix[i][comparador] + matrix[i][posicion];
					matrix[i][posicion] = 0;
					matrix[i][comparador] = 0;
					matrix[i][cursor] = suma;
					cursor++;
					posicion = comparador + 1;
					comparador += 2;
					setScore(getScore() + suma);
				} else if (matrix[i][posicion] == 0) {
					posicion++;
					comparador++;
				} else if (matrix[i][comparador] == 0) {
					comparador++;
				} else { // Ambos son diferentes de cero y diferentes entre
							// si
					int aux = matrix[i][posicion];
					matrix[i][posicion] = 0;
					matrix[i][cursor] = aux;
					cursor++;
					posicion = comparador;
					comparador++;
				}
			}
			if (posicion <= 3) {
				int aux = matrix[i][posicion];
				matrix[i][posicion] = 0;
				matrix[i][cursor] = aux;

			}
		}
	}

	/**
	 * Movimiento derecha en el juego.
	 */
	private void movimientoDerecha() {
		for (int i = 0; i < 4; i++) {
			int posicion = 3;
			int comparador = 2;
			int cursor = 3;
			while (posicion >= 0 && comparador > -1) {
				if (posicion > 0 && matrix[i][posicion] == matrix[i][comparador] && matrix[i][posicion] != 0
						&& matrix[i][comparador] != 0) {
					int suma = matrix[i][comparador] + matrix[i][posicion];
					matrix[i][posicion] = 0;
					matrix[i][comparador] = 0;
					matrix[i][cursor] = suma;
					cursor--;
					posicion = comparador - 1;
					comparador -= 2;
					setScore(getScore() + suma);
				} else if (matrix[i][posicion] == 0) {
					posicion--;
					comparador--;
				} else if (matrix[i][comparador] == 0) {
					comparador--;
				} else { // Ambos son diferentes de cero y diferentes entre si
					int aux = matrix[i][posicion];
					matrix[i][posicion] = 0;
					matrix[i][cursor] = aux;
					cursor--;
					posicion = comparador;
					comparador--;
				}
			}
			if (posicion >= 0) {
				int aux = matrix[i][posicion];
				matrix[i][posicion] = 0;
				matrix[i][cursor] = aux;

			}
		}
	}

	/**
	 * Movimiento izquierda en el juego.
	 */
	private void movimientoAbajo() {
		for (int i = 0; i < 4; i++) {
			int posicion = 3;
			int comparador = 2;
			int cursor = 3;
			while (posicion >= 0 && comparador > -1) {
				if (posicion > 0 && matrix[posicion][i] == matrix[comparador][i] && matrix[posicion][i] != 0
						&& matrix[comparador][i] != 0) {
					int suma = matrix[comparador][i] + matrix[posicion][i];
					matrix[posicion][i] = 0;
					matrix[comparador][i] = 0;
					matrix[cursor][i] = suma;
					cursor--;
					posicion = comparador - 1;
					comparador -= 2;
					setScore(getScore() + suma);
				} else if (matrix[posicion][i] == 0) {
					posicion--;
					comparador--;
				} else if (matrix[comparador][i] == 0) {
					comparador--;
				} else { // Ambos son diferentes de cero y diferentes entre si
					int aux = matrix[posicion][i];
					matrix[posicion][i] = 0;
					matrix[cursor][i] = aux;
					cursor--;
					posicion = comparador;
					comparador--;
				}
			}
			if (posicion >= 0) {
				int aux = matrix[posicion][i];
				matrix[posicion][i] = 0;
				matrix[cursor][i] = aux;

			}
		}
	}

	/**
	 * Metodo toString para mostrar a la interfaz de consola.
	 */
	@Override
	public String toString() {
		StringBuffer stringPrint = new StringBuffer();

		for (int i = 0; i < 4; i++) {
			stringPrint.append("[ ");
			for (int j = 0; j < 4; j++) {
				stringPrint.append(matrix[i][j] != 0 ? matrix[i][j] + " " : "  ");
			}
			stringPrint.append("]\n");
		}
		stringPrint.append("(Score: " + score + ")");
		return stringPrint.toString();
	}
}
