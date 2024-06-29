package ds.trees_graphs;

public class Tree<T> {
    private Node<T> root;
    public static class Node<T>{
        private T name;
        private boolean visited;
        private Node<T> left;
        private Node<T> right;
        private Node<T>[] children;

        public T getName() {
            return name;
        }

        public void setName(T name) {
            this.name = name;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public Node<T>[] getChildren() {
            return children;
        }

        public void setChildren(Node<T>[] children) {
            this.children = children;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }
    }
}
