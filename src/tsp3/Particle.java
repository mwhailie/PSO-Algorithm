/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp3;

import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;

/**
 *
 * @author mwhai_000
 */
class Particle implements Comparable<Particle> {
    int name;
    int data[]; 
    double localBest = 0;
    double velocity = 0.0;
    ArrayList<City> tour = new ArrayList<>();
    
    public Particle(int CITY_COUNT) {
        data= new int[CITY_COUNT];
        this.localBest = 0;
        this.velocity = 0.0;
    }

    public int compareTo(Particle that) {
        if (this.getLocalBest() < that.getLocalBest()) {
            return -1;
        } else if (this.getLocalBest() > that.getLocalBest()) {
            return 1;
        } else {
            return 0;
        }
    }

    public int[] getmData() {
        return data;
    }

    public void setmData(int[] data) {
        this.data = data;
    }

    public double getLocalBest() {
        return localBest;
    }

    public void setLocalBest(double localBest) {
        this.localBest = localBest;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }   
    
    public int data(final int index) {
        return this.data[index];
    }

    public void data(final int index, final int value) {
        this.data[index] = value;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Particle{ " +  name +  " }";
    }
    
}
