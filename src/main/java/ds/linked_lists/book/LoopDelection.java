package ds.linked_lists.book;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 2.8 Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
 * beginning of the loop.
 *
 * DEFINITION
 * Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node,
 * so as to make a loop in the linked list.
 * EXAMPLE
 * Input: A -> B -> C -> D -> E - > C [the same C as earlier]
 * Output: C
 * Hints: #50, #69, #83, #90
 *
 * #50   2.8 There are really two parts to this problem. First, detect if the linked list has a loop.
 *           Second, figure out where the loop starts.
 * #69   2.8 To identify if there's a cycle, try the "runner" approach described on page 93. Have one
 *           pointer move faster than the other.
 * #83   2.8 You can use two pointers, one moving twice as fast as the other. If there is a cycle, the
 *           two pointers will collide. They will land at the same location at the same time. Where do
 *           they land? Why there?
 * #90   2.8 If you haven't identified the pattern of where the two pointers start, try this: Use the
 *           linked list 1->2->3->4->5->6->7->8->9->?, where the? links to another node. Try
 *           making the? the first node (that is, the 9 points to the 1 such that the entire linked list
 *           is a loop). Then make the? the node 2. Then the node 3. Then the node 4. What is the
 *           pattern? Can you explain why this happens?
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
public class LoopDelection {


