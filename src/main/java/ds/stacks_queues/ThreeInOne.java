package ds.stacks_queues;

import java.util.Arrays;

/**
 * 3.1 Three in One: Describe how you could use a single array to implement three stacks.
 * Hints: #2, #12, #38, #58
 *
 * #2  3.1 A stack is simply a data structure in which the most recently added elements are
 *         removed first. Can you simulate a single stack using an array? Remember that there are
 *         many possible solutions, and there are tradeoffs of each.
 * #12 3.1 We could simulate three stacks in an array by just allocating the first third of the array to
 *         the first stack, the second third to the second stack, and the final third to the third stack.
 *         One might actually be much bigger than the others, though. Can we be more flexible
 *         with the divisions?
 * #34 3.1 If you want to allow for flexible divisions, you can shift stacks around. Can you ensure
 *         that all available capacity is used?
 * #58 3.1 Try thinking about the array as circular, such that the end of the array "wraps around" to
 *         the start of the array.
 **/
public class ThreeInOne {
    private int numberOfStacks;
    private int stackSize;
    int[] arrayOfStacks;
    int[] sizeOfArrayOfStack;

    public ThreeInOne(int numberOfStacks, int stackSize){
        this.numberOfStacks = numberOfStacks;
        this.stackSize = stackSize;
        arrayOfStacks = new int[numberOfStacks*stackSize];
        sizeOfArrayOfStack = new int[numberOfStacks];
    }

    public void push(int data, int stackNumber){
        if(stackNumber > stackSize){
            throw new RuntimeException("Stack not found!");
        }
        if(isFull(stackNumber)){
            throw new RuntimeException("Stack over flow!");
        }
        arrayOfStacks[sizeOfArrayOfStack[stackNumber-1]+(stackSize*(stackNumber-1))] = data;
        sizeOfArrayOfStack[stackNumber-1]++;
    }
    public int pop(int stackNumber){
        if(stackNumber > stackSize){
            throw new RuntimeException("Stack not found!");
        }
        if(isEmpty(stackNumber)){
            throw new RuntimeException("Stack is empty!");
        }
        int arrayIndex = ((stackNumber-1)*stackSize) + sizeOfArrayOfStack[stackNumber-1];
        int data = arrayOfStacks[arrayIndex-1];
        arrayOfStacks[arrayIndex-1] = 0;
        sizeOfArrayOfStack[stackNumber-1]--;
        return data;
    }

    public int peek(int stackNumber){
        if(stackNumber > stackSize){
            throw new RuntimeException("Stack not found!");
        }
        if(isEmpty(stackNumber)){
            throw new RuntimeException("Stack is empty!");
        }
        return arrayOfStacks[(sizeOfArrayOfStack[stackNumber-1]+(stackSize*(stackNumber-1)))-1];
    }

    public boolean isFull(int stackNumber){
        return sizeOfArrayOfStack[stackNumber-1]== stackSize;
    }

    public boolean isEmpty(int stackNumber){
        return sizeOfArrayOfStack[stackNumber-1] == 0;
    }
    @Override
    public String toString(){
        return String.valueOf("\nArrays:"+ Arrays.toString(arrayOfStacks) +"\nArraysSize:"+ Arrays.toString(sizeOfArrayOfStack));
    }
    public static void main(String... args){
        ThreeInOne threeStacksInOneArray = new ThreeInOne(3,3);
        System.out.println(threeStacksInOneArray);
        threeStacksInOneArray.push(1,1);
        threeStacksInOneArray.push(2,1);
        threeStacksInOneArray.push(3,1);
        threeStacksInOneArray.push(4,2);
        threeStacksInOneArray.push(5,2);
        threeStacksInOneArray.push(6,2);
        threeStacksInOneArray.push(7,3);
        threeStacksInOneArray.push(8,3);
        threeStacksInOneArray.push(9,3);
        threeStacksInOneArray.pop(2);
        threeStacksInOneArray.pop(2);
        threeStacksInOneArray.pop(2);
        threeStacksInOneArray.pop(1);
        System.out.println(threeStacksInOneArray.peek(1));
        threeStacksInOneArray.pop(1);
        System.out.println(threeStacksInOneArray.peek(1));
        threeStacksInOneArray.pop(1);
        //System.out.println(threeStacksInOneArray.peek(1));
        System.out.println(threeStacksInOneArray);
    }

