package com.fmartinezvidal.tda;

import com.fmartinezvidal.exceptions.IsEmptyException;
import com.fmartinezvidal.exceptions.MissMatchException;
import com.fmartinezvidal.interfaces.ILinkedList;
import com.fmartinezvidal.interfaces.IQueue;
import com.fmartinezvidal.utils.UtilsGeneral;

public class Queue implements IQueue {
    ILinkedList data;

    public Queue() {
        data = new LinkedList();
    }

    @Override
    public int getElement() {
        if (data.isEmpty()){
            throw new IsEmptyException("Queue is empty");
        }
        return data.get(0);
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
            throw new IsEmptyException("Queue is empty");
        }else {
            data.remove(0);
        }
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
