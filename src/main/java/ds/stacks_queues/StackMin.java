package ds.stacks_queues;

import java.util.Arrays;

/**
 * 3.2 Stack Min: How would you design a stack which, in addition to push and pop, has a function min
 * which returns the minimum element? Push, pop and min should all operate in 0(1) time.
 * Hints: #27, #59, #78
 *
 * #27 3.2 Observe that the minimum element doesn't change very often. It only changes when a
 *         smaller element is added, or when the smallest element is popped.
 * #59 3.2 What if we kept track of extra data at each stack node? What sort of data might make it
 *         easier to solve the problem?
 * #78 3.2 Consider having each node know the minimum of its "substack" (all the elements
 *         beneath it, including itself).
 **/
public class StackMin extends MyStack<Integer> {

    private final MyStack<Integer> minStack;

    public StackMin(){
        minStack = new MyStack<>();
    }

    @Override
    public void push(Integer data){
        if(getMin() >= data){
            minStack.push(data);
        }
        super.push(data);
    }

    @Override
    public Integer pop(){
        int data = super.pop();
        if(data == getMin()){
            minStack.pop();
        }
        return data;
    }

    public Integer getMin(){
        if(minStack.isEmpty()){
            return Integer.MAX_VALUE;
        }
        return minStack.peek();
    }

    public static void main(String... args){
        StackMin stackmin = new StackMin();
        stackmin.push(9);
        stackmin.push(6);
        System.out.println(stackmin + "\t" + stackmin.getMin());
        stackmin.push(3);
        stackmin.push(6);
        System.out.println(stackmin + "\t" + stackmin.getMin());
        stackmin.push(3);
        stackmin.push(2);
        System.out.println(stackmin + "\t" + stackmin.getMin());
        stackmin.push(2);
        System.out.println(stackmin + "\t" + stackmin.getMin());

        stackmin.pop();
        System.out.println(stackmin + "\t" + stackmin.getMin());
        stackmin.pop();
        System.out.println(stackmin + "\t" + stackmin.getMin());
        stackmin.pop();
        System.out.println(stackmin + "\t" + stackmin.getMin());
        stackmin.pop();
        System.out.println(stackmin + "\t" + stackmin.getMin());
        stackmin.pop();
        System.out.println(stackmin + "\t" + stackmin.getMin());
        stackmin.pop();
        System.out.println(stackmin + "\t" + stackmin.getMin());
        stackmin.pop();
        System.out.println(stackmin + "\t" + stackmin.getMin());

    }

    /**
     * 
     * */
}
