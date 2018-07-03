package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node root;
    int index = 0;
    int count = 0;


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

    private E levelOrder(Node<E> localRoot) {
     Queue<E> data = new LinkedList<>();
    Node<E> next = localRoot.leaves().get(count);
            while (true) {
                data.offer(localRoot.leaves().get(count++).getValue());
                break;
            }


     return data.poll();
    }

    @Override
    public Iterator<E> iterator() {
        Node<E> next = root;
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return next.leaves().get(index) != null;
            }

            @Override
            public E next() {
                return levelOrder(next);
            }
        };
    }



}
