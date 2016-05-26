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
 * @author Matt Lucrida
 */

/**
 * All tests compare the results of the searches
 * to a predetermined queue with the expected results
 * declared within the test project.
 */
public class JavaShineTests {
    
    private Tree tree = new Tree(1);
    public Queue<Node> BFS_Test_Queue = new LinkedList<Node>();
    public Queue<Node> BFS_Helper_Queue = new LinkedList<Node>();
    public Queue<Node> DFS_Test_Queue = new LinkedList<Node>();
    public Queue<Node> HC_Test_Queue = new LinkedList<Node>();
    
    public JavaShineTests(){
        // Initialize expected BFS result 
        BFS_Test_Queue.add(new Node(1, "Root"));
        BFS_Test_Queue.add(new Node(3, "A"));
        BFS_Test_Queue.add(new Node(2, "N"));
        BFS_Test_Queue.add(new Node(0, "G"));
        BFS_Test_Queue.add(new Node(8, "K"));
        BFS_Test_Queue.add(new Node(4, "M"));
        BFS_Test_Queue.add(new Node(3, "L"));
        BFS_Test_Queue.add(new Node(7, "X"));
        
        // Initialize BFS Helper Queue
        BFS_Helper_Queue.add(this.tree.RootNode);
        
        // Initialize expected DFS result
        DFS_Test_Queue.add(new Node(1, "Root"));
        DFS_Test_Queue.add(new Node(3, "A"));
        DFS_Test_Queue.add(new Node(0, "G"));
        DFS_Test_Queue.add(new Node(8, "K"));
        DFS_Test_Queue.add(new Node(2, "N"));
        DFS_Test_Queue.add(new Node(4, "M"));
        DFS_Test_Queue.add(new Node(7, "X"));
        DFS_Test_Queue.add(new Node(3, "L"));
        
        // Initialize expected Hill Climbing result
        HC_Test_Queue.add(new Node(1, "Root"));
        HC_Test_Queue.add(new Node(3, "A"));
        HC_Test_Queue.add(new Node(8, "K"));
    }
    
    // Test for BFS
    private Boolean BFSTest(){
        Boolean Result = true;
        int size = BFS_Test_Queue.size();
        tree.BFS(BFS_Helper_Queue);
        for(int i = 0; i < size; i++){
            System.out.println("Test Q: " + BFS_Test_Queue.peek().name + 
                                " || Q: " +tree.BFS_Queue.peek().name);
            if(tree.BFS_Queue.poll().name != BFS_Test_Queue.poll().name){
                Result = false;
                //return Result; // Uncomment for shortened version
            }
        }
        return Result;
    }
    // Test for DFS
    private Boolean DFSTest(){
        Boolean Result = true;
        int size = DFS_Test_Queue.size();
        tree.DFS(tree.RootNode);
        for(int i = 0; i < size; i++){
            System.out.println("Test Q: " + DFS_Test_Queue.peek().name + 
                                " || Q: " +tree.DFS_Queue.peek().name);
            if(tree.DFS_Queue.poll().name != DFS_Test_Queue.poll().name){
                Result = false;
                //return Result; // Uncomment for shortened version
            }
        }
        return Result;
    }
    
    // Test for Hill Climbing
    private Boolean HCTest(){
        Boolean Result = true;
        int size = HC_Test_Queue.size();
        tree.HillClimb(tree.RootNode);
        for(int i = 0; i < size; i++){
            System.out.println("Test Q: " + HC_Test_Queue.peek().name + 
                                " || Q: " +tree.HC_Queue.peek().name);
            if(tree.HC_Queue.poll().name != HC_Test_Queue.poll().name)
                Result = false;
        }
        return Result;
    }
    
    // used to runn all tests and report their results
    public void RUN(){      
        
        if(BFSTest())
            System.out.println("BFS Test: PASSED\n");
        else
            System.out.println("BFS Test: FAILED\n");
        
        
        if(DFSTest())
            System.out.println("DFS Test: PASSED\n");
        else
            System.out.println("DFS Test: FAILED\n");
        
        
        if(HCTest())
            System.out.println("Hill Climbing Test: PASSED\n");
        else
            System.out.println("Hill Climbing Test: FAILED\n");
    }
}
