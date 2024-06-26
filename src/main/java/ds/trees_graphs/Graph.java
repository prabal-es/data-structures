package ds.trees_graphs;

public class Graph {
    private Node[] nodes;
    public static class Node{
        private String name;
        private boolean isVisited;
        private Node[] children;

        public Node(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isVisited() {
            return isVisited;
        }

        public void setVisited(boolean visited) {
            isVisited = visited;
        }

        public Node[] getChildren() {
            return children;
        }

        public void setChildren(Node[] children) {
            this.children = children;
        }
    }
}
