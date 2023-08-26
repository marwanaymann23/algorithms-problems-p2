
import java.util.Arrays;
import java.util.Scanner;

public class KruskalSpecialSubTree {
    static DisjointUnionSet disjointUnionSet;
    static Edge[] edge;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int numberOfNodes = input.nextInt();
        int numberOfEdges = input.nextInt();
        disjointUnionSet = new DisjointUnionSet(numberOfNodes);
        edge = new Edge[numberOfEdges];

        for (int i = 0; i < numberOfEdges; i++) {
            int nodeOne = input.nextInt();
            int nodeTwo = input.nextInt();
            int weight = input.nextInt();
            edge[i] = new Edge(nodeOne, nodeTwo, weight);
        }
        input.close();
        System.out.println(kruskalSolution(numberOfNodes));
    }

    private static int kruskalSolution(int numberOfNodes) {
        Arrays.sort(edge);
        int edgesOfSubTree = 0;
        int subTreeWeight = 0;

        for(int i = 0; i < numberOfNodes; i++){
            Edge currentEdge = edge[i];
            int firstNode = disjointUnionSet.search(currentEdge.nodeOne);
            int secondNode = disjointUnionSet.search(currentEdge.nodeTwo);
            if (firstNode != secondNode) {
                disjointUnionSet.union(currentEdge.nodeOne, currentEdge.nodeTwo);
                subTreeWeight += currentEdge.weight;
                edgesOfSubTree++;
                if(edgesOfSubTree == numberOfNodes - 1){
                    break;
                }
            }
        }
        return subTreeWeight;
    }
}

class Edge implements Comparable<Edge> {
    int nodeOne;
    int nodeTwo;
    int weight;

    public Edge(int nodeOne, int nodeTwo, int weight) {
        this.nodeOne = nodeOne;
        this.nodeTwo = nodeTwo;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge edge2) {
        return this.weight - edge2.weight;
    }
}

class DisjointUnionSet {
    private int[] parent;
    private int[] rank;
    private int numberOfNodes;

    public DisjointUnionSet(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        parent = new int[numberOfNodes + 1];
        rank = new int[numberOfNodes + 1];
        makeSet();
    }

    void makeSet() {
        for (int i = 0; i < numberOfNodes; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    int search(int i) {
        return parent[i];
    }

    void union(int one, int two) {
        int firstNode = parent[one];
        int secondNode = parent[two];
        if (rank[firstNode] < rank[secondNode]) {
            parent[firstNode] = secondNode;
        }
        else if (rank[firstNode] > rank[secondNode]) {
            parent[secondNode] = firstNode;
        }
        else {
            parent[firstNode] = secondNode;
            rank[secondNode] = rank[secondNode] + 1;
        }
    }
}


