package ru.job4j.nonblock;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

@ThreadSafe
public class ConcurrentCache {
@GuardedBy(value = "this")
    private volatile ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();


    public void add(Base model) {
        map.putIfAbsent(model.getId(), model);
    }

    public void upDateValue(int  id, int value) {
      final   int oldVersion = map.get(id).getVersion();
        this.map.computeIfPresent(id, new BiFunction<Integer, Base, Base>() {
            @Override
            public Base apply(Integer integer, Base base) {
                if (oldVersion == base.getVersion()) {
                    base.setValue(value);
                    base.incrementVersion();
                } else {
                    try {
                        throw new OptimisticException("optimistic exception");
                    } catch (OptimisticException e) {
                        e.printStackTrace();
                    }
                }
                return base;
            }
        });
    }

            public void delete(Base model) {
                map.remove(model.getId());
            }

            public Base getBase(int id) {
                return map.get(id);
            }


            public int size() {
                return map.size();
            }
        }
