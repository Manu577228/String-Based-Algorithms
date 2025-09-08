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

public class RabinKarp {

    // Function to search for a pattern inside the given text
    static void rabinKarp(String text, String pattern) {
        int d = 256;              // Number of characters in ASCII alphabet
        int q = 101;              // A prime number for modulus to reduce collisions

        int n = text.length();    // Length of text
        int m = pattern.length(); // Length of pattern

        int pHash = 0;            // Hash value for the pattern
        int tHash = 0;            // Hash value for current text window
        int h = 1;                // Multiplier for rolling hash

        // Precompute h = (d^(m-1)) % q
        for (int i = 0; i < m - 1; i++) {
            h = (h * d) % q;
        }

        // Calculate initial hash values for pattern and first window of text
        for (int i = 0; i < m; i++) {
            pHash = (d * pHash + pattern.charAt(i)) % q;
            tHash = (d * tHash + text.charAt(i)) % q;
        }

        // Slide the pattern over the text one character at a time
        for (int i = 0; i <= n - m; i++) {
            // If hash values match, check actual characters
            if (pHash == tHash) {
                if (text.substring(i, i + m).equals(pattern)) {
                    System.out.println("Pattern found at index " + i);
                }
            }

            // Calculate rolling hash for next window
            if (i < n - m) {
                tHash = (d * (tHash - text.charAt(i) * h) + text.charAt(i + m)) % q;

                // Ensure hash value is positive
                if (tHash < 0) {
                    tHash += q;
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = "ABCCDDAEFG";   // Input text
        String pattern = "CDD";       // Pattern to search
        rabinKarp(text, pattern);     // Function call
    }
}
