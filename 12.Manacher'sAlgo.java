/* ----------------------------------------------------------------------------  */
/*   ( The Authentic JS/JAVA CodeBuff )                                          */
/*  ___ _                      _              _                                  */
/*  | _ ) |_  __ _ _ _ __ _ __| |_ __ ____ _ (_)                                */
/*  | _ \ ' \/ _` | '_/ _` / _` \ V  V / _` || |                                */
/*  |___/_||_\__,_|_| \__,_\__,_|\_/\_/\__,_|/ |                                */
/*                                     |__/                                      */
/* ----------------------------------------------------------------------------  */
/*    Youtube: https://youtube.com/@code-with-Bharadwaj                         */
/*    Github : https://github.com/Manu577228                                    */
/*    Portfolio : https://manu-bharadwaj-portfolio.vercel.app/portfolio         */
/* ----------------------------------------------------------------------------  */

import java.io.*;

public class ManacherAlgo {

    // Function implementing Manacher's Algorithm
    public static String manacher(String s) {
        // Step 1: Transform the string by inserting '#' between characters
        StringBuilder sb = new StringBuilder("^#");
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i)).append("#");
        }
        sb.append("$");
        String t = sb.toString(); // Example: "babad" -> "^#b#a#b#a#d#$"

        int n = t.length();
        int[] P = new int[n];   // Step 2: Array to store palindrome radius
        int C = 0, R = 0;       // Center (C) and right boundary (R)

        // Step 3: Loop through transformed string
        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * C - i; // Mirror index of i around center C

            // If within current palindrome boundary
            if (i < R) {
                P[i] = Math.min(R - i, P[mirror]);
            }

            // Try to expand palindrome around i
            while (t.charAt(i + 1 + P[i]) == t.charAt(i - 1 - P[i])) {
                P[i]++;
            }

            // If palindrome expands beyond R, update C and R
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
        }

        // Step 4: Find max length palindrome
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }

        // Step 5: Extract substring from original string
        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

    // Driver code
    public static void main(String[] args) throws IOException {
        String s = "babad";
        String result = manacher(s);
        System.out.println("Longest Palindromic Substring: " + result);
    }
}
