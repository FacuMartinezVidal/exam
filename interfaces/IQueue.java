package com.fmartinezvidal.interfaces;

public interface IQueue {
    /**
     * Descripcion: Devuelve el primer elemento de la estructura. Precondición: La estructura debe tener elementos.
     */
    int getElement();

    /**
     * Descripcion: Agrega un elemento al final de la estructura. Precondición: La estructura no debe sobrepasar la
     * capacidad.
     */
    void add(int value);

    /**
     * Descripcion: Elimina el primer elemento que existe. Precondición: La estructura debe tener elementos.
     */
    void remove();

    /**
     * Descripcion: Debe comprobar si la estructura tiene o no valores. Precondición: No tiene.
     */
    boolean isEmpty();
}
