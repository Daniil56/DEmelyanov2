package ru.job4j.trie;
import java.util.*;

public class AchoCorasic {

    private static final int ALPHABET_SIZE = 26;
    private Node[] nodes;
    private int nodeCount;
    private List<Integer> position = new ArrayList<>(Collections.nCopies(ALPHABET_SIZE, -1));
    private int indexP = 1;
    private List<String> name = new ArrayList<>();

    private static class Node {
        private int parent;
        private char charFromParent;
        private int suffLink = -1;
        private int[] children = new int[ALPHABET_SIZE];
        private int[] transitions = new int[ALPHABET_SIZE];
        private boolean leaf;
            {
                Arrays.fill(children, -1);
                Arrays.fill(transitions, -1);
            }
        }


        public AchoCorasic(int maxNodes) {
            nodes = new Node[maxNodes];
            nodes[0] = new Node();
            nodes[0].suffLink = 0;
            nodes[0].parent = -1;
            nodeCount = 1;


        }

        public void add(String s) {
            int cur = 0;

            if (s.isEmpty()) {
                this.nodes[cur].leaf = true;
                return;
            }
            for (char ch : s.toCharArray()) {
                int c = ch - 'a';
                if (nodes[cur].children[c] == -1) {
                    nodes[nodeCount] = new Node();
                    nodes[nodeCount].parent = cur;
                    nodes[nodeCount].charFromParent = ch;
                    nodes[cur].children[c] = nodeCount++;
                }
                cur = nodes[cur].children[c];
            }
            nodes[cur].leaf = true;
            position.set(cur, indexP++);
            name.add(s);
        }

        public int suffLink(int nodeIndex) {
            Node node = nodes[nodeIndex];
            if (node.suffLink == -1) {
                node.suffLink = node.parent == 0 ? 0 : transition(
                        suffLink(node.parent), node.charFromParent);
            }
            return node.suffLink;
        }

        public int transition(int nodeIndex, char ch) {
            int c = ch - 'a';
            Node node = nodes[nodeIndex];
            if (node.transitions[c] == -1) {
                node.transitions[c] = node.children[c] != -1 ? node.children[c]
                        : (nodeIndex == 0 ? 0 : transition(suffLink(nodeIndex), ch));
            }
            return node.transitions[c];
        }

        public Set<Integer> seach(String s) {
            Set<Integer> setLine = new HashSet<>();
            for (int i = 0; i < name.size(); i++) {
                if (name.get(i).equals(s)) {
                    setLine.add(i + 1);
                } else if (!isWord(s)) {
                    setLine.add(null);
                }
            }
         return setLine;
        }

        public boolean isWord(String s) {
            boolean result;
            int node = 0;
            for (char ch : s.toCharArray()) {
                node = this.transition(node, ch);
            }
                 result = nodes[node].leaf;
            return result;
    }
}
