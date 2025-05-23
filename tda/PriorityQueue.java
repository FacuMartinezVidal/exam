package com.fmartinezvidal.tda;

import com.fmartinezvidal.exceptions.IsEmptyException;
import com.fmartinezvidal.exceptions.MissMatchException;
import com.fmartinezvidal.interfaces.IPriorityQueue;
import com.fmartinezvidal.utils.UtilsGeneral;

public class PriorityQueue  implements IPriorityQueue {
    // Usaremos dos LinkedList paralelas: una para los valores y otra para las prioridades
    private LinkedList values;
    private LinkedList priorities;

    public PriorityQueue() {
        values = new LinkedList();
        priorities = new LinkedList();
    }

    @Override
    public int getElement() {
        if (isEmpty()) {
            throw new IsEmptyException("The priority queue is empty");
        }
        return values.get(0);
    }

    @Override
    public int getPriority() {
        if (isEmpty()) {
            throw new IsEmptyException("The priority queue is empty");
        }
        return priorities.get(0);
    }

    @Override
    public void add(int value, int priority) {
        if (!UtilsGeneral.isNumber(String.valueOf(value))) {
            throw new MissMatchException("The value must be a number");
        }

        if (!UtilsGeneral.isNumber(String.valueOf(priority))) {
            throw new MissMatchException("The priority must be a number");
        }

        if (isEmpty()) {
            values.add(value);
            priorities.add(priority);
            return;
        }

        int position = 0;
        boolean inserted = false;

        for (int i = 0; i < priorities.size(); i++) {
            if (priorities.get(i) < priority) {
                values.insert(i, value);
                priorities.insert(i, priority);
                inserted = true;
                break;
            }
            position++;
        }

        if (!inserted) {
            values.add(value);
            priorities.add(priority);
        }
    }

    @Override
    public void remove() {
        if (isEmpty()) {
            throw new IsEmptyException("The priority queue is empty");
        }
        values.remove(0);
        priorities.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }
}
