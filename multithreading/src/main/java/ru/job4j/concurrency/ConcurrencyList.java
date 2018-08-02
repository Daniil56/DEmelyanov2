package ru.job4j.concurrency;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@ThreadSafe
public class ConcurrencyList<E> implements Iterable<E>  {
    @GuardedBy(value = "this")
    private int index = 0;
    private int count = 0;
    private transient int modCount = 0;
    private  volatile transient Object[] container;
    private final transient Object lock = new Object();

    private final Object[] getArray() {
        return container;
    }

    private final void setContainer(Object[] container) {
        this.container = container;
    }

    public ConcurrencyList() {
        setContainer(new Object[0]);
    }

    public void add(final E value) {
        synchronized (lock) {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newContainer = Arrays.copyOf(elements, len + 1);
            newContainer[len] = value;
            setContainer(newContainer);
        }
    }

    public E remove(int index) {
       synchronized (lock) {
           Object[] elements = getArray();
           int len = elements.length;
           E oldValue = elementAt(elements, index);
           int  numMoved = len - index - 1;
           if (numMoved == 0) {
               setContainer(Arrays.copyOf(elements, len - 1));
           } else {
               Object[] newContainer = new Object[len - 1];
               System.arraycopy(elements, 0, newContainer, 0, index);
               System.arraycopy(elements, index + 1, newContainer, index, numMoved);
               setContainer(newContainer);
           }
           return oldValue;
       }
    }

    @SuppressWarnings("unchecked")
   private static <E> E elementAt(Object[] a, int index) {
        return (E) a[index];
    }

    public E get(int position) {
             return elementAt(getArray(), position);
    }

    public synchronized int size() {
        return getArray().length;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        Object[] elements = getArray();

        return new Itr<>(elements, 0);
    }


    static final class  Itr<E> implements ListIterator<E> {
        private final Object[] snapshot;
        private int cursor;

        Itr(Object[] elements, int initialCurssor) {
            cursor = initialCurssor;
            snapshot = elements;
        }

        @Override
        public boolean hasNext() {
            return cursor < snapshot.length;
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return (E) snapshot[cursor++];
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            return (E) snapshot[--cursor];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }
}
