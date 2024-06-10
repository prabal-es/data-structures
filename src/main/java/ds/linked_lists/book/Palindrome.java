package ds.linked_lists.book;

/**
 * 2.6 Palindrome: Implement a function to check if a linked list is a palindrome.
 * Hints: #5, #13, #29, #61, #101
 *
 * #5    2.6 A palindrome is something which is the same when written forwards and backwards.
 *           What if you reversed the linked list?
 * #13   2.6 Try using a stack.
 * #29   2.6 Assume you have the length of the linked list. Can you implement this recursively?
 * #61   2.6 In the recursive approach (we have the length of the list), the middle is the base case:
 *           iSPermutation(middle) is true. The node x to the immediate left of the middle:
 *           What can that node do to check if x- >middle- >y forms a palindrome? Now suppose
 *           that checks out. What about the previous node a? If x- >middle- >y is a palindrome,
 *           how can it check that a - >x - >middle - >y- >b is a palindrome?
 * #101  2.6 Go back to the previous hint. Remember: There are ways to return multiple values. You
 *           can do this with a new class.
* */
public class Palindrome {

    boolean checkPalindrome(LinkedList<Integer> l1){
        LinkedList<Integer> reverseList = getReverseList(l1);

        return isEqual(l1, reverseList);
    }

    boolean isEqual(LinkedList<Integer> l1, LinkedList<Integer> l2){
        boolean check = true;
        while (l1!=null && l2!=null){
            if(!l1.data.equals(l2.data)){
                check = false;
            }
            l1 = l1.next;
            l2 = l2.next;
            if(l1 == null && l2 != null){
                check = false;
            } else if (l1 != null && l2 == null) {
                check = false;
            }
        }
        return check;
    }
    LinkedList<Integer> getReverseList(LinkedList<Integer> l1){
        LinkedList<Integer> reverseListHead = null;
        while(null != l1){
            LinkedList<Integer> node = new LinkedList<>(l1.data);
            node.next = reverseListHead;
            reverseListHead = node;
            l1 = l1.next;
        }
        return reverseListHead;
    }

    public static void main(String... args) {
        Palindrome palindrome = new Palindrome();

        LinkedList<Integer> l1 = new LinkedList<>(3);
        l1.appendToTail(6);
        l1.appendToTail(3);
        //l1.appendToTail(9);

        System.out.println(l1);
        System.out.println(palindrome.checkPalindrome(l1));
        System.out.println(l1);
    }

