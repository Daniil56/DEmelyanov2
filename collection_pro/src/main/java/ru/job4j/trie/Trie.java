package ru.job4j.trie;

/**
 * Класс нагруженного дерева.
 */
public class Trie {
    private TrieNode root;

    /**
     * Конструктр создает корневой узел со значение ' '.
     */
    public Trie() {
        this.root = new TrieNode(' ');
    }

    /**
     * Метод вставки строки в дерево.
     * Строка разбивает на символы, которые становятся ключами и значениями узла.
     * Каждая строка представляет собой цепочку узлов.
     * @param word Строка добавляемая в дерево.
     */
    public void insert(String word) {

        int length = word.length();
        TrieNode current = this.root;

        if (length == 0) {
            current.setWord(true);
        }
        for (int index = 0; index < length; index++) {

            char letter = word.charAt(index);
            TrieNode child = current.getChild(letter);

            if (child != null) {
                current = child;
            } else {
                current.setChildren(letter, new TrieNode(letter));
                current = current.getChild(letter);
            }
            if (index == length - 1) {
                current.setWord(true);
            }
        }
    }


    /**
     * Метод проверяет находится ли строка str в дереве
     * @param str искомая строка
     * @return является ли str частью дерева
     */
    protected boolean exists(String str) {
      TrieNode current = this.root;
        if (str == null) {
            return true;
        } else {
            char[] characters = str.toCharArray();
            int var5 = characters.length;

            for (int var4 = 0; var4 < var5; ++var4) {
                char character = characters[var4];
                TrieNode childNode = current.getChild(character);
                if (childNode == null) {
                    return false;
                }

               current = childNode;
            }

            return true;
        }
    }

    /**
     * Метод возвращающий узел принадлежашей строке.
     * @param str строка
     * @return Узел принадлежащий строке
     */
    public TrieNode getLeaf(String str) {
        TrieNode current = this.root;
        if (str == null) {
            return this.root;
        } else {
            char[] characters = str.toCharArray();
            int var5 = characters.length;

            for (int var4 = 0; var4 < var5; ++var4) {
                char character = characters[var4];
                TrieNode childNode = current.getChild(character);
                if (childNode == null) {
                    return null;
                }
                current = childNode;
            }
            return current;
        }
    }

    }