package ru.job4j.trie;


import java.util.*;

/**
 * Класс узла нагруженного дерева.
 */
public class TrieNode {
    private static final int ALPHABET = 26;
    private char letter;
    private boolean isWord;
    private Map<Character, TrieNode> children;

    public void setChildren(char c, TrieNode node) {
        this.children.put(c, node);
    }

    public void setWord(boolean value) {
        this.isWord = value;
    }

    public TrieNode(char letter) {
        this.isWord = false;
        this.letter = letter;
        children = new HashMap<>(ALPHABET);
    }


    /**
     * Метод получения потомков дааного символа.
     * @param letter символ.
     * @return  потомок символа, null если не найдены.
     */
    protected TrieNode getChild(char letter) {
        if (children != null) {
            if (children.containsKey(letter)) {
                return children.get(letter);
            }
        }
        return null;
    }

    /**
     * Метод возвращает всех потомков символа
     * @param c символ
     * @return узел потомкв символа, null если не найден.
     */
    public TrieNode getChildNode(char c) {
        Map<Character, TrieNode> children = this.children;
        if (children != null) {
            for (TrieNode child : children.values()) {
                if (c == child.letter) {
                    return child;
                }
            }
        }
        return null;
    }
}