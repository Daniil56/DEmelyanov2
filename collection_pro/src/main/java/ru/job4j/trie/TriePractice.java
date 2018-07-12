package ru.job4j.trie;

import java.util.ArrayList;
import java.util.List;

public class TriePractice {
        private char c = 0;
        private TriePractice[] children;
        private boolean word;
        private int[] charArray = new int[27];
    private List<String> pattern = new ArrayList<>(26);
    private List<Integer> indexlist = new ArrayList<>(26);

        public TriePractice() {
            this.c = 0;
            this.children = new TriePractice[26];
            this.word = false;
        }
    public TriePractice(String s) {
        this.children = new TriePractice[26];
        pattern = new ArrayList<>(26);
        charArray = new int[27];
       add(s);
    }

        public void add(String s) {

            if (s.isEmpty()) {
                this.word = true;
                return;
            }
             c = s.charAt(0);
            int indexChar = s.charAt(0) - 'a';
            indexlist.add(indexChar);
            if (indexChar >= 0 & indexChar <= 26) {
                charArray[indexChar]++;
                s = s.substring(1); //удаляем обработанную букву из слова
                if (charArray[indexChar] == 1) {
                    TriePractice nextWord = new TriePractice(s);
                    children[indexChar] = nextWord;
                } else {
                    children[indexChar].add(s);
                }
            }


            pattern.add(s);

        }

        public boolean isWord(String s) {
            if (s.isEmpty()) {
                return this.word;
            }


            char letter = s.charAt(0);
            int index = s.charAt(0) - 'a';
            if (this.children[index] == null) {
                return false;
            }
            return this.children[index].isWord(s.substring(1));
        }
    }

