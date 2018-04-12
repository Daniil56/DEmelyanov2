package ru.job4j.tracker;
class ShowItems implements UserAction {
    @Override
    public int key() {
        return 3;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("-----------Проссмотр всех заявок-----------");
        if (tracker.getAll().length == 0) {
            System.out.println("-----------У Вас нет заявок :" + "-----------");
        } else if (tracker.getAll().length != 0) {
            for (Item item : tracker.getAll()) {
                System.out.println("-----------Заявка с идентификатором:" + item.getId() + "-----------");
                System.out.println("Имя заявки :" + item.getName());
                System.out.println("Описание заявки :" + item.getDescription());
            }
        }
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Показать все заявки");
    }
}

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[0] = new Exit();
        this.actions[1] = this.new Add();
        this.actions[2] = new MenuTracker.Edit();
        this.actions[3] = new ShowItems();
        this.actions[4] = new Delete();
        this.actions[5] = new FindBuID();
        this.actions[6] = new FindByName();
    }
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }
    private class Exit implements UserAction {
        public int key() {
            return 0;
        }
        public void execute(Input input, Tracker tracker) {
        }
        public String info() {
            return String.format("%s. %s", this.key(), "Выход.");
        }
    }
    private class Add implements UserAction {

        public int key() {
            return 1;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("-----------Добавление новой заявки-----------");
            String name = input.ask("Введите имя заявки:");
            String desc = input.ask("Введите описание заявки:");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("-----------Новая заявка с getID :" + item.getId() + "-----------");
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Добавить новую заявку");
        }
    }
    private static class Edit implements UserAction {
        public int key() {
            return 2;
        }
        public void execute(Input input, Tracker tracker) {
            System.out.println("-----------Редактирование заявки-----------");
            String id = input.ask("Введите индентификатор заявки :");

            for (int index = 0; index < tracker.getAll().length; index++) {
                if (tracker.getAll()[index].getId().equals(id) && tracker.getAll()[index].getId() != null) {
                    String name = input.ask("Введите новое имя :");
                    String desc = input.ask("Введите новое описание :");
                    Item previus = new Item(name, desc);
                    previus.setId(tracker.getAll()[index].getId());
                    tracker.replace(id, previus);
                    System.out.println("-----------Заявка с getID :" + tracker.getAll()[index].getId() + " изменена-----------");
                    System.out.println("-----------Имя заявки :" + tracker.getAll()[index].getName() + " -----------");
                    System.out.println("-----------Описание заявки :" + tracker.getAll()[index].getDescription() + "-----------");
                } else {
                    System.out.println("-----------Такой заявки нету.-----------");
                }
            }
        }
        public String info() {
            return String.format("%s. %s", this.key(), "Редактировать заявку.");
        }
    }
    private class Delete implements UserAction {
        public int key() {
            return 4;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("-----------Удаление заявки-----------");
            String id = input.ask("Введите идентификатор заявки:");
            for (Item item : tracker.getAll()) {
                if (item.getId().equals(id) && item.getId() != null) {
                    tracker.delete(id);
                    System.out.println("Заявка с идентификатором :" + item.getId() + " удалена.");
                } else {
                    System.out.println("Заявка с идентификатором :" + id + " не найдена.");
                }
            }
        }
            public String info() {
                return String.format("%s. %s", this.key(), "Удалить заявку.");
            }
        }
    private class FindBuID implements UserAction {
        public int key() {
            return 5;
        }
        public void execute(Input input, Tracker tracker) {
            System.out.println("-----------Поиск заявки по идентификатору-----------");
            String id = input.ask("Введите идентификтор заявки.");
            for (Item item : tracker.getAll()) {
                if (item.getId().equals(id) && item.getId() != null) {
                    tracker.findById(id);
                    System.out.println("Заявка с идентификатором :" + id + " найдена :");
                    System.out.println("Имя заявки :" + item.getName());
                    System.out.println("Описание заявки :" + item.getDescription());
                } else if (!(item.getId() == null && item.getId().equals(id))) {
                    System.out.println("Заявка с идентификатором :" + id + " не найдена.");
                }
            }
        }
        public String info() {
            return String.format("%s. %s", this.key(), "Найти заявку по идентификатору.");
        }
    }
    private class FindByName implements UserAction {
        public int key() {
            return 6;
        }
        public void execute(Input input, Tracker tracker) {
            System.out.println("-----------Поиск заявки по имени.-----------");
            String name = input.ask("Введите имя заявки");
            for (Item item : tracker.getAll()) {
                tracker.findByName(name);
                if (item != null && item.getName().equals(name)) {
                    System.out.println("Заявка с именем :" + item.getName() + " найдена :");

                } else {
                    System.out.println("Заявок с именем :" + name + " не обноружено.");
                }
            }
        }
        public String info() {
            return String.format("%s. %s", this.key(), "Найти заявку по имени.");
        }
    }
}