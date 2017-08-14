/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp3;

import edu.princeton.cs.algs4.In;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;
import static sun.java2d.cmm.ColorTransform.In;

/**
 *
 * @author mwhai_000
 */
public class Run {

    public static void main(String[] args) throws FileNotFoundException {
        In in = new In("C:\\Users\\mwhai_000\\Downloads\\tsp\\tsp10.txt");
        int xscale = in.readInt();
        int yscale = in.readInt();
        TSP3 tsp = new TSP3(10);
        int i = 0;
        tsp.TARGET = 1566.2;
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            tsp.XLocs[i] = (int) x;
            tsp.YLocs[i] = (int) y;
            // Print line with new points coordinates
            System.out.println(tsp.XLocs[i] + " " + tsp.YLocs[i]);
            i++;
        }
//                TSP3 tsp = new TSP3();
        tsp.initializeMap();
        tsp.PSOAlgorithm();
        tsp.printBestSolution();
        
        
        Visualization visualization = new Visualization(800, 800, tsp);

        JFrame imgFrame = new JFrame();
        imgFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imgFrame.setSize(800, 800);
        imgFrame.add(visualization);
        visualization.setMinimumSize(new Dimension(800, 800));
        visualization.setPreferredSize(new Dimension(800, 800));
        imgFrame.setVisible(true);


    }
}
