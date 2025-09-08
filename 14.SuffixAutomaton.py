# A Suffix Automaton (SAM) is a powerful data structure that represents
# all substrings of a given string efficiently.
# It is the smallest deterministic finite automaton (DFA) that accepts all suffixes of a string.

# Explanation
# A Suffix Automaton compresses all substrings into a graph-like structure with states and transitions.
# Each state represents a set of substrings (end positions).
# SAM allows substring queries, longest common substring, and counting distinct substrings in O(n).

class State:
    def __init__(self):
        self.next = {}
        self.link = -1
        self.length = 0

class SuffixAutomaton:
    def __init__(self):
        self.states = [State()]
        self.last = 0

    def extend(self, c):
        cur = len(self.states)
        self.states.append(State())
        self.states[cur].length = self.states[self.last].length + 1

        p = self.last
        while p != -1 and c not in self.states[p].next:
            self.states[p].next[c] = cur
            p = self.states[p].link

        if p == -1:
            self.states[cur].link = 0
        else:
            q = self.states[p].next[c]
            if self.states[p].length + 1 == self.states[q].length:
                self.states[cur].link = q
            else:
                clone = len(self.states)
                self.states.append(State())
                self.states[clone].length = self.states[p].length + 1
                self.states[clone].next = self.states[q].next.copy()
                self.states[clone].link = self.states[q].link

                while p != -1 and self.states[p].next.get(c) == q:   # ✅ must use get()
                    self.states[p].next[c] = clone
                    p = self.states[p].link

                self.states[q].link = self.states[cur].link = clone

        self.last = cur


s = "ababa"
sam = SuffixAutomaton()
for ch in s:
    sam.extend(ch)

for i, st in enumerate(sam.states):
    print(f"State {i}: len={st.length}, link={st.link}, transitions={st.next}")

