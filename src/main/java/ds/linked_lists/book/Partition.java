package ds.linked_lists.book;

/*
* 2.4 Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
* before all nodes greater than or equal to x. lf x is contained within the list, the values of x only need
* to be after the elements less than x (see below). The partition element x can appear anywhere in the
* "right partition"; it does not need to appear between the left and right partitions.
* EXAMPLE
* Input: 3 -> 5 -> 8 -> 5 - > 10 -> 2 -> 1 [partition = 5)
* Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
* Hints: #3, #24
*
* #3   2.4 There are many solutions to this problem, most of which are equally optimal in runtime.
*      Some have shorter, cleaner code than others. Can you brainstorm different solutions?
* #24  2.4 Consider that the elements don't have to stay in the same relative order. We only need
*      to ensure that elements less than the pivot must be before elements greater than the
*      pivot. Does that help you come up with more solutions?
* */
public class Partition {


    LinkedList<Integer> doPartition(LinkedList<Integer> n, int point) {
        if (n == null || n.next == null) {
            return null; // Failure
        }
        LinkedList<Integer> before = null;
        LinkedList<Integer> end = null;
        while(n != null){
            if(n.data < point){
                if(before == null){
                    before = new LinkedList<Integer>(n.data);
                } else {
                    before.appendToTail(n.data);
                }
            } else {
                if(end == null){
                    end = new LinkedList<Integer>(n.data);
                } else {
                    end.appendToTail(n.data);
                }
            }
            n = n.next;
        }
        LinkedList<Integer> currentNode = before;
        while(null != currentNode.next){
            currentNode = currentNode.next;
        }
        currentNode.next = end;
        return before;
    }
    public static void main(String... args) {
        Partition partition = new Partition();
        LinkedList<Integer> linkedList = new LinkedList<>(3);
        linkedList.appendToTail(3);
        linkedList.appendToTail(5);
        linkedList.appendToTail(8);
        linkedList.appendToTail(5);
        linkedList.appendToTail(10);
        linkedList.appendToTail(2);
        linkedList.appendToTail(1);
        System.out.println(linkedList);
        System.out.println(partition.doPartition(linkedList, 5));
        System.out.println(linkedList);
    }

    /**
     * 2.4 Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
     * before all nodes greater than or equal to x. lf x is contained within the list, the values of x only need
     * to be after the elements less than x (see below). The partition element x can appear anywhere in the
     * "right partition"; it does not need to appear between the left and right partitions.
     * EXAMPLE
     * Input: 3 -> 5 -> 8 -> 5 - > 10 -> 2 -> 1 [partition = 5)
     * Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
     * Hints: #3, #24
     *
     * #3   2.4 There are many solutions to this problem, most of which are equally optimal in runtime.
     *          Some have shorter, cleaner code than others. Can you brainstorm different solutions?
     * #24  2.4 Consider that the elements don't have to stay in the same relative order. We only need
     *          to ensure that elements less than the pivot must be before elements greater than the
     *          pivot. Does that help you come up with more solutions?
     * SOLUTION
     * If this were an array, we would need to be careful about how we shifted elements. Array shifts are very
     * expensive.
     * However, in a linked list, the situation is much easier. Rather than shifting and swapping elements, we can
     * actually create two different linked lists: one for elements less than x, and one for elements greater than or
     * equal to x.
     * We iterate through the linked list, inserting elements into our before list or our after list. Once we reach
     * the end of the linked list and have completed this splitting, we merge the two lists.
     * This approach is mostly "stable" in that elements stay in their original order, other than the necessary movement around the partition. The code below implements this approach.
     * 1 /* Pass in the head of the linked list and the value to partition around
     * 2 LinkedListNode partition(LinkedListNode node, int x) {
     * 3 LinkedListNode beforeStart = null;
     * 4 LinkedListNode beforeEnd = null;
     * 5 LinkedListNode afterStart = null;
     * 6 LinkedListNode afterEnd = null;
     * 7
     * 8 / * Partition list
     * 9 while (node != nUll) {
     * 10 LinkedListNode next = node. next;
     * 11 node. next = null;
     * 12 if (node.data < x) {
     * 13 /* Insert node into end of before list
     * 14 if (beforeStart == null) {
     * 15 beforeStart = node;
     * 16 beforeEnd = beforeStart;
     * 17 } else {
     * 18 beforeEnd.next = node;
     * 19 beforeEnd = node;
     * 20 }
     * 21 } else {
     * 22 / * Insert node into end of after list
     * 23 if (afterStart == nUll) {
     * 24 afterStart = node;
     * 25 afterEnd = afterStart;
     * 26 } else {
     * 27 afterEnd . next = node;
     * 28 afterEnd = node;
     * 29 }
     * 30 }
     * 31 node next;
     * 32 }
     * 33
     * 34 if (beforeStart == nUll) {
     * 35 return afterStart;
     * 36 }
     * 37
     * 38 1* Merge before list and after list *1
     * 39 beforeEnd.next = afterStart;
     * 40 return beforeStart;
     * 41 }
     * If it bugs you to keep around four different variables for tracking two linked lists, you're not alone. We can
     * make this code a bit shorter.
     * If we don't care about making the elements of the list "stable" (which there's no obligation to, since the
     * interviewer hasn't specified that), then we can instead rearrange the elements by growing the list at the
     * head and tail.
     * In this approach, we start a "new"list (using the existing nodes). Elements bigger than the pivot element are
     * put at the tail and elements smaller are put at the head. Each time we insert an element, we update either
     * the head or tail.
     * 1 LinkedListNode partition(LinkedListNode node, int x) {
     * 2 LinkedListNode head node;
     * 3 LinkedListNode tail = node;
     * 4
     * 5 while (node != nUll) {
     * 6 LinkedListNode next = node. next;
     * 7 if (node. data < x) {
     * 8 1* Insert node at head.
     * 9 node. next = head;
     * 10 head = node;
     * 11 } else {
     * 12 1* Insert node at tail.
     * 13 tail.next = node;
     * 14 tail = node;
     * 15 }
     * 16 node = next;
     * 17 }
     * 18 tail. next = null;
     * 19
     * 20 II The head has changed, so we need to return it to the user.
     * 21 return head;
     * 22 }
     * There are many equally optimal solutions to this problem. If you came up with a different one, that's okay!
     *
     */
}
