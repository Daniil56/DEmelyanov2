package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FirstLastList<e> implements Iterable<e> {
    private Node<e> first;
    private Node<e> last;
    private int size = 0;
    protected transient int modCount = 0;
    int  expectedModCount = 0;

    public boolean isEmpty() {
        return first == null;
    }

    public void add(Integer value) {
        Node<e> newLink = new Node<>(value);
        if (isEmpty()) {
            last = newLink;
            newLink.next = this.first;
            this.first = newLink;
        } else {
            last.next = newLink;
            last = newLink;
        }
        this.size++;
        modCount++;

    }


    public Integer get(int index) {
        Node<e> result = this.first;
        for (int i = 0; i < index; i++) {
           result = result.next;
        }
        return result.date;
    }

    public int getSize() {
        return size;
    }

    private static class Node<e> {
        Integer date;
        Node<e> next;

        Node(Integer date) {
            this.date = date;
       }
    }

    final void checkModcount() {
         if (modCount != expectedModCount) {
            throw new ConcurrentModificationException("this collection has undergone a change");
        }
     }


    @Override
    public Iterator<e> iterator() {
        expectedModCount = modCount;
        return new Iterator<e>() {
            @Override
            public boolean hasNext() {
                return first != null;
            }

            @Override
            public e next() {
                checkModcount();
                if (!hasNext()) {
                    throw new NoSuchElementException("no such");
                }
               Integer result = first.date;
                first = first.next;

                return (e) result;
            }
        };
    }
}
