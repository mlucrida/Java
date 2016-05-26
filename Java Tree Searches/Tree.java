/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javashine;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author lucri
 */
public class Tree {
    public Node RootNode;
    
    // Linked Lists used in Testing
    public Queue<Node> BFS_Queue = new LinkedList<Node>();
    public Queue<Node> DFS_Queue = new LinkedList<Node>();
    public Queue<Node> HC_Queue = new LinkedList<Node>();
    
    public Tree(int value){
        this.RootNode = new Node(value, "Root"); 
               
        Node A3 = new Node(3, "A"); this.RootNode.LeftChild = A3;
        Node N2 = new Node(2, "N"); this.RootNode.RightChild = N2;
        Node G0 = new Node(0, "G"); A3.LeftChild = G0;
        Node K8 = new Node(8, "K"); A3.RightChild = K8;
        Node M4 = new Node(4, "M"); N2.LeftChild = M4;
        Node L3 = new Node(3, "L"); N2.RightChild = L3;
        Node X7 = new Node(7, "X"); M4.LeftChild = X7;
        
        HC_Queue.add(this.RootNode);
    }    
    
    // Breath-First Search (Recursive)
    public void BFS(Queue<Node> q){
        Node node = q.poll();
        BFS_Queue.add(node);
        System.out.println(node.name + ": " + node.value + " ");
        if(node.LeftChild != null)
            q.add(node.LeftChild);
        if(node.RightChild != null)
            q.add(node.RightChild);
        if(q.size() != 0)
            BFS(q);
    }
    
    // Depth-First Search (Recursive)
    public void DFS(Node node){
        if(node != null){
            DFS_Queue.add(node);
            System.out.println(node.name + ": " + node.value + " ");
            DFS(node.LeftChild);
            DFS(node.RightChild);
        }        
    }
    
    // Hill Climbing (Recursive)
    public void HillClimb(Node node){
        int CurrentVal = node.value;
        int LeftVal = -1, RightVal = -1;
        Node next;
        if(node.LeftChild != null)
            LeftVal = node.LeftChild.value;
        if(node.RightChild != null)
            RightVal = node.RightChild.value;
        
        if(RightVal < LeftVal)
            next = node.LeftChild;
        else
            next = node.RightChild;
        
        if((next != null) && (CurrentVal < next.value)){
            HC_Queue.add(next);
            System.out.print(" => " + next.name + next.value);
            HillClimb(next);
        }
        else
            System.out.print(" => " + "FINISHED \n");
            
    }
    
}
