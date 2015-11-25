/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarytree;


/**
 *
 * @author prabod
 */
public class BintreeNode {
    int value;
    BintreeNode left;
    BintreeNode right;
    
    boolean addLeftChild (BintreeNode parent, int newvalue){ 
        if (left != null){
            left = new BintreeNode();
            left.value = newvalue;
            return true;
        }
        return false;
    }
    boolean addRightChild (BintreeNode parent, int newvalue){
    if (right != null){
            right = new BintreeNode();
            right.value = newvalue;
            return true;
        }
        return false;
    }
    
}