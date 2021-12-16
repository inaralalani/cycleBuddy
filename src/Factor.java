/**
 * <b>Factor</b>
 * <p>
 * The Factor class creates objects of the physical factors that 
 * contribute to the length of a menstrual cycle. These factors include alcohol 
 * consumption, the hours of physical activity in a week, and age. A numeric
 * value of each factor is created in order to determine when the next period
 * will arrive.
 * </p>
 *
 * @authors Inara Lalani, Akansha Malik, Amanda Nixon
 * @version 1.0, 12-Dec-21
 **/

public class Factor {
    private final String name;
    private final String generalInfo;
    private final double effectOnCycle;

    
    /**
     * <p>
     * A constructor for the class that sets the factor name, value,
     * and its general information.
     * </p>
     * 
     * @param nameToSet			a string of the factor name
     * @param generalInfoToSet	a string of information on the effects of each factor
     * @param effectToSet		a double of the Factor effect
     */
    public Factor(String nameToSet, String generalInfoToSet, double effectToSet) {
        name = new String(nameToSet);
        generalInfo = new String(generalInfoToSet);
        effectOnCycle = effectToSet;
    }
    
    
    /**
     * <p>
     * A copy constructor.
     * </p>
     * 
     * @param toCopy
     */
    public Factor(Factor toCopy) {
        name = new String(toCopy.getName());
        generalInfo = new String(toCopy.getGeneralInfo());
        effectOnCycle = toCopy.getEffectOnCycle();
    }

    /**
     * <p>
     * Get the alcohol consumption Factor. This is a static method 
     * that creates a new Factor object with the required parameters.
     * </p>
     * 
     * @return alcohol consumption Factor object
     */
    public static Factor alcoholConsumption() {
        String alcConsumptionInfo = new String("\nAlcohol consumption may decrease \ncycle length. When taking a weighted average from \nLiu et al., individuals who consumed \nalcohol had cycles that were 1.04 days shorter \nthan the cycles of those who did not.");
        return new Factor("alcohol consumption", alcConsumptionInfo, -1.04);
    }
    
    /**
     * <p>
     * Get the age Factor. This is a static method 
     * that creates a new Factor object with the required parameters.
     * </p>
     *
     * @return alcohol consumption Factor object
     */
    public static Factor ageStub() {
    	String ageInfo = new String("\nCycle length varies by age. In Bull et al., \nage and cycle length had an inverse linear relationship \nfrom ages 20-45. the formula derived from Bull et al. \nto calculate cycle length given age is: \n(-0.176 * (age) + 34.743).");
    	return new Factor("age", ageInfo, 0);
    }

    /**
     * <p>
     * Get the physical activity Factor object based
     * on the hours of activity. This is a static method 
     * that creates a new Factor object with the required parameters.
     * </p>
     *
     * @param hoursOfActivity integer hours of activity
     * @return physical activity Factor object
     */
    public static Factor physicalActivity(int hoursOfActivity) {
        // if hoursOfActivity not within constraints, have no effect
        double effect = 0;

        // hoursOfActivity on interval [1, 3]
        if (hoursOfActivity >= 1 && hoursOfActivity <= 3)
            effect = 0.55;

        // hoursOfActivity on interval [4, inf]
        if (hoursOfActivity >= 4)
            effect = 1.03;

        String activityInfo = new String("\nPhysical activity may increase cycle length. \nWhen taking a weighted average from \nLiu et al., more than 4 hours of physical activity \nincreased cycle length by 1.03 days on average, \nand 1-4 hours increased cycle length by 0.55 days \non average.");
        return new Factor("physical activity", activityInfo, effect);
    }

    /**
     * @return string name of Factor
     */
    public String getName() {
        return new String(name);
    }

    /**
     * @return string general info of Factor
     */
    public String getGeneralInfo() {
        return new String(generalInfo);
    }

    /**
     * @return the numeric value of the effect of the Factors
     */
    public double getEffectOnCycle() {
        return effectOnCycle;
    }
}
