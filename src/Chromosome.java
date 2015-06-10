/**
 * Created by Karl Brown (thekarlbrown) on 6/9/2015.
 * This is a class for a generic Chromosome
 * It defines Chromosome specific operations
 */
abstract class Chromosome {
    private double fitnessScore; //Score describing how well the Chromosome solves the problem at hand
    private Object contents; //What the specific Chromosome is made up of

    public double getFitnessScore() { return fitnessScore; }
    public void setFitnessScore (double newScore) { fitnessScore=newScore; };
    public Object getContents() { return contents; }
    public void setContents(Object contents) { this.contents = contents; }

    /**
     * Constructor to create a new generic Chromosome
     * @param contents Object to be assigned as a Chromosome
     */
    public Chromosome (Object contents){ this.contents = contents; }

    /**
     * Mutate contents while keeping the odds of mutation
     * @param oddsOfMutation Odds of mutating each specific element of object
     */
    abstract void mutationOfContents(double oddsOfMutation);

    /**
     * Perform a generic crossover using partner's Chromosome
     * @param partner Chromosome to crossover with
     */
    abstract void performCrossover(Chromosome partner);
}
