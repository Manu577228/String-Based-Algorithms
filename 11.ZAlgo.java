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
/*    Portfolio : https://manu-bharadwaj-portfolio.vercel.app/portfolio        */
/* -----------------------------------------------------------------------  */

import java.io.*;
import java.util.*;

public class Main {

    // Function to build Z-array
    static int[] calculateZ(String s) {
        int n = s.length();                   // Length of the string
        int[] Z = new int[n];                 // Initialize Z-array with zeros
        int l = 0, r = 0;                     // Left and right boundaries of Z-box

        for (int i = 1; i < n; i++) {         // Start from index 1, prefix always at index 0
            if (i <= r) {                     // If i lies inside the Z-box
                Z[i] = Math.min(r - i + 1, Z[i - l]);
            }
            // Try expanding substring starting from i
            while (i + Z[i] < n && s.charAt(Z[i]) == s.charAt(i + Z[i])) {
                Z[i]++;
            }
            // Update Z-box if we expanded past r
            if (i + Z[i] - 1 > r) {
                l = i;
                r = i + Z[i] - 1;
            }
        }
        return Z;                             // Return constructed Z-array
    }

    // Function to search pattern occurrences in text
    static List<Integer> searchPattern(String text, String pattern) {
        String concat = pattern + "$" + text; // Concatenate pattern + special char + text
        int[] Z = calculateZ(concat);         // Generate Z-array for concatenated string
        List<Integer> result = new ArrayList<>(); // To store indices of matches

        for (int i = pattern.length() + 1; i < concat.length(); i++) {
            if (Z[i] == pattern.length()) {   // If Z-value equals length of pattern → match
                result.add(i - pattern.length() - 1); // Store index in text
            }
        }
        return result;                        // Return all positions of matches
    }

    public static void main(String[] args) throws IOException {
        String text = "abxabcabcaby";         // Example text
        String pattern = "abcaby";            // Example pattern

        List<Integer> positions = searchPattern(text, pattern);
        System.out.println("Pattern found at indices: " + positions);
    }
}
