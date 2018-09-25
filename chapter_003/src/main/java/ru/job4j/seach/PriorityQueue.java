package ru.job4j.seach;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позицию определять по полю приоритет.
     * Для вставок использовать add(int index, E value)
     * @param task задача
     */
    public void  put(Task task) {
        tasks.add(task);
        IntStream.range(0, tasks.size()).filter(index -> task.getPriority() < tasks.get(index).getPriority())
                .findFirst().ifPresent(index -> tasks.add(index, task));
    }

    public Task take() {
        return this.tasks.poll();
    }
}
