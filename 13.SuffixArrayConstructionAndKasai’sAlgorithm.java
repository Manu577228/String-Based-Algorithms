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
/*    Portfolio : https://manu-bharadwaj-portfolio.vercel.app/portfolio       */
/* -----------------------------------------------------------------------  */

import java.io.*;
import java.util.*;

public class Main {

    // Step 1: Function to build suffix array
    static int[] buildSuffixArray(String s) {
        int n = s.length();
        // Create an array of suffixes along with their indices
        String[] suffixes = new String[n];
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            suffixes[i] = s.substring(i);  // Each suffix string
            idx[i] = i;                    // Store starting index
        }

        // Sort suffixes lexicographically by comparator
        Arrays.sort(idx, (a, b) -> suffixes[a].compareTo(suffixes[b]));

        // Convert Integer[] to int[]
        int[] sa = new int[n];
        for (int i = 0; i < n; i++) {
            sa[i] = idx[i];
        }
        return sa; // Return suffix array (indices)
    }

    // Step 2: Kasai’s Algorithm to build LCP array
    static int[] kasaiLCP(String s, int[] sa) {
        int n = s.length();
        int[] lcp = new int[n];
        int[] rank = new int[n];

        // Build rank array: rank[i] = position of suffix starting at i in suffix array
        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int k = 0; // Length of current LCP
        for (int i = 0; i < n; i++) {
            if (rank[i] == n - 1) { // Last suffix in suffix array has no next suffix
                k = 0;
                continue;
            }
            int j = sa[rank[i] + 1]; // Next suffix in suffix array

            // Compare characters while they match
            while (i + k < n && j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            }
            lcp[rank[i]] = k; // Store LCP length
            if (k > 0) k--;   // Reduce length for next iteration
        }
        return lcp;
    }

    public static void main(String[] args) throws IOException {
        String text = "banana";

        // Build suffix array
        int[] sa = buildSuffixArray(text);

        // Build LCP array
        int[] lcp = kasaiLCP(text, sa);

        // Output results
        System.out.println("Text: " + text);

        System.out.print("Suffix Array: ");
        for (int i = 0; i < sa.length; i++) {
            System.out.print(sa[i] + (i == sa.length - 1 ? "" : ", "));
        }
        System.out.println();

        System.out.print("LCP Array: ");
        for (int i = 0; i < lcp.length; i++) {
            System.out.print(lcp[i] + (i == lcp.length - 1 ? "" : ", "));
        }
        System.out.println();
    }
}
