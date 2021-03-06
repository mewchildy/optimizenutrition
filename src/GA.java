import java.util.Random;
import java.util.Scanner;

public class GA {
    //GA parameter
    private static final double crossoverRate = NutritionPlan.crossoverRate;
    private static final double mutationRate = NutritionPlan.mutationRate;
    private static final int tournamentSize = 10;
    private static final boolean elitism = true;
    final static int elitismCount = 5;

    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false);
        //Elitism

        if (elitism) {
            for(int i=0 ; i<elitismCount ; i++) {
                newPopulation.saveIndividual(i, pop.getFittest());
            }
        }
        int elitismOffset;
        if (elitism) {
            elitismOffset = elitismCount;
        } else {
            elitismOffset = 0;
        }

        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // Crossover individuals
    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        // Loop through genes
        Random rn = new Random();
        for (int i = 0; i < indiv1.size(); i++) {
            // Crossover
            if (Math.random() <= crossoverRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    // Mutate an individual
    private static void mutate(Individual indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            Random rn = new Random();
            if (rn.nextDouble() <= mutationRate) {
                // Create random gene
                int gene =  Math.abs(rn.nextInt()%2);
                indiv.setGene(i, gene);
            }
        }
    }

    // Select individuals for crossover
    private static Individual tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.getFittest();
        return fittest;
    }



}
