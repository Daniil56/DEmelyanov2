package ru.job4j.concurrency;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
@ThreadSafe
public class ConcurrencyFirstLastList<E> implements Iterable<E> {
    @GuardedBy(value = "this")
    private transient volatile Node<E> first;
    private transient volatile Node<E> last;
    private volatile int size = 0;
    private transient volatile int modCount = 0;
    transient volatile int  expectedModCount = 0;

    private  boolean isEmpty() {
        synchronized (this) {
            return first == null;
        }
    }

    public void add(E value) {
        final Node<E> newLink = new Node<>(value);
        synchronized (this) {
            synchronized (Node.class) {
                if (isEmpty()) {
                    last = newLink;
                    newLink.next = this.first;
                    this.first = newLink;
                } else {
                    last.next = newLink;
                    newLink.previous = last;
                    last = newLink;
                }
                this.size++;
                modCount++;
            }
        }
    }
    public  void insetFirst(E value) {
        synchronized (this) {
            final Node<E> newLink = new Node<>(value);
            synchronized (Node.class) {
                if (isEmpty()) {
                    this.last = newLink;
                } else {
                    first.previous = newLink;
                    newLink.next = first;
                    first = newLink;
                }
                size++;
                modCount++;
            }
        }
        }

    public synchronized E get(int index) {
        synchronized (this) {
            Node<E> result = this.first;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
            return result.date;
        }
    }

    public E deleteFirst() {
        synchronized (this) {
            E temp = first.date;
            synchronized (Node.class) {
                if (size > 1) {
                    this.first.next.previous = null;
                    this.first = first.next;
                } else {
                    this.first = null;
                    this.last = null;
                }
                this.size--;
                modCount--;
                return temp;
            }
        }
    }
    public E deleteLast() {
        synchronized (this) {
            E temp = last.date;
            synchronized (Node.class) {
                if (size > 1) {
                    this.last.previous.next = null;
                    this.last = this.last.previous;
                } else {
                    this.first = null;
                    this.last = null;
                }
                this.size--;
                modCount--;
                return temp;
            }
        }
    }

    public synchronized int getSize() {
        return size;
    }

    private final static class Node<E> {
       volatile E date;
       volatile Node<E> next;
       volatile Node<E> previous;

        Node(final E date) {
            this.date = date;
        }
    }

  private void checkModcount() {
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
                E result = first.date;
                first = first.next;

                return  result;
            }
        };
    }
}