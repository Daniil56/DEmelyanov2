package ru.job4j.nonblock;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
@ThreadSafe
public class ConcurrentCache {
@GuardedBy(value = "this")
    private volatile ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();

    public synchronized ConcurrentHashMap getMap() {
        return map;
    }

    public void add(Base model) {
        map.put(model.getId(), model);
    }

    public void upDateId(int  id, int value) {
       final int oldVersion = map.get(id).getVersion();
        map.computeIfPresent(id, (integer, base) -> {
            if (oldVersion == base.getVersion()) {
                base.setValue(value);
                base.incrementVersion();
            } else {
                throw new OptimisticException("optimistic exception");
            }
            return base;
        });

    }


    public void delete(Base model) {
        map.remove(model.getId());
    }

    public Base getBase(int id) {
        return map.get(id);
    }


}
