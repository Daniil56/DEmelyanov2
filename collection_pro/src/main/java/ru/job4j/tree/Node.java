package ru.job4j.tree;

import java.util.*;

public class Node<E extends  Comparable<E>> {
        private final List<Node<E>> children = new ArrayList<>();
    int index = 0;


    public E getValue() {
        return value;
    }

    private final E value;

        public Node(final E value) {
            this.value = value;
        }

        public void add(Node<E> child) {
            this.children.add(child);
        }

        public List<Node<E>> leaves() {
            return this.children;
        }

        public boolean eqValue(E that) {
            return this.value.compareTo(that) == 0;
        }

        public boolean isBinary() {
            boolean result = true;
            if (leaves().size() > 2) {
                result = false;
            } else {
                for (Node<E> node : leaves()) {
                    if (!node.isBinary()) {
                        result = false;
                        break;
                    }
                }
            }





            return result;
        }
    }