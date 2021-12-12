public class Factor {
    private final String name;
    private final String generalInfo;
    private final double effectOnCycle;

    public Factor(String nameToSet, String generalInfoToSet, double effectToSet) {
        name = new String(nameToSet);
        generalInfo = new String(generalInfoToSet);
        effectOnCycle = effectToSet;
    }

    public Factor(Factor toCopy) {
        name = new String(toCopy.getName());
        generalInfo = new String(toCopy.getGeneralInfo());
        effectOnCycle = toCopy.getEffectOnCycle();
    }

    /**
     * <p>
     * Get the alcohol consumption Factor
     * </p>
     *
     * <em><b>
     * This is a static method not any kind of setter.
     * It is not meant to set anything, it is only meant
     * to create a new Factor object with the required
     * parameters.
     * </em></b>
     *
     * @return alcohol consumption Factor object
     */
    public static Factor AlcoholConsumption() {
        String alcConsumptionInfo = new String("\nAlcohol consumption may decrease \ncycle length. When taking a weighted average from \nLu et al., individuals who consumed \nalcohol had cycles that were 1.04 days shorter \nthan the cycles of those who did not.");
        return new Factor("alcohol consumption", alcConsumptionInfo, -1.04);
    }
    
    public static Factor AgeStub() {
    	String ageInfo = new String("\nCycle length varies by age. In Bull et al., \nage and cycle length had an inverse linear relationship \nfrom ages 20-45. the formula derived from Bull et al. \nto calculate cycle length given age is: \n(-0.176 * (age) + 34.743).");
    	return new Factor("age", ageInfo, 0);
    }

    /**
     * <p>
     * Get the physical activity Factor object based
     * on the hours of activity.
     * </p>
     *
     * <em><b>
     * This is a static method not any kind of setter.
     * It is not meant to set anything, it is only meant
     * to create a new Factor object with the required
     * parameters.
     * </em></b>
     *
     * @param hoursOfActivity integer hours of activity
     * @return physical activity Factor object
     */
    public static Factor PhysicalActivity(int hoursOfActivity) {
        // if hoursOfActivity not within constraints, have no effect
        double effect = 0;

        // hoursOfActivity on interval [1, 3]
        if (hoursOfActivity >= 1 && hoursOfActivity <= 3)
            effect = 0.55;

        // hoursOfActivity on interval [4, inf)
        if (hoursOfActivity >= 4)
            effect = 1.03;

        String activityInfo = new String("\nPhysical activity may increase cycle length. \nWhen taking a weighted average from \nLu et al., more than 4 hours of physical activity \nincreased cycle length by 1.03 days on average, \nand 1-4 hours increased cycle length by 0.55 days \non average.");
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
     * @return numeric effect on cycle of Factor
     */
    public double getEffectOnCycle() {
        return effectOnCycle;
    }
}
