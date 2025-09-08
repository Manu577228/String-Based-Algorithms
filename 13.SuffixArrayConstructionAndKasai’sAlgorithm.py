# Suffix Array: A sorted array of all suffixes of a string. 
# It helps in efficient string pattern searching and solving many substring problems.

# Kasai’s Algorithm: An algorithm to construct the Longest Common Prefix (LCP) array 
# from a suffix array in linear time.

# Explanation

# A suffix of a string is just the string starting from some index till the end.
# Example: For "banana", suffixes are: "banana", "anana", "nana", "ana", "na", "a".

# The suffix array is simply the indices of suffixes when arranged in lexicographic (dictionary) order.

# The LCP array stores the length of the longest common prefix between 
# every pair of adjacent suffixes in the suffix array.

# Kasai’s Algorithm builds the LCP array efficiently in O(n) using the suffix array and a rank array.

def build_suffix_array(s):
    n = len(s)
    suffixes = [(s[i:], i) for i in range(n)]
    suffixes.sort()
    return [suffix[1] for suffix in suffixes]

def kasais_lcp(s, sa):
    n = len(s)
    lcp = [0] * n
    rank = [0] * n

    for i in range(n):
        rank[sa[i]] = i

        k = 0
        for i in range(n):
            if rank[i] == n - 1:
                k = 0
                continue
            j = sa[rank[i] + 1]
            while i + k < n and j + k < n and s[i + k] == s[j + k]:
                k += 1
            lcp[rank[i]] = k
            if k > 0:
                k -= 1
    return lcp

text = "banana"
sa = build_suffix_array(text)
lcp = kasais_lcp(text, sa)

print("Text", text)
print("Suffix Array:", sa)
print("LCP Array:", lcp)