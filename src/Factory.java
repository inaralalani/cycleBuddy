import java.util.Random;

/**
 * <b>Factory</b>
 * <p>
 * The Factory class makes a Person object with randomly 
 * generated alcohol, activity, and age values intended
 * for mock implementations of the application.
 * </p>
 *
 * @authors Inara Lalani, Akansha Malik, Amanda Nixon
 * @version 1.0, 12-Dec-21
 **/
public class Factory {

    public Factory() {
    }
    
    /**
     * <p>
     * A static utility method that makes a Person object
     * with randomly generated Factors.
     * </p>
     *
     * @return random Person object
     */
    public static Person createRandomPerson() {
        int ageMin = 18; // research for cycle length is based on a minimum age of 18
        int ageMax = 45; // research for cycle length is based on a maximum age of 45       
        int activityMax = 42; // assuming max of 6 hours a day per week

        Random rand = new Random();
        int age = (int) Math.floor(Math.random() * (ageMax - ageMin + 1) + ageMin);

        // days since last period on the interval [0, 30]
        int daysSinceLastPeriod = rand.nextInt(30);

        // alcohol is a boolean indicated whether a person has one or more
        // drinks per week
        boolean alcohol = rand.nextBoolean();

        // activity is a non-zero integer on the interval [0, 42]
        int activity = (int) Math.floor(Math.random() * activityMax + 1);

        System.out.printf(
                "\nage: %d, alcohol: %b, activity: %d, days since last period: %d \n",
                age, alcohol, activity, daysSinceLastPeriod);

        return new Person(age, alcohol, activity, daysSinceLastPeriod);
    }

}
