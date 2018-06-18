package ru.job4j.set;

import java.util.Arrays;
import java.util.Objects;

public class HashTable<E> {

    private Object[] hashArray;
    private int arraySize;
    private int nonItem;
    private int count;
    int index = 0;


    public HashTable(int size) {
        this.arraySize = size;
        this.hashArray = new Object[arraySize];
        this.nonItem = -1;
    }

    public int hashFunc(int key) {
        return key % arraySize;
    }
    public int size() {
        return count;
    }

    public void reSize() {
        int temp = arraySize;
        arraySize = arraySize * 2;
        Object[] newHashArray = new Object[arraySize];
        int hasval2 = 0;
        int hasval = hashFunc(hashArray[index].hashCode());

        for (int index = 0; index != count; index++) {

            newHashArray[hasval++] = hashArray[index];

        }
        hashArray = newHashArray;

    }


    public void add(E e) {
        if (count >= hashArray.length - 1) {
            reSize();
        }
        int key =  e.hashCode();
        int hashVal = hashFunc(key);


        while (hashArray[hashVal] != null) {
            ++hashVal;
            hashVal %= arraySize;
        }
        hashArray[hashVal] = e;
        count++;
    }

    private boolean isContains(E e) {
        int hashval = hashFunc(e.hashCode());
        return hashArray[hashval].hashCode() == e.hashCode();
    }

    public boolean remove(E e) {
       boolean result = false;
       int hashval = hashFunc(e.hashCode());

           if (isContains(e)) {
               hashArray[hashval] = nonItem;
               result = true;
           }

       return result;
    }

    public boolean conrains(E e) {
        boolean result = false;

        if (isContains(e)) {
            result = true;
        }

        return result;

    }

}
