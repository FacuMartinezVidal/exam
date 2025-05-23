package com.fmartinezvidal.tda;

import com.fmartinezvidal.exceptions.IsEmptyException;
import com.fmartinezvidal.exceptions.MissMatchException;
import com.fmartinezvidal.interfaces.IMultipleDictionary;
import com.fmartinezvidal.interfaces.ISet;

public class MultipleDictionary implements IMultipleDictionary {
    // Clase interna para representar una entrada clave-valores múltiples
    private static class Entry {
        int key;
        LinkedList values; // Lista de valores para esta clave
        Entry next;

        Entry(int key) {
            this.key = key;
            this.values = new LinkedList();
            this.next = null;
        }
    }

    private Entry head; // Primera entrada del diccionario

    /**
     * Constructor que inicializa un diccionario múltiple vacío
     */
    public MultipleDictionary() {
        this.head = null;
    }

    /**
     * Valida que el valor esté dentro del rango permitido
     */
    private void validateNumber(int value) {
        if (value == Integer.MIN_VALUE || value == Integer.MAX_VALUE) {
            throw new MissMatchException("The value is not valid for this dictionary");
        }
    }

    /**
     * Busca una entrada por su clave
     * @param key la clave a buscar
     * @return la entrada si se encuentra, null en caso contrario
     */
    private Entry findEntry(int key) {
        Entry current = head;
        while (current != null) {
            if (current.key == key) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Convierte una LinkedList a un array de enteros
     * @param list la lista a convertir
     * @return array con los elementos de la lista
     */
    private int[] listToArray(LinkedList list) {
        if (list.isEmpty()) {
            return new int[0];
        }

        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    @Override
    public ISet getKeys() {
        ISet keys = new Set();
        Entry current = head;

        while (current != null) {
            keys.add(current.key);
            current = current.next;
        }

        return keys;
    }

    @Override
    public int[] get(int key) {
        validateNumber(key);

        if (isEmpty()) {
            throw new IsEmptyException("The dictionary is empty");
        }

        Entry entry = findEntry(key);
        if (entry == null) {
            throw new IllegalArgumentException("Key " + key + " not found in dictionary");
        }

        return listToArray(entry.values);
    }

    @Override
    public void add(int key, int value) {
        validateNumber(key);
        validateNumber(value);

        // Buscar si la clave ya existe
        Entry existingEntry = findEntry(key);

        if (existingEntry != null) {
            // Si existe, agregar el valor a la lista existente
            existingEntry.values.add(value);
        } else {
            // Si no existe, crear nueva entrada
            Entry newEntry = new Entry(key);
            newEntry.values.add(value);
            newEntry.next = head;
            head = newEntry;
        }
    }

    @Override
    public void remove(int key) {
        validateNumber(key);

        if (isEmpty()) {
            throw new IsEmptyException("The dictionary is empty");
        }

        // Caso especial: eliminar el primer elemento
        if (head.key == key) {
            head = head.next;
            return;
        }

        // Buscar la entrada anterior a la que queremos eliminar
        Entry current = head;
        while (current.next != null && current.next.key != key) {
            current = current.next;
        }

        // Si encontramos la entrada, la eliminamos completamente
        if (current.next != null) {
            current.next = current.next.next;
        }
        // Si no se encuentra la clave, no hacer nada
    }

    @Override
    public void remove(int key, int value) {
        validateNumber(key);
        validateNumber(value);

        if (isEmpty()) {
            throw new IsEmptyException("The dictionary is empty");
        }

        Entry entry = findEntry(key);
        if (entry != null) {
            // Buscar y eliminar el valor específico de la lista
            for (int i = 0; i < entry.values.size(); i++) {
                if (entry.values.get(i) == value) {
                    entry.values.remove(i);
                    break; // Solo eliminar la primera ocurrencia
                }
            }

            // Si la lista queda vacía después de eliminar el valor,
            // eliminar toda la entrada
            if (entry.values.isEmpty()) {
                remove(key);
            }
        }
        // Si no se encuentra la clave o el valor, no hacer nada
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
