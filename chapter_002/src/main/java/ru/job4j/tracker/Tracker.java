package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class Tracker {
    private List<Item> items = new ArrayList<>();
    private static final Random RN = new Random();

    public Item add(Item item) {
        item.setId(this.generateid());
        items.add(item);
        return item;
    }

    private String generateid() {

        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    public List<Item> getAll() {
        List<Item> result = new ArrayList<>();
        for (Item item: items) {
            if (item != null) {
                result.add(item);
            }
        }
        return result;
    }

    public void replace(Item item) {
        for (Item i : items) {
            if (findById(item.getId()).equals(i) && item.getId() != null) {
                items.add(items.indexOf(i), item);
                break;
           }
        }
    }

    public void  delete(String id) {
        for (Item item : items) {
            if (findById(id).equals(item)) {
                items.remove(item);
                break;
            }
        }
    }

    public List<Item> findByName(String key) {
        Predicate<String> predicate = p -> p.equals(key);
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
        if (predicate.test(item.getName())) {
            result.add(item);
            }
        }
        return result;
    }

    public Item findById(String id) {
        Predicate<String> predicate = p -> p.equals(id);
         Item result = null;
        for (Item item : items) {
            if (predicate.test(item.getId())) {
                result = item;
                break;
            }
        }
        return result;
    }
}