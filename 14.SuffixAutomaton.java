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
import java.util.*;

class State {
    Map<Character, Integer> next;   // transitions from this state
    int link;                       // suffix link
    int length;                     // max length of substring for this state

    State() {
        next = new HashMap<>();
        link = -1;
        length = 0;
    }
}

class SuffixAutomaton {
    List<State> states;     // all states
    int last;               // last added state

    SuffixAutomaton() {
        states = new ArrayList<>();
        states.add(new State()); // state 0 = initial
        last = 0;
    }

    void extend(char c) {
        // Step 1: create new state
        int cur = states.size();
        states.add(new State());
        states.get(cur).length = states.get(last).length + 1;

        // Step 2: set transitions from last
        int p = last;
        while (p != -1 && !states.get(p).next.containsKey(c)) {
            states.get(p).next.put(c, cur);
            p = states.get(p).link;
        }

        // Step 3: set suffix link
        if (p == -1) {
            states.get(cur).link = 0;
        } else {
            int q = states.get(p).next.get(c);
            if (states.get(p).length + 1 == states.get(q).length) {
                states.get(cur).link = q;
            } else {
                // Clone state
                int clone = states.size();
                states.add(new State());
                states.get(clone).length = states.get(p).length + 1;
                states.get(clone).next.putAll(states.get(q).next);
                states.get(clone).link = states.get(q).link;

                while (p != -1 && states.get(p).next.get(c) == q) {
                    states.get(p).next.put(c, clone);
                    p = states.get(p).link;
                }

                states.get(q).link = states.get(cur).link = clone;
            }
        }
        last = cur; // update last
    }
}

public class Main {
    public static void main(String[] args) {
        String s = "ababa";   // Input string

        // Build SAM
        SuffixAutomaton sam = new SuffixAutomaton();
        for (char ch : s.toCharArray()) {
            sam.extend(ch);   // extend automaton with each character
        }

        // Output states
        for (int i = 0; i < sam.states.size(); i++) {
            State st = sam.states.get(i);
            System.out.println("State " + i +
                               ": len=" + st.length +
                               ", link=" + st.link +
                               ", transitions=" + st.next);
        }
    }
}
