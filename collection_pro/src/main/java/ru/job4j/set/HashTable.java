package ru.job4j.set;

public class HashTable<E> {

    private Object[] hashArray;
    private int arraySize;
    private int nonItem;
    private int count;

    public HashTable(int size) {
        this.arraySize = size;
        this.hashArray = new Object[size];
        this.nonItem = -1;
    }

    private int hashFunc(int key) {
        return key % arraySize;
    }
    public int getSize() {
        return count;
    }

    public void reSize() {
        Object[] newHashArray = new Object[hashArray.length * 2];
        int hasval = 0;
        int hasval2 = 0;
        for (int index = 0; index != count; index++) {
           hasval = hashFunc(hashArray[index].hashCode());
           hasval2 = hashArray[index].hashCode() % (hashArray.length * 2);
            newHashArray[hasval2] = hashArray[hasval];
        }
        hashArray = newHashArray;
    }

    public void add(E e) {
        if (count == hashArray.length) {
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