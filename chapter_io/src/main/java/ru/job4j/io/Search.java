package ru.job4j.io;


import org.w3c.dom.Node;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

public class Search {

   public List<File> files(String parent, Predicate<String> condition) {
        List<File> result = new ArrayList<>();
        File root = new File(parent);
        Queue<File> queue = new ArrayDeque<>();
        Queue<File> checked = new LinkedList<>();
        if (root.isDirectory()) {
            File[] files = root.listFiles();
            queue.addAll(Arrays.asList(Objects.requireNonNull(files)));
          while (!queue.isEmpty()) {
              var current = queue.remove();
              var name = current.getName();
              if (current.isDirectory()) {
                  queue.addAll(Arrays.asList(Objects.requireNonNull(current.listFiles())));
              } else if (condition.test(name) && !checked.contains(current)) {
                  result.add(current);
                  checked.add(current);
              }
          }
        }
        return result;
    }
}
