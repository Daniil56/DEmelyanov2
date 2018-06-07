package ru.job4j.list;


public class SimpleArrayList<e> {

    private int size;
    private Node<e> first;

    public void add(Integer date) {
        Node<e> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    public Integer delete() {

        Node<e> newLink = new Node<>(get(1));
        Integer temp = get(0);

        newLink.next = this.first;

        this.first = newLink;

        this.size--;

        return temp;
    }

    public Integer get(int index) {
        Node<e> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    public int getSize() {
        return this.size;
    }

    private static  class Node<e> {

        Integer date;
        Node<e> next;

        Node(Integer date) {
            this.date = date;
        }
    }

}
