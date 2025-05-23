package com.fmartinezvidal.tda;

import com.fmartinezvidal.exceptions.IsEmptyException;
import com.fmartinezvidal.exceptions.MissMatchException;
import com.fmartinezvidal.interfaces.ILinkedList;
import com.fmartinezvidal.interfaces.IStack;
import com.fmartinezvidal.utils.UtilsGeneral;

public class Stack implements IStack {
    private final ILinkedList data;
    public Stack() {
        data = new LinkedList();
    }

    @Override
    public int getElement() {
        if (data.isEmpty()){
            throw new IsEmptyException("Stack is empty");
        }else {
            return data.get(data.size() - 1);
        }
    }

    @Override
    public void add(int value) {
        if (!UtilsGeneral.isNumber(String.valueOf(value))){
            throw new MissMatchException("Value must be integer");
        }
        if (data.isEmpty()){
            data.add(value);
        }else {
            data.insert(data.size(), value);
        }
    }

    @Override
    public void remove() {
        if (data.isEmpty()){
            throw new IsEmptyException("Stack is empty");
        }else {
            data.remove(data.size() - 1);
        }

    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
