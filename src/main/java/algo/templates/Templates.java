package algo.templates;

import java.util.LinkedList;
import java.util.List;

public class Templates {

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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
