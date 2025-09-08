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

import java.util.*;

public class KMPAlgorithm {

    // Function to build LPS (Longest Prefix Suffix) array
    static int[] buildLPS(String p) {
        int m = p.length();        // length of pattern
        int[] lps = new int[m];    // lps array initialized to 0
        int len = 0;               // current length of longest prefix suffix
        int i = 1;                 // start from index 1
        while (i < m) {
            if (p.charAt(i) == p.charAt(len)) {
                len++;             // extend prefix-suffix length
                lps[i] = len;      // set lps[i]
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // fall back to previous lps
                } else {
                    lps[i] = 0;    // no prefix-suffix match
                    i++;
                }
            }
        }
        return lps;
    }

    // KMP search function
    static List<Integer> kmpSearch(String text, String pat) {
        int n = text.length();     // length of text
        int m = pat.length();      // length of pattern
        List<Integer> res = new ArrayList<>();
        if (m == 0) return res;    // empty pattern -> no matches

        int[] lps = buildLPS(pat); // precompute lps array
        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < n) {
            if (text.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
                if (j == m) {      // full match found
                    res.add(i - j); // store start index
                    j = lps[j - 1]; // continue search using lps
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1]; // use fallback in pattern
                } else {
                    i++;            // move in text
                }
            }
        }
        return res;
    }

    // Main function to demonstrate
    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";

        System.out.println("Text:    " + text);
        System.out.println("Pattern: " + pattern);

        int[] lps = buildLPS(pattern);
        System.out.println("\nLPS Array: " + Arrays.toString(lps));

        List<Integer> matches = kmpSearch(text, pattern);
        System.out.println("\nMatch starting indices (0-based): " + matches);
        for (int idx : matches) {
            System.out.println("Match at index " + idx + ": '" 
                + text.substring(idx, idx + pattern.length()) + "'");
        }
    }
}
