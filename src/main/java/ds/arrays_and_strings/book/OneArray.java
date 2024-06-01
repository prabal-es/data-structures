package ds.arrays_and_strings.book;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.5 One Away: There are three types of edits that can be performed on strings: insert a character,
 * remove a character, or replace a character. Given two strings, write a function to check if they are
 * one edit (or zero edits) away.
 * EXAMPLE
 * pale, ple -> true
 * pales, pale -> true
 * pale, bale -> true
 * pale, bake -> false
 * Hints: #23, #97, #130
 *
 * #23.  1.5 Start with the easy thing. Can you check each of the conditions separately?
 * #97.  1.5 What is the relationship between the "insert character" option and the "remove character"
 *       option? Do these need to be two separate checks?
 * #130. 1.5 Can you do all three checks in a single pass?
 */
public class OneArray {

    public boolean checkOneEditAway(char[] baseCharSet, char[] targetCharSet){
        if(null == baseCharSet || null == targetCharSet || new String(baseCharSet).equals(new String(targetCharSet))){
            return false;
        }
        if(baseCharSet.length == targetCharSet.length){// Replace
            return checkForOneReplaceChar(baseCharSet, targetCharSet);
        } else if (baseCharSet.length == targetCharSet.length-1){// Insert
            return checkForOneInsertChar(baseCharSet, targetCharSet);
        } else if (baseCharSet.length-1 == targetCharSet.length) {// remove
            return checkForOneRemoveChar(baseCharSet, targetCharSet);
        }
        return false;
    }
    private boolean checkForOneRemoveChar(char[] baseCharSet, char[] targetCharSet) {
        int baseCharIndex = 0, targetCharIndex = 0;
        boolean isOneEditAway = false;
        while (baseCharIndex < baseCharSet.length && targetCharIndex < targetCharSet.length) {
            if(baseCharSet[baseCharIndex] != targetCharSet[targetCharIndex]){
                if(isOneEditAway){
                    return false;
                }
                isOneEditAway = true;
                baseCharIndex ++;
            } else {
                baseCharIndex++;
                targetCharIndex++;
            }
        }
        return true;
    }
    private boolean checkForOneInsertChar(char[] baseCharSet, char[] targetCharSet){
        int baseCharIndex =0, targetCharIndex = 0;
        boolean isOneEditAway = false;
        while(baseCharIndex < baseCharSet.length && targetCharIndex < targetCharSet.length){
            if(baseCharSet[baseCharIndex] != targetCharSet[targetCharIndex]){
                if(isOneEditAway){
                    return false;
                }
                isOneEditAway = true;
                targetCharIndex ++;
            } else {
                baseCharIndex++;
                targetCharIndex++;
            }
        }
        return true;
    }
    private boolean checkForOneReplaceChar(char[] baseCharSet, char[] targetCharSet){
        boolean difCheck = false;
        for(int i = 0; i < baseCharSet.length; i++){
            if (Character.toLowerCase(baseCharSet[i]) != Character.toLowerCase(targetCharSet[i])){
                if (difCheck){
                    return false;
                } else {
                    difCheck = true;
                }
            }
        }
        return difCheck;
    }

    public boolean checkOneEditAwaySolution2(char[] baseCharSet, char[] targetCharSet){
        if(null == baseCharSet || null == targetCharSet || new String(baseCharSet).equals(new String(targetCharSet))){
            return false;
        }
        if(baseCharSet.length == targetCharSet.length){// Replace
            return checkForOneReplaceChar(baseCharSet, targetCharSet);
        } else if (baseCharSet.length == targetCharSet.length-1){// Insert
            return checkForOneInsertOrDeleteChar(baseCharSet, targetCharSet);
        } else if (baseCharSet.length-1 == targetCharSet.length) {// remove
            return checkForOneInsertOrDeleteChar(targetCharSet, baseCharSet);
        }
        return false;
    }

