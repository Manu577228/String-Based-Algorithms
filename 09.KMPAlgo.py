# Knuth–Morris–Pratt (KMP) is a linear-time string matching algorithm that finds all occurrences of a pattern
# in a text by precomputing a longest-prefix-that-is-also-suffix table (LPS / failure function).
# It uses the LPS to skip unnecessary comparisons, achieving O(n + m) time
# where n = text length and m = pattern length.

# Explanation (intuition + how it works)

# LPS array (failure function) — for each pattern index i, lps[i] stores the
# length of the longest proper prefix of pattern[:i+1] that is also a suffix of pattern[:i+1].
# Proper = not whole string. This tells us where to resume matching in the pattern after a mismatch.

# Build LPS — iterate the pattern once using a two-pointer idea: i scans current position, length keeps track of current matched prefix length. On mismatch you fall back to lps[length-1] (like jumping to the previous possible prefix), never decreasing i unless necessary.

# Search loop — scan the text with i and the pattern with j. On match advance both. On mismatch:

# if j > 0, set j = lps[j-1] (resume from last safe position in pattern),

# else advance i.
# This avoids re-checking characters in text that we already matched.

# Complexity — building LPS is O(m), searching is O(n); total O(n + m). Extra space O(m) for LPS.

def build_lps(p):
    m = len(p)
    lps = [0] * m
    length = 0
    i = 1

    while i < m:
        if p[i] == p[length]:
            length += 1
            lps[i] = length
            i += 1
        else:
            if length != 0:
                length = lps[length - 1]
            else:
                lps[i] = 0
                i += 1
    return lps


def kmp_search(text, pattern):
    n = len(text)
    m = len(pattern)
    if m == 0:
        return []
    lps = build_lps(pattern)
    res = []
    i = 0
    j = 0
    while i < n:
        if text[i] == pattern[j]:
            i += 1
            j += 1
            if j == m:
                res.append(i - j)
                j = lps[j - 1]
        else:
            if j != 0:
                j = lps[j - 1]
            else:
                i += 1
    return res


text = "ABABDABACDABABCABAB"
pattern = "ABABCABAB"

print("Text: ", text)
print("Pattern: ", pattern)
print("\nBuilding LPS array for the pattern...")
lps = build_lps(pattern)
print("LPS array:", lps)
print("\nSearching for pattern occurrences...")
matches = kmp_search(text, pattern)
print("Match starting indices (0-based):", matches)
for idx in matches:
    print(f"Match at index {idx}: '{text[idx: idx + len(pattern)]}'")

