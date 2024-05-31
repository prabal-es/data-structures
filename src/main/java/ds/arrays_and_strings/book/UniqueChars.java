package ds.arrays_and_strings.book;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.1 Is Unique: Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 * Hints: #44, #117, #132
 *
 * #44. 1.1 Try a hash table.
 * #117. 1.1 Could a bit vector be useful?
 * #132. 1.1 Can you solve it in O(N log N) time? What might a solution like that look like?
 *
 * 5 Solutions with different Big O values
 */
public class UniqueChars {

    /*
        Final Big O = O(n)
     */
    public boolean isUniqueCharsUsingMap(char[] chars){
        if(null == chars){
            return false;
        }
        Map<Character, Integer> allCharMap = new HashMap<>();
        for(int i = 0; i< chars.length; i++){/*O(n)*/
            if(null != allCharMap.get(chars[i])){/*O(1)*/
                return false;
            }
            allCharMap.put(chars[i], i);
        }
        return true;
    }

    /*
     * O(n)
     * ASCII = 128 chars
     * Extended ASCII = 256 chars
     * Unicode UTF-8 = Extended ASCII = 256 [UTF-8 is capable of encoding all 1,112,064 valid Unicode code points using
     *      one to four one-byte (8-bit) code units.]
     * Unicode UTF-16 = UTF-16 uses a single 16-bit code unit to encode over 60,000 of the most common characters in
     *      Unicode, and a pair of 16-bit code units, called surrogates, to encode the remainder of about 1 million
     *      characters in Unicode.
     *
     * The time complexity for this code is O( n), where n is the length ofthe string. The space complexity is O( 1).
     * (You could also argue the time complexity is O( 1), since the for loop will never iterate through more than
     * 128 characters.} If you didn't want to assume the character set is fixed, you could express the complexity as
     * o (c) space and 0 (min (c, n)) or 0 ( c) time, where c is the size of the character set.
     */
    public boolean isUniqueCharsArray(char[] chars){
        if (null == chars || chars.length > 256)
            return false;
        boolean[] asciis = new boolean[256];
        for(int i = 0; i< chars.length; i++){/*O(n)*/
            if(asciis[chars[i]]){/*O(1)*/
                return false;
            }
            asciis[chars[i]] = true;
        }
        return true;
    }

