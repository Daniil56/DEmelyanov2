package ru.job4j.io;


import org.w3c.dom.Node;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

public class Search {

   public List<File> files(String parent, Predicate<String> condition) {
        List<File> result = new ArrayList<>();
        File root = new File(parent);
        Queue<Tree<File>> queue = new ArrayDeque<>();
        Queue<File> checked = new LinkedList<>();
        if (root.isDirectory()) {
            File[] files = root.listFiles();
            Tree<File> node = new Tree<>(root);
            node.addChildren(files);
            queue.add(node);
          while (!queue.isEmpty()) {
              var currentNode = queue.remove();
              var name = currentNode.getValue().getName();
              if (condition.test(name) && !checked.contains(currentNode.getValue())) {
                  result.add(currentNode.getValue());
                  checked.add(currentNode.getValue());
              } else if (currentNode.getValue().isDirectory()) {
                  var childes = currentNode.getValue().listFiles();
                  currentNode.addChildren(childes);
                  queue.addAll(currentNode.getChildren());
              }
          }
        }
        return result;
    }
}
