package com.fmartinezvidal.tda;

import com.fmartinezvidal.exceptions.IsEmptyException;
import com.fmartinezvidal.exceptions.MissMatchException;
import com.fmartinezvidal.interfaces.ISet;
import com.fmartinezvidal.interfaces.ISimpleDictionary;

public class SimpleDictionary implements ISimpleDictionary {
    private Entry head; // Primera entrada del diccionario

    /**
     * Constructor que inicializa un diccionario vacío
     */
    public SimpleDictionary() {
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
    public int get(int key) {
        validateNumber(key);

        if (isEmpty()) {
            throw new IsEmptyException("The dictionary is empty");
        }

        Entry entry = findEntry(key);
        if (entry == null) {
            throw new IllegalArgumentException("Key " + key + " not found in dictionary");
        }

        return entry.value;
    }

    @Override
    public void add(int key, int value) {
        validateNumber(key);
        validateNumber(value);

        // Buscar si la clave ya existe
        Entry existingEntry = findEntry(key);

        if (existingEntry != null) {
            // Si existe, reemplazar el valor (característica del diccionario simple)
            existingEntry.value = value;
        } else {
            // Si no existe, crear nueva entrada al inicio
            Entry newEntry = new Entry(key, value);
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

        // Si encontramos la entrada, la eliminamos
        if (current.next != null) {
            current.next = current.next.next;
        }
        // Si no se encuentra la clave, no hacer nada (comportamiento típico)
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
