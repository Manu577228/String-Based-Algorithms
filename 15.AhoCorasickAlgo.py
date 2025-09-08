# The Aho-Corasick algorithm is a powerful string searching algorithm
# that efficiently finds all occurrences of multiple patterns in a text.
# It builds a finite state machine (trie + failure links) to match all patterns simultaneously in linear time.

# Explanation

# First, we insert all patterns into a trie (prefix tree).

# Then, we build failure links that help us skip to the longest suffix which is
# also a prefix when mismatches occur.

# Finally, we process the input text character by character, following trie edges, a
# nd use failure links to report all matches.

# This allows us to search multiple patterns at once in O(N + M + Z) time, where:

# N = length of text

# M = total length of all patterns

# Z = total number of matches

from collections import deque

class AhoCorasick:
    def __init__(self, patterns):
        self.trie = {}
        self.output = {}
        self.fail = {}
        self.build_trie(patterns)
        self.build_failure_links()

    def build_trie(self, patterns):
        new_state = 0
        for idx, word in enumerate(patterns):
            state = 0
            for ch in word:
                if (state, ch) not in self.trie:
                    new_state += 1
                    self.trie[(state, ch)] = new_state
                state = self.trie[(state, ch)]
            self.output.setdefault(state, []).append(idx)

    def build_failure_links(self):
        q = deque()
        self.fail[0] = 0
        for (state, ch), nxt in list(self.trie.items()):
            if state == 0:
                self.fail[nxt] = 0
                q.append(nxt)

        while q:
            r = q.popleft()
            for (state, ch), nxt in list(self.trie.items()):
                if state == r:
                    q.append(nxt)
                    f = self.fail[state]
                    while (f, ch) not in self.trie and f != 0:
                        f = self.fail[f]
                    self.fail[nxt] = self.trie.get((f, ch), 0)
                    self.output.setdefault(nxt, [])
                    self.output[nxt] += self.output.get(self.fail[nxt], [])

    def search(self, text, patterns):
        state = 0
        results = []
        for i, ch in enumerate(text):
            while (state, ch) not in self.trie and state != 0:
                state = self.fail[state]

            state = self.trie.get((state, ch), 0)

            for pattern_idx in self.output.get(state, []):
                word = patterns[pattern_idx]
                pos = i - len(word) + 1
                results.append((word, pos))
        return results


patterns = ["he", "she", "his", "hers"]
text = "ahishers"

ac = AhoCorasick(patterns)
matches = ac.search(text, patterns)

print("Matches found:")
for word, pos in matches:
    print(f"Pattern `{word}` found at position {pos}")
