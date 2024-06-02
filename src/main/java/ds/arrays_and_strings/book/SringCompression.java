package ds.arrays_and_strings.book;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.6 String Compression: Implement a method to perform basic string compression using the counts
 * of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3. If the
 * "compressed" string would not become smaller than the original string, your method should return
 * the original string. You can assume the string has only uppercase and lowercase letters (a - z).
 * Hints: #92, # 110
 *
 * #92.  1.6 Do the easy thing first. Compress the string, then compare the lengths.
 * #110. 1.6 Be careful that you aren't repeatedly concatenating strings together. This can be very
 *           inefficient.
 */
public class SringCompression {

    /*
        Final Big O = O(n)
        String concatenation == O(n^2)
        So use String builder
     */
    public String doStringCompressionUsingWhile(String inputString){
        if(null == inputString || inputString.isEmpty()){
            return inputString;
        }
        char[] inputChars = inputString.toCharArray();
        int counter = 0, currentCharCount = 0;
        char currentChar = inputChars[0];
        StringBuilder result = new StringBuilder();
        while(counter < inputChars.length){
            if(inputChars[counter] == currentChar){
                currentCharCount++;
            }else {
                result.append(currentChar).append(currentCharCount);
                currentCharCount = 1;
                currentChar=inputChars[counter];
            }
            counter++;
            if(counter == inputChars.length){
                result.append(currentChar).append(currentCharCount);
            }
        }

        if(inputChars.length <= result.length()){
            return inputString;
        } else {
            return result.toString();
        }
    }

    public String doStringCompressionUsingFor(String inputString) {
        if (null == inputString || inputString.isEmpty()) {
            return inputString;
        }
        int sameCharCount = 0;
        StringBuilder compressedString = new StringBuilder();
        for(int i = 0; i < inputString.length(); i++){
            sameCharCount ++;
            if((i+1) >= inputString.length() || inputString.charAt(i) != inputString.charAt(i+1)){
                compressedString.append(inputString.charAt(i)).append(sameCharCount);
                sameCharCount = 0;
            }
        }
        return inputString.length() <= compressedString.length()? inputString : compressedString.toString();
    }

    public String doStringCompressionUsingForAfterLengthCheck(String inputString) {
        if (null == inputString || inputString.isEmpty()) {
            return inputString;
        }
        int stringCompressedCount = stringCompressedCount(inputString);
        if(stringCompressedCount >= inputString.length()){
            System.out.println("In check count failed");
            return inputString;
        }
        int sameCharCount = 0;
        StringBuilder compressedString = new StringBuilder(stringCompressedCount);
        for(int i = 0; i < inputString.length(); i++){
            sameCharCount ++;
            if(i+1 >= inputString.length() || inputString.charAt(i) != inputString.charAt(i+1)){
                compressedString.append(inputString.charAt(i)).append(sameCharCount);
                sameCharCount = 0;
            }
        }
        return inputString.length() <= compressedString.length()? inputString : compressedString.toString();
    }

    private int stringCompressedCount(String inputString){
        int sameCharCount = 0, compressedLength = 0;
        for(int i = 0; i < inputString.length(); i++){
            sameCharCount ++;
            if(i+1 >= inputString.length() || inputString.charAt(i) != inputString.charAt(i+1)){
                compressedLength+=2;
                sameCharCount = 0;
            }
        }
        return compressedLength;
    }