    public boolean checkOneEditAwaySolution3(char[] baseCharSet, char[] targetCharSet){
        if(null == baseCharSet || null == targetCharSet || new String(baseCharSet).equals(new String(targetCharSet))){
            return false;
        }
        if(baseCharSet.length == targetCharSet.length){// Replace
            return checkForOneReplaceOrInsertOrDeleteChar(baseCharSet, targetCharSet);
        } else if (baseCharSet.length == targetCharSet.length-1){// Insert
            return checkForOneReplaceOrInsertOrDeleteChar(baseCharSet, targetCharSet);
        } else if (baseCharSet.length-1 == targetCharSet.length) {// remove
            return checkForOneReplaceOrInsertOrDeleteChar(targetCharSet, baseCharSet);
        }
        return false;
    }
    private boolean checkForOneReplaceOrInsertOrDeleteChar(char[] baseCharSetSmall, char[] targetCharSetBig){
        int baseCharIndex =0, targetCharIndex = 0;
        boolean isOneEditAway = false;
        while(baseCharIndex < baseCharSetSmall.length && targetCharIndex < targetCharSetBig.length){
            if(baseCharSetSmall[baseCharIndex] != targetCharSetBig[targetCharIndex]){
                if(isOneEditAway){
                    return false;
                }
                isOneEditAway = true;
                targetCharIndex ++;
                if(baseCharSetSmall.length == targetCharSetBig.length){
                    baseCharIndex++;
                }
            } else {
                baseCharIndex++;
                targetCharIndex++;
            }
        }
        return true;
    }
    private boolean checkForOneInsertOrDeleteChar(char[] baseCharSetSmall, char[] targetCharSetBig){
        int baseCharIndex =0, targetCharIndex = 0;
        boolean isOneEditAway = false;
        while(baseCharIndex < baseCharSetSmall.length && targetCharIndex < targetCharSetBig.length){
            if(baseCharSetSmall[baseCharIndex] != targetCharSetBig[targetCharIndex]){
                if(isOneEditAway){
                    return false;
                }
                isOneEditAway = true;
                targetCharIndex ++;
            } else {
                baseCharIndex++;
                targetCharIndex++;
            }
        }
        return true;
    }
    public static void main(String... args){
        OneArray oneArray = new OneArray();

/*        System.out.println(oneArray.checkOneEditAway("pale".toCharArray(), "bale".toCharArray()));
        System.out.println(oneArray.checkOneEditAway("pale".toCharArray(), "bake".toCharArray()));

        System.out.println(oneArray.checkOneEditAway("ple".toCharArray(), "pale".toCharArray()));
        System.out.println(oneArray.checkOneEditAway("pale".toCharArray(), "pales".toCharArray()));

        System.out.println(oneArray.checkOneEditAway("pales".toCharArray(), "pale".toCharArray()));
        System.out.println(oneArray.checkOneEditAway("palea".toCharArray(), "pals".toCharArray()));

        // Solution 2
        System.out.println(oneArray.checkOneEditAwaySolution2("pale".toCharArray(), "bale".toCharArray()));
        System.out.println(oneArray.checkOneEditAwaySolution2("pale".toCharArray(), "bake".toCharArray()));

        System.out.println(oneArray.checkOneEditAwaySolution2("ple".toCharArray(), "pale".toCharArray()));
        System.out.println(oneArray.checkOneEditAwaySolution2("pale".toCharArray(), "pales".toCharArray()));

        System.out.println(oneArray.checkOneEditAwaySolution2("pales".toCharArray(), "pale".toCharArray()));
        System.out.println(oneArray.checkOneEditAwaySolution2("palea".toCharArray(), "pals".toCharArray()));*/

        // Solution 3
        System.out.println(oneArray.checkOneEditAwaySolution3("pale".toCharArray(), "bale".toCharArray()));
        System.out.println(oneArray.checkOneEditAwaySolution3("pale".toCharArray(), "bake".toCharArray()));

        System.out.println(oneArray.checkOneEditAwaySolution3("ple".toCharArray(), "pale".toCharArray()));
        System.out.println(oneArray.checkOneEditAwaySolution3("pale".toCharArray(), "pales".toCharArray()));

        System.out.println(oneArray.checkOneEditAwaySolution3("pales".toCharArray(), "pale".toCharArray()));
        System.out.println(oneArray.checkOneEditAwaySolution3("palea".toCharArray(), "pals".toCharArray()));
    }
    /**
     * 1.5 One Away: There are three types of edits that can be performed on strings: insert a character,
     * remove a character, or replace a character. Given two strings, write a function to check if they are
     * one edit (or zero edits) away.
     * EXAMPLE
     * pale, ple -) true
     * pales, pale -) true
     * pale, bale -) true
     * pale, bae -) false
     *
     * SOLUTION
     * There is a "brute force" algorithm to do this. We could check all possible strings that are one edit away by
     * testing the removal of each character (and comparing), testing the replacement of each character (and
     * comparing), and then testing the insertion of each possible character (and comparing).
     * That would be too slow, so let's not bother with implementing it.
     * This is one of those problems where it's helpful to think about the "meaning" of each of these operations.
     * What does it mean for two strings to be one insertion, replacement, or removal away from each other?
     * Replacement: Consider two strings, such as bale and pale, that are one replacement away. Yes, that
     * does mean that you could replace a character in bale to make pale. But more precisely, it means that
     * they are different only in one place.
     * Insertion: The strings apple and aple are one insertion away. This means that if you compared the
     * strings, they would be identical-except for a shift at some point in the strings.
     * Removal: The strings apple and aple are also one removal away, since removal is just the inverse of
     * insertion.
     *
     * We can go ahead and implement this algorithm now. We'll merge the insertion and removal check into one
     * step, and check the replacement step separately.
     * Observe that you don't need to check the strings for insertion, removal, and replacement edits. The lengths
     * of the strings will indicate which of these you need to check.
     * 1 boolean oneEditAway(String first, String second) {
     * 2 if (first.length() == second.length()) {
     * 3 return oneEditReplace(first, second)j
     * 4 } else if (first.length() + 1 == second.length()) {
     * 5 return oneEditlnsert(first, second)j
     * 6 } else if (first.length() - 1 == second.length()) {
     * 7 return oneEditlnsert(second, first)j
     * 8 }
     * 9 return falsej
     * 1e }
     * 11
     * 12 boolean oneEditReplace(String s1, String S2) {
     * 13 boolean foundDifference = falsej
     * 14 for (i nt i = ej i < s1.length()j i++) {
     * 15 if (s1.charAt(i) != s2.charAt(i)) {
     * 16 if (foundDifference) {
     * 17 return falsej
     * 18 }
     * 19
     * 20 foundDifference = true;
     * 21 }
     * 22 }
     * 23 return true;
     * 24 }
     * 25
     * 26 /* Check if you can insert a character into s1 to make s2.
     * 27 boolean oneEditlnsert(String s1, String s2) {
     * 28 int index! = 0;
     * 29 int index2 = 0;
     * 30 while (index2 < s2.length() && index1 < s1.length()) {
     * 31 if (s1.charAt(index1) != s2.charAt(index2)) {
     * 32 if (index1 != index2) {
     * 33 return false;
     * 34 }
     * 35 index2++;
     * 36 } else {
     * 37 index1++;
     * 38 index2++;
     * 39 }
     * 40 }
     * 41 return true;
     * 42 }
     * This algorithm (and almost any reasonable algorithm) takes 0 (n) time, where n is the length of the shorter
     * string.
     * I Why is the runtime dictated by the shorter string instead of the longer string? If the strings are
     * the same length (plus or minus one character), then it doesn't matter whether we use the longer
     * string or the shorter string to define the runtime. If the strings are very different lengths, then the
     * algorithm will terminate in 0 (1) time. One really, really long string therefore won't significantly
     * extend the runtime. It increases the runtime only if both strings are long.
     * We might notice that the code for oneEdi tReplace is very similar to that for oneEdi tInsert. We can
     * merge them into one method.
     * To do this, observe that both methods follow similar logic: compare each character and ensure that the
     * strings are only different by one. The methods vary in how they handle that difference. The method
     * oneEditReplace does nothing other than flag the difference, whereas oneEditlnsert increments
     * the pointer to the longer string. We can handle both of these in the same method.
     * 1 boolean oneEditAway(String first, String second) {
     * 2 /* Length checks.
     * 3 if (Math.abs(first.length() - second.length()) > 1) {
     * 4 return false;
     * 5 }
     * 6
     * 7 /* Get shorter and longer string.
     * 8 String s1 = first.length() < second.length()
     * 9 String s2 = first.length() < second.length()
     * 10
     * 11 int index1 = 0;
     * 12 int index2 = 0;
     * 13 boolean foundDifference = false;
     * first : second;
     * second : first j
     * 14 while (index2 < s2.length() && index1 < s1.length()) {
     * 15 if (s1.charAt(index1) != s2.charAt(index2)) {
     * 16 /* Ensure that this is the first difference found.
     * 17 if (foundDifference) return false;
     * 18 foundDifference = true;
     * 19
     * 20 if (sl.length() == s2.length()) { liOn replace, move shorter pointer
     * 21 indexl++;
     * 22 }
     * 23 } else {
     * 24 indexl++; II If matching, move shorter pointer
     * 25 }
     * 26 index2++ j II Always move pointer for longe r string
     * 27 }
     * 28 return truej
     * 29 }
     * Some people might argue the first approach is better, as it is clearer and easier to follow. Others, however,
     * will argue that the second approach is better, since it's more compact and doesn't duplicate code (which
     * can facilitate maintainability).
     * You don't necessarily need to "pick a side:'You can discuss the tradeoffs with your interviewer. 
     * */
}
