/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author mwhai_000
 */
public class Visualization extends JPanel {

    TSP3 tsp;
    private int animationStepTime = 10;
    Timer timer;

    public Visualization(int width, int height, TSP3 tsp) {
//    this.addMouseListener(this);
        this.setBackground(new Color(255, 255, 255));
        this.tsp = tsp;
        this.timer = new Timer(animationStepTime, new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent evt) {
                repaint();
            }
        });
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //draw particles
        g2d.setColor(new Color(0, 0, 0));
        for (City city : tsp.map) {      
            g.fillOval((int) (city.x), (int) (city.y), 10, 10);
        }
        for (int i =0; i<tsp.CITY_COUNT-1;i++) {      
            g.drawLine(tsp.map.get(tsp.particles.get(0).getmData()[i]).x, tsp.map.get(tsp.particles.get(0).getmData()[i]).y, tsp.map.get(tsp.particles.get(0).getmData()[i+1]).x,tsp.map.get(tsp.particles.get(0).getmData()[i+1]).y);
        }
        g.drawLine(tsp.map.get(tsp.particles.get(0).getmData()[0]).x, tsp.map.get(tsp.particles.get(0).getmData()[0]).y, tsp.map.get(tsp.particles.get(0).getmData()[tsp.CITY_COUNT-1]).x,tsp.map.get(tsp.particles.get(0).getmData()[tsp.CITY_COUNT-1]).y);
    }

 
}
