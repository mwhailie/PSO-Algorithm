/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp3;

import edu.princeton.cs.algs4.StdDraw;

/**
 *
 * @author mwhai_000
 */
public class City {

    int x = 0;
    int y = 0;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double distanceTo(City city) {
        return Math.sqrt(((getX() - city.getX()) * (getX() - city.getX())) + ((getY() - city.getY()) * (getY() - city.getY())));
    }
//    // draws this point to standard drawing
//    public void draw() {
//        StdDraw.point(x, y);
//    }

    // draws the line segment between the two points to standard drawing
    public void drawTo(City that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
}
