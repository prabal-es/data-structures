package ds.linked_lists.book;

/**
 * 2.7 Intersection: Given two (singly) linked lists, determine if the two lists intersect.
 * Return the intersecting node. Note that the intersection is defined based on reference,
 * not value. That is, if the kth
 * node of the first linked list is the exact same node (by reference) as the jth node of the second
 * linked list, then they are intersecting.
 * Hints: #20, #45, #55, #65, #76, #93, #111, #120, #129
 *
 * #20   2.7 You can do this in 0 (A+B) time and 0 (1) additional space. That is, you do not need a
 *           hash table (although you could do it with one).
 * #45   2.7 Examples will help you. Draw a picture of intersecting linked lists and two equivalent
 *           linked lists (by value) that do not intersect.
 * #55   2.7 Focus first on just identifying if there's an intersection.
 * #65   2.7 Observe that two intersecting linked lists will always have the same last node. Once they
 *           intersect, all the nodes after that will be equal.
 * #76   2.7 You can determine if two linked lists intersect by traversing to the end of each and
 *           comparing their tails.
 * #93   2.7 Now, you need to find where the linked lists intersect. Suppose the linked lists were the
 *           same length. How could you do this?
 * #111  2.7 If the two linked lists were the same length, you could traverse forward in each until you
 *           found an element in common. Now, how do you adjust this for lists of different lengths?
 * #120  2.7 Try using the difference between the lengths of the two linked lists.
 * #129  2.7 If you move a pointer in the longer linked list forward by the difference in lengths, you
 *           can then apply a similar approach to the scenario when the linked lists are equal.
* */
public class Intersection {

    LinkedList<Integer> getIntersectionNode(LinkedList<Integer> l1, LinkedList<Integer> l2){
        LinkedList<Integer> l2Head = l2;
        while (l1 != null && l2Head != null){
            while (l2 != null){
                if(l1 == l2){
                    return l1;
                }
                l2 = l2.next;
            }
            l2 = l2Head;
            l1 = l1.next;
        }
        return null;
    }

    public static void main(String... args) {
        Intersection intersection = new Intersection();
        LinkedList<Integer> l13 = new LinkedList<>(3);
        LinkedList<Integer> l11 = new LinkedList<>(1);
        LinkedList<Integer> l15 = new LinkedList<>(5);
        LinkedList<Integer> l19 = new LinkedList<>(9);
        l13.next = l11;
        l11.next = l15;
        l15.next = l19;

        LinkedList<Integer> l24 = new LinkedList<>(4);
        LinkedList<Integer> l26 = new LinkedList<>(6);
        l24.next = l26;

        LinkedList<Integer> l37 = new LinkedList<>(7);
        LinkedList<Integer> l32 = new LinkedList<>(2);
        LinkedList<Integer> l31 = new LinkedList<>(1);
        l37.next = l32;
        l32.next = l31;

        l19.next = l37;
        l26.next = l37;

        System.out.println(l13);
        System.out.println(l24);
        System.out.println(intersection.getIntersectionNode(l13, l24));
    }

    /**
     * 2.7 Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the
     * intersecting node. Note that the intersection is defined based on reference, not value. That is, if the
     * kth node of the first linked list is the exact same node (by reference) as the jth node of the second
     * linked list, then they are intersecting.
     * SOLUTION
     * Let's draw a picture of intersecting linked lists to get a better feel for what is going on.
     * Here is a picture of intersecting linked lists:
     * And here is a picture of non-intersecting linked lists:
     * We should be careful here to not inadvertently draw a special case by making the linked lists the same
     * length.
     * Let's first ask how we would determine if two linked lists intersect.
     * Determining if there's an intersection.
     * How would we detect if two linked lists intersect? One approach would be to use a hash table and just
     * throw all the linked lists nodes into there. We would need to be careful to reference the linked lists by their
     * memory location, not by their value.
     * There's an easier way though. Observe that two intersecting linked lists will always have the same last node.
     * Therefore, we can just traverse to the end of each linked list and compare the last nodes.
     * How do we find where the intersection is, though?
     *
     * Finding the intersecting node.
     * One thought is that we could traverse backwards through each linked list. When the linked lists "split'; that's
     * the intersection. Of course, you can't really traverse backwards through a singly linked list.
     * If the linked lists were the same length, you could just traverse through them at the same time. When they
     * collide, that's your intersection.
     * When they're not the same length, we'd like to just "chop oW-or ignore-those excess (gray) nodes.
     * How can we do this? Well, if we know the lengths of the two linked lists, then the difference between those
     * two linked lists will tell us how much to chop off.
     * We can get the lengths at the same time as we get the tails of the linked lists (which we used in the first step
     * to determine if there's an intersection).
     *
     * Putting it all together.
     * We now have a multistep process.
     * 1. Run through each linked list to get the lengths and the tails.
     * 2. Compare the tails. If they are different (by reference, not by value), return immediately. There is no intersection.
     * 3. Set two pointers to the start of each linked list.
     * 4. On the longer linked list, advance its pointer by the difference in lengths.
     * 5. Now, traverse on each linked list until the pointers are the same.
     * The implementation for this is below.
     * 1 LinkedListNode findIntersection(LinkedListNode list1, LinkedListNode list2) {
     * 2 if (list1 == null I I list2 == nUll) return null;
     * 3
     * 4 /* Get tail and sizes.
     * 5 Result result1 getTailAndSize(list1);
     * 6 Result result2 = getTailAndSize(list2);
     * 7
     * 8 /* If different tail nodes, then there's no intersection.
     * 9 if (result1.tail != result2.tail) {
     * 10 return null;
     * 11 }
     * 12
     * 13 /* Set pointers to the start of each linked list.
     * 14 LinkedListNode shorter = result1.size < result2.size ? list1 : list2;
     * 15 LinkedListNode longer = result1.size < result2.size ? list2 : list1;
     * 16
     * 17 /* Advance the pointer for the longer linked list by difference in lengths.
     * 18 longer = getKthNode(longer, Math.abs(result1.size - result2.size»;
     * 19
     * 20 /* Move both pointers until you have a collision.
     * 21 while (shorter != longer) {
     * 22 shorter = shorter. next;
     * 23 longer = longer. next;
     * 24 }
     * 25
     * 26 /* Return either one.
     * 27 return longer;
     * 28 }
     * 29
     * 30 class Result {
     * 31 public LinkedListNode tail;
     * 32 publi c int size;
     * 33 public Result(LinkedListNode tail, int size) {
     * 34 this. tail = tail;
     * 35 this.size = size;
     * 36 }
     * 37 }
     * 38
     * 39 Result getTailAndSize(LinkedListNode list) {
     * 40 if (list == nUll) return null;
     * 41
     * 42 int size = 1;
     * 43 LinkedListNode current = list;
     * 44 while (current. next != null) {
     * 45 size++;
     * 46 current = current.next;
     * 47 }
     * 48 return new Result(current, size);
     * 49 }
     * 51 LinkedListNode getKthNode(LinkedListNode head, int k) {
     * 52 LinkedListNode current = head;
     * 53 while (k > 0 && current != nUll) {
     * 54 cur rent = current.next;
     * 55 k--;
     * 56 }
     * 57 return current;
     * 58 }
     * This algorithm takes O(A + B) time, where A and B are the lengths of the two linked lists. It takes O( 1)
     * additional space.
     * */
}
