/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author prabod
 */
public class Bintree {
    
    public static void main(String[] args){
        int[] numbers = new int[]{25, 15, 9, 18, 2, 90, 25, 42, 60, 35, 1, 19, 10, 100, 3};
        BintreeNode r = createTree(numbers);
        int[] ex = new int[]{421,21421,124214,12};
        expandTree(r,ex);
        int size = SizeOfTree(r);
        BintreeNode[] nodes = new BintreeNode[size];
        breadth(r,nodes);
        for(BintreeNode node : nodes){
            System.out.print(node.value + " ");
        }
        System.out.println(r.left.value);
        
    }
    
    public static BintreeNode createTree (int[] elements){ 
        BintreeNode[] nodes = new BintreeNode[elements.length];
        nodes[0] = new BintreeNode();
        nodes[0].value = elements[0];
        for (int i = 1; i < elements.length; i++){
            nodes[i] = new BintreeNode();
            nodes[i].value = elements[i];
            if ( i % 2 == 1){
                nodes[(i-1)/2].left = nodes[i];
            }
            else{
                nodes[(i-1)/2].right = nodes[i];
            }
        }
        return nodes[0];
    }
    public static boolean expandTree (BintreeNode root, int[] elements){
        
        int size = SizeOfTree(root);
        BintreeNode[] nodes = new BintreeNode[size + elements.length];
        breadth(root,nodes);
        for (int i = 0; i < elements.length; i++){
            nodes[i + size] = new BintreeNode();
            nodes[i + size].value = elements[i];
            if ( (i + size) % 2 == 1){
                nodes[(i+size-1)/2].left = nodes[i + size];
            }
            else{
                nodes[(i+size-1)/2].right = nodes[i + size];
            }
        }
        return true;
        
    }
    public static void traverseIn(BintreeNode node,ArrayList tList){
        if(node.left == null && node.right == null){
            tList.add(node.value);
        }
        else{ 
            if (node.left != null){
                traverseIn(node.left,tList);
            }
            
            tList.add(node.value);
            if(node.right != null){
                traverseIn(node.right,tList);
            }
        }
    }
    
    public static void traverse_inorder(BintreeNode root){
        ArrayList tlist = new ArrayList();
        traverseIn(root,tlist);
        System.out.println(tlist);
    }
    
    public static void traversePre(BintreeNode node,ArrayList tList){
        if(node.left != null || node.right != null){
            tList.add(node.value);
            if (node.left != null){
                traversePre(node.left,tList);
            }
            if(node.right != null){
                traversePre(node.right,tList);
            }
        }
        else{
            tList.add(node.value);
        }
    }
    public static void traverse_preorder(BintreeNode root){ 
        ArrayList tlist = new ArrayList();
        traversePre(root,tlist);
        System.out.println(tlist);
    }
    
    public static void traversePost(BintreeNode node,ArrayList tList){
        if(node.left == null || node.right == null){
            tList.add(node.value);
        }
        else{
            if (node.left != null){
                traversePost(node.left,tList);
            }
            if(node.right != null){
                traversePost(node.right,tList);
            }
            tList.add(node.value);
        }
    }
    
    public static void traverse_postorder(BintreeNode root){ 
        ArrayList tlist = new ArrayList();
        traversePost(root,tlist);
        System.out.println(tlist);
    }
    
    public static int SizeOfTree(BintreeNode n){
        if (n == null){
            return 0;
        }
        else{
            return (1 + SizeOfTree(n.left) + SizeOfTree(n.right));
        }
    }
    
    static Queue<BintreeNode> queue = new LinkedList<>() ;
    public static  void breadth(BintreeNode root,BintreeNode[] nodes) {
        int index = 0;
        if (root == null){
            return;
        }
        queue.clear();
        queue.add(root);
        while(!queue.isEmpty()){
            BintreeNode node = queue.remove();
            nodes[index++] = node;
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
        }

}
}
