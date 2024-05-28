package ds.arrays_and_strings.book;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.2 Check Permutation: Given two strings, write a method to decide if one is a permutation
 * of the other.
 * Hints: #1, #84, #122, #131
 *
 * #1. 1.2 Describe what it means for two strings to be permutations of each other. Now, look at
 *      that definition you provided. Can you check the strings against that definition?
 * #84. 1.2 There is one solution that is 0 (N log N) time. Another solution uses some space, but
 *      isO(N) time.
 * #122. 1.2 Could a hash table be useful?
 * #131. 1.2 Two strings that are permutations should have the same characters, but in different
 *      orders. Can you make the orders the same?
 *
 */
public class Permutation {

    /*
        Final Time Big O = O(n^2)
        Final Space Big O = O(n)
     */
    public boolean permutationCheck(String s1, String s2){
        if(null == s1 || null == s2){
            return false;
        } else if (s1.length() != s2.length()) {
            return false;
        }
        boolean[] checks = new boolean[s1.length()]; /* Space: O(n)*/
        for(int i = 0; i < s1.length(); i++){ /* Time: O(n)*/
            for(int j=0; j < s2.length(); j++){ /* Time: O(n)*/
                if(s1.charAt(i) == s2.charAt(j)){
                    checks[i] = true;
                    break;
                }
            }
        }
        for(boolean check: checks){/* Time: O(n)*/
            if (!check){
                return false;
            }
        }
        return true;
    }

    /*
        Final Time Big O = O(n)
        Final Space Big O = O(n)
     */
    public boolean permutationCheckUsingIntArray(String s1, String s2){
        if(null == s1 || null == s2){
            return false;
        } else if (s1.length() != s2.length()) {
            return false;
        }
        int[] checks = new int[256]; /* Assumption ASCII; Space: O(n)*/
        for(int i = 0; i < s1.length(); i++){ /* Time: O(n)*/
            checks[s1.charAt(i)]++;
        }
        for(int i = 0; i < s2.length(); i++){ /* Time: O(n)*/
            checks[s2.charAt(i)]--;
            if(checks[s2.charAt(i)]<0){
                return false;
            }
        }
        return true;
    }

    /*
    Final Time Big O = O(n)
    Final Space Big O = O(n)
    */
    public boolean permutationCheckUsingMap(String s1, String s2){
        if(null == s1 || null == s2){
            return false;
        } else if (s1.length() != s2.length()) {
            return false;
        }
        Map<Character,Integer> stringChars = new HashMap<>(); /* Space: O(n)*/
        for(int i = 0; i < s1.length(); i++){ /* Time: O(n)*/
            stringChars.put(s1.charAt(i), i);
        }
        for(char currentChar: s2.toCharArray()){/* Time: O(n)*/
            if(null == stringChars.get(currentChar)){
                return false;
            }
        }
        return true;
    }

    /*
    Final Time Big O = O(N log N)
    Final Space Big O = ?
    */
    public boolean permutationCheckUsingSort(String s1, String s2){
        if(null == s1 || null == s2){
            return false;
        } else if (s1.length() != s2.length()) {
            return false;
        }
        return sort(s1).equals(sort(s2));
    }

