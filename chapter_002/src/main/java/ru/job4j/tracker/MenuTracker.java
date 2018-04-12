package ru.job4j.tracker;
class ShowItems extends BaseAction {
    ShowItems(int key, String name) {
        super(key, name);
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

}

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private  int position = 0;
    private UserAction[] actions = new UserAction[7];

    MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[position++] = new Exit(0, "Выход.");
        this.actions[position++] = new Add(1, "Добавить новую заявку");
        this.actions[position++] = new MenuTracker.Edit(2, "Редактировать заявку.");
        this.actions[position++] = new ShowItems(3, "Показать все заявки");
        this.actions[position++] = new Delete(4, "Удалить заявку.");
        this.actions[position++] = new FindBuID(5, "Найти заявку по идентификатору.");
        this.actions[position++] = new FindByName(6, "Найти заявку по имени.");
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
    private class Exit extends BaseAction {
        Exit(int key, String name) {
            super(key, name);
        }
        public void execute(Input input, Tracker tracker) {
        }
    }
    private class Add extends BaseAction {

        Add(int key, String name) {
            super(key, name);
        }
        public void execute(Input input, Tracker tracker) {
            System.out.println("-----------Добавление новой заявки-----------");
            String name = input.ask("Введите имя заявки:");
            String desc = input.ask("Введите описание заявки:");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("-----------Новая заявка с getID :" + item.getId() + "-----------");
        }

    }
    private static class Edit extends BaseAction {
        Edit(int key, String name) {
            super(key, name);
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
    }

    private class Delete extends BaseAction {
        Delete(int key, String name) {
            super(key, name);
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

        }
    private class FindBuID extends BaseAction {
        FindBuID(int key, String name) {
            super(key, name);
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
    }
    private class FindByName extends BaseAction {
        FindByName(int key, String name) {
            super(key, name);
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
    }
}