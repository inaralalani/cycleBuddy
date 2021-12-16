import java.util.ArrayList;

/**
 * <b>Person</b>
 * <p>
 * The Person class calculates the numerical value the effect of age 
 * has on the length of time between periods. It also takes the double of the 
 * alcohol consumption and physical activity factor from the Factor class.
 * The double of the three factors are used here to calculate when the
 * next period will be.
 * </p>
 *
 * @authors Inara Lalani, Akansha Malik, Amanda Nixon
 * @version 1.0, 12-Dec-21
 **/

public class Person {

	private static final int MAX_NUMBER_OF_FACTORS = 2;
	private final ArrayList<Factor> factors = new ArrayList<>();
	private final double cycleLength;
	private final int age;
	private final int daysSinceLastPeriod;

	/**
	 * <p>
	 * Sets the age and daysSincePeriod factors, and adds the 
	 * alcohol factor if it is true as well as the physical 
	 * activity factor.
	 * </p>
	 * 
	 * @param age      				int age of Person
	 * @param alcohol 				boolean Person consumes more than one drink per week
	 * @param activity 				int hours of physical activity per week
	 * @param daysSinceLastPeriod	int days since last period
	 */
	public Person(int age, boolean alcohol, int activity, int daysSinceLastPeriod) {
		this.age = age;
		this.daysSinceLastPeriod = daysSinceLastPeriod;

		if (alcohol)
			addFactor(Factor.alcoholConsumption());
			addFactor(Factor.physicalActivity(activity));
			cycleLength = calculateCycleLength();
	}

	/**
	 * @return an arrayList of the factors
	 */
	public ArrayList<Factor> getFactors() {
		return new ArrayList<>(factors);
	}

	/**
	 * @return days until next period
	 */
	public double daysUntilNextPeriod() {
		return cycleLength - daysSinceLastPeriod;
	}

	/**
	 * @return string representation of Person object
	 */
	public String toString() {
		String personString = String.format("Your cycle is %.2f days long. ", calculateCycleLength());
		if (daysUntilNextPeriod() < 0)
			personString += String.format("Your next period should have already started %.2f days ago!",
					(daysUntilNextPeriod() * -1));
		else
			personString += String.format("Your next period will arrive in %.2f days!", daysUntilNextPeriod());
		return personString;
	}

	/**
	 * <p>
	 * Add a factor to the factors list up to a maximum of MAX_NUMBER_OF_FACTORS
	 * factors.
	 * </p>
	 *
	 * @param factor Factor to add to factors
	 */
	private void addFactor(Factor factorToAdd) {
		if (factors.size() < MAX_NUMBER_OF_FACTORS)
			factors.add(new Factor(factorToAdd));
	}

	/**
	 * @return augmented cycle length double
	 */
	private double calculateCycleLength() {
		double augmentedCycleLength = baselineCycleLength();

		// Add effect on cycle from each Factor to the augmented cycle
		for (Factor factor : factors) {
			augmentedCycleLength += factor.getEffectOnCycle();
		}

		return augmentedCycleLength;
	}

	/**
	 * <p>
	 * The baselineCycleLength method calculates what the mean cycle length is given
	 * the age of the individual. The formula for this (derived from Bull et al.) is
	 * (-0.176 * ((double) age) + 34.743). There is a standard deviation, but we
	 * will just store the mean here.
	 * </p>
	 *
	 * @return baseline cycle length double
	 */
	private double baselineCycleLength() {
		return (-0.176 * ((double) age) + 34.743);
	}
}
