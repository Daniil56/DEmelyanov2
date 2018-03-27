package ru.job4j.tracker;

import java.util.*;

import static java.util.Arrays.*;

public class Tracker {
    private  Item[] items = new Item[100];
    private int position;
    private static final Random RN = new Random();



    public Item add(Item item) {
        item.setId(this.generateid());
        this.items[this.position++] = item;
        return item;
    }
    private String generateid() {

        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
    public Item[] getAll() {
        Item[] result = new Item[position];
        for (int index = 0; index != this.position; index++) {
            result[index] = this.items[index];
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
            if (items[index] != null && items[index].getId().equals(id)) {
                System.arraycopy(items, index + 1, items, index, this.position - 1 - index);

            }
            position--;


        }
    }

    public Item[] findAll(){

        return new Item[0] ;
    }
    public Item[] findByName(String key){
        Item[] result = new Item[position];
        for (Item item : items) {
            if (item != null && item.getName().equals(key)) {
                result[position++] = item;
                break;
            }
        }
        return result;
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
