package com.fmartinezvidal.tda;

public class Entry {
    int key;
    int value;
    Entry next;

    Entry(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
