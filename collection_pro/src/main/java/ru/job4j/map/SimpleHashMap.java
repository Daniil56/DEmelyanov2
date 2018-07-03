package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<K, V>  {
    private static final int DEFAULT_SIZE = 16;
    private Node[] container;

    public int getSize() {
        return size;
    }

    private int size;
    private int modCount;

    public SimpleHashMap() {
        this.container = new Node[DEFAULT_SIZE];
    }


    private static class Node<K, V> {

        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        @Override
        public int hashCode() {

            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }


        public boolean put(K key, V value) {
            boolean result = false;
            int factor = this.container.length;
            if (this.container[position(key)] == null) {
                if (this.size > factor * 2 / 3) {
                    resize();
                }
                this.container[position(key)] = new Node(key, value);
                this.size++;
                result = true;
                this.modCount++;
            }
            return result;
        }


        public V get(K key) {
        V result = null;
            int index = position(key);
            if (this.container[index] != null && this.container[index].key.equals(key)) {
                result = (V) this.container[index].value;
            }
            return result;
        }


        public boolean delete(K key) {
            boolean result = false;
            int index = this.position(key);
            if (this.container[index] != null
                    && this.container[index].getKey().equals(key)) {
                this.container[index] = null;
                result = true;
                this.size--;
                this.modCount++;
            }
            return result;
        }


        private int position(K key) {
            int h = key.hashCode();
            return h % Math.abs(hash(key, this.container.length) % this.container.length);
        }


        private int hash(K key, int newSize) {
        int h = key.hashCode();
            h =  h ^ h >>> 16;
            return (31 * h) % newSize;
        }


        private void resize() {
            Node<K, V>[] newContainer = (Node<K, V>[]) new Node[this.container.length * 2];
            for (Node<K, V> entry : this.container) {
                if (entry != null) {
                    newContainer[hash(entry.getKey(), newContainer.length)] = entry;
                }
            }
            this.container = newContainer;
        }
    class HashIterator {
        Node<K, V> next;
        int expectedModcount;
        int index;

        HashIterator() {
            expectedModcount = modCount;
            Node<K, V>[] t = container;
            index = 0;
            if (t != null && size > 0) {
                while (index < t.length &&  next == null) {
                    next = t[index++];
                }
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Node<K, V> nextNode() {
            Node<K, V>[] t = container;
            Node<K, V> e = next;
            if (modCount != expectedModcount) {
                throw new ConcurrentModificationException("mod exception");
            }
            if (e == null) {
                throw new NoSuchElementException("no such");
            }
            next =  container[index++];
            if ((next == null && t != null)) {
                while (index < t.length && next == null) {
                    next = t[index++];
                }
            }
            return e;
        }
    }
    final class KeyIterator extends HashIterator implements Iterator<K> {

        @Override
        public K next() {
            return nextNode().key;
        }
    }

    final class ValueIterator extends HashIterator implements Iterator<V> {

        @Override
        public V next() {
            return nextNode().value;
        }
    }

    final class  EntryIterator extends HashIterator implements Iterator<SimpleHashMap.Node<K, V>> {

        @Override
        public Node<K, V> next() {
            return nextNode();
        }
    }
}