package ru.job4j.trie;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WordIndex {
    private Trie root = new Trie();
    private String[] line;
    /**
     * Загрузка данных из файла и построение индекса
     * Для построения индекса испотзуется массив строк с разделением на строки
     * @param filename адресс файла
     */
    public void loadFile(String filename) throws IOException {
        //Files.lines(Paths.get(filename), StandardCharsets.UTF_8).forEach(root::insert);
        FileInputStream fis = new FileInputStream(new File(filename));
        byte[] content = new byte[fis.available()];
        fis.read(content);
        fis.close();
        String[] lines = new String(content, "UTF-8").split("\n");
        line = lines;
        for (int index = 0; index < line.length - 1; index++) {
            root.insert(lines[index]);
        }
    }

    /**
     * Возвращает список позиций слова в файле. Если данного слова в файле нет, то возвращается null.
     * @param searchWord искомое слова
     * @return Множество в котором первый символ указывает на позицию в строке, второй символ позиция строки в файле.
     */
    public Set<Integer> getIndexes4Word(String searchWord) {
        Trie current = root;

        Set<Integer> wordIndex = new HashSet<>();
        //if (current.exists(searchWord)) {
            int i = 1;
            int k = 0;
            for (String line1 : line) {
                String[] words = line1.split(" ");
                int j = 1;
                for (String word : words) {
                    if (word.equalsIgnoreCase(searchWord)) {
                        wordIndex.add(i);
                        wordIndex.add(j);
                        k = k + 1;
                    }
                    j++;
                }
                i++;
            }
            if (k < 1) {
               wordIndex.add(null);
            }

     //   } else wordIndex.add(null);

        return wordIndex;
    }
    /*
     public Set<Integer> getIndexes4Word(String searchWord) {
        Trie current = root;
        int index1 = 0;
        char[] charcters = searchWord.toCharArray();
        Set<Integer> wordIndex = new HashSet<>();
        if (current.exists(searchWord)) {
            TrieNode node = current.getLeaf(searchWord);
            for (int index = 0; index < charcters.length; index++) {
                node.getChildNode(charcters[index]);
                index1 = index;
            }
            wordIndex.add(index1);


        } else wordIndex.add(null);

        return wordIndex;
    }
     */
}
