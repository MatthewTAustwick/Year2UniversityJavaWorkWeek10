package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepthFirstTraversal<T> extends AdjacencyGraph<T> implements Traversal<T> {
    public List<T> traversalList = new ArrayList<T>(); //Creates a list ready to store the nodes which the program traversed throughout its traversal

    @Override
    public List<T> traverse() throws GraphError {
        T node = getUnvisited(); //Get another node which hasn't been visited at all
        while (node != null){ //This WHILE loop occurs only when there are no univisited nodes - otherwise, this WHILE loop is ignored
            visitNode(node);
            node = getUnvisited();
        }
        return traversalList;
    }

    protected T getUnvisited() {
        for (T node: getNodes()) { //For each node that exists
            if (!visitedNode(node)) { //Check to see if it has been visited
                return node; //If it hasn't then it is subsequently an univisited node.
            }
        }
        //If there are NO unvisited nodes
        return null; //Return NULL
    }

    private boolean visitedNode(T node) {
        return traversalList.contains(node);
    }

    private void visitNode(T node) throws GraphError {
        if (visitedNode(node)) return; //There is nothing else to do with this node if it hasn't been visited
        traversalList.add(node); //Add the node to the list of traversed nodes
        for (T neighbour: getNeighbours(node)) { //Check if the node has any neighbours
            if (!visitedNode(neighbour))
                visitNode(neighbour); //Visit the neighbour ONLY if it hasn't been visited before
        }
    }

    public static void main(String[] args) throws GraphError {
        AdjacencyGraph<Integer> g = new AdjacencyGraph<>();
        for (int i = 0; i < 6; i++){ //Creates a graph full of 6  vertices (0-5)
            g.add(i);
        }
        g.add(1,2);
        g.add(2,1);
        g.add(4,2);
        g.add(0,1);
        g.add(0,3);
        g.add(2,4);
        g.add(2,5);
        g.add(4,5);
        g.add(5,4); //The above code assigns edges between certain sets of vertices
        DepthFirstTraversal dft = new DepthFirstTraversal();
        System.out.println(dft.traverse());
    }

}
