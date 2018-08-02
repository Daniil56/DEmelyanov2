package ru.job4j.concurrency;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

@ThreadSafe
public class ConcurrencyFirstLastList<E> implements Iterable<E>  {
    @GuardedBy(value = "this")
    private final Object lock = new Object();

    private LinkedList<E> list;



    public ConcurrencyFirstLastList() {
    this.list = new LinkedList<>();
    }

    @SafeVarargs
    public ConcurrencyFirstLastList(E... args) {
        this.list = new LinkedList<>(Arrays.asList(args));
    }

    public boolean offer(E value) {
        synchronized (this.lock) {
            return this.list.offer(value);
        }
    }

    public E get(int index) {
        synchronized (this.lock) {
            return this.list.get(index);
        }
    }

    public E poll() {
        synchronized (this.lock) {
            return this.list.poll();
        }
    }

    public int size() {
        synchronized (this.lock) {
            return  this.list.size();
        }
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        synchronized (this.lock) {
            return this.list.iterator();
        }
    }

    @Override
    public String toString() {
        synchronized (this.lock) {
            return this.list.toString();
        }
    }


}
