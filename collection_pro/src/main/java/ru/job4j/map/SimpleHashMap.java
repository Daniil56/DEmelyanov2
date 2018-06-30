package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<K, V> {

    private transient int modCount;
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int threshold;
    private transient int size;
    private final float loadFactor;

    public SimpleHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public int getSize() {
        return size;
    }

    private int hash(K key) {
        int h = key.hashCode();
        return h ^ h >>> 16;
    }

    private static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public Object setValue(V newValue) {
            V oldValue = value;
            value = newValue;

            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            return hash == node.hash && Objects.equals(key, node.key) && Objects.equals(value, node.value) && Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {

            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }



   // private void afterNodeInsertion(boolean evict) { }



    private transient Node<K, V>[] table;

    private Node<K, V> newNode(int hash, K key, V value, Node<K, V> next) {
        return new Node<>(hash, key, value, next);
    }
    private V putForNullKey(V value) {
        for (Node<K, V> e = table[0]; e != null; e = e.next) {
            if (e.key == null) {
                V oldValue = e.value;
                e.value = value;
                recordAccess(this);
                return oldValue;
            }
        }
        modCount++;
       addEntry(0, null, value, 0);
        return null;
    }
    private V getForNullKey() {
        for (Node<K, V> e = table[0]; e != null; e = e.next) {
            if (e.key == null) {
                return e.value;
            }
        }
        return null;
    }
    public V get(K key) {
        if (key == null) {
            return getForNullKey();
        }
        int n;
        //Node<K, V>[] tab;
        int hash = hash(key);
        n = resize().length;
        int i = (n - 1) & hash;
        for (Node<K, V> e = table[i];
             e != null;
             e = e.next) {
            K k = e.key;
            if (e.hash == hash && k == key || key.equals(k)) {
                return e.value;
            }
        }
        return null;
    }

    private void recordAccess(SimpleHashMap<K, V> m) {
    }

    private void addEntry(int hash, K key, V value, int bucketIndex) {
        Node<K, V> e = table[bucketIndex];
        table[bucketIndex] = new Node<>(hash, key, value, e);
        if (size++ >= threshold) {
            resize();
        }
    }

    public boolean delete(K key) {
        Node<K, V> e = removeEntryForKey(key);
        return (e != null);
    }
    private void recordRemoval(SimpleHashMap<K, V> m) {
    }

     final Node<K, V> removeEntryForKey(K key) {
        int hash = (key == null) ? 0 : hash(key);
        int n;
        Node<K, V>[] tab;
        n = table.length;
        int i = (n - 1) & hash;
        Node<K, V> prev = table[i];
        Node<K, V> e = prev;

        while (e != null) {
            Node<K, V> next = e.next;
            K k = e.key;
            if (e.hash == hash && k  == key || (key != null && key.equals(k))) {
                modCount++;
                size--;
                if (prev == e) {
                    table[i] = next;
                } else {
                    prev.next = next;
                    recordRemoval(this);
                }
                    return e;
            }
            prev = e;
            e = next;
        }

        return e;
    }

    final boolean put(K key, V value) {
        if (key == null) {
            putForNullKey(value);
        }
        int hash = hash(key);
        int n;
       Node<K, V>[] tab = table;
        n = resize().length;
        int i = (n - 1) & hash;
        for (Node<K, V> e = table[i]; e != null; e = e.next) {
            K k  = e.key;
            if (e.hash == hash && k == key || key.equals(k)) {
                //V oldValue = e.value;
                e.value = value;
                recordAccess(this);
                return false;
            }
        }

        modCount++;
        addEntry(hash, key, value, i);
        return true;
    }


    final Node<K, V>[] resize() {
        Node<K, V>[] oldtab = table;
        int oldCap = (oldtab == null) ? 0 : oldtab.length;
        int newCap, newThr = 0;
        int oldThr = threshold;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldtab;
            } else {
                newCap = oldCap << 1;
                if (newCap < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY) {
                    newThr = oldThr << 1;
                }
            }
            } else if (oldThr > 0) {
                newCap = oldThr;
            } else {
                newCap = DEFAULT_INITIAL_CAPACITY;
                newThr = (int) DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY;
            }
            if (newThr == 0) {
                float ft = (float) newCap * loadFactor;
                newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ? (int) ft : Integer.MAX_VALUE);
            }


            threshold = newThr;
            @SuppressWarnings({"rawtypes", "unchecked"})
            Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap];

            table = newTab;
            if (oldtab != null) {
                for (int j = 0; j < oldCap; ++j) {
                    Node<K, V> e = oldtab[j];
                    if (e != null) {
                        oldtab[j] = null;
                        if (e.next == null) {
                            newTab[e.hash & (newCap - 1)] = e;
                        } else {
                            Node<K, V> loHead = null, loTail = null;
                            Node<K, V> hiHead = null, hiTail = null;
                            Node<K, V> next;
                            do {
                                next = e.next;
                                if ((e.hash & oldCap) == 0) {
                                    if (loTail == null) {
                                        loHead = e;
                                    } else {
                                        loTail.next = e;
                                        loTail = e;
                                    }
                                } else {
                                    if (hiTail == null) {
                                        hiHead = e;
                                    } else {
                                        hiTail.next = e;
                                        hiTail = e;
                                    }
                                }
                                e = next;
                            }
                            while (e  != null);
                            if (loTail != null) {
                                loTail.next = null;
                                newTab[j] = loHead;
                            }
                            if (hiTail != null) {
                                hiTail.next = null;
                                newTab[j + oldCap] = hiHead;
                            }
                        }
                    }
                }
            }
            return newTab;
    }

   class HashIterator {
       Node<K, V> next;
      // Node<K, V> current;
       int expectedModcount;
       int index;

       HashIterator() {
           expectedModcount = modCount;
           Node<K, V>[] t = table;
          // current = next = null;
           index = 0;
           if (t != null && size > 0) {
             //  if (index < t.length && (t[index++]) == null) {
                   while (index < t.length &&  next == null) {
                       next = t[index++];
                   }
              // }
           }
       }

       public final boolean hasNext() {
           return next != null;
       }

       final Node<K, V> nextNode() {
           Node<K, V>[] t = table;
           Node<K, V> e = next;
           if (modCount != expectedModcount) {
               throw new ConcurrentModificationException("mod exception");
           }
           if (e == null) {
               throw new NoSuchElementException("no such");
           }
           next =  e.next;
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