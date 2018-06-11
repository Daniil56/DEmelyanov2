package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FirstLastList<E> implements Iterable<E> {
    private Node<E> first;
    private Node<E> last;
    private int size = 0;
    protected transient int modCount = 0;
    int  expectedModCount = 0;

    public boolean isEmpty() {
        return first == null;
    }

    public void add(Integer value) {
        Node<E> newLink = new Node<>(value);
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
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
           result = result.next;
        }
        return result.date;
    }

    public E deleteFirst() {
        Integer temp = first.date;
        this.first = first.next;
        this.size--;
        return (E) temp;
    }

    public E deleteLast() {
        Integer temp = last.date;
        this.size--;
        for (int index  = 0; index < size - 1; index++) {
            this.last = first.next;
        }
        last.next = null;

        return (E) temp;
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
         if (modCount != size) {
            throw new ConcurrentModificationException("this collection has undergone a change");
        }
     }


    @Override
    public Iterator<E> iterator() {
        expectedModCount = modCount;
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return first != null;
            }

            @Override
            public E next() {
                checkModcount();
                if (!hasNext()) {
                    throw new NoSuchElementException("no such");
                }
               Integer result = first.date;
                first = first.next;

                return (E) result;
            }
        };
    }
}
