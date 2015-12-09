package modelo;

/**
 * Clase que representa una posicion en la tabla de juego.
 * @author Lemark
 *
 */
public class Pair {
	private int i;
	private int j;
	
	/**
	 * Metodo constructor de la posicion en la tabla
	 * @param i Posicion en el eje y.
	 * @param j Posicion en el eje x.
	 */
	public Pair(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	/**
	 * Obtiene la fila de la celda.
	 * @return Numero de la fila de la celda.
	 */
	public int getI(){
		return i;
	}
	
	/**
	 * Metodo que obtiene la columna de la celda.
	 * @return Numero de la fila en la celda.
	 */
	public int getJ() {
		return j;
	}
	
	/**
	 * Metodo que cambia la fila de una posicion.
	 * @param i Nueva fila de la posicion.
	 */
	public void setI(int i) {
		this.i = i;
	}
	
	/**
	 * Metodo que cambia la columna de la posicion.
	 * @param j Nueva columna de la posicion.
	 */
	public void setJ(int j) {
		this.j = j;
	}
}
