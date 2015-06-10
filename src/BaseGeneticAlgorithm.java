import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karl Brown (thekarlbrown) on 6/9/2015.
 * This class functions as a basis for genetic algorithms.
 * It must be extended, as seen by abstract methods.
 * It contains references to Chromosome, a class containing each Chromosome
 */
public abstract class BaseGeneticAlgorithm {
    private final double chanceOfCrossover; //Chance of each Chromosome switching
    private final int sizeOfPopulation; //Number of Chromosomes in the genetic algorithm
    private final double chanceOfMutation; //Chance that a Chromosome will randomly mutate
    private List<Chromosome> chromosomes; //Each of the Chromosomes in our genetic algorithm
    protected int firstSelection; //Save the two altered components for speed increase in Fitness Score calculation
    protected int secondSelection;
    public List<Chromosome> getChromosomes(){ return chromosomes; }
    public int getSizeOfPopulation(){ return sizeOfPopulation; }

    /**
     * Constructor for a generic Genetic Algorithm with universal characteristics
     * @param crossoverRate Odds of each Chromosome switching
     * @param populationSize Number of Chromosomes in the genetic algorithm
     * @param mutationRate Chance that a Chromosome will randomly mutate
     */
    public BaseGeneticAlgorithm (double crossoverRate, int populationSize, double mutationRate){
        sizeOfPopulation=populationSize;
        chanceOfCrossover=crossoverRate;
        chanceOfMutation=mutationRate;
        chromosomes=new ArrayList<>();
        firstSelection=-1;
        secondSelection=-1;
    }

    abstract void assignFitnessScores(boolean firstTime); //How the specific Genetic Algorithm creates a fitness score
    abstract String visualizeContents(); //How we choose to visualize the contents of our List
    abstract Chromosome createRandomChromosome(); //Will be abstracted with subclass of Chromosome


    /**
     * This is a method that implements the Roulette Wheel algorithm to randomly choose
     * a Chromosome from the List, giving extra weight based on each Fitness Score
     * @return List Location chosen at Random
     */
    public int rouletteWheelSelection(){
        double offset=0.0;
        double totalFitness=0.0;
        //Add up the total fitness score of every Chromosome
        for (Chromosome c : chromosomes){ totalFitness+=c.getFitnessScore(); }
        //Randomly choose a value inside the total fitness score
        double randomizedSelection=totalFitness*Math.random();
        //Go through the List, adding up the fitness scores then exit when we hit the chosen value
        for(int x=0;x<chromosomes.size();x++){
            offset+=chromosomes.get(x).getFitnessScore();
            if(randomizedSelection<offset){  return x;    }
        }
        //This point is unreachable, -1 indicates an error in the Roulette Wheel Algorithm
        return -1;
    }

    public void performRoundOfGeneticAlgorithm(){
        //Test each Chromosome to see its quality compared to goal
        assignFitnessScores(false);
        //Select two Chromosomes from the population
        firstSelection = rouletteWheelSelection();
        secondSelection = rouletteWheelSelection();
        while(firstSelection==secondSelection){ secondSelection=rouletteWheelSelection(); }
        Chromosome firstSelected = chromosomes.get(firstSelection);
        Chromosome secondSelected = chromosomes.get(secondSelection);
        //Perform a crossover with the two selected Chromosomes
        if(chanceOfCrossover>Math.random()){ firstSelected.performCrossover(secondSelected); }
        //Potentially mutate both Chromosomes
        firstSelected.mutationOfContents(chanceOfMutation);
        secondSelected.mutationOfContents(chanceOfMutation);
    }

}
