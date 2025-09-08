# Manacher’s Algorithm is an efficient algorithm to find the longest palindromic substring in a given string.
# It runs in O(n) time, making it much faster than the naive O(n²) or dynamic programming approaches.

# Explanation

# A palindrome is a string that reads the same forwards and backwards (like racecar, aba).

# The brute-force approach checks every possible substring → O(n²).

# Manacher’s Algorithm improves this by using previously computed palindrome lengths and symmetry to skip redundant checks.

# It works by:

# Transforming the string with separators (#) so even and odd length palindromes can be handled uniformly.

# Example: "abba" → ^#a#b#b#a#$ (^ and $ are sentinels).

# Maintaining an array P where P[i] stores the radius of palindrome centered at position i.

# Using two variables:

# C → the center of the current palindrome.

# R → right boundary of the current palindrome.

# Expanding palindromes while reusing results from mirror indices.

# Result: The maximum value in P gives the length and position of the longest palindrome substring.

def manacher(s):
    t = "^#" + "#".join(s) + "#$"
    n = len(t)

    P = [0] * n
    C, R = 0, 0

    for i in range(1, n - 1):
        mirror = 2*C - i

        if i < R:
            P[i] = min(R - i, P[mirror])

        while t[i + 1 + P[i]] == t[i - 1 - P[i]]:
            P[i] += 1

        if i + P[i] > R:
            C, R = i, i + P[i]

    max_len = max(P)
    center_index = P.index(max_len)

    start = (center_index - max_len) // 2
    return s[start: start + max_len]

s = "babad"
print("Longest Palindromic Substring:", manacher(s))






  