import java.util.ArrayList;

public class Person {

	private static final int MAX_NUMBER_OF_FACTORS = 2;
	private final ArrayList<Factor> factors = new ArrayList<>();
	private final double cycleLength;
	private final int age;
	private final int daysSinceLastPeriod;

	/**
	 * @param age      int age of Person
	 * @param alcohol  boolean Person consumes more than one drink per week
	 * @param activity int hours of physical activity per week
	 */
	public Person(int age, boolean alcohol, int activity, int daysSinceLastPeriod) {
		this.age = age;
		this.daysSinceLastPeriod = daysSinceLastPeriod;

		// Add alcohol factor if alcohol is true
		if (alcohol)
			addFactor(Factor.AlcoholConsumption());
		// It should not be necessary to create a copy of the returned Factor
		// since the static method returns a new object for us anyways. Nothing
		// else owns this object, so privacy leaks should not be an issue
		// (this is the same with the PhysicalActivity factor)

		// Add physical activity factor regardless since if hours
		// are not in the interval [1, inf) the effect will be zero
		addFactor(Factor.PhysicalActivity(activity));

		// After age and factors are added, we can calculate
		// the persons augmented cycle length
		cycleLength = calculateCycleLength();
	}

	/**
	 * Add a factor to the factors list up to a maximum of MAX_NUMBER_OF_FACTORS
	 * factors.
	 *
	 * @param factor Factor to add to factors
	 */
	private void addFactor(Factor factor) {
		if (factors.size() < MAX_NUMBER_OF_FACTORS)
			// While factors do not need to be copied since no other
			// object owns the new Factor created by the static methods,
			// we can copy them anyways and the garbage collector will
			// pick up the extraneous Factor object.
			factors.add(new Factor(factor));
	}

	public ArrayList<Factor> getFactors() {
		return new ArrayList<>(factors);
	}

	/**
	 * The baselineCycleLength method calculates what the mean cycle length is given
	 * the age of the individual. The formula for this (derived from Bull et al.) is
	 * (-0.176 * ((double) age) + 34.743). There is a standard deviation, but we
	 * will just store the mean here.
	 *
	 * @return baseline cycle length double
	 */
	private double baselineCycleLength() {
		return (-0.176 * ((double) age) + 34.743);
	}

	/**
	 * @return augmented cycle length double
	 */
	private double calculateCycleLength() {
		// Start augmented at baseline (do this here instead of in constructor
		double augmentedCycleLength = baselineCycleLength();

		// Add effect on cycle from each Factor to the augmented cycle
		for (Factor factor : factors) {
			augmentedCycleLength += factor.getEffectOnCycle();
		}

		return augmentedCycleLength;
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
		if (daysUntilNextPeriod() < 0)
			return String.format("your next period should have already started %.2f days ago!",
					(daysUntilNextPeriod() * -1));
		else
			return String.format("Your next period will arrive in %.2f days!", daysUntilNextPeriod());
	}
}
