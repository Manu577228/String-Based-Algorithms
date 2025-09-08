# The Rabin–Karp Algorithm is a string searching technique that uses 
# hashing to quickly find occurrences of a pattern in a text.
# It improves efficiency by comparing hash values instead of directly comparing characters.

# 2. Explanation

# Normally, naive string matching compares the pattern with every substring of the text, 
# which can take O(n·m) time (where n = text length, m = pattern length).

# Rabin–Karp improves this by computing a rolling hash for substrings of the text.

# If the hash values match, only then we compare characters (to avoid hash collisions).

# This makes it efficient with an average time complexity of O(n + m).

def rabin_karp(text, pattern):
    d = 256
    q = 101
    n = len(text)
    m = len(pattern)
    p_hash = 0
    t_hash = 0
    h = 1

    for i in range(m-1):
        h = (h * d) % q

    for i in range(m):
        p_hash = (d * p_hash + ord(pattern[i])) % q
        t_hash = (d * t_hash + ord(text[i])) % q

    for i in range(n - m + 1):
        if p_hash == t_hash:
            if text[i:i+m] == pattern:
                print(f"Pattern found at index {i}")
        if i < n - m:
            t_hash = (d * (t_hash - ord(text[i]) * h) + ord(text[i+m])) % q
            if t_hash < 0:
                t_hash += q

text = "ABCCDDAEFG"
pattern = "CDD"
rabin_karp(text, pattern)
