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
public class Stopwatch {
    private double start;
    private double stop;
    
    public void Start(){
        this.start = System.nanoTime();
    }
    
    public void Stop(){
        this.stop = System.nanoTime();
    }
    
    public void Reset(){
        this.stop = 0;
        this.start = 0;
    }
    
    public void ElapsedTime(){
        double elapsed = (stop-start)/100000.0;
        System.out.println("Time Elapsed: " + elapsed + " Nano Seconds\n");
    }
}
