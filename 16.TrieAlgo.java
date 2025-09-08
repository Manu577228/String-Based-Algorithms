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
/*    Portfolio : https://manu-bharadwaj-portfolio.vercel.app/portfolio       */
/* -----------------------------------------------------------------------  */

import java.io.*;
import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children;  // Map to store child nodes
    boolean isEnd;                      // Flag to mark end of word

    TrieNode() {
        children = new HashMap<>();
        isEnd = false;
    }
}

class Trie {
    private TrieNode root;

    Trie() {
        root = new TrieNode();   // Root is always empty
    }

    // Insert a word into the Trie
    void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());  // Add node if missing
            node = node.children.get(ch);                   // Move to the next node
        }
        node.isEnd = true;   // Mark end of word
    }

    // Search for a word in the Trie
    boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.children.containsKey(ch)) {   // Path doesn’t exist
                return false;
            }
            node = node.children.get(ch);
        }
        return node.isEnd;   // True only if word ends here
    }

    // Check if any word starts with a given prefix
    boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return false;
            }
            node = node.children.get(ch);
        }
        return true;   // Prefix exists
    }
}

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");     // Insert word 'apple'
        trie.insert("app");       // Insert word 'app'

        System.out.println(trie.search("apple"));   // True
        System.out.println(trie.search("app"));     // True
        System.out.println(trie.search("appl"));    // False
        System.out.println(trie.startsWith("ap"));  // True
    }
}
