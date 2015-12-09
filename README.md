# 2048
Versión del juego 2048 implementado en Java.

Esta implementación del juego 2048 se realiza con un algoritmo de movimiento que trabaja sobre la misma matriz del modelo, es decir,
no usa memoria adicional. Los números que aparecen despues de realizar un movimiento se ubican en posiciones aleatorias del tablero;
en este proceso se evitan redundancias como escoger una posición que ya está ocupada. La ubicacion de los números iniciales también se realiza de manera aleatoria y se evitan colisiones en la elección.

Dentro del juego los movimientos se ejecutan con "w" (arriba), "a" (izquierda), "s" (abajo), "d" (derecha).
