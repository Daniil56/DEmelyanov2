package ru.job4j.list;


public class SimpleArrayList<E> {
    private int size;
    private Node<E> first;

    public void add(Integer date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    public E delete() {
        Integer temp = first.date;
        this.first = first.next;
        this.size--;
        return (E) temp;
    }

    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return (E) result.date;
    }

    public int getSize() {
        return this.size;
    }

    private static class Node<e> {
        Integer date;
        Node<e> next;
        Node(Integer date) {
            this.date = date;
        }
    }

}
