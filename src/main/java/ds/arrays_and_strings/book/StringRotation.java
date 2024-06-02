package ds.arrays_and_strings.book;

/**
 * 1.9 String Rotation: Assume you have a method isSubstring which checks if one word is a substring
 * of another. Given two strings, S1 and S2, write code to check if 52 is a rotation of S1 using only one
 * call to isSubstring (e.g., "waterbottle" is a rotation of"erbottlewat").
 * Hints: #34, #88, #104
 *
 * #34  1.9 If a string is a rotation of another, then it's a rotation at a particular point. For example,
 *          a rotation of water bottle at character 3 means cutting waterbottle at character 3
 *          and putting the right half (erbottle) before the left half (wat).
 * #88  1.9 We are essentially asking if there's a way of splitting the first string into two parts, x and
 *          y, such that the first string is xy and the second string is yx. For example, x = wat and
 *          y = erbottle. The first string is xy = waterbottle. The second string is yx =
 *          erbottlewat.
 * #104 1.9 Think about the earlier hint. Then think about what happens when you concatenate
 *          erbottlewat to itself. You get erbottlewaterbottlewat.
 *
 */
public class StringRotation {

    /*
    * o = O(N^2)
    * */
    public boolean doStringRotationCheck(String s1, String s2){
        return (s1+s1).contains(s2);
    }



    public static void main(String... args){
        StringRotation stringRotation = new StringRotation();
        System.out.println(stringRotation.doStringRotationCheck("waterbottle", "erbottlewat"));
    }
    /**
     * 1.9 String Rotation: Assume you have a method i5Substring which checks if one word is a substring
     * of another. Given two strings, S1 and S2, write code to check if S2 is a rotation of 51 using only one
     * call to isSubstring (e.g., Uwaterbottleuis a rotation ofuerbottlewatU).
     *
     * SOLUTION
     * If we imagine that S2 is a rotation of S1, then we can ask what the rotation point is. For example, if you
     * rotate waterbottle after wat, you get erbottlewat. In a rotation, we cut S1 into two parts, x and y,
     * and rearrange them to get 52.
     * S1 = xy = waterbottle
     * x = wat
     * y = erbottle
     * S2 = yx = erbottlewat
     * So, we need to check if there's a way to split 51 into x and y such that xy = S1 and yx = S2. Regardless of
     * where the division between x and y is, we can see that yx will always bea substring ofxyxy. That is, 52 will
     * always be a substring of S1S1.
     *
     * And this is precisely how we solve the problem: simply do i5Sub5tring( 5151, 52).
     * The code below implements this algorithm.
     * 1 boolean i5Rotation(String 51, String 52) {
     * 2 int len = sl.length();
     * 3 /* Check that 51 and 52 are equal length and not empty
     * 4 if (len == s2.length() && len> 8) {
     * 5 /* Concatenate 51 and sl within new buffer
     * 6 String 5151 = 51 + 51;
     * 7 return isSubstring(slsl, 52);
     * 8 }
     * 9 return false;
     * 18 }
     * The runtime of this varies based on the runtime of isSub5tring. But if you assume that i5Substring
     * runs in O(A+B) time (on strings of length A and B), then the runtime of i5Rotation is O( N) .
     *
    * */
}
