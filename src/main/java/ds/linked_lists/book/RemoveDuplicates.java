package ds.linked_lists.book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 2.1 Remove Dups: Write code to remove duplicates from an unsorted linked list.
* FOLLOW UP
* How would you solve this problem if a temporary buffer is not allowed?
* Hints: #9, #40
*
* #9  2.1 Have you tried a hash table? You should be able to do this in a single pass of the linked
*     list.
* #40 2.1 Without extra space, you'll need a (N2) time. Try using two pointers, where the second
        one searches ahead of the first one.
* Big(O) =
* */
public class RemoveDuplicates {

    public LinkedList<Integer> removeDuplicates(LinkedList<Integer> linkedList){
        LinkedList<Integer> currentNode = linkedList;
        while (null != currentNode){
            LinkedList<Integer> runner = currentNode;
            while (null != runner.next){
                if(runner.next.data.equals(currentNode.data)){
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            currentNode = currentNode.next;
        }
        return linkedList;
    }
    public void removeDuplicatesUsingHashTable(LinkedList<Integer> linkedList){
        Map<Integer, String> map = new HashMap<>();
        LinkedList<Integer> previous = null;
        while (null != linkedList){
            if(null == map.get(linkedList.data)){
                map.put(linkedList.data, linkedList.toString());
                previous=linkedList;
            }else{
                previous.next = linkedList.next;
            }
            linkedList = linkedList.next;
        }
    }
    public static void main(String... args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        LinkedList<Integer> linkedList = new LinkedList<>(3);
        linkedList.appendToTail(3);
        linkedList.appendToTail(2);
        linkedList.appendToTail(5);
        linkedList.appendToTail(4);
        linkedList.appendToTail(6);
        linkedList.appendToTail(3);
        System.out.println(linkedList);
        //System.out.println(removeDuplicates.removeDuplicates(linkedList));
        removeDuplicates.removeDuplicatesUsingHashTable(linkedList);
        System.out.println(linkedList);
    }

    /*
    * 2.1 Remove Dups: Write code to remove duplicates from an unsorted linked list.
    * FOLLOW UP
    * How would you solve this problem if a temporary buffer is not allowed?
    * SOLUTION
    * In order to remove duplicates from a linked list, we need to be able to track duplicates. A simple hash table
    * will work well here.
    * n the below solution, we simply iterate through the linked list, adding each element to a hash table. When
    * we discover a duplicate element, we remove the element and continue iterating. We can do this all in one
    * ass since we are using a linked list.
    * 1 void deleteDups(LinkedListNode n) {
    * 2 HashSet<Integer> set = new HashSet<Integer>();
    * 3 LinkedListNode previous = null;
    * 4 while (n != nUll) {
    * 5 if (set.contains(n.data)) {
    * 6 previous. next = n.next;
    * 7 } else {
    * 8 set.add(n.data);
    * 9 previous = n' ,Ie }
    * 11 n = n.next;
    * 12 }
    * 13 }
    * The above solution takes 0 (N) time, where N is the number of elements in the linked list.
    * Follow Up: No Buffer Allowed
    If we don't have a buffer, we can iterate with two pointers: cu rrent which iterates through the linked list,
    and runner which checks all subsequent nodes for duplicates.
    * 1 void deleteDups(LinkedListNode head) {
    * 2 LinkedListNode current = head;
    * 3 while (current != nUll) {
    * 4 / Remove all future nodes that have the same value
    * 5 LinkedListNode runner = current;
    * 6 while (runner. next != nUll) {
    * 7 if (runner.next.data == current.data) {
    * 8 runner. next = runner.next.nextj
    * } else {
    * runner = runner.nextj
    * }
    * 13 current current.nextj
    * 14 }
    * 15 }
    * This code runs in O( 1) space, but 0 (N2 ) time.
    */
}
