package ru.job4j.tracker;

import java.util.*;
public class Tracker {
    private Item[] items = new Item[100];
    private int position;
    private static final Random RN = new Random();

    public Item add(Item item) {
        item.setId(this.generateid());
        this.items[this.position++] = item;
        return item;
    }

    public Item[] getItems() {
        return items;
    }

    private String generateid() {

        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
    public Item[] getAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            if (this.items[index] != null) {
                result[index] = this.items[index];
            }
        }

        return result;
    }
    public void replace(String id, Item item) {
        for (int index = 0; index != this.position; index++) {
            if (item.getId().equals(id)) {
                this.items[index] = item;
                break;
            }
        }
    }
    public void  delete(String id) {
        for (int index = 0; index < this.position; index++) {
            if (this.items[index] != null && this.items[index].getId().equals(id)) {
                this.items[index] = null;
                System.arraycopy(this.items, index + 1, this.items, index, this.position - 1 - index);
                this.position--;
            }
        }
    }
    public Item[] findByName(String key) {
        int i = 0;
        Item[] result = new Item[this.position];
        for (Item item : items) {
        if (item != null && item.getName().equals(key)) {
            result[i] = item;
            i++;
            }
        }
        return Arrays.copyOf(result, i);
    }
    public Item findById(String id){
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId(). equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}