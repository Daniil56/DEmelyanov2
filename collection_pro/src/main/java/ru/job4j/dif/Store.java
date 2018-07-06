package ru.job4j.dif;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Store {

    Info diff(List<User> previoues, List<User> current) {
        Info info = new Info();
        int changeindex = 0;
        String changes = "Changes";
        String added = "Added";
        String deleted = "Deleted";
        Set<User> changeSet = new HashSet<>();
        Set<User> delSet = new HashSet<>();

        if (!previoues.containsAll(current)) {
            for (User listprev : previoues) {
                for (User listcur : current) {
                    if (!(listprev.name.equals(listcur.name)) && listprev.id == listcur.id) {
                        changeindex++;
                        info.add(changes, changeindex);
                    }
                        if (!previoues.contains(listcur)) {
                            changeSet.add(listcur);
                            info.add(added, changeSet.size() - changeindex);
                        }
                        if (!current.contains(listprev)) {
                        delSet.add(listprev);
                        info.add(deleted, delSet.size() - changeindex);
                        }
                }
            }

        }
        return info;
    }

    static class User {

        int id;
        String name;


        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            User user = (User) o;

            if (id != user.id) {
                return false;
            }
            return name != null ? name.equals(user.name) : user.name == null;
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
        }
    }

}