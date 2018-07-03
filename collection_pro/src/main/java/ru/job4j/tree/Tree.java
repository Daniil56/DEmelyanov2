package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node root;
    private int modCount = 0;

    public Tree(E data) {
        this.root = new Node(data);
    }


    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Node<E> newNode = new Node<>(child);
        Node<E> rst = new Node<>(parent);
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el != null && el.eqValue(parent)) {
                rst = el;
                result = true;
                break;
            }
            for (Node<E> childs : el.leaves()) {
                data.offer(childs);
            }
        }
        rst.add(newNode);
        modCount++;
        return result;
    }


    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                result = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }


    @Override
    public Iterator<E> iterator() {
        class TreeIterator implements Iterator<E> {
            private int expectedModCount = modCount;
            private Node<E> value;
            private Queue<Node<E>> data;

            private TreeIterator() {
                data = new LinkedList<>();
                data.offer(root);
            }
            @Override
            public boolean hasNext() {
                return !data.isEmpty();
        }

        @Override
            public E next() {
                if (modCount != expectedModCount) {
                    throw  new ConcurrentModificationException("modification alert");
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("no such");
                }
                value = data.poll();
            assert value != null;
            for (Node<E> child : value.leaves()) {
                    data.offer(child);
                }
                return value.getValue();
        }
    }
    return new TreeIterator();
}

}
