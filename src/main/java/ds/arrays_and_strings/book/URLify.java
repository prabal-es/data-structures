package ds.arrays_and_strings.book;

/**
 * 1.3 URLify: Write a method to replace all spaces in a string with '%20: You may assume that the
 * string has sufficient space at the end to hold the additional characters, and that you are given
 * the "true" length of the string. (Note: If implementing in Java, please use a character array so
 * that you can perform this operation in place.)
 * EXAMPLE
 * Input: "Mr John Smith    ", 13
 * Output: "Mr%20J ohn%20Smith"
 * Hints: #53, #118
 *
 * #53.  1.3  It's often easiest to modify strings by going from the end of the string to the beginning.
 * #118  1.3  You might find you need to know the number of spaces. Can you just count them?
 *
 * 2 Solutions with different Big O values
 */
public class URLify {

    /**
     * Final Big O = O(n)
    */
    public String doUrlifyUsingArray(char[] urlChars, int trueLength){
        if(urlChars == null || trueLength <=0){
            return null;
        }
        int spaceCount = 0;
        for(int i=0; i<trueLength;i++){
            if(urlChars[i] == ' '){
                spaceCount++;
            }
        }
        int counter = trueLength + (spaceCount*2);
        if(counter > urlChars.length){
            return null;
        }
        for(int i = trueLength-1; i >=0; i--){
            if(urlChars[i] == ' '){
                urlChars[counter-1] = '0';
                urlChars[counter-2] = '2';
                urlChars[counter-3] = '%';
                counter = counter -3;
            } else {
                urlChars[counter-1] = urlChars[i];
                counter --;
            }
        }
        return new String(urlChars);
    }

    /**
     * Final Big O = O(n)
     */
    public String doUrlifyUsingStringBuilder(char[] urlChars, int trueLength){
        StringBuilder urlifyData = new StringBuilder();
        if(urlChars == null || trueLength <=0){
            return null;
        }
        for(int i = 0; i < trueLength; i++){
            if(urlChars[i]==' '){
                urlifyData.append("%20");
            } else {
                urlifyData.append(urlChars[i]);
            }
        }
        return urlifyData.toString();
    }

    public static void main(String... args){
        URLify urLify = new URLify();
        System.out.println(urLify.doUrlifyUsingArray("Mr John Smith    ".toCharArray(), 13));
        System.out.println(urLify.doUrlifyUsingArray("Mr John Smith     ".toCharArray(), 13));
        System.out.println(urLify.doUrlifyUsingArray("Mr John Smith     ".toCharArray(), 15));
        System.out.println(urLify.doUrlifyUsingArray("Mr John Smith   ".toCharArray(), 13));
        System.out.println(urLify.doUrlifyUsingArray(null, 13));
        System.out.println(urLify.doUrlifyUsingArray("Mr John Smith   ".toCharArray(), 0));

        System.out.println(urLify.doUrlifyUsingStringBuilder("Mr John Smith    ".toCharArray(), 13));
        System.out.println(urLify.doUrlifyUsingStringBuilder("Mr John Smith     ".toCharArray(), 13));
        System.out.println(urLify.doUrlifyUsingStringBuilder("Mr John Smith     ".toCharArray(), 15));
        System.out.println(urLify.doUrlifyUsingStringBuilder("Mr John Smith   ".toCharArray(), 13));
        System.out.println(urLify.doUrlifyUsingStringBuilder(null, 13));
        System.out.println(urLify.doUrlifyUsingStringBuilder("Mr John Smith   ".toCharArray(), 0));
    }
    /**
     * 1.3 URLify: Write a method to replace all spaces in a string with '%2e: You may assume that the string
     * has sufficient space at the end to hold the additional characters, and that you are given the "true"
     * length of the string. (Note: if implementing in Java, please use a character array so that you can
     * perform this operation in place.)
     * EXAMPLE
     * Input:  "Mr John Smith    , 13
     * Output: "Mr%2eJohn%2eSmith"
     *
     * SOLUTION
     * A common approach in string manipulation problems is to edit the string starting from the end
     * and working backwards. This is useful because we have an extra buffer at the end, which allows
     * us to change characters without worrying about what we're overwriting. We will use this approach
     * in this problem. The algorithm employs a two-scan approach. In the first scan, we count the
     * number of spaces. By tripling this number, we can compute how many extra characters we will
     * have in the final string. In the second pass, which is done in reverse order, we actually edit the
     * string. When we see a space, we replace it with %20. If there is no space, then we copy the
     * original character. The code below implements this algorithm.
     * 1 void replaceSpaces(char[] str, int trueLength) {
     * 2    int spaceCount = e, index, i = ej
     * 3    for (i = ej i < trueLengthj i++) {
     * 4        if (str[i] == ' ') {
     * 5            spacecount++;
     * 6        }
     * 7    }
     * 8    index = trueLength + spaceCount * 2j
     * 9    if (trueLength < str.length) str[trueLength] = '/0' // End array
     * 10   for (i = trueLength - Ij i )= ej i --) {
     * 11       if (str[i] == ' ') {
     * 12           str[index - 1] '0';
     * 13           str[index - 2] = '2';
     * 14           str[index - 3] = '%';
     * 15            index = index - 3;
     * 16       } else {
     * 17           str[index - 1] = str[i];
     * 18           index--;
     * 19       }
     * 26   }
     * 21 }
     * We have implemented this problem using character arrays, because Java strings are immutable.
     * If we used strings directly, the function would have to return a new copy of the string,
     * but it would allow us to implement this in just one pass.
    * */
}
