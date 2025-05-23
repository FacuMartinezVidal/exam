package com.fmartinezvidal.tda;

import com.fmartinezvidal.exceptions.IsEmptyException;
import com.fmartinezvidal.exceptions.MissMatchException;
import com.fmartinezvidal.interfaces.ISet;

import java.util.Random;

public class Set implements ISet {
    private LinkedList elements;
    private Random random;

    public Set() {
        this.elements = new LinkedList();
        this.random = new Random();
    }

    /**
     * Valida que el valor esté dentro del rango permitido para números enteros
     * y que no sea un valor especial como NaN (simulado)
     */
    private boolean isValidNumber(int value) {
        // Verificar que el valor esté en el rango válido de enteros
        // y no sea un valor "especial" que podríamos considerar inválido
        return value != Integer.MIN_VALUE && value != Integer.MAX_VALUE;
    }

    /**
     * Valida que el valor sea un número válido para nuestro conjunto
     */
    private void validateNumber(int value) {
        if (!isValidNumber(value)) {
            throw new MissMatchException("The value is not a valid number for this set");
        }
    }

    @Override
    public boolean exist(int value) {
        validateNumber(value);

        // Recorrer la lista para verificar si el elemento existe
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i) == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int choose() {
        if (isEmpty()) {
            throw new IsEmptyException("The set is empty");
        }

        // Generar un índice aleatorio y devolver el elemento en esa posición
        int randomIndex = random.nextInt(elements.size());
        return elements.get(randomIndex);
    }

    @Override
    public void add(int value) {
        validateNumber(value);

        // Solo agregar si el elemento no existe (característica del conjunto)
        if (!exist(value)) {
            elements.add(value);
        }
        // Si ya existe, no hacer nada (comportamiento típico de un conjunto)
    }

    @Override
    public void remove(int element) {
        validateNumber(element);

        if (isEmpty()) {
            throw new IsEmptyException("The set is empty");
        }

        // Buscar el elemento y eliminarlo si existe
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i) == element) {
                elements.remove(i);
                return; // Salir después de eliminar (solo debería haber uno)
            }
        }
        // Si no se encuentra el elemento, no hacer nada
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

}