    private String sort(String s){
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String... args) {
        Permutation permutation = new Permutation();
        System.out.println(permutation.permutationCheckUsingMap("abc", "bca"));
        System.out.println(permutation.permutationCheckUsingMap("abcd", "bcae"));
        System.out.println(permutation.permutationCheckUsingMap("abc", "bcae"));
        System.out.println(permutation.permutationCheckUsingMap(null, "bcae"));
        System.out.println(permutation.permutationCheckUsingMap("abc", null));
        System.out.println(permutation.permutationCheckUsingMap("{}[]", "[]{}"));
        System.out.println("\n");
        System.out.println(permutation.permutationCheck("abc", "bca"));
        System.out.println(permutation.permutationCheck("abcd", "bcae"));
        System.out.println(permutation.permutationCheck("abc", "bcae"));
        System.out.println(permutation.permutationCheck(null, "bcae"));
        System.out.println(permutation.permutationCheck("abc", null));
        System.out.println(permutation.permutationCheck("{}[]", "[]{}"));
        System.out.println("\n");
        System.out.println(permutation.permutationCheckUsingIntArray("abc", "bca"));
        System.out.println(permutation.permutationCheckUsingIntArray("abcd", "bcae"));
        System.out.println(permutation.permutationCheckUsingIntArray("abc", "bcae"));
        System.out.println(permutation.permutationCheckUsingIntArray(null, "bcae"));
        System.out.println(permutation.permutationCheckUsingIntArray("abc", null));
        System.out.println(permutation.permutationCheckUsingIntArray("{}[]", "[]{}"));
        System.out.println("\n");
        System.out.println(permutation.permutationCheckUsingSort("abc", "bca"));
        System.out.println(permutation.permutationCheckUsingSort("abcd", "bcae"));
        System.out.println(permutation.permutationCheckUsingSort("abc", "bcae"));
        System.out.println(permutation.permutationCheckUsingSort(null, "bcae"));
        System.out.println(permutation.permutationCheckUsingSort("abc", null));
        System.out.println(permutation.permutationCheckUsingSort("{}[]", "[]{}"));
    }

}

/**
 *1.2 Check Permutation: Given two strings, write a method to decide if one is a permutation of the
 * other.
 *
 * SOLUTION
 * Like in many questions, we should confirm some details with our interviewer. We should understand if the
 * permutation comparison is case sensitive. That is: is God a permutation of dog? Additionally, we should
 * ask if whitespace is significant. We will assume for this problem that the comparison is case sensitive and
 * whitespace is significant. So, "god " is different from "dog".
 * Observe first that strings of different lengths cannot be permutations of each other. There are two easy
 * ways to solve this problem, both of which use this optimization.
 * Solution #1: Sort the strings.
 * If two strings are permutations, then we know they have the same characters, but in different orders. Therefore, sorting the strings will put the characters from two permutations in the same order. We just need to
 * compare the sorted versions of the strings.
 * 1 String sort(String s) {
 * 2 char[] content = s.toCharArray();
 * 3 java.util.Arrays.sort(content);
 * 4 return new String(content);
 * 5 }
 * 6
 * 7 boolean permutation(String s, String t) {
 * 8 if (s.length() != t.length(» {
 * 9 return false;
 * 10 }
 * 11 return sort(s).equals(sort(t» ;
 * 12 }
 * Though this algorithm is not as optimal in some senses, it may be preferable in one sense: It's clean, simple
 * and easy to understand. In a practical sense, this may very well be a superior way to implement the problem.
 * However, if efficiency is very important, we can implement it a different way.
 *
 * Solution #2: Check if the two strings have identical character counts.
 * We can also use the definition of a permutation-two words with the same character counts-to implement this algorithm. We simply iterate through this code, counting how many times each character appears.
 * Then, afterwards, we compare the two arrays.
 * 1 boolean permutation(String s, String t) {
 * 2 if (s.lengthO != t.lengthO) {
 * 3 return false ;
 * 4 }
 * 5
 * 6 int[] letters = new int[128]; II Assumption
 * 7
 * 8 char[] s_array = s.toCharArray();
 * 9 for (char c : s_array) { II count number of each char i n s.
 * 10 letters[c]++;
 * 11 }
 * 12
 * 13 for (int i = e; i < t.length(); i++) {
 * 14 int c = (int) t . charAt(i)j
 * 15 letters[c] --;
 * 16 if (letters[c] < 0) {
 * 17 return false;
 * 18 }
 * 19 }
 * 20
 * 21 return true;
 * 22 }
 * Note the assumption on line 6. In your interview, you should always check with your interviewer about the
 * size of the character set. We assumed that the character set was ASCII
 * */