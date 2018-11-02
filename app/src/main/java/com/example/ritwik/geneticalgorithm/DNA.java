package com.example.ritwik.geneticalgorithm;

import java.util.Random;

public class DNA {

    // The genetic sequence
    private char[] genes;

    double fitness;

    Random r = new Random();

    // Constructor (makes a random DNA)
    DNA(int num) {
        genes = new char[num];
        for (int i = 0; i < genes.length; i++) {
            int Low = 32;
            int High = 128;
            int Result = r.nextInt(High-Low) + Low;
            char c = (char)Result;
            genes[i] = c;  // Pick from range of chars
        }
    }

    // Converts character array to a String
    String getPhrase() {
        return new String(genes);
    }

    // Fitness function (returns floating point % of "correct" characters)
    void fitness (String target) {
        int score = 0;
        for (int i = 0; i < genes.length; i++) {
            if (genes[i] == target.charAt(i)) {
                score++;
            }
        }


        fitness = (double)score / (double) target.length();
    }

    // Crossover
    DNA crossover(DNA partner) {
        // A new child
        DNA child = new DNA(genes.length);

        int midpoint = r.nextInt(genes.length); // Pick a midpoint

        // Half from one, half from the other
        for (int i = 0; i < genes.length; i++) {
            if (i > midpoint) child.genes[i] = genes[i];
            else              child.genes[i] = partner.genes[i];
        }
        return child;
    }

    // Based on a mutation probability, picks a new random character
    void mutate(double mutationRate) {
        for (int i = 0; i < genes.length; i++) {
            double rangeMin = 0.0f;
            double rangeMax = 1.0f;
            double createdRanNum = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            if (createdRanNum < mutationRate) {
                int Low = 32;
                int High = 128;
                int Result = r.nextInt(High-Low) + Low;
                char c = (char)Result;
                genes[i] = c;
            }
        }
    }
}