    /**
     * O(N^2)
     * */
    public boolean isAllCharsUnique(char[] chars){
        if (null == chars)
            return false;
        for(int i = 0; i < chars.length; i++){/*O(n)*/
            for(int j = i+1; j < chars.length; j++){ /*O(n)*/
                if(chars[i] == chars[j]){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * O(N log (N))
     * */
    public boolean isAllCharsUniqueUsingSort(char[] chars){
        if (null == chars)
            return false;
        Arrays.sort(chars);/* Quick sort: O(log(N)) */
        for(int i = 0; i < chars.length-1; i++){ /*O(N-1)*/
            if(chars[i] == chars[i+1]){
                return false;
            }
        }
        return true;
    }

    public static void main(String... args){
        UniqueChars uniqueChars = new UniqueChars();
        System.out.println("Is hello contains unique chars[isUniqueCharsUsingMap]:"+ uniqueChars.isUniqueCharsUsingMap("hello".toCharArray()));
        System.out.println("Is Helo contains unique chars[isUniqueCharsUsingMap]:"+ uniqueChars.isUniqueCharsUsingMap("Helo".toCharArray()));
        System.out.println("Is Prabal contains unique chars[isUniqueCharsUsingMap]:"+ uniqueChars.isUniqueCharsUsingMap("Prabal".toCharArray()));
        System.out.println("Is Prabal contains unique chars[isUniqueCharsUsingMap]:"+ uniqueChars.isUniqueCharsUsingMap(null));
        System.out.println("Is Prabal contains unique chars[isUniqueCharsUsingMap]:"+ uniqueChars.isUniqueCharsUsingMap("".toCharArray()));
        System.out.println("\n");
        System.out.println("Is hello contains unique chars[isUniqueCharsArray]:"+ uniqueChars.isUniqueCharsArray("hello".toCharArray()));
        System.out.println("Is Helo contains unique chars[isUniqueCharsArray]:"+ uniqueChars.isUniqueCharsArray("Helo".toCharArray()));
        System.out.println("Is Prabal contains unique chars[isUniqueCharsArray]:"+ uniqueChars.isUniqueCharsArray("Prabal".toCharArray()));
        System.out.println("Is Prabal contains unique chars[isUniqueCharsArray]:"+ uniqueChars.isUniqueCharsArray(null));
        System.out.println("Is Prabal contains unique chars[isUniqueCharsArray]:"+ uniqueChars.isUniqueCharsArray("".toCharArray()));
        System.out.println("\n");
        System.out.println("Is hello contains unique chars[isAllCharsUnique]:"+ uniqueChars.isAllCharsUnique("hello".toCharArray()));
        System.out.println("Is Helo contains unique chars[isAllCharsUnique]:"+ uniqueChars.isAllCharsUnique("Helo".toCharArray()));
        System.out.println("Is Prabal contains unique chars[isAllCharsUnique]:"+ uniqueChars.isAllCharsUnique("Prabal".toCharArray()));
        System.out.println("Is Prabal contains unique chars[isAllCharsUnique]:"+ uniqueChars.isAllCharsUnique(null));
        System.out.println("Is Prabal contains unique chars[isAllCharsUnique]:"+ uniqueChars.isAllCharsUnique("".toCharArray()));
        System.out.println("\n");
        System.out.println("Is hello contains unique chars[isAllCharsUniqueUsingSort]:"+ uniqueChars.isAllCharsUniqueUsingSort("hello".toCharArray()));
        System.out.println("Is Helo contains unique chars[isAllCharsUniqueUsingSort]:"+ uniqueChars.isAllCharsUniqueUsingSort("Helo".toCharArray()));
        System.out.println("Is Prabal contains unique chars[isAllCharsUniqueUsingSort]:"+ uniqueChars.isAllCharsUniqueUsingSort("Prabal".toCharArray()));
        System.out.println("Is Prabal contains unique chars[isAllCharsUniqueUsingSort]:"+ uniqueChars.isAllCharsUniqueUsingSort(null));
        System.out.println("Is Prabal contains unique chars[isAllCharsUniqueUsingSort]:"+ uniqueChars.isAllCharsUniqueUsingSort("".toCharArray()));
        System.out.println("Is Prabal contains unique chars[isAllCharsUniqueUsingSort]:"+ uniqueChars.isAllCharsUniqueUsingSort("abcdb".toCharArray()));
    }
    /**
     * 1.1 Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
     * cannot use additional data structures?
     *
     * SOLUTION
     * You should first ask your interviewer if the string is an ASCII string or a Unicode string. Asking this question
     * will show an eye for detail and a solid foundation in computer science. We'll assume for simplic
     *
     * One solution is to create an array of boolean values, where the flag at index i indicates whether character
     * i in the alphabet is contained in the string. The second time you see this character you can immediately
     * return false.
     * We can also immediately return false if the string length exceeds the number of unique characters in the
     * alphabet. After all, you can't form a string of 280 unique characters out of a 128-character alphabet.
     * I It's also okay to assume 256 characters. This would be the case in extended ASCII. You should
     * clarify your assumptions with your interviewer.
     * The code below implements this algorithm.
     * 1 boolean isUniqueChars(String str) {
     * 2 if (str.length() > 128) return false;
     * 3
     * 4 boolean[] char_set = new boolean[128];
     * 5 for (int i = 8; i < str.length(); i++) {
     * 6 int val = str.charAt(i);
     * 7 if (char_set[val]) { II Already found this char in string
     * 8 return false;
     * 9 }
     * 18 char_set[val] = true;
     * 11 }
     * 12 return true;
     * 13 }
     * The time complexity for this code is O( n), where n is the length ofthe string. The space complexity is O( 1).
     * (You could also argue the time complexity is O( 1), since the for loop will never iterate through more than
     * 128 characters.} If you didn't want to assume the character set is fixed, you could express the complexity as
     * o (c) space and 0 (min (c, n)) or 0 ( c) time, where c is the size of the character set.
     *
     * We can reduce our space usage by a factor of eight by using a bit vector. We will assume, in the below code,
     * that the string only uses the lowercase letters a through z. This will allow us to use just a single into
     * 1 boolean isUniqueChars(String str) {
     * 2 int checker = a;
     * 3 for (int i = a; i < str.length(); i++) {
     * 4 int val = str.charAt(i) - 'a';
     * 5 if «checker & (1 « val» > a) {
     * 6 return false;
     * 7 }
     * 8 checker 1= (1 « val);
     * 9 }
     * la return true;
     * 11 }
     * If we can't use additional data structures, we can do the following:
     * 1. Compare every character of the string to every other character of the string. This will take O( n2) time
     * and 0 (1) space.
     * 2. If we are allowed to modify the input string, we could sort the string in O( n log( n» time and then
     * linearly check the string for neighboring characters that are identical. Careful, though: many sorting
     * algorithms take up extra space.
     * These solutions
    * */
}
