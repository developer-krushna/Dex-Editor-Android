package modder.hub.dexeditor.utils;

import java.util.List;
import modder.hub.dexeditor.model.TreeNode;

/**
 * TreeHelper: Manages recursive operations for TreeNode structures.
 */
public class TreeHelper {

    public static void expandAll(List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            if (!node.getChildren().isEmpty()) {
                node.setExpanded(true);
                expandAll(node.getChildren());
            }
        }
    }

    public static void collapseAll(List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            node.setExpanded(false);
            collapseAll(node.getChildren());
        }
    }

    public static void setCheckedRecursive(TreeNode node, boolean checked) {
        node.setChecked(checked);
        if (node.isDirectory()) {
            for (TreeNode child : node.getChildren()) {
                setCheckedRecursive(child, checked);
            }
        }
    }

    public static void clearSelectionRecursive(List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            node.setChecked(false);
            if (!node.getChildren().isEmpty()) {
                clearSelectionRecursive(node.getChildren());
            }
        }
    }

    public static void onlyExpandPackages(List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            if (node.isDirectory()) {
                node.setExpanded(true);
                onlyExpandPackages(node.getChildren());
            } else {
                node.setExpanded(false);
            }
        }
    }
}
