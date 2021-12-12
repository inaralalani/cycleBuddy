import java.util.Random;

public class Factory {

    public Factory() {

    }

    /**
     * Static utility method to generate a random Person object
     *
     * @return random Person object
     */
    public static Person createRandomPerson() {
        int ageMin = 18; // research for cycle length is based on a minimum age of 18
        int ageMax = 45; // research for cycle length is based on a maximum age of 45

        // While it doesn't make a difference on the factor, this should probably be
        // a random value skewed towards single digit values
        int activityMax = 168; // 24 hours x 7 days a week

        Random rand = new Random();

        // self explanatory
        int age = (int) Math.floor(Math.random() * (ageMax - ageMin + 1) + ageMin);

        // days since last period on the interval [0, 30]
        int daysSinceLastPeriod = rand.nextInt(30);

        // alcohol is a boolean indicated whether a person has one or more
        // drinks per week
        boolean alcohol = rand.nextBoolean();

        // activity is a non-zero integer on the interval [0, 168]
        int activity = (int) Math.floor(Math.random() * activityMax + 1);

        // testing for random age, alcohol, and activity
        System.out.printf(
                "\nage: %d, alcohol: %b, activity: %d, days since last period: %d \n",
                age, alcohol, activity, daysSinceLastPeriod);

        return new Person(age, alcohol, activity, daysSinceLastPeriod);
    }

}
