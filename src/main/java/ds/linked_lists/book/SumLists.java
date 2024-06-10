package ds.linked_lists.book;

/**
 * 2.5 Sum Lists: You have two numbers represented by a linked list, where each node contains a single
 * digit. The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a
 * function that adds the two numbers and returns the sum as a linked list.
 * EXAMPLE
 * Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295.
 * Output: 2 -> 1 -> 9. That is, 912.
 * FOLLOW UP
 * Suppose the digits are stored in forward order. Repeat the above problem.
 * EXAMPLE
 * Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295.
 * Output: 9 -> 1 -> 2. That is, 912.
 * Hints: #7, #30, #71, #95, #109
 *
 * #7   2.5 Of course, you could convert the linked lists to integers, compute the sum, and then
 *           convert it back to a new linked list. If you did this in an interview, your interviewer would
 *           likely accept the answer, and then see if you could do this without converting it to a
 *           number and back.
 * #30  2.5 Try recursion. Suppose you have two lists, A = 1- >5 - >9 (representing 951) and B
 *           2 - > 3 - >6 - > 7 (representing 7632), and a function that operates on the remainder of the
 *           lists (5 - >9 and 3- >6 - > 7). Could you use this to create the sum method? What is the
 *           relationship between sum(1->5->9, 2->3->6->7) and sum(5->9, 3->6->7)?
 * #71  2.5 Make sure you have considered linked lists that are not the same length.
 * #95  2.5 Does your algorithm work on linked lists like 9->7->8 and 6->8->5? Double check that.
 * #109 2.5 For the follow-up question: The issue is that when the linked lists aren't the same length,
 *           the head of one linked list might represent the 1000's place while the other represents
 *           the 1 D's place. What if you made them the same length? Is there a way to modify the
 *           linked list to do that, without changing the value it represents?
* */
public class SumLists {

    LinkedList<Integer> doSum(LinkedList<Integer> l1, LinkedList<Integer>l2){
        LinkedList<Integer> result = null;
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        while(l1 != null && l2!= null){
            s1.append(l1.data);
            s2.append(l2.data);
            l1 = l1.next;
            l2 = l2.next;
        }
        int a1 = Integer.parseInt(s1.reverse().toString());
        int b2 = Integer.parseInt(s2.reverse().toString());
        int sum = a1 + b2;
        char[] resultChar = String.valueOf(sum).toCharArray();
        LinkedList<Integer> lastNode = null;
        for(int i = resultChar.length; i > 0; i-- ){
            if(null == result){
                result = new LinkedList<>(Integer.parseInt(String.valueOf(resultChar[i-1])));
                lastNode = result;
            } else {
                lastNode.next = new LinkedList<>(Integer.parseInt(String.valueOf(resultChar[i-1])));
                lastNode = lastNode.next;
            }
        }
        return result;
    }

    LinkedList<Integer> doSumInOrder(LinkedList<Integer> l1, LinkedList<Integer>l2){
        LinkedList<Integer> result = null;
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        while(l1 != null && l2!= null){
            s1.append(l1.data);
            s2.append(l2.data);
            l1 = l1.next;
            l2 = l2.next;
        }
        int a1 = Integer.parseInt(s1.toString());
        int b2 = Integer.parseInt(s2.toString());
        int sum = a1 + b2;
        char[] resultChar = String.valueOf(sum).toCharArray();
        LinkedList<Integer> lastNode = null;
        for(int i = 0; i <resultChar.length; i++ ){
            if(null == result){
                result = new LinkedList<>(Integer.parseInt(String.valueOf(resultChar[i])));
                lastNode = result;
            } else {
                lastNode.next = new LinkedList<>(Integer.parseInt(String.valueOf(resultChar[i])));
                lastNode = lastNode.next;
            }
        }
        return result;
    }

    public static void main(String... args) {
        SumLists sumlists = new SumLists();
/*        LinkedList<Integer> l1 = new LinkedList<>(7);
        l1.appendToTail(1);
        l1.appendToTail(6);
        LinkedList<Integer> l2 = new LinkedList<>(5);
        l2.appendToTail(9);
        l2.appendToTail(2);

        System.out.println(l1);
        System.out.println(l2);
        System.out.println(sumlists.doSum(l1, l2));*/

        LinkedList<Integer> l1 = new LinkedList<>(6);
        l1.appendToTail(1);
        l1.appendToTail(7);
        LinkedList<Integer> l2 = new LinkedList<>(2);
        l2.appendToTail(9);
        l2.appendToTail(5);

        System.out.println(l1);
        System.out.println(l2);
        System.out.println(sumlists.doSumInOrder(l1, l2));

    }

