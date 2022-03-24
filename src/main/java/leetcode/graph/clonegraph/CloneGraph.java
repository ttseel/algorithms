package leetcode.graph.clonegraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class CloneGraph {

    public static void main(String[] args) {
        Solution s = new Solution();

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        n1.neighbors.add(n2);
        n1.neighbors.add(n3);
        n1.neighbors.add(n4);
        n1.neighbors.add(n5);

        n2.neighbors.add(n1);
        n2.neighbors.add(n3);
        n2.neighbors.add(n4);
        n2.neighbors.add(n5);

        n3.neighbors.add(n1);
        n3.neighbors.add(n2);
        n3.neighbors.add(n4);
        n3.neighbors.add(n5);

        n4.neighbors.add(n1);
        n4.neighbors.add(n2);
        n4.neighbors.add(n3);
        n4.neighbors.add(n5);

        n5.neighbors.add(n1);
        n5.neighbors.add(n2);
        n5.neighbors.add(n3);
        n5.neighbors.add(n4);

        s.cloneGraph(n1);
    }
}


class Solution {

    static final int MAX_NODE_CNT = 100 + 2;
    List<Node> nodeQueue = new ArrayList<>();
    Node[] nodeList = new Node[MAX_NODE_CNT];
    int[][] network = new int[MAX_NODE_CNT][MAX_NODE_CNT];
    int nodeCnt = 0;

    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }

        initNetwork();

        dfs(node);

        for (int i = 1; i <= nodeCnt; i++) {
            for (int j = 1; j <= nodeCnt; j++) {
                if (i == j) {
                    continue;
                } else if (network[i][j] == 1) {
                    nodeList[i].neighbors.add(nodeList[j]);
                }
            }
        }

        return nodeList[1];
    }

    public void initNetwork() {
        for (int i = 0; i < MAX_NODE_CNT; i++) {
            network[i][i] = 1;
        }
    }

    public void dfs(Node node) {
        if (nodeList[node.val] == null) {
            nodeList[node.val] = new Node(node.val);
            nodeCnt++;

            for (Node neighbor : node.neighbors) {
                network[node.val][neighbor.val] = 1;
                dfs(neighbor);
            }
        } else {
            return;
        }
    }
}

// Definition for a Node.
class Node {

    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}