package ds.linked_lists.book;

import java.util.HashMap;
import java.util.Map;

/*
2.2 Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
Hints: #8, #25, #47, #67, # 126

#8   2.2 What if you knew the linked list size? What is the difference between finding the Kth-to last
         element and finding the Xth element?
#25  2.2 If you don't know the linked list size, can you compute it? How does this impact the
         runtime?
#47  2.2 Try implementing it recursively. If you could find the (K -l)th to last element, can you
         find the Kth element?
#67  2.2 You might find it useful to return multiple values. Some languages don't directly support
         this, but there are workarounds in essentially any language. What are some of those
         workarounds?
#126 2.2 Can you do it iteratively? Imagine if you had two pointers pointing to adjacent nodes
         and they were moving at the same speed through the linked list. When one hits the end
         of the linked list, where will the other be?
* */
public class KthToLast {

    // Hint #1
    public LinkedList<Integer> getKthElementWithTotalLength(LinkedList<Integer> linkedList, int totalLength, int kth){
        if(kth > totalLength){
            return null;
        }
        int targetNode = totalLength - kth;
        int currentIndex = 0;
        LinkedList<Integer> currentNode = linkedList;
        while(null != currentNode){ // O(N)
            currentIndex++;
            if(currentIndex == targetNode){
                return currentNode;
            }else{
                currentNode = currentNode.next;
            }
        }
        return null;
    }


    public static void main(String... args) {
        KthToLast kthToLast = new KthToLast();
        LinkedList<Integer> linkedList = new LinkedList<>(3);
        linkedList.appendToTail(3);
        linkedList.appendToTail(2);
        linkedList.appendToTail(5);
        linkedList.appendToTail(4);
        linkedList.appendToTail(6);
        linkedList.appendToTail(3);
        System.out.println(linkedList);
        //System.out.println(kthToLast.getKthElementWithTotalLength(linkedList, 7, 0).data);// Last node
        //System.out.println(kthToLast.getKthElementWithTotalLength(linkedList, 7, 1).data);// Second Last node

//        System.out.println(kthToLast.getKthElementWithoutTotalLength(linkedList, 0).data);// Last node
//        System.out.println(kthToLast.getKthElementWithoutTotalLength(linkedList, 1).data);// Second Last node
    }

    /*
    * 2.2 Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
    *
    * SOLUTION
    * We will approach this problem both recursively and non-recursively. Remember that recursive solutions are
    * often cleaner but less optimal. For example, in this problem, the recursive implementation is about half the
    * length of the iterative solution but also takes 0 (n) space, where n is the number of elements in the linked
    * list.
    * Note that for this solution, we have defined k such that passing in k = 1 would return the last element, k
    * 2 would return to the second to last element, and so on. It is equally acceptable to define k such that k
    * = e would return the last element.
    *
    * Solution #1: If linked list size is known
    * If the size of the linked list is known, then the kth to last element is the (length - k)th element. We can
    * just iterate through the linked list to find this element. Because this solution is so trivial, we can almost be
    * sure that this is not what the interviewer intended.
    *
    * Solution #2: Recursive
    * This algorithm recurses through the linked list. When it hits the end, the method passes back a counter set
    * to o. Each parent call adds 1 to this counter. When the counter equals k, we know we have reached the kth
    * to last element of the linked list.
    * Implementing this is short and sweet- provided we have a way of "passing back" an integer value through
    * the stack. Unfortunately, we can't pass back a node and a counter using normal return statements. So how
    * do we handle this?
    * Approach A: Don't Return the Element.
    * One way to do this is to change the problem to simply printing the kth to last element. Then, we can pass
    * back the value of the counter simply through return values.
    * 1 int printKthToLast(LinkedListNode head, int k) {
    * 2 if (head == null) {
    * 3 return 0j
    * 4 }
    * 5 int index = printKthToLast(head.next, k) + l j
    * 6 if (index == k) {
    * 7 System.out.println(k + "th to last node is " + head.data)j
    * 8 }
    * 9 return index;
    * 10 }
    * Of course, this is only a valid solution if the interviewer says it is valid.
    * Approach B: Use C++.
    * A second way to solve this is to use C++ and to pass values by reference. This allows us to return the node
    * value, but also update the counter by passing a pointer to it.
    * 1 node* nthToLast(node* head, int k, int& i) {
    * 2 if (head == NULL) {
    * 3 return NULL;
    * 4 }
    * 5 node* nd = nthToLast(head->next, k, i);
    * 6 i = i + 1;
    * 7 if (i == k) {
    * 8 return head;
    * 9 }
    * 10 return nd;
    * 11 }
    * 12
    * 13 node* nthToLast(node* head, int k) {
    * 14 int i = 0;
    * 15 return nthToLast(head, k, i);
    * 16 }
    *
    * Approach C: Create a Wrapper Class.
    * We described earlier that the issue was that we couldn't simultaneously return a counter and an index. If
    * we wrap the counter value with simple class (or even a single element array), we can mimic passing by
    * reference.
    * 1 class Index {
    * 2 public int value =0;
    * 3 }
    * 4
    * 5 LinkedListNode kthToLast(LinkedListNode head, int k) {
    * 6 Index idx = new Index();
    * 7 return kthToLast(head, k, idx);
    * 8 }
    * 9
    * 10 LinkedListNode kthToLast(LinkedListNode head, int k, Index idx) {
    * 11 if (head == nUll) {
    * 12 return null;
    * 13 }
    * 14 LinkedListNode node kthToLast(head.next, k, idx);
    * 15 idx.value = idx.value + 1;
    * 16 if (idx.value == k) {
    * 17 return head;
    * 18 }
    * 19 return node;
    * 20 }
    * Each of these recursive solutions takes 0 (n) space due to the recursive calls.
    * There are a number of other solutions that we haven't addressed. We could store the counter in a static variable. Or, we could create a class that stores both the node and the counter, and return an instance of that
    * class. Regardless of which solution we pick, we need a way to update both the node and the counter in a
    * way that all levels of the recursive stack will see.
    *
    * Solution #3: Iterative
    * A more optimal, but less straightforward, solution is to implement this iteratively. We can use two pointers,
    * pi and p2. We place them k nodes apart in the linked list by putting p2 at the beginning and moving pi
    * k nodes into the list. Then, when we move them at the same pace, pi will hit the end of the linked list after
    * LENGTH - k steps. At that point, p2 will be LENGTH - k nodes into the list, or k nodes from the end.
    * The code below implements this algorithm.
    * 1 LinkedLi stNode nthToLast( LinkedListNode head, int k) {
    * 2 Linked ListNode p1 head;
    * 3 LinkedListNode p2 = head;
    * 4
    * 5 // Move pi k nodes into the list.
    * 6 for (int i = 0; i < k; i++) {
    * 7 if ( p1 == nUll) return null; II Out of bounds
    * 8 p1 = pl. next;
    * 9 }
    * 10
    * 11  //Move them at the same pace. When p1 hits the end, p2 will be at the right element .
    * 13 while (p1 != nUll) {
    * 14 pl pl . next;
    * 15 p2 = p2.next;
    * 16 }
    * 17 return p2;
    * 18 }
    * This algorithm takes O( n) time and O( 1) space.
    **/
}
