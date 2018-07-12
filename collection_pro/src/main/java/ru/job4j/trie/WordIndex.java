package ru.job4j.trie;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WordIndex {
    private Trie root = new Trie();
    private String[] line;
    private AchoCorasic corasic = new AchoCorasic(100);
    /**
     * Загрузка данных из файла и построение индекса
     * Для построения индекса испотзуется массив строк с разделением на строки
     * @param filename адресс файла
     */
    public void loadFile(String filename) throws IOException {
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
     * Загрузка лямбдой файла в память и добавления его содержимого в trie
     * @param filename фдресс файла
     * @throws IOException обработка искоючения отсутсвия файла
     */
    public void loadFileWithoutSplit(String filename) throws IOException {
        Files.lines(Paths.get(filename), StandardCharsets.UTF_8).forEach(corasic::add);

    }

    /**
     * Возвращает список позиций слова в файле. Если данного слова в файле нет, то возвращается null.
     * @param searchWord искомое слова
     * @return Множество в котором первый символ указывает на позицию в строке, второй символ позиция строки в файле.
     */
    public Set<Integer> getIndexes4Word(String searchWord) {
        Set<Integer> wordIndex = new HashSet<>();
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


        return wordIndex;
    }

    /**
     * Поиск по алгоритму Ахо - Корасик.
     * @param s искомое слово.
     *          передается в меотд seach классв AchoCorasic.
     * @return множество вхождений слова в файле.
     */
    public Set<Integer> getIndexes4AhoAloritm(String s) {
        return corasic.seach(s);

    }

}
