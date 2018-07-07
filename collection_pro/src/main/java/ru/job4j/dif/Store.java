package ru.job4j.dif;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
           for (int preIndex = 0, postIndex = 0; preIndex < previoues.size() && postIndex < current.size(); postIndex++, preIndex++) {
                if (!previoues.get(postIndex).name.equals(current.get(postIndex).name) && previoues.get(postIndex).id == current.get(postIndex).id) {
                    changeindex++;
                    info.add(changes, changeindex);
                }
                if (!previoues.contains(current.get(postIndex)) && previoues.size() >= current.size()) {
                    changeSet.add(current.get(postIndex));
                    info.add(added, changeSet.size() - changeindex);
                } else {
                    if (previoues.size() < current.size()) {
                        info.add(added, current.size() - previoues.size() + changeindex);
                    }
                }
                if (!(current.contains(previoues.get(preIndex)) && current.size() >= previoues.size())) {
                    delSet.add(previoues.get(preIndex));
                    info.add(deleted, delSet.size() - changeindex);
                }  else {
                    if (previoues.size() > current.size()) {
                        info.add(deleted, previoues.size() - current.size() + changeindex);
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