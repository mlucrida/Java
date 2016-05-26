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
 * Examined by Daniel Sacdalan
 */
public class JavaShine {
   public Tree tree;
   public Queue<Node> q = new LinkedList<Node>();

    /**
     *
     * @param value
     */
   // Constructor used to assemble different classes used in main.
    public JavaShine(int value){
       // Initialize Tree
       this.tree = new Tree(value);
    }

    // Initilize call to BFS
    public void BFS_Start(){
        q.add(tree.RootNode);
        tree.BFS(q);
    }

    // Initialize call to DFS
    public void DFS_Start(){
        tree.DFS(tree.RootNode);
    }

    // Initialize call to Hill Climbing
    public void HillClimb_Start(){
        System.out.print(tree.RootNode.name + tree.RootNode.value);
        tree.HillClimb(tree.RootNode);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JavaShine Shine = new JavaShine(1);
        Stopwatch timer = new Stopwatch();
        JavaShineTests Tests = new JavaShineTests();

        // UNCOMMENT TO SEE TESTS
        //Tests.RUN();

        // Breadth-First Search
        System.out.println("BREADTH-FIRST SEARCH:");

        timer.Start();
        Shine.BFS_Start();
        timer.Stop();
        timer.ElapsedTime();

        // Depth-First Search
        System.out.println("DEPTH-FIRST SEARCH:");


        timer.Start();
        Shine.DFS_Start();
        timer.Stop();
        timer.ElapsedTime();

        System.out.println("Hill Climbing:");
        Shine.HillClimb_Start();
    }

}
