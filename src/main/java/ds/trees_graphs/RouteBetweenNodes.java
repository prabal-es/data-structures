package ds.trees_graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 4.1 Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a
 * route between two nodes.
 * Hints: #127
 *
 * #127 4.1 Two well-known algorithms can do this. What are the tradeoffs between them?
 * */
public class RouteBetweenNodes {

    public boolean searchBFS(Graph.Node start, Graph.Node end){
        if(start.getName().equals( end.getName())){
            return true;
        }
        Queue<Graph.Node> queue = new LinkedList<>();
        queue.add(start);
        //start.setVisited(true);
        Graph.Node currentNode;
        while (!queue.isEmpty()){
            currentNode = queue.remove();
            if(null != currentNode){
                System.out.println(currentNode.getName());
                if(null != currentNode.getChildren()) {
                    for (Graph.Node node : currentNode.getChildren()) {
                        if (!node.isVisited()) {
                            if (node.getName().equals(end.getName())) {
                                System.out.println(node.getName());
                                return true;
                            } else {
                                queue.add(node);
                            }
                        }
                    }
                }
                currentNode.setVisited(true);
            }
        }

        return false;
    }

    public boolean searchDFS(Graph.Node start, Graph.Node end){
        if(null == start || null == end){
            return false;
        }
        System.out.println(start.getName());
        if(start.getName().equals(end.getName())){
            return true;
        }
        start.setVisited(true);
        if(null != start.getChildren()) {
            for (Graph.Node node : start.getChildren()) {
                if (!node.isVisited()) {
                    if (searchDFS(node, end)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    public static void main(String... args){

        Graph.Node n0 = new Graph.Node("0");
        Graph.Node n1 = new Graph.Node("1");
        Graph.Node n2 = new Graph.Node("2");
        Graph.Node n3 = new Graph.Node("3");
        Graph.Node n4 = new Graph.Node("4");
        Graph.Node n5 = new Graph.Node("5");

        Graph.Node [] n0Children = {n1, n4, n5};
        n0.setChildren(n0Children);
        Graph.Node [] n1Children = {n3, n4};
        n1.setChildren(n1Children);
        Graph.Node [] n2Children = {n1};
        n2.setChildren(n2Children);
        Graph.Node [] n3Children = {n2, n4};
        n3.setChildren(n3Children);

        RouteBetweenNodes rbn = new RouteBetweenNodes();
        //System.out.println(rbn.searchBFS(n0, n2));
        System.out.println(rbn.searchDFS(n0, n5));
    }

}
