/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javashine;

/**
 *
 * @author lucri
 */
public class Node {
    public int value;
    public String name;
    public Node LeftChild;
    public Node RightChild;
    public Node Parent;
    
    public Node(int value, String name){
        this.value = value;
        this.name = name;
        this.LeftChild = null;
        this.RightChild = null;
        this.Parent = null;
    }
    
    public void InsertLeftBranch(Node parent, Node target){
        Node temp = parent.LeftChild;
        parent.LeftChild = target;
        target.Parent = parent;
        target.LeftChild = temp;
        temp.Parent = target;
    }
    
    public void InsertRightBranch(Node parent, Node target){
        Node temp = parent.RightChild;
        parent.RightChild = target;
        target.Parent = parent;
        target.RightChild = temp;
        temp.Parent = target;
    }
    
}
