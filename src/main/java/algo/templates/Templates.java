package algo.templates;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Templates {

    public static Set<Edge> mstPrims(int n, Map<Integer, List<Edge>> graph) {

        // contains vertices that already added to mst
        Set<Integer> mst = new HashSet<>();
        Set<Edge> mstEdges = new HashSet<>();

        PriorityQueue<Edge> q = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));

        // let start from random vertex
        int src = 0;

        // add vertex we want to start from to mst and add all adjacent edges to queue.
        mst.add(src);
        q.addAll(graph.get(src));

        // during each iteration peek the edge with smallest weight pointing to node outside of MST
        while(!q.isEmpty()) {
            Edge edge = q.poll();
            int v = edge.v;

            // skip edges that are pointing to node that is already in MST
            if (mst.contains(v)) continue;

            mst.add(v);
            mstEdges.add(edge);

            for (Edge e : graph.get(v)) {
                if (!mst.contains(e.v)) q.add(e);
            }
        }

        return mstEdges;
    }

    /**
     * Preorder traversal of the graph using Morris traversal
     */
    public static List<Integer> morrisTraversal(TreeNode root) {
        TreeNode current = root;

        var result = new LinkedList<Integer>();

        while(current != null) {

            // nothing to explore on the left side
            if (current.left == null) {
                result.add(current.val);
                current = current.right;
                continue;
            }

            TreeNode pred = current.left;
            while(pred.right != null && pred.right != current) pred = pred.right;

            // if true we have already updated predecessor to point to current node, that is we explored left part
            if (pred.right == current) {
                pred.right = null;
                current = current.right;
            }
            //  create back reference from the closest predecessor to current node and move to the left part
            else {
                result.add(current.val);
                pred.right = current;
                current = current.left;
            }
        }

        return result;
    }

    static class Edge {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