    public static void main(String... args) {
        SringCompression sringCompression = new SringCompression();
        System.out.println("aabcccccaaa\n"+sringCompression.doStringCompressionUsingWhile("aabcccccaaa"));
        System.out.println("acdefghi\n"+sringCompression.doStringCompressionUsingWhile("acdefghi"));

        System.out.println("\nSecond Solution:\naabcccccaaa\n"+sringCompression.doStringCompressionUsingFor("aabcccccaaa"));
        System.out.println("acdefghi\n"+sringCompression.doStringCompressionUsingFor("acdefghi"));

        System.out.println("\nThird  Solution:\naabcccccaaa\n"+sringCompression.doStringCompressionUsingForAfterLengthCheck("aabcccccaaa"));
        System.out.println("acdefghi\n"+sringCompression.doStringCompressionUsingForAfterLengthCheck("acdefghi"));
    }
    /**
     *  1.6 String Compression: Implement a method to perform basic string compression using the counts
     * of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the
     * "compressed" string would not become smaller than the original string, your method should return
     * the original string. You can assume the string has only uppercase and lowercase letters (a - z).
     * SOLUTION
     * At first glance, implementing this method seems fairly straightforward, but perhaps a bit tedious. We iterate
     * through the string, copying characters to a new string and counting the repeats. At each iteration, check
     * ifthe current character is the same as the next character. If not, add its compressed version to the result.
     * How hard could it be?
     * 1 String compressBad(String str) {
     * 2 String compressedString = "";
     * 3 int countConsecutive = 0j
     * 4 for (int i = 0j i < str.length()j i++) {
     * 5 countConsecutive++;
     * 6
     * 7 /* If next character is different than current, append this char to result.*
     * 8 if (i + 1 >= str. length() I I str.charAt(i) != str.charAt(i + 1)) {
     * 9 compressedString += " " + str.charAt(i) + countConsecutivej
     * 10 countConsecutive = 0;
     * 11 }
     * 12 }
     * 13 return compressedString.length() < str.length() ? compressedString str;
     * 14 }
     * This works. Is it efficient, though? Take a look at the runtime of this code.
     * The runtime is O(p + k2), where p is the size of the original string and k is the number of character
     * sequences. For example, if the string is aabccdeeaa, then there are six character sequences. It's slow
     * because string concatenation operates in O( n2) time (see StringBuilder on pg 89).
     * We can fix this by using a StringBuilder.
     * 1 String compress(String str) {
     * 2 StringBuilder compressed = new StringBuilder();
     * 3 int countConsecutive = 0;
     * 4 for (int i = 0; i < str.length(); i++) {
     * 5 countConsecutive++;
     * 6
     * 7 /* If next character is different than current, append this char to result.
     * 8 if (i + 1 >= str.length() I I str.charAt(i) != str.charAt(i + 1» {
     * 9 compressed.append(str.charAt(i»;
     * 10 compressed.append( countConsecutive);
     * 11 countConsecutive = 0;
     * 12 }
     * 13 }
     * 14 return compressed.length() < str.length() ? compressed .toString() : str;
     * 15 }
     * Both of these solutions create the compressed string first and then return the shorter of the input string
     * and the compressed string.
     * Instead, we can check in advance. This will be more optimal in cases where we don't have a large number of
     * repeating characters. It will avoid us having to create a string that we never use. The downside of this is that
     * it causes a second loop through the characters and also adds nearly duplicated code.
     * 1 String compress(String str) {
     * 2 1* Check final length and return input string if it would be longer.
     * 3 int finalLength = countCompression(str);
     * 4 if (finalLength )= str.length(» return str;
     * 5
     * 6 StringBuilder compressed = new StringBuilder(finalLength); II initial capacity
     * 7 int countConsecutive = 0;
     * 8 for (int i = 0; i < str.length(); i++) {
     * 9 countConsecutive++;
     * 10
     * 11 1* If next character is different than current, append this char to result.
     * 12 if (i + 1 >= str.lengthO II str.charAt(i) != str.cha rAt(i + 1» {
     * 13 compressed.append(str.charAt(i»;
     * 14 compressed.append(countConsecutive);
     * 15 countConsecutive = 0;
     * 16 }
     * 17 }
     * 18 return compressed.toString();
     * 19 }
     * 20
     * 21 int countCompression(String str) {
     * 22 int compressed Length = 0;
     * 23 int countConsecutive = 0;
     * 24 for (int i = 0; i < str.length(); i++) {
     * 25 countConsecutive++;
     * 26
     * 27 1* If next character is different than current, increase the length.
     * 28 if (i + 1 )= str. lengthO II str.charAt(i) != str.charAt(i + 1» {
     * 29 compressed Length += 1 + String.valueOf(countConsecutive).length();
     * 30 countConsecutive = 0;
     * 31 }
     * 32 }
     * 33 return compressed Length;
     * 34 }
     * One other benefit of this approach is that we can initialize StringBuilder to its necessary capacity
     * up-front. Without this, StringBuilder will (behind the scenes) need to double its capacity every time it
     * hits capacity. The capacity could be double what we ultimately need.
     * */
}
