# Edit Distance is the minimum number of operations (insert, delete, replace) 
# required to convert one string into another.
# The Wagner-Fischer algorithm solves this using Dynamic Programming in O(m × n) time.

# Explanation

# Suppose we want to transform string word1 into word2.

# Allowed operations are:

# Insert a character.

# Delete a character.

# Replace a character.

# We use a DP table, where dp[i][j] = minimum operations to convert 
# first i chars of word1 into first j chars of word2.

# Fill base cases:

# If one string is empty → need insertions/deletions.

# Transition:

# If characters match → no operation needed.

# Otherwise → 1 + min(insert, delete, replace)

def edit_distance(s1, s2):
    m, n = len(s1), len(s2)

    dp = [[0] * (n + 1) for _ in range(m + 1)]
    
    for i in range(m + 1):
        dp[i][0] = i
    for j in range(n + 1):
        dp[0][j] = j

    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if s1[i - 1] == s2[j - 1]:
                dp[i][j] = dp[i - 1][j - 1]
            
            else:
                dp[i][j] = 1 + min(
                    dp[i - 1][j],
                    dp[i][j - 1],
                    dp[i - 1][j - 1]
                )

    return dp[m][n]

word1 = "kitten"
word2 = "sitting"
result = edit_distance(word1, word2)
print("Edit Distance between", word1, "and", word2, "is:", result)