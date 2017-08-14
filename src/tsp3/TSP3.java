/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author mwhai_000
 */
public class TSP3 {

    private static final int PARTICLE_COUNT = 100;
    private static final int V_MAX = 3; // Maximum velocity change allowed.
    // Range: 0 >= V_MAX < CITY_COUNT

    private static final int MAX_ITERATION = 100;

    ArrayList<Particle> particles = new ArrayList<Particle>();

    ArrayList<City> map = new ArrayList<City>();
//    int CITY_COUNT = 10;
    double TARGET =2000;				// Number for algorithm to find.
//    int XLocs[] = new int[]{ 110, 161, 325, 490, 157, 283, 379, 306,343,552};
//    int YLocs[] = new int[]{225, 280, 554, 285, 443, 379, 306, 360,110,199};
    
    int CITY_COUNT;
    int XLocs[];
    int YLocs[];
    public TSP3(int CITY_COUNT) {
        this.CITY_COUNT = CITY_COUNT;
        XLocs= new int[CITY_COUNT];
        YLocs= new int[CITY_COUNT];
    }

    
    void initializeMap() {
        for (int i = 0; i < CITY_COUNT; i++) {
            City city = new City();
            city.setX(XLocs[i]);
            city.setY(YLocs[i]);
            map.add(city);
        }
    }
    void initializeAgent() {
        for (int i = 0; i < PARTICLE_COUNT; i++) {
            Particle newParticle = new Particle(CITY_COUNT);
            newParticle.setName(i);
            for (int j = 0; j < CITY_COUNT; j++) {
                newParticle.data(j, j);                
            } // j
            particles.add(newParticle);
            for (int j = 0; j < CITY_COUNT; j++) {
                randomlyArrange(particles.indexOf(newParticle));
            }
            getTotalDistance(particles.indexOf(newParticle));
        } // i
    }

    void PSOAlgorithm() {
        Particle agent = null;
        int iteration = 0;
        boolean done = false;

        initializeAgent();

        while (!done) {
            // Two conditions can end this loop:
            //    if the maximum number of epochs allowed has been reached, or,
            //    if the Target value has been found.
            if (iteration < MAX_ITERATION) {

                for (int i = 0; i < PARTICLE_COUNT; i++) {
                    agent = particles.get(i);
                    System.out.print("Agent: " + agent.toString()+ ", ");
                    System.out.print("Route: ");
                    for (int j = 0; j < CITY_COUNT; j++) {
                        System.out.print(agent.data(j) + ", ");
                    } // j

                    getTotalDistance(i);
                    System.out.print("Distance: " + agent.getLocalBest() + "\n");
                    if (agent.getLocalBest() <= TARGET) {
                        done = true;
                    }
                } // i
                Collections.sort(particles);
                // sort particles by their pBest scores, best to worst.
                updateVelocity();
                updateParticles();
                System.out.println("iteration number: " + iteration);
                iteration++;

            } else {
                done = true;
            }
        }
    }


    private void randomlyArrange(final int index) {
        int cityA = new Random().nextInt(CITY_COUNT);
        int cityB = 0;
        boolean done = false;
        while (!done) {
            cityB = new Random().nextInt(CITY_COUNT);
            if (cityB != cityA) {
                done = true;
            }
        }
        int temp = particles.get(index).data(cityA);
        particles.get(index).data(cityA, particles.get(index).data(cityB));
        particles.get(index).data(cityB, temp);
    }

    void updateVelocity() {
        double worstResults = 0;
        double vValue = 0.0;
        // after sorting, worst will be last in list.
        worstResults = particles.get(PARTICLE_COUNT - 1).getLocalBest();
        for (int i = 0; i < PARTICLE_COUNT; i++) {
            vValue = (V_MAX * particles.get(i).getLocalBest()) / worstResults;

            if (vValue > V_MAX) {
                particles.get(i).setVelocity(V_MAX);
            } else if (vValue < 0.0) {
                particles.get(i).setVelocity(0.0);
            } else {
                particles.get(i).setVelocity(vValue);
            }
        }
    }

    void updateParticles() {
        // Best is at index 0, so start from the second best.
        for (int i = 1; i < PARTICLE_COUNT; i++) {
            // The higher the velocity score, the more changes it will need.
            int changes = (int) Math.floor(Math.abs(particles.get(i).getVelocity()));
            System.out.println("Changes for particle " + i + ": " + changes);
            for (int j = 0; j < changes; j++) {
                if (new Random().nextBoolean()) {
                    randomlyArrange(i);
                }
                // Push it closer to it's best .
                copyFromParticle(0, i);
            } // j
            // Update pBest value.
            getTotalDistance(i);
        } // i

    }

    void printBestSolution() {
        if (particles.get(0).getLocalBest() <= TARGET) {
            // Print it.
            System.out.println("Target reached.");
        } else {
            System.out.println("Target not reached");
        }
        System.out.print("Shortest Route: ");
        for (int j = 0; j < CITY_COUNT; j++) {
            System.out.print(particles.get(0).data(j) + ", ");
        } // j

        System.out.print("Distance: " + particles.get(0).getLocalBest() + "\n");
        
    }

    void copyFromParticle(final int source, final int destination) {
        // push destination's data points closer to source's data points.
        Particle best = particles.get(source);
        int targetA = new Random().nextInt(CITY_COUNT); // source's city to target.
        int targetB = 0;
        int indexA = 0;
        int indexB = 0;
        int tempIndex = 0;
        // targetB will be source's neighbor immediately succeeding targetA (circular).
        int i = 0;
        for (; i < CITY_COUNT; i++) {
            if (best.data(i) == targetA) {
                if (i == CITY_COUNT - 1) {
                    targetB = best.data[0]; // if end of array, take from beginning.
                } else {
                    targetB = best.data[i + 1];
                }
                break;
            }
        }
        // Move targetB next to targetA by switching values.
        for (int j = 0; j < CITY_COUNT; j++) {
            if (particles.get(destination).data[j] == targetA) {
                indexA = j;
            }
            if (particles.get(destination).data[j] == targetB) {
                indexB = j;
            }
        }
        // get temp index succeeding indexA.
        if (indexA == CITY_COUNT - 1) {
            tempIndex = 0;
        } else {
            tempIndex = indexA + 1;
        }

        // Switch indexB value with tempIndex value.
        int temp = particles.get(destination).data[tempIndex];
        particles.get(destination).data[tempIndex]= particles.get(destination).data(indexB);
        particles.get(destination).data[indexB]= temp;
    }

    void getTotalDistance(final int index) {
        Particle thisParticle = null;
        thisParticle = particles.get(index);
        thisParticle.setLocalBest(0.0);

        for (int i = 0; i < CITY_COUNT; i++) {
            if (i == CITY_COUNT - 1) {
                thisParticle.setLocalBest(thisParticle.getLocalBest() + distanceTo(thisParticle.data(CITY_COUNT - 1), thisParticle.data(0))); // Complete trip.
            } else {
                thisParticle.setLocalBest(thisParticle.getLocalBest() + distanceTo(thisParticle.data(i), thisParticle.data(i + 1)));
            }
        }
    }

    double distanceTo(int firstCity,  int secondCity) {
        City cityA = null;
        City cityB = null;
        cityA = map.get(firstCity);
        cityB = map.get(secondCity);        
        return cityA.distanceTo(cityB);
    }

}