    /**
     * 2.6 Palindrome: Implement a function to check if a linked list is a palindrome.
     *
     * SOLUTION
     * To approach this problem, we can picture a palindrome like e -> 1 - > 2 - > 1 - > e. We know that,
     * since it's a palindrome, the list must be the same backwards and forwards. This leads us to our first solution.
     *
     * Solution #1: Reverse and Compare
     * Our first solution is to reverse the linked list and compare the reversed list to the original list. If they're the
     * same, the lists are identical.
     * Note that when we compare the linked list to the reversed list, we only actually need to compare the first
     * half of the list. If the first half of the normal list matches the first half of the reversed list, then the second half
     * of the normal list must match the second half of the reversed list.
     * 1 boolean isPalindrome(LinkedListNode head) {
     * 2 LinkedListNode reversed = reverseAndClone(head);
     * 3 return isEqual(head, reversed);
     * 4 }
     * 5
     * 6 LinkedListNode reverseAndClone(LinkedListNode node) {
     * 7 LinkedListNode head = null;
     * 8 while (node != nUll) {
     * 9 LinkedListNode n = new LinkedListNode(node.data); II Clone
     * 16 n.next = head;
     * 11 head n;
     * 12 node = node. next;
     * 13 }
     * 14 return head;
     * 15 }
     * 16
     * 17 boolean isEqual(LinkedListNode one, LinkedListNode two) {
     * 18 while (one != null && two != nUll) {
     * 19 if (one.data != two. data) {
     * 26 return false;
     * 21 }
     * 22 one = one. next;
     * 23 two = two. next;
     * 24 }
     * 25 return one == null && two == null;
     * 26 }
     * Observe that we've modularized this code into reverse and is Equal functions. We've also created a new
     * class so that we can return both the head and the tail of this method. We could have also returned a twoelement array, but that approach is less maintainable.
     * Solution #2: Iterative Approach
     * We want to detect linked lists where the front half of the list is the reverse of the second half. How would we
     * do that? By reversing the front half of the list. A stack can accomplish this.
     * We need to push the first half of the elements onto a stack. We can do this in two different ways, depending
     * on whether or not we know the size of the linked list.
     * If we know the size of the linked list, we can iterate through the first half of the elements in a standard for
     * loop, pushing each element onto a stack. We must be careful, of course, to handle the case where the length
     * of the linked list is odd.
     * If we don't know the size of the linked list, we can iterate through the linked list, using the fast runner / slow
     * runner technique described in the beginning of the chapter. At each step in the loop, we push the data from
     * the slow runner onto a stack. When the fast runner hits the end of the list, the slow runner will have reached
     * the middle of the linked list. By this point, the stack will have all the elements from the front of the linked
     * list, but in reverse order.
     * Now, we simply iterate through the rest of the linked list. At each iteration, we compare the node to the top
     * of the stack. If we complete the iteration without finding a difference, then the linked list is a palindrome.
     * 1 boolean isPalindrome(LinkedListNode head) {
     * 2 LinkedListNode fast head;
     * 3 LinkedListNode slow = head;
     * 4
     * 5 Stack<Integer> stack = new Stack<Integer>();
     * 6
     * 7 1* Push elements from first half of linked list onto stack. When fast runner
     * 8 * (which is moving at 2x speed) reaches the end of the linked list, then we
     * 9 * know we're at the middle
     * 16 while (fast != null && fast. next != nUll) {
     * 11 stack.push(slow.data);
     * 12 slow slow. next;
     * 13 fast = fast.next.next;
     * 14 }
     * 15
     * 16 1* Has odd number of elements, so skip the middle element
     * 17 if (fast != nUll) {
     * 18 slow = slow. next;
     * 19 }
     * 26
     * 21 while (slow != nUll) {
     * 22 int top = stack.pop().intValue();
     * 23
     * 24 1* If values are different, then it's not a palindrome
     * 25 if (top != slow.data) {
     * 26 return false;
     * 27 }
     * 28 slow = slow. next;
     * 29 }
     * 36 return true;
     * 31 }
     * Solution #3: Recursive Approach
     * First, a word on notation: in this solution, when we use the notation node Kx, the variable K indicates the
     * value of the node data, and x (which is either f or b) indicates whether we are referring to the front node
     * with that value or the back node. For example, in the below linked list, node 2b would refer to the second
     * (back) node with value 2.
     * Now, like many linked list problems, you can approach this problem recursively. We may have some intuitive idea that we want to compare element e and element n - 1, element 1 and element n - 2, element 2
     * and element n - 3, and so on, until the middle element(s). For example:
     * e ( 1 ( 2 ( 3 ) 2 ) 1 ) e
     * In order to apply this approach, we first need to know when we've reached the middle element, as this will
     * form our base case. We can do this by passing in length - 2 for the length each time. When the length
     * equals e or 1, we're at the center of the linked list. This is because the length is reduced by 2 each time. Once
     * we've recursed Yz times, length will be down to O.
     * 1 recurse(Node n, int length) {
     * 2 if (length == 6 II length == 1) {
     * 3 return [something]; II At middle
     * 4 }
     * 5 recurse(n.next, length - 2);
     * 6
     * 7 }
     * This method will form the outline of the iSPalindrome method. The "meat" of the algorithm though is
     * comparing node i to node n - i to check if the linked list is a palindrome. How do we do that?
     * Let's examine what the call stack looks like:
     * 1 vl = isPalindrome: list = e ( 1 ( 2 ( 3 ) 2 ) 1 ) e. length = 7
     * 2 v2 = i SPalindrome: list = 1 ( 2 ( 3 ) 2 ) 1 ) e. length = 5
     * 3 v3 = iSPalindrome: list = 2 ( 3 ) 2 ) 1 ) a. length = 3
     * 4 v4 = iSPalindrome: list = 3 ) 2 ) 1 ) a. length = 1
     * 5 returns v3
     * 6 returns v2
     * 7 returns vl
     * 8 returns?
     * In the above call stack, each call wants to check if the list is a palindrome by comparing its head node with
     * the corresponding node from the back of the list. That is:
     * • Line 1 needs to compare node af with node ab
     * • Line 2 needs to compare node 1 f with node lb
     * • Line 3 needs to compare node 2f with node 2b
     * Line 4 needs to compare node 3f with node 3b.
     * If we rewind the stack, passing nodes back as described below, we can do just that:
     * • Line4 sees that it is the middle node (since length = 1), and passes back head. next. The value head
     * equals node 3, so head. next is node 2b.
     * • Line 3 compares its head, node 2f, to returned_node (the value from the previous recursive call),
     * which is node 2b.lfthe values match, it passes a reference to node lb (returned_node. next) up
     * to line 2.
     * • Line 2 compares its head (node 1 f) to returned_node (node lb).lf the values match, it passes a
     * reference to node eb (or, returned_node. next) up to line 1.
     * • Line 1 compares its head, node af, to returned_node, which is node ab. If the values match, it
     * returns true.
     * To generalize, each call compares its head to returned_node, and then passes returned_node. next
     * up the stack. In this way, every node i gets compared to node n - i. If at any point the values do not
     * match, we return false, and every call up the stack checks for that value.
     * But wait, you might ask, sometimes we said we'll return a boolean value, and sometimes we're returning
     * a node. Which is it?
     * It's both. We create a simple class with two members, a boolean and a node, and return an instance of
     * that class.
     * 1 class Result {
     * 2 public LinkedListNode node;
     * 3 publi c boolean result;
     * 4 }
     * The example below illustrates the parameters and return values from this sample list.
     * 1 iSPalindrome: list = e ( 1 ( 2 ( 3 ( 4 ) 3 ) 2 ) 1 ) a. len = 9
     * 2 isPalindrome: list = 1 ( 2 ( 3 ( 4 ) 3 ) 2 ) 1 ) a. len = 7
     * 3 isPalindrome: list = 2 ( 3 ( 4 ) 3 ) 2 ) 1 ) a. len = 5
     * 4
     * 5
     * isPalindrome: list = 3 ( 4 ) 3 ) 2 ) 1 ) e. len = 3
     * isPalindrome: list = 4 ) 3 ) 2 ) 1 ) e. len = 1
     * 6 returns node 3b, true
     * 7 returns node 2b, true
     * 8 returns node 1b, true
     * 9 returns node 0b, true
     * 10 returns null, true
     * Implementing this code is now just a matter of fill ing in the details.
     * 1 boolean isPalindrome(LinkedListNode head) {
     * 2 int length = lengthOfList(head);
     * 3 Result p = isPalindromeRecurse(head, length);
     * 4 return p.result;
     * 5 }
     * 6
     * 7 Result isPalindromeRecurse(LinkedListNode head, int length) {
     * 8 if (head == null I I length (= 0) { // Even number of nodes
     * 9 return new Result(head, true);
     * 10 } else if (length == 1) { // Odd number of nodes
     * 11 return new Result(head.next, true);
     * 12 }
     * 13
     * 14 /* Recurse on sublist.
     * 15 Result res = isPalindromeRecurse(head.next, length - 2);
     * 16
     * 17 / * If child calls are not a palindrome, pass back up
     * 18 * a failure .
     * 19 if (!res.result I I res.node == nUll) {
     * 20 return res;
     * 21 }
     * 22
     * 23 /* Check if matches corresponding node on other side.
     * 24 res. result = (head.data == res.node.data);
     * 25
     * 26 /* Return corresponding node.
     * 27 res.node = res.node.next;
     * 28
     * 29 return res;
     * 30 }
     * 31
     * 32 int lengthOfList(LinkedListNode n) {
     * 33 int size = 0;
     * 34 while (n != nUll) {
     * 35 size++;
     * 36 n = n.next;
     * 37 }
     * 38 return size;
     * 39 }
     * Some of you might be wondering why we went through all this effort to create a special Result class. Isn't
     * there a better way? Not really-at least not in Java.
     * However, if we were implementing this in C or C++, we could have passed in a double pointer.
     * 1 bool isPalindromeRecurse(Node head, int length, Node** next) {
     * 2
     * 3 }
     * It's ugly, but it works.
     * 
     * */
}
