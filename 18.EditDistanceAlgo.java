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
/*    Portfolio : https://manu-bharadwaj-portfolio.vercel.app/portfolio      */
/* -----------------------------------------------------------------------  */

import java.io.*;

public class EditDistance {
    
    // Function to calculate Edit Distance using Wagner-Fischer DP Algorithm
    public static int editDistance(String s1, String s2) {
        int m = s1.length(); // length of first string
        int n = s2.length(); // length of second string
        
        // Step 1: Create DP table of size (m+1) x (n+1)
        int[][] dp = new int[m + 1][n + 1];
        
        // Step 2: Fill base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;  // converting s1[0..i] to empty string requires i deletions
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;  // converting empty string to s2[0..j] requires j insertions
        }
        
        // Step 3: Fill DP table for all subproblems
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // chars match → copy diagonal
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j],          // deletion
                        Math.min(dp[i][j - 1], // insertion
                                 dp[i - 1][j - 1]) // replacement
                    );
                }
            }
        }
        
        // Final answer is bottom-right cell
        return dp[m][n];
    }
    
    public static void main(String[] args) throws IOException {
        // Example input
        String word1 = "kitten";
        String word2 = "sitting";
        
        // Compute edit distance
        int result = editDistance(word1, word2);
        
        // Print result
        System.out.println("Edit Distance between " + word1 + " and " + word2 + " is: " + result);
    }
}
