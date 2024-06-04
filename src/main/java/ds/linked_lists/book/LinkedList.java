package ds.linked_lists.book;

public class LinkedList<T> {
    LinkedList<T> next;
    T data;
    public LinkedList(T data){
        this.data = data;
    }

    public void appendToTail(T data){
        LinkedList<T> end = new LinkedList<>(data);
        LinkedList<T> currentNode = this;
        while(null != currentNode.next){
            currentNode = currentNode.next;
        }
        currentNode.next = end;
    }

    public LinkedList<T> deleteNode(LinkedList<T> headNode, T data){
        LinkedList<T> currentNode = headNode;
        if(currentNode.data.equals(data)){
            return headNode.next;
        }
        while(null != currentNode.next){
            if(currentNode.data.equals(data)){
                currentNode.next = currentNode.next.next;
                return headNode;
            }
            currentNode = currentNode.next;
        }
        return headNode;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        LinkedList<T> node = this;
        while (null != node){
            result.append(node.data);
            node = node.next;
            if(null != node){
                result.append(",");
            }
        }
        return result.toString();
    }
}
