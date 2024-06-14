package ds.stacks_queues;

/**
 * 3.3 Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
 * Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
 * threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be
 * composed of several stacks and should create a new stack once the previous one exceeds capacity.
 * SetOfStacks. push () and SetOfStacks. pop () should behave identically to a single stack
 * (that is, pop ( ) should return the same values as it would if there were just a single stack).
 * FOLLOW UP
 * Implement a function popAt (int index) which performs a pop operation on a specific sub-stack.
 * Hints: #64, #81
 *
 * #64 3.3 You will need to keep track of the size of each substack. When one stack is full, you may
 *         need to create a new stack.
 * #81 3.3 Popping an element at a specific substack will mean that some stacks aren't at full
 *         capacity. Is this an issue? There's no right answer, but you should think about how to
 *         handle this.
 * */
public class StackOfPlates {
    // Already did the practiced

    /**
     * 3.3 Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
     * Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
     * threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be
     * composed of several stacks and should create a new stack once the previous one exceeds capacity.
     * SetOfStacks. push () and SetOfStacks. pop() should behave identically to a single stack
     * (that is, pop ( ) should return the same values as it would if there were just a single stack).
     * FOLLOW UP
     * Implement a function popAt (int index) which performs a pop operation on a specific substack.
     *
     * SOLUTION
     * In this problem, we've been told what our data structure should look like:
     * 1 class SetOfStacks {
     * 2 ArrayList<Stack > stacks new ArrayList<Stack>()j
     * 3 public void push(int v) { . . . }
     * 4 public int pope) { ... }
     * 5 }
     * We know that push () should behave identically to a single stack, which means that we need push () to
     * call push () on the last stack in the array of stacks. We have to be a bit careful here though: if the last stack
     * is at capacity, we need to create a new stack. Our code should look something like this:
     * 1 void push(int v) {
     * 2 Stack last = getLastStack();
     * 3 if (last != null && !last.isFull(» { II add to last stack
     * 4 last.push(v);
     * 5 } else { II must create new stack
     * 6 Stack stack = new Stack(capacity);
     * 7 stack.push(v);
     * 8 stacks.add(stack);
     * 9 }
     * 16 }
     * What should pop () do? It should behave similarly to push () in that it should operate on the last stack. If
     * the last stack is empty (after popping), then we should remove the stack from the list of stacks.
     * 1 int pope) {
     * 2 Stack last = getLastStack();
     * 3 if (last == nUll) throw new EmptyStackException();
     * 4 int v = last.pop();
     * 5 if (last.size == 6) stacks.remove(stacks.size() - 1);
     * 6 return v;
     * 7 }
     * Follow Up: Implement popAt(int index)
     * This is a bit trickier to implement, but we can imagine a "rollover" system. If we pop an element from stack
     * 1, we need to remove the bottom of stack 2 and push it onto stack 1. We then need to rollover from stack 3
     * to stack 2, stack 4 to stack 3, etc.
     * You could make an argument that, rather than "rolling over;' we should be okay with some stacks not
     * being at full capacity. This would improve the time complexity (by a fair amount, with a large number of
     * elements), but it might get us into tricky situations later on if someone assumes that all stacks (other than
     * the last) operate at full capacity. There's no "right answer" here; you should discuss this trade-off with your
     * interviewer.
     * 1 public class SetOfStacks {
     * 2 ArrayList <Stack> stacks = new ArrayList<Stack >();
     * 3 public int capacity;
     * 4 public SetOfStacks(int capacity) {
     * 5 this. capacity = capacity;
     * 6 }
     * 7
     * 8 public Stack getLastStack() {
     * 9 if (stacks.size() == 6) return null;
     * 16 return stacks.get(stacks.size() - 1);
     * 11 }
     * 12
     * 13 public void push(int v) { / * see earlier code }
     * 14 public int pope) { / * see earlier code }
     * 15 public boolean isEmpty() {
     * 16 Stack last = getLastStack();
     * 17 return last == null I I last.isEmpty();
     * 18 }
     * 19
     * 20 public int popAt(int index) {
     * 21 ret urn leftShift(index, true);
     * 22 }
     * 23
     * 24 public int leftShift(int index, boolean removeTop) {
     * 25 Stack stack = stacks.get(index);
     * 26 i nt removed_item;
     * 27 if (removeTop) removed_item = stack.pop();
     * 28 else removed_item = stack.removeBottom();
     * 29 if (stack.isEmpty(» {
     * 30 stacks.remove(index);
     * 31 } else if (stacks.size() > index + 1) {
     * 32 i nt v = leftShift(index + 1, false);
     * 33 stack . push(v);
     * 34 }
     * 35 ret urn removed_item;
     * 36 }
     * 37 }
     * 38
     * 39 public class Stack {
     * 40 privat e int capacity;
     * 41 public Node top, bottom;
     * 42 public int si ze = 0;
     * 43
     * 44 public Stack(int capacity) { this.capacity = capacity; }
     * 45 public boolean isFull() { return capacity == size; }
     * 46
     * 47 public void join(Node above, Node below) {
     * 48 if (below != nUll) below. above = above;
     * 49 if (above ! = nUll) above. below = below;
     * 50 }
     * 51
     * 52 public boolean push(int v) {
     * 53 if (size >= capacity) return false;
     * 54 size++;
     * 55 Node n = new Node(v) ;
     * 56 if (size == 1) bottom n;
     * 57 joi n(n, top) ;
     * 58 top = n;
     * 59 ret urn true;
     * 60 }
     * 61
     * 62 public int pop() {
     * 63 Node t = top;
     * 64 top = top. below;
     * 65 size- -;
     * 66 ret urn t.value;
     * 67 }
     * 68
     * 69 public boolean isEmpty() {
     * 70 ret urn size == 0;
     * 71 }
     * 72
     * 73 public int removeBottom() {
     * 74 Node b = bottom;
     * 75 bottom = bottom. above;
     * 76 if (bottom != nUll) bottom. below null;
     * 77 size--;
     * 78 return b.value;
     * 79 }
     * 80 }
     * This problem is not conceptually that tough, but it requires a lot of code to implement it fully. Your interviewer would not ask you to implement the entire code.
     * A good strategy on problems like this is to separate code into other methods, like a leftShi ft method
     * that popAt can call. This will make your code cleaner and give you the opportunity to lay down the skeleton of the code before dealing with some of the details.
     *
     * */

}