    public static void main(String... args) {
        LoopDelection loopDelection = new LoopDelection();
        LinkedList<Integer> a = new LinkedList<>(1);
        LinkedList<Integer> b = new LinkedList<>(2);
        LinkedList<Integer> c = new LinkedList<>(3);
        LinkedList<Integer> d = new LinkedList<>(4);
        LinkedList<Integer> e = new LinkedList<>(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = c;
        System.out.println(loopDelection.checkLoop(a).data);
    }

    private LinkedList<Integer> checkLoop(LinkedList<Integer> a) {
        LinkedList<Integer> result = null;
        LinkedList<Integer> head = a;
        Set<LinkedList<Integer>> table = new HashSet<>();
        while (head != null){
            if(table.contains(head)){
                return head;
            } else {
                table.add(head);
            }
            head = head.next;
        }
        return result;
    }

    /**
     * 2.8 Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
     * beginning of the loop.
     * DEFINITION
     * Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so
     * as to make a loop in the linked list.
     * EXAMPLE
     * Input: A - > B - > C - > D - > E - > C [the same C as earlier)
     * Output: C
     *
     * SOLUTION
     * This is a modification of a classic interview problem: detect if a linked list has a loop. Let's apply the Pattern
     * Matching approach.
     *
     * Part 1: Detect If Linked List Has A Loop
     * An easy way to detect if a linked list has a loop is through the FastRunner / SlowRunner approach.
     * FastRunne r moves two steps at a time, while SlowRunner moves one step. Much like two cars racing
     * around a track at different steps, they must eventually meet.
     * An astute reader may wonder if FastRunner might "hop over" SlowRunner completely, without
     * ever colliding. That's not possible. Suppose that FastRunner did hop over SlowRunner, such that
     * SlowRunner is at spot i and FastRunner is at spot i + 1. In the previous step, SlowRunner would
     * beatspoti - 1andFastRunnerwouldatspot«i + 1) - 2),orspoti - 1.Thatis, theywould
     * have collided.
     *
     * Part 2: When Do They Collide?
     * Let's assume that the linked list has a "non-looped" part of size k.
     * If we apply our algorithm from part 1, when will FastRunner and SlowRunner collide?
     * We know that for every p steps that SlowRunner takes, FastRunner has taken 2p steps. Therefore, when
     * SlowRunner enters the looped portion after k steps, FastRunner has taken 2k steps total and must be
     * 2k - k steps, or k steps, into the looped portion. Since k might be much larger than the loop length, we
     * should actually write this as mod ( k, LOOP_SIZE) steps, which we will denote as K.
     * At each subsequent step, FastRunner and SlowRunner get either one step farther away or one step
     * closer, depending on your perspective. That is, because we are in a circle, when A moves q steps away from
     * B, it is also moving q steps closer to B.
     * So now we know the following facts:
     * 1. SlowRunner is 0 steps into the loop.
     * 2. FastRunner is K steps into the loop.
     * 3. SlowRunner is K steps behind FastRunner.
     * 4. FastRunner is LOOP_SIZE - K steps behind SlowRunner.
     * 5. FastRunner catches up to SlowRunner at a rate of 1 step per unit of time.
     * So, when do they meet? Well, if FastRunner is LOOP_SIZE - K steps behind SlowRunner, and
     * FastRunner catches up at a rate of 1 step per unit oftime, then they meet after LOOP_SIZE - K steps.
     * At this point, they will be K steps before the head of the loop. Let's call this point CollisionS pot.
     * Part 3: How Do You Find The Start of the Loop?
     * We now know that CollisionSpot is K nodes before the start ofthe loop. Because K = mod (k, LOOP_
     * SIZE) (or, in other words, k = K + M * LOOP_SIZE, for any integer M), it is also correct to say that it is
     * k nodes from the loop start. For example, if node N is 2 nodes into a 5 node loop, it is also correct to say that
     * it is 7, 12, or even 397 nodes into the loop.
     * Therefore, both CollisionSpot and LinkedListHead are k nodes from the start of the loop.
     * Now, if we keep one pointer at CollisionSpot and move the other one to LinkedListHead, they will
     * each be k nodes from LoopStart. Moving the two pointers at the same speed will cause them to collide
     * again-this time after k steps, at which point they will both be at LoopStart. All we have to do is return
     * this node.
     *
     * Part 4: Putting It All Together
     * To summarize, we move FastPointer twice as fast as SlowPointer. When SlowPointer enters
     * the loop, after k nodes, FastPointer is k nodes into the loop. This means that FastPointer and
     * SlowPointer are LOOP_SIZE - k nodes away from each other.
     * Next, if FastPointer moves two nodes for each node that SlowPointer moves, they move one node
     * closer to each other on each turn. Therefore, they will meet after LOOP_SIZE - k turns. Both will be k
     * nodes from the front of the loop.
     * The head of the linked list is also k nodes from the front of the loop. So, if we keep one pointer where it is,
     * and move the other pointer to the head of the linked list. then they will meet at the front of the loop.
     * Our algorithm is derived directly from parts 1, 2 and 3.
     * 1. Create two pointers, FastPointer and SlowPointer.
     * 2. Move FastPointer at a rate of 2 steps and SlowPointer at a rate of 1 step.
     * 3. When they collide, move SlowPointer to LinkedListHead. Keep FastPointer where it is.
     * 4. Move SlowPointer and FastPointer at a·rate of one step. Return the new collision point.
     *
     * The code below implements this algorithm.
     * 1 LinkedListNode FindBeginning(LinkedListNode head) {
     * 2 LinkedListNode slow head;
     * 3 LinkedListNode fast = head;
     * 4
     * 5 / * Find meeting point. This will be LOOP_SIZE - k steps into the linked list.
     * 6 while (fast != null && fast. next != nUll) {
     * 7 slow = slow . next;
     * 8 fast = fast.next.next;
     * 9 if (slow == fast) { 1/ Collision
     * 16 break;
     * 11 }
     * 12 }
     * 13
     * 14 1* Error check - no meeting point, and therefore no loop
     * 15 if (fast == null I I fast.next == null) {
     * 16 return null;
     * 17 }
     * 18
     * 19 / * Move slow to Head. Keep fast at Meeting Point. Each are k steps from the
     * 26 * Loop Start. If they move at the same pace, they must meet at Loop Start.
     * 21 slow = head;
     * 22 while (slow != fast) {
     * 23 slow = slow. next;
     * 24 fast = fast.next;
     * 25 }
     * 26
     * 27 / * Both now point to the start of the loop.
     * 28 return fast;
     * 29 }
     *
     * */
}
