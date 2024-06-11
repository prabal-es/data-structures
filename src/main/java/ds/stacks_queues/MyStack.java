package ds.stacks_queues;

public class MyStack<T> {

    private class StackNode<T> {
        private StackNode<T> next;
        private T data;
        public StackNode(T data){
            this.data = data;
        }
    }
    private StackNode<T> top;



    public void push(T data){
        StackNode<T> element = new StackNode<>(data);
        element.next = top;
        top = element;
    }

    public T pop(){
        if(top == null){
            throw new RuntimeException("Stack is empty");
        }
        T data = top.data;
        top = top.next;
        return data;
    }
    public T peek(){
        if(top == null){
            throw new RuntimeException("Stack is empty");
        }
        return top.data;
    }

    public boolean isEmpty(){
        return top == null;
    }

    @Override
    public String toString(){
        StringBuilder data = new StringBuilder();
        StackNode<T> currentNode = top;
        while (null != currentNode ){
            data.append(currentNode.data);
            if(null != currentNode.next){
                data.append(",");
            }
            currentNode = currentNode.next;
        }
        return data.toString();
    }
}
