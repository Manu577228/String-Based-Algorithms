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

public class RollingHashExample {
    static final int p = 31;                // base for characters (like size of alphabet)
    static final int M = 1000000009;        // large prime modulus

    // Function to compute hash of a string
    static long computeHash(String s) {
        long h = 0;
        long p_pow = 1;

        for (int i = 0; i < s.length(); i++) {
            // (s.charAt(i) - 'a' + 1) converts 'a'->1, 'b'->2, ...
            h = (h + (s.charAt(i) - 'a' + 1) * p_pow) % M;
            p_pow = (p_pow * p) % M;
        }
        return h;
    }

    public static void main(String[] args) throws Exception {
        String text = "abacaba";
        String pattern = "aba";

        // Step 1: compute hash of pattern
        long patternHash = computeHash(pattern);

        // Step 2: precompute prefix hashes for text
        int n = text.length();
        long[] prefixHash = new long[n + 1];
        long[] p_pow = new long[n + 1];
        p_pow[0] = 1;

        for (int i = 1; i <= n; i++) {
            prefixHash[i] = (prefixHash[i - 1] + (text.charAt(i - 1) - 'a' + 1) * p_pow[i - 1]) % M;
            p_pow[i] = (p_pow[i - 1] * p) % M;
        }

        // Step 3: search pattern in text using rolling hash
        System.out.print("Pattern found at indices: ");
        for (int i = 0; i <= n - pattern.length(); i++) {
            long curHash = (prefixHash[i + pattern.length()] - prefixHash[i] + M) % M;

            if (curHash == (patternHash * p_pow[i]) % M) {
                System.out.print(i + " ");
            }
        }
    }
}
