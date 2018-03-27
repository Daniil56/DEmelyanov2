package ru.job4j.tracker;

public class Item {
    private String id;
    public   String name;
    public String description;
    public   long created;
    public String comments;

    public Item(String name, String description, long created) {
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setCreated(long created){
        this.created = created;
    }

    public String getName() {
        return this.name;
    }
}
