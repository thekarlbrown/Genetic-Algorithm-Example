import java.util.List;
import java.util.Random;

/**
 * Created by Karl (thekarlbrown) on 6/9/2015.
 * Implementation of my Abstract BaseGeneticAlgorithm class
 * TODO: Decide how to handle matching fitness score
 */
public class StringGeneticAlgorithm extends BaseGeneticAlgorithm {
    private final int stringLength;
    private final char[] targetChars;

    /**
     * Constructor for the default Algorithm
     * @param crossoverRate Odds of each Chromosome switching
     * @param populationSize Number of Chromosomes in the genetic algorithm
     * @param mutationRate Chance that a Chromosome will randomly mutate
     */
    public StringGeneticAlgorithm(double crossoverRate, int populationSize, double mutationRate, String targetString){
        super(crossoverRate, populationSize, mutationRate);
        this.stringLength=targetString.length();
        this.targetChars=targetString.toCharArray();
        for(int x=0;x<populationSize;x++){ getChromosomes().add(createRandomChromosome()); }
    }

    /**
     * Method to create an appropriately formed random Chromosome with lowercase characters
     * @return Chromosome to be created for the Genetic Algorithm
     */
    @Override
    Chromosome createRandomChromosome() {
        StringBuilder stringBuilder = new StringBuilder(stringLength);
        Random random = new Random();
        //Generate random characters for the length of the String
        for(int x=0;x<stringLength;x++){  stringBuilder.append((char)(random.nextInt(26)+'a'));  }
        return new StringChromosome(stringBuilder.toString());
    }

    /**
     * Utilizes StringBuilder to display the current contents of my Genetic Algorithm
     * @return Formatted String with every Chromosomes String and Fitness Score
     */
    @Override
    String visualizeContents() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the current Strings and Fitness Scores for every chromosome\n");
        for(Chromosome chromosome : getChromosomes()){
            stringBuilder.append(chromosome.getContents());
            stringBuilder.append(" : ");
            stringBuilder.append(chromosome.getFitnessScore());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Calculates then assigns the Fitness Score of each Chromosome
     * Uses a 1/Mismatches summation
     * @param firstTime Makes sure to assign a score to every Chromosome the first time
     */
    @Override
    void assignFitnessScores(boolean firstTime) {
        double mismatchedCount;
        //Go over every Chromosome in the List
        List<Chromosome> currentList = getChromosomes();
        for(int x=0;x<currentList.size();x++){
            //Using a protected modifier, checks to make sure the
            if(firstTime||x==firstSelection||x==secondSelection) {
                Chromosome currentChromosome=currentList.get(x);
                mismatchedCount = 0;
                //Compare to the target String character by character
                char[] currentChars = ((String) currentChromosome.getContents()).toCharArray();
                for (int y = 0; y < stringLength; y++) {
                    //Add to the mismatches if unequal
                    if (targetChars[y] != currentChars[y]) { mismatchedCount++; }
                }
                //TODO:Should we exit out at a full match?
               currentChromosome.setFitnessScore(1.0 / mismatchedCount);
            }
        }
    }
}