    /**
     * 3.1 Three in One: Describe how you could use a single array to implement three stacks.
     * SOLUTION
     * Like many problems, this one somewhat depends on how well we'd like to support these stacks. If we're
     * okay with simply allocating a fixed amount of space for each stack, we can do that. This may mean though
     * that one stack runs out of space, while the others are nearly empty.
     * Alternatively, we can be flexible in our space allocation, but this significantly increases the complexity of
     * the problem.
     * Approach 1: Fixed Division
     * We can divide the array in three equal parts and allow the individual stack to grow in that limited space.
     * Note: We will use the notation "[" to mean inclusive of an end point and "(" to mean exclusive of an end
     * point.
     * • For stack 1 , we will use [e, ).
     * • For stack 2, we will use [ ~, 2~).
     * • For stack 3, we will use [ 2~, n).
     * The code for this solution is below.
     * 1 class FixedMultiStack {
     * 2 private int numberOfStacks 3;
     * 3 private int stackCapacity;
     * 4 private int[] values;
     * 5 private int[] sizes;
     * 6
     * 7 public FixedMultiStack(int stackSize) {
     * 8 stackCapacity = stackSize;
     * 9 values = new int[stackSize * numberOfStacks];
     * 10 sizes = new int[numberOfStacks];
     * 11 }
     * 12
     * 13 /* Push value onto stack.
     * 14 public void push(int stackNum, int value) throws FullStackException {
     * 15 /* Check that we have space for the next element
     * 16 if (iSFull(stackNum) {
     * 17 throw new FullStackException();
     * 18 }
     * 19 1* Increment stack pointer and then update top value.
     * 20 sizes[stackNum]++;
     * 21 values[indexOfTop(stackNum)] = value;
     * 22 1* Pop item from top stack.
     * public int pop(int stackNum) {
     * if (isEmpty(stackNum» {
     * }
     * throw new EmptyStackException();
     * }
     * int topIndex = indexOfTop(stackNum);
     * int value = values[topIndex]; II Get top
     * values[topIndex) = 0; II Clear
     * sizes[stackNum]--; II Shrink
     * return value;
     * 1* Return top element. *
     * public int peek(int stackNum) {
     * if (isEmpty(stackNum» {
     * throw new EmptyStackException();
     * }
     * return values[indexOfTop(stackNum)];
     * }
     * /* Return if stack is empty.
     * public boolean isEmpty(int stackNum) {
     * return sizes[stackNum] == 0;
     * }
     * 1* Return if stack is full.
     * public boolean isFull(int stackNum) {
     * return sizes[stackNum] == stackCapacity;
     * }
     * 1* Returns index of the top of the stack.
     * private int indexOfTop(int stackNum) {
     * }
     * int offset = stackNum * stackCapacity;
     * int size = sizes[stackNum);
     * return offset + size - 1;
     * }
     * }
     * If we had additional information about the expected usages of the stacks, then we could modify this algorithm accordingly. For example, if we expected Stack 1 to have many more elements than Stack 2, we could
     * allocate more space to Stack 1 and less space to Stack 2.
     * Approach 2: Flexible Divisions
     * A second approach is to allow the stack blocks to be flexible in size. When one stack exceeds its initial
     * capacity, we grow the allowable capacity and shift elements as necessary.
     * We will also design our array to be circular, such that the final stack may start at the end of the array and
     * wrap around to the beginning.
     * Please note that the code for this solution is far more complex than would be appropriate for an interview.
     * You could be responsible for pseudocode, or perhaps the code of individual components, but the entire
     * implementation would be far too much work.
     * 1 public class MultiStack {
     * 2 /* StackInfo is a simple class that holds a set of data about each stack. It
     * 3 * does not hold the actual items in the stack. We could have done this with
     * 4 * just a bunch of individual variables, but that's messy and doesn't gain us
     * 5 * much.
     * 6 private class StackInfo {
     * 7 public int start, size, capacity;
     * 8 public StackInfo(int start, int capacity) {
     * 9 this. start = start;
     * 16 this.capacity = capacityj
     * 11 }
     * 12
     * 13 /* Check if an index on the full array is within the stack boundaries. The
     * 14 * stack can wrap around to the start of the array.
     * 15 public boolean isWithinStackCapacity(int index) {
     * 16 /* If outside of bounds of array, return false.
     * 17 if (index < 6 I I index >= values. length) {
     * 18 return false;
     * 19 }
     * 26
     * 21 /* If index wraps around, adjust it.
     * 22 int contiguousIndex = index < start? index + values. length indexj
     * 23 int end = start + capacity j
     * 24 return start <= contiguousIndex && contiguousIndex < endj
     * 25 }
     * 26
     * 27 public int lastCapacitylndex() {
     * 28 return adjustIndex(start + capacity - l)j
     * 29 }
     * 36
     * 31 public int lastElementIndex() {
     * 32 return adjustIndex(start + size - l)j
     * 33 }
     * 34
     * 35 public boolean iSFull() { return size == capacity; }
     * 36 public boolean isEmpty() { return size == 6j }
     * 37 }
     * 38
     * 39 private Stacklnfo[] info;
     * 46 private int[] values;
     * 41
     * 42 public MultiStack(int numberOfStacks, int defaultSize) {
     * 43 /* Create metadata for all the stacks.
     * 44 info = new Stacklnfo[numberOfStacks]j
     * 45 for (int i = 6; i < numberOfStacks; i++) {
     * 46 info[i] = new StackInfo(defaultSize * i, defaultSize)j
     * 47 }
     * 48 values = new int[numberOfStacks * defaultSize]j
     * 49 }
     * 56
     * 51 /* Push value onto stack num, shifting/expanding stacks as necessary. Throws
     * 52 * exception if all stacks are full.
     * 53 public void push(int stackNum, int value) throws FullStackException {
     * 54 if (allStacksAreFull()) {
     * 55 throw new FullStackException()j
     * 56 }
     * 57
     * 58 1* If this stack is full, expand it.
     * 59 StackInfo stack = info[stackNum]j
     * 66 if (stack.isFull()) {
     * 61 expand(stackNum)j
     * 62 }
     * 63
     * 64 1* Find the index of the top element in the array + 1, and increment the
     * 65 * stack pointer
     * 66 stack.size++j
     * 67 values[stack.lastElementIndex()] valuej
     * 68 }
     * 69
     * 76 1* Remove value from stack.
     * 71 public int pop(int stackNum) throws Exception {
     * 72 StackInfo stack = info[stackNum]j
     * 73 if (stack.isEmpty()) {
     * 74 throw new EmptyStackException()j
     * 75 }
     * 76
     * 77 1* Remove last element
     * 78 int value = values[stack.lastElementIndex()]j
     * 79 values[stack.lastElementIndex()] = 6j II Clear item
     * 86 stack.size--j II Shrink size
     * 81 return valuej
     * 82 }
     * 83
     * 84 1* Get top element of stack.*
     * 85 public int peek(int stackNum) {
     * 86 StackInfo stack = info[stackNum]j
     * 87 return values[stack.lastElementIndex()]j
     * 88 }
     * 89 1* Shift items in stack over by one element. If we have available capacity, then
     * 96 * we'll end up shrinking the stack by one element. If we don't have available
     * 91 * capacity, then we'll need to shift the next stack over too.
     * 92 private void shift(int stackNum) {
     * 93 System.out.println("/// Shifting" + stackNum)j
     * 94 Stacklnfo stack = info[stackNum]j
     * 95
     * 96 1* If this stack is at its full capacity, then you need to move the next
     * 97 * stack over by one element. This stack can now claim the freed index.
     * 98 if (stack.size )= stack. capacity) {
     * 99 int nextStack = (stackNum + 1) % info.lengthj
     * 166 shift(nextStack)j
     * 161 stack.capacitY++j II claim index that next stack lost
     * 162 }
     * 163
     * 164 1* Shift all elements in stack over by one .
     * 165 int index = stack.lastCapacityIndex()j
     * 166 while (stack.isWithinStackCapacity(index)) {
     * 167 values[index] = values[previousIndex(index)]j
     * 168 index = previousIndex(index)j
     * 169 }
     * 110
     * 111 1* Adjust stack data.
     * 112 values[stack.start] = 0j II Clear item
     * 113 stack. start = nextlndex(stack.start)j II move start
     * 114 stack.capacitY--j II Shrink capacity
     * 115 }
     * 116
     * 117 1* Expand stack by shifting over other stacks
     * 118 private void expand(int stackNum) {
     * 119 shift«stackNum + 1) % info.length)j
     * 120 info[stackNum].capacitY++j
     * 121 }
     * 122
     * 123 1* Returns the number of items actually present in stack.
     * 124 public int numberOfElements() {
     * 125 int size = 0j
     * 126 for (Stacklnfo sd : info) {
     * 127 size += sd.sizej
     * 128 }
     * 129 return size;
     * 130 }
     * 131
     * 132 1* Returns true is all the stacks are full.
     * 133 public boolean alIStacksAreFull() {
     * 134 return numberOfElements() == values.lengthj
     * 135 }
     * 136
     * 137 1* Adjust index to be within the range of 0 -> length - 1.
     * 138 private int adjustlndex(int index) {
     * 139 1* Java's mod operator can return neg values. For example, (-11 % 5) will
     * 140 * return -1, not 4. We actually want the value to be 4 (since we're wrapping
     * 141 * around the index). *1
     * 142 int max = values.lengthj
     * 143 return «index % max) + max) % maxj
     * 144 }
     * 145
     * 146 1* Get index after this index, adjusted for wrap around.
     * 147 private int nextlndex(int index) {
     * 148 return adjustlndex(index + 1);
     * 149 }
     * 150
     * 151 1* Ge t index before this index, adjusted for wrap around.
     * 152 private int previouslndex(int index) {
     * 153 return adjustlndex(index - l)j
     * 154 }
     * 155 }
     * In problems like this, it's important to focus on writing clean, maintainable code. You should use additional
     * classes, as we did with Stac klnfo, and pull chunks of code into separate methods. Of course, this advice
     * applies to the "real world" as well.
     *
     * */
}
