package ds.stacks_queues;

public class MyQueue<T> {
    private T data;
    private MyQueue<T> next;
    private MyQueue<T> first;
    private MyQueue<T> last;

    public MyQueue(T data){
        this.data = data;
    }

    public void add(T data){
        MyQueue<T> newElement = new MyQueue<>(data);
        if(null != last){
            last.next = newElement;
        }
        last = newElement;
        if(null != first){
            first = last;
        }
    }

    public T remove(){
        if(null == first){
            throw new RuntimeException("Queue is enpty.");
        }
        T data = first.data;
        first = first.next;
        if(null == first){
            last = null;
        }
        return data;
    }

    public T peek(){
        if(null == first){
            throw new RuntimeException("Queue is enpty.");
        }
        return first.data;
    }

    public boolean isEmpty(){
        return null == first;
    }

}
