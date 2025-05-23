package com.fmartinezvidal.tda;

import com.fmartinezvidal.exceptions.IndexOutBoundsException;
import com.fmartinezvidal.exceptions.IsEmptyException;
import com.fmartinezvidal.exceptions.MissMatchException;
import com.fmartinezvidal.interfaces.ILinkedList;
import com.fmartinezvidal.utils.UtilsGeneral;

public class LinkedList implements ILinkedList {
    Node head;

    @Override
    public void add(int value) {
        if (!UtilsGeneral.isNumber(String.valueOf(value))){
            throw new MissMatchException("The value must be a number");
        }

        Node newNode = new Node();
        newNode.data = value;

        // Si la lista está vacía, el nuevo nodo será la cabeza
        if (head == null) {
            head = newNode;
            return;
        }

        // Si no está vacía, recorrer hasta el último nodo
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        // Agregar el nuevo nodo al final
        current.next = newNode;
    }

    @Override
    public void insert(int index, int value) {
        if (!UtilsGeneral.isNumber(String.valueOf(value)) ){
            throw new MissMatchException("The value must be a number");
        }
        if (index < 0 || index > size()) {
            throw new IndexOutBoundsException("The index must be between 0 and " + size());
        }
        
        if (index == 0) {
            Node newNode = new Node();
            newNode.data = value;
            newNode.next = head;
            head = newNode;
            return;
        }
        
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        
        Node newNode = new Node();
        newNode.data = value;
        newNode.next = current.next;
        current.next = newNode;
    } 

    @Override
    public void remove(int index) {
        if (!UtilsGeneral.isNumber(String.valueOf(index))){
            throw new MissMatchException("The index must be a number");
        }
        if (isEmpty()) {
            throw new IsEmptyException("The list is empty");
        }
        if (index < 0 || index >= size()) {
            throw new IndexOutBoundsException("The index must be between 0 and " + (size() - 1));
        }
        
        if (index == 0) {
            // Remove the first element
            head = head.next;
            return;
        }
        
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
    }

    @Override
    public int get(int index) {
        if (isEmpty()) {
            throw new IsEmptyException("The list is empty");
        }
        if (index < 0 || index >= size()) {
            throw new IndexOutBoundsException("The index must be between 0 and " + (size() - 1));
        }
        if (!UtilsGeneral.isNumber(String.valueOf(index))){
            throw new MissMatchException("The index must be a number");
        }
        
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public int size() {
        int count = 0;
        Node current = head;  // Use a temporary variable for traversal
        while (current != null){
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}