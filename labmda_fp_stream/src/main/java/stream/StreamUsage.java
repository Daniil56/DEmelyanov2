package stream;

import java.util.List;
import java.util.stream.Collectors;

public class StreamUsage {
    public static class Task {
        private final String name;
        private final long spentt;

        public Task(String name, long spentt) {
            this.name = name;
            this.spentt = spentt;
        }

        @Override
        public String toString() {
            return "Task{" + "name='" + name + '\'' + ", spentt=" + spentt + '}';
        }
    }
    public static void main(String[] args) {
        List<Task> tasks = List.of(
                new Task("Bug #1", 100),
                new Task("Bug #2", 100),
                new Task("Bug #3", 100)
        );
        List<Task> bugs = tasks.stream().filter(
                task -> task.name.contains("Bug")
        ).collect(Collectors.toList());
        List<String> names = tasks.stream().map(
                task -> task.name
        ).collect(Collectors.toList());
        long total = tasks.stream().map(
                task -> task.spentt
        ).reduce(0L, Long::sum);

        bugs.forEach(System.out::println);
        names.forEach(System.out::println);
        System.out.println(total);
    }

}
