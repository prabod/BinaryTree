
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author prabod
 */
public class Lab5_140520G {

    public static void main(String[] args) {
        int[] numbers = new int[]{25, 15, 9, 18, 2, 90, 25, 42, 60, 35, 1, 19, 10, 100, 3};
        BSTNode bstree = new BSTNode();
        bstree.value = 25;
        for(int i = 1;i < numbers.length;i++ ){
            BSTNode.addValue(bstree, numbers[i]);
        }
        int size = Bintree.SizeOfTree(bstree);
        System.out.println(size);
        Bintree.traverse_postorder(bstree);
        BintreeNode[] nodes = new BintreeNode[size];
        Bintree.breadth(bstree, nodes);
        for (BintreeNode node : nodes) {
            System.out.print(node.value + " ");
        }

    }
}

class BintreeNode {

    int value;
    BintreeNode left;
    BintreeNode right;

    boolean addLeftChild(BintreeNode parent, int newvalue) {
        if (left != null) {
            left = new BintreeNode();
            left.value = newvalue;
            return true;
        }
        return false;
    }

    boolean addRightChild(BintreeNode parent, int newvalue) {
        if (right != null) {
            right = new BintreeNode();
            right.value = newvalue;
            return true;
        }
        return false;
    }

}

class Bintree {

    public static BintreeNode createTree(int[] elements) {
        BintreeNode[] nodes = new BintreeNode[elements.length];
        nodes[0] = new BintreeNode();
        nodes[0].value = elements[0];
        for (int i = 1; i < elements.length; i++) {
            nodes[i] = new BintreeNode();
            nodes[i].value = elements[i];
            if (i % 2 == 1) {
                nodes[(i - 1) / 2].left = nodes[i];
            } else {
                nodes[(i - 1) / 2].right = nodes[i];
            }
        }
        return nodes[0];
    }

    public static boolean expandTree(BintreeNode root, int[] elements) {

        int size = SizeOfTree(root);
        BintreeNode[] nodes = new BintreeNode[size + elements.length];
        breadth(root, nodes);
        for (int i = 0; i < elements.length; i++) {
            nodes[i + size] = new BintreeNode();
            nodes[i + size].value = elements[i];
            if ((i + size) % 2 == 1) {
                nodes[(i + size - 1) / 2].left = nodes[i + size];
            } else {
                nodes[(i + size - 1) / 2].right = nodes[i + size];
            }
        }
        return true;

    }

    public static void traverseIn(BintreeNode node, ArrayList tList) {
        if (node.left == null && node.right == null) {
            tList.add(node.value);
        } else {
            if (node.left != null) {
                traverseIn(node.left, tList);
            }

            tList.add(node.value);
            if (node.right != null) {
                traverseIn(node.right, tList);
            }
        }
    }

    public static void traverse_inorder(BintreeNode root) {
        ArrayList tlist = new ArrayList();
        traverseIn(root, tlist);
        System.out.println(tlist);
    }

    public static void traversePre(BintreeNode node, ArrayList tList) {
        if (node.left != null || node.right != null) {
            tList.add(node.value);
            if (node.left != null) {
                traversePre(node.left, tList);
            }
            if (node.right != null) {
                traversePre(node.right, tList);
            }
        } else {
            tList.add(node.value);
        }
    }

    public static void traverse_preorder(BintreeNode root) {
        ArrayList tlist = new ArrayList();
        traversePre(root, tlist);
        System.out.println(tlist);
    }

    public static void traversePost(BintreeNode node, ArrayList tList) {
        if (node.left == null && node.right == null) {
            tList.add(node.value);
        } else {
            if (node.left != null) {
                traversePost(node.left, tList);
            }
            if (node.right != null) {
                traversePost(node.right, tList);
            }
            tList.add(node.value);
        }
    }

    public static void traverse_postorder(BintreeNode root) {
        ArrayList tlist = new ArrayList();
        traversePost(root, tlist);
        System.out.println(tlist);
    }

    public static int SizeOfTree(BintreeNode n) {
        if (n == null) {
            return 0;
        } else {
            return (1 + SizeOfTree(n.left) + SizeOfTree(n.right));
        }
    }

    static Queue<BintreeNode> queue = new LinkedList<>();

    public static void breadth(BintreeNode root, BintreeNode[] nodes) {
        int index = 0;
        if (root == null) {
            return;
        }
        queue.clear();
        queue.add(root);
        while (!queue.isEmpty()) {
            BintreeNode node = queue.remove();
            nodes[index++] = node;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

    }
}

class BSTNode extends BintreeNode {

    static void put(BSTNode root, int newvalue) {
        if (root.left != null || root.right != null) {
            if (root.left!= null && newvalue < root.value) {
                put((BSTNode) root.left, newvalue);
            } 
            else if(root.right != null && newvalue >= root.value){
                put((BSTNode) root.right, newvalue);
            }
            else if(newvalue < root.value){
                root.left = new BSTNode();
                root.left.value = newvalue;
            }
            else{
                root.right = new BSTNode();
                root.right.value = newvalue;
            }
        } else {
            if (newvalue < root.value) {
                root.left = new BSTNode();
                root.left.value = newvalue;
            } else {
                root.right = new BSTNode();
                root.right.value = newvalue;
            }
        }
    }

    static boolean addValue(BSTNode root, int newvalue) {
        put(root, newvalue);
        return true;
    }

    boolean deleteValue(BSTNode root, int value) {
        BSTNode search_result = searchValue(root, value);
        if (search_result != null) {
            BSTNode minimum = min((BSTNode) search_result.right);
            searchValue(root, value).value = minimum.value;
            deleteMin((BSTNode) search_result.right);
            return true;
        }
        return false;
    }

    BSTNode min(BSTNode root) {
        if (root.left != null) {
            return min((BSTNode) root.left);
        }
        return root;
    }

    void deleteMin(BSTNode root) {
        if (root.left != null && root.left.left != null) {
            deleteMin((BSTNode) root.left);
        } else if (root.left.right != null) {
            root.left = root.left.right;
        } else {
            root.left = null;
        }
    }

    BSTNode searchValue(BSTNode root, int value) {
        if (value < root.value) {
            return searchValue((BSTNode) root.left, value);
        } else if (value > root.value) {
            return searchValue((BSTNode) root.right, value);
        } else if (value == root.value) {
            return root;
        }
        return null;
    }

    void print_tree(BSTNode root) {
    }
}
