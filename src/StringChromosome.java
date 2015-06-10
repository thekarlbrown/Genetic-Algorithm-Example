import java.util.Random;

/**
 * Created by Karl (thekarlbrown) on 6/9/2015.
 * Implementation of my Abstract Chromosome class
 */
public class StringChromosome extends Chromosome {

    /**
     * Constructor which implements a specific object for the Chromosome
     * Deals with lowercase only currently
     * TODO: Implement both alphabetical cases, potentially eliminate amount of casting
     * @param contents String to be used as contents of Chromosome
     */
    public StringChromosome (String contents){ super(contents);   }

    /**
     * Implementation of a Genetic Mutation for Strings that changes a letter
     * to a random letter with custom odds 1/(Length*2)
     * @param oddsOfMutation Odds of mutating each specific element of object, unused
     */
    @Override
    void mutationOfContents(double oddsOfMutation) {
        int stringLength=((String)getContents()).length();
        oddsOfMutation=.5/((double)stringLength);
        String contents=((String) getContents());
        Random random = new Random(); //Creating it once here is currently quickest approach
        //Randomly checking for a mutation at every character
        for(int x=0;x<stringLength;x++){
            if(Math.random()<oddsOfMutation){
                if(x==0){//If 0 or final element operations stay in bounds and are quicker
                    setContents((char)(random.nextInt(26)+'a') + contents.substring(1));
                }else if(x==(stringLength-1)){
                    setContents(contents.substring(0,stringLength-1) + (char)(random.nextInt(26)+'a'));
                }else{//For every other case
                    StringBuilder stringBuilder = new StringBuilder(stringLength);
                    stringBuilder.append(contents.substring(0, x));
                    stringBuilder.append((char)(random.nextInt(26)+'a'));
                    stringBuilder.append(contents.substring(x+1,stringLength));
                    setContents(stringBuilder.toString());
                }
            }
        }
    }

    /**
     * Implement a single point crossover. All Genetics are kept from the intersection forward
     * @param partner Chromosome to crossover with
     */
    @Override
    void performCrossover(Chromosome partner) {
        String contents = (String)getContents();
        String partnerContents=(String)partner.getContents();
        Random random = new Random();
        int stringLength=contents.length();
        //Choose a random point for the crossover, insuring one Gene remains
        int intersection=random.nextInt(stringLength-1);
        //Perform a swap utilizing the saved String contents
        setContents(partnerContents.substring(intersection,intersection+1)+contents.substring(intersection+1,stringLength));
        partner.setContents(contents.substring(intersection,intersection+1)+partnerContents.substring(intersection+1,stringLength));
    }
}
