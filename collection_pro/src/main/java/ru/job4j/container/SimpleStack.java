package ru.job4j.container;
import ru.job4j.list.FirstLastList;

public class SimpleStack<T> {
  private FirstLastList<T> list = new FirstLastList<>();


    public T poll() {
         return list.deleteFirst();
    }

    public void push(T value) {
        list.insetFirst(value);

    }


}
