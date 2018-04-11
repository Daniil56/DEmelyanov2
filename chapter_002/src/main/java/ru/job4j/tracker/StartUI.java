package ru.job4j.tracker;

public class StartUI {
    private final Input input;
    private final Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
    this.input = input;
    this.tracker = tracker;
    }
    public void init() {
        Tracker tracker = new Tracker();
        MenuTracker menu = new MenuTracker(this.input, tracker);
        menu.fillActions();
        do {
    menu.show();
    int key = Integer.valueOf(input.ask("Выберете пункт меню :"));
    menu.select(key);
        } while (!"д".equals(this.input.ask("Выйти? д/н: ")));
    }
    public  static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}