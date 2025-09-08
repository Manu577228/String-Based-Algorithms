# The Z Algorithm is a string-matching algorithm that finds all 
# occurrences of a pattern inside a text in linear time O(n).
# It works by constructing a Z-array, where each element represents the longest substring starting 
# from that position that matches the prefix of the string.

# Imagine you have a pattern (the word you want to search for) and 
# a text (where you want to find the pattern).
# Instead of comparing the pattern with the text character by character 
# (which is slow), Z Algorithm concatenates the pattern, a special delimiter (like $), and the text.

# It then builds the Z-array, which tells us at each index how many characters match with the prefix.
# Whenever a value in the Z-array is equal to the pattern length, we know that the pattern appears 
# in the text at that position.

def calculateZ(s):
    n = len(s)
    Z = [0] * n
    l, r = 0, 0

    for i in range(1, n):
        if i <= r:
            Z[i] = min(r - i + 1, Z[i - l])

        while i + Z[i] < n and s[Z[i]] == s[i + Z[i]]:
            Z[i] += 1

        if i + Z[i] - 1 > r:
            l, r = i, i + Z[i] - 1

    return Z

def searchPattern(text, pattern):
    concat = pattern + "$" + text
    Z = calculateZ(concat)
    result = []

    for i in range(len(pattern) + 1, len(concat)):
        if Z[i] == len(pattern):
            result.append(i - len(pattern) - 1)

    return result

text = "abxabcabcaby"
pattern  = "abcaby"
positions = searchPattern(text, pattern)

print("Pattern found at indices:", positions)
