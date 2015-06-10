/**
 * Created by Karl (thekarlbrown) on 6/9/2015.
 * Serves to test out my Genetic Algorithms implementation
 */
public class TestIt {
    public static void main (String [] args){
        //Set up my Genetic Algorithm for the first time and output Chromosomes
        StringGeneticAlgorithm test = new StringGeneticAlgorithm(.88,25,.001,"sillypants");
        test.assignFitnessScores(true);
        System.out.println(test.visualizeContents());
        //Perform the first round of the algorithm and output Chromosomes
        test.performRoundOfGeneticAlgorithm();
        System.out.println(test.visualizeContents());
        //Perform ten million rounds and output Chromosomes
        for(int x=0;x<10000000;x++){ test.performRoundOfGeneticAlgorithm(); }
        System.out.println(test.visualizeContents());
    }
}
