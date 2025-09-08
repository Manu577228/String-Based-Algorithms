# A Trie (pronounced "try") is a tree-like data structure used to efficiently store and search strings, 
# especially useful for prefix-based searching. 
# It is commonly used in autocomplete, spell-checkers, and dictionary implementations.

# Explanation

# Each node of a Trie represents a character in the string.

# Words are inserted character by character, creating a path from the root to the end of the word.

# A special flag (is_end) is used to mark the end of a valid word.

# Searching is efficient because common prefixes are stored only once.

class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_end = False

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        node = self.root
        for ch in word:
            if ch not in node.children:
                node.children[ch] = TrieNode()
            node = node.children[ch]
        node.is_end = True

    def search(self, word):
        node = self.root
        for ch in word:
            if ch not in node.children:
                return False
            node = node.children[ch]
        return node.is_end
    
    def starts_with(self, prefix):
        node = self.root
        for ch in prefix:
            if ch not in node.children:
                return False
            node = node.children[ch]
        return True
    
trie = Trie()
trie.insert("apple")
trie.insert("app")

print(trie.search("apple"))
print(trie.search("app"))
print(trie.search("appl"))
print(trie.starts_with("ap"))



