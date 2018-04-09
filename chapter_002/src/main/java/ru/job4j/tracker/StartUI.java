package ru.job4j.tracker;

import java.util.Arrays;

public class StartUI {
    private static final String EXIT = "0";
    private static final String ADD = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FINDBYID = "4";
    private static final String FINDVYNAME = "5";
    private static final String SHOWALL = "6";
    private final Input input;
    private final Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
    this.input = input;
    this.tracker = tracker;
    }

    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню :");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FINDBYID.equals(answer)) {
                this.findById();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (FINDVYNAME.equals(answer)) {
               this.findByName();
            } else  if (SHOWALL.equals(answer)) {
               this.showAll();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }
    public  void createItem() {
    System.out.println("-----------Добавление новой заявки-----------");
    String name = this.input.ask("Введите имя заявки:");
    String desc = this.input.ask("Введите описание заявки:");
    Item item = new Item(name, desc);
    this.tracker.add(item);
    System.out.println("-----------Новая заявка с getID :" + item.getId() + "-----------");
    }
    public void editItem() {
        System.out.println("-----------Редактирование заявки-----------");
        String id = this.input.ask("Введите индентификатор заявки :");

        for (int index = 0; index < tracker.getAll().length; index++) {
            if (tracker.getAll()[index].getId().equals(id) && tracker.getAll()[index].getId() != null) {
                String name = this.input.ask("Введите новое имя :");
                String desc = this.input.ask("Введите новое описание :");
                Item previus = new Item(name, desc);
                previus.setId(tracker.getAll()[index].getId());
              this.tracker.replace(id, previus);
                System.out.println("-----------Заявка с getID :" + tracker.getAll()[index].getId() + " изменена-----------");
                System.out.println("-----------Имя заявки :" + tracker.getAll()[index].getName() + " -----------");
                System.out.println("-----------Описание заявки :" + tracker.getAll()[index].getDescription() + "-----------");
            } else {
                System.out.println("-----------Такой заявки нету.-----------");
            }
        }
    }
    public void showAll() {
        System.out.println("-----------Проссмотр всех заявок-----------");
        if (this.tracker.getAll().length == 0) {
            System.out.println("-----------У Вас нет заявок :" + "-----------");
        } else if (this.tracker.getAll().length != 0) {
            for (Item item : this.tracker.getAll()) {
                System.out.println("-----------Заявка с идентификатором:" + item.getId() + "-----------");
                System.out.println("Имя заявки :" + item.getName());
                System.out.println("Описание заявки :" + item.getDescription());
          }
        }
    }
        public void deleteItem() {
            System.out.println("-----------Удаление заявки-----------");
            String id = this.input.ask("Введите идентификатор заявки:");
            for (Item item : tracker.getAll()) {
                if (item.getId().equals(id) && item.getId() != null) {
                    this.tracker.delete(id);
                    System.out.println("Заявка с идентификатором :" + item.getId() + " удалена.");
                } else {
                    System.out.println("Заявка с идентификатором :" + id + " не найдена.");
                }
                }
        }
        public void findById() {
            System.out.println("-----------Поиск заявки по идентификатору-----------");
            String id = this.input.ask("Введите идентификтор заявки.");
            for (Item item : this.tracker.getAll()) {
                if (item.getId().equals(id) && item.getId() != null) {
                    this.tracker.findById(id);
                    System.out.println("Заявка с идентификатором :" + id + " найдена :");
                    System.out.println("Имя заявки :" + item.getName());
                    System.out.println("Описание заявки :" + item.getDescription());
                } else if (!(item.getId() == null && item.getId().equals(id))) {
                    System.out.println("Заявка с идентификатором :" + id + " не найдена.");
                }
            }
        }
        public void findByName() {
            System.out.println("-----------Поиск заявки по имени.-----------");
            String name = this.input.ask("Введите имя заявки");
            for (Item item : tracker.getAll()) {
                this.tracker.findByName(name);
                if (item != null && item.getName().equals(name)) {
                    System.out.println("Заявка с именем :" + item.getName() + " найдена :");

                } else {
                    System.out.println("Заявок с именем :" + name + " не обноружено.");
                }
            }
        }
    private void showMenu() {
    System.out.println("Меню");
    System.out.println("0. Выход");
    System.out.println("1. Добавить заявку.");
    System.out.println("2. Редактировать заявку.");
    System.out.println("3. Удалить заявку.");
    System.out.println("4. Найти заявку по идентификатору");
    System.out.println("5. Найти заявку по имени");
    System.out.println("6. Показать все заявки.");
    }
    public  static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}