    /**
     * 2.5 Sum Lists: You have two numbers represented by a linked list, where each node contains a single
     * digit. The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a
     * function that adds the two numbers and returns the sum as a linked list.
     * EXAMPLE
     * Input: (7-> 1 -> 6) + (5 -> 9 -> 2) .Thatis,617 + 295.
     * Output: 2 - > 1 - > 9. That is, 912.
     * FOLLOW UP
     * Suppose the digits are stored in forward order. Repeat the above problem.
     * Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).Thatis,617 + 295.
     * Output: 9 - > 1 - > 2. That is, 912.
     *
     * SOLUTION
     * It's useful to remember in this problem how exactly addition works. Imagine the problem:
     * 617
     * + 2 9 5
     * pg95
     * First, we add 7 and 5 to get 12. The digit 2 becomes the last digit of the number, and 1 gets carried over to
     * the next step. Second, we add 1, 1, and 9 to get 11 . The 1 becomes the second digit, and the other 1 gets
     * carried over the final step. Third and finally, we add 1,6 and 2 to get 9. So, our value becomes 912.
     * We can mimic this process recursively by adding node by node, carrying over any "excess" data to the next
     * node. Let's walk through this for the below linked list:
     * 7 -> 1 -> 6
     * + 5 -> 9 -> 2
     * We do the following:
     * 1. We add 7 and 5 first, getting a result of 12. 2 becomes the first node in our linked list, and we "carry" the
     * 1 to the next sum.
     * List: 2 -> ?
     * 2. We then add 1 and 9, as well as the "carry;' getting a result of 11 . 1 becomes the second element of our
     * linked list, and we carry the 1 to the next sum.
     * List: 2 -> 1 -> ?
     * 3. Finally, we add 6, 2 and our "carry;' to get 9. This becomes the final element of our linked list.
     * List: 2 - > 1 - > 9.
     * The code below implements this algorithm.
     * 1 LinkedListNode addLists(LinkedListNode 11, LinkedListNode 12, int carry) {
     * 2 if (11 == null && 12 == null && carry == 0) {
     * 3 return nullj
     * 4 }
     * 5
     * 6 LinkedListNode result new LinkedListNode()j
     * 7 int value = carryj
     * 8 if (11 != nUll) {
     * 9 value += 11.dataj
     * 10 }
     * 11 if (12 != null) {
     * 12 val ue += 12.dataj
     * 13 }
     * 14
     * 15 result.data value % 18j / * Second digit of number
     * 16
     * 17 / * Re curse
     * 18 if (11 != null I I 12 != nUll) {
     * 19 LinkedListNode more = addLists(ll == null? null: 11. next,
     * 20 12 == null? null: 12.next,
     * 21 value >= 18 ? 1 : 0)j
     * 22 res ult.setNext(more)j
     * 23 }
     * 24 return resultj
     * 25 }
     * In implementing this code, we must be careful to handle the condition when one linked list is shorter than
     * another. We don't want to get a null pointer exception.
     * Follow Up
     * Part B is conceptually the same (recurse, carry the excess), but has some additional complications when it
     * comes to implementation:
     * 1. One list may be shorter than the other, and we cannot handle this "on the fly:' For example, suppose we
     * were adding (1 -> 2 -> 3 -> 4) and (5 -> 6 -> 7). We need to know that the 5 should be "matched"with the
     * 2, not the 1. We can accomplish this by comparing the lengths of the lists in the beginning and padding
     * the shorter list with zeros.
     * 2. In the first part, successive results were added to the tail (Le., passed forward). This meant that the recursive call would be passed the carry, and would return the result (which is then appended to the tail). In
     * this case, however, results are added to the head (Le., passed backward). The recursive call must return
     * the result, as before, as well as the carry. This is not terribly challenging to implement, but it is more
     * cumbersome. We can solve this issue by creating a wrapper class called Partial Sum.
     * The code below implements this algorithm.
     * 1 class PartialSum {
     * 2 public LinkedListNode sum = nullj
     * 3 publ ic int carry = 8j
     * 4 }
     * 5
     * 6 LinkedLi stNode addLists(LinkedListNode 11, LinkedListNode 12) {
     * 7 int lenl length(ll)j
     * 8 int l en2 = length(12)j
     * 9
     * 10 / * Pad the shorter list with zeros - see note (1)
     * 11 if (lenl < len2) {
     * 12 11 = padList(ll, len2 - lenl)j
     * 13 } else {
     * 14 12 = padList(12, lenl - len2)j
     * 15 }
     * 16
     * 17 / * Ad d lists
     * 18 PartialSum sum = addListsHelper(ll, l2)j
     * 19
     * 20 / * If there was a carry value left over, insert this at the front of the list.
     * 21 * Otherwise, just return the linked list.
     * 22 if (s um . carry == 8) {
     * 23 return sum.sum;
     * 24 } else {
     * 25 LinkedListNode result insertBefore(sum.sum, sum. carry);
     * 26 return result;
     * 27 }
     * 28 }
     * 29
     * 36 PartialSum addListsHelper(LinkedListNode 11, LinkedListNode 12) {
     * 31 if (11 == null && 12 == nUll) {
     * 32 PartialSum sum = new PartialSum();
     * 33 return sum;
     * 34 }
     * 35 /* Add smaller digits recursively
     * 36 PartialSum sum = addListsHelper(ll.next, l2.next);
     * 37
     * 38 /* Add carry to current data
     * 39 int val = sum. carry + l1.data + l2.data;
     * 46
     * 41 /* Insert sum of current digits
     * 42 LinkedListNode full_result = insertBefore(sum.sum, val % 16);
     * 43
     * 44 /* Return sum so far, and the carry value
     * 45 sum.sum = full_result;
     * 46 sum. carry val / 16;
     * 47 return sum;
     * 48 }
     * 49
     * 56 /* Pad the list with zeros
     * 51 LinkedListNode padList(LinkedListNode 1, int padding) {
     * 52 LinkedListNode head = 1;
     * 53 for (int i = 6; i < padding; i++) {
     * 54 head = insertBefore(head, a);
     * 55 }
     * 56 return head;
     * 57 }
     * 58
     * 59 /* Helper function to insert node in the front of a linked list
     * 6a LinkedListNode insertBefore(LinkedListNode list, int data) {
     * 61 LinkedListNode node = new LinkedListNode(data);
     * 62 if (list != nUll) {
     * 63 node. next = list;
     * 64 }
     * 65 return node;
     * 66 }
     * Note how we have pulled insertBeforeO, padListO, and lengthO (not listed) into their own
     * methods. This makes the code cleaner and easier to read-a wise thing to do in your interviews!
     * */
}
