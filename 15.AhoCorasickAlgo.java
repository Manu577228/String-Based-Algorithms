/* ----------------------------------------------------------------------------  */
/*   ( The Authentic JS/JAVA CodeBuff )
 ___ _                      _              _ 
 | _ ) |_  __ _ _ _ __ _ __| |_ __ ____ _ (_)
 | _ \ ' \/ _` | '_/ _` / _` \ V  V / _` || |
 |___/_||_\__,_|_| \__,_\__,_|\_/\_/\__,_|/ |
                                        |__/ 
 */
/* --------------------------------------------------------------------------   */
/*    Youtube: https://youtube.com/@code-with-Bharadwaj                        */
/*    Github : https://github.com/Manu577228                                  */
/*    Portfolio : https://manu-bharadwaj-portfolio.vercel.app/portfolio      */
/* -----------------------------------------------------------------------  */

import java.io.*;
import java.util.*;

class AhoCorasick {
    // Node structure for the trie
    static class Node {
        Map<Character, Node> next = new HashMap<>(); // transitions
        Node fail;                                   // failure link
        List<Integer> out = new ArrayList<>();       // store pattern indexes
    }

    private Node root = new Node();

    // Step 1: Insert patterns into trie
    public void buildTrie(String[] patterns) {
        for (int idx = 0; idx < patterns.length; idx++) {
            String word = patterns[idx];
            Node cur = root;
            for (char ch : word.toCharArray()) {
                cur = cur.next.computeIfAbsent(ch, k -> new Node());
            }
            cur.out.add(idx); // store index of pattern ending here
        }
    }

    // Step 2: Build failure links using BFS
    public void buildFailureLinks() {
        Queue<Node> q = new LinkedList<>();
        // Initialize fail links of depth-1 nodes
        for (Node nxt : root.next.values()) {
            nxt.fail = root;
            q.add(nxt);
        }

        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (Map.Entry<Character, Node> entry : cur.next.entrySet()) {
                char ch = entry.getKey();
                Node nxt = entry.getValue();
                q.add(nxt);

                Node f = cur.fail;
                while (f != null && !f.next.containsKey(ch)) {
                    f = f.fail;
                }
                nxt.fail = (f == null) ? root : f.next.get(ch);
                if (nxt.fail != null) {
                    nxt.out.addAll(nxt.fail.out);
                }
            }
        }
    }

    // Step 3: Search text
    public List<String> search(String text, String[] patterns) {
        List<String> res = new ArrayList<>();
        Node cur = root;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            while (cur != null && !cur.next.containsKey(ch)) {
                cur = cur.fail; // follow failure links
            }
            if (cur == null) {
                cur = root;
                continue;
            }

            cur = cur.next.get(ch);

            for (int idx : cur.out) {
                String word = patterns[idx];
                int pos = i - word.length() + 1;
                res.add("Pattern '" + word + "' found at position " + pos);
            }
        }
        return res;
    }

    // Driver Code
    public static void main(String[] args) throws IOException {
        String[] patterns = {"he", "she", "his", "hers"};
        String text = "ahishers";

        AhoCorasick ac = new AhoCorasick();
        ac.buildTrie(patterns);
        ac.buildFailureLinks();

        List<String> matches = ac.search(text, patterns);

        System.out.println("Matches found:");
        for (String s : matches) {
            System.out.println(s);
        }
    }
}
