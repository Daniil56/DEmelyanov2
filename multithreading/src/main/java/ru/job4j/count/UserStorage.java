package ru.job4j.count;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;
@ThreadSafe
public  class  UserStorage {
    @GuardedBy("this")
   private final Map<Integer, User> userMap = new HashMap<>();

    public  boolean add(User user) {
        synchronized (this) {
            boolean result;
            if (userMap.values().contains(user)) {
                result = false;
            } else {
                userMap.put(user.getId(), user);
                result = true;
            }
            return result;
        }
    }

    public  boolean update(User user) {
        synchronized (this) {
            boolean result;
            if (userMap.keySet().contains(user.getId())) {
                userMap.put(user.getId(), user);
                result = true;
            } else {
                result = false;
            }
            return result;
        }
    }

    public  boolean transfer(int fromID, int toID, int amount) {
        synchronized (this) {
            boolean result;
            synchronized (this.userMap.get(fromID)) {
                if (userMap.keySet().contains(fromID) && userMap.keySet().contains(toID) && userMap.get(fromID).getAmount() >= amount) {
                    userMap.get(toID).setAmount(userMap.get(toID).getAmount() + amount);
                    userMap.get(fromID).setAmount(userMap.get(fromID).getAmount() - amount);
                    result = true;
                } else {
                    result = false;
                }
            }
            return result;
        }
    }

    public Map<Integer, User> getUserMap() {
        synchronized (this) {
            return userMap;
        }
    }
}
