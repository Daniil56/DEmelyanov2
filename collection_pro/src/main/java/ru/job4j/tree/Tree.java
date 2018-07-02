package ru.job4j.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node root;


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
                    rst =  el;
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
        return  result;
    }





    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }
        };
    }
}
