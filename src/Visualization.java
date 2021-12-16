import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * <b>Visualization</b>
 * <p>
 * The Visualization class prints the calendar visualization
 * based on the double returned from the daysUntilNextPeriod()
 * method in the Person class.
 * The code that implements the calendar is adapted from StackOverFlow
 * https://stackoverflow.com/questions/26962388/printing-a-calendar
 * </p>
 *
 * @authors Inara Lalani, Akansha Malik, Amanda Nixon
 * @version 1.0, 12-Dec-21
 **/
public class Visualization {
	public static Calendar ourCalendar = GregorianCalendar.getInstance();
	private static final int day = ourCalendar.get(Calendar.DAY_OF_MONTH);
	public static ArrayList<Integer> nextMonthPeriodDays = new ArrayList<>();
	public static ArrayList<Integer> periodDays = new ArrayList<>();

	
	/**
	 * <p>
	 * Initializes the periodDays arrayList and if necessary,
	 * nextMonthPeriodDays arrayList as well.
	 * </p>
	 * 
	 * @param aPerson		a Person object
	 * @param ourCalendar	a Calendar object
	 */
	public static void periodDayArray(Person aPerson, Calendar ourCalendar) {
		
		int firstDayOfPeriod = day + (int) aPerson.daysUntilNextPeriod();
		for (int i = firstDayOfPeriod; i < firstDayOfPeriod + 5; i++) { //An average period length of 5 days is added to the first day of the period
			int maxForThisMonth = ourCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			if (i > maxForThisMonth) {
				int in = i - maxForThisMonth;
				nextMonthPeriodDays.add(in);
			} else
				periodDays.add(i);
		}
	}

	/**
	 * <p>
	 * Formats and prints the calendar, as well as
	 * colourizes the days of the period.
	 * The code to implement the colour plugin is adapted from mihai-nita.net
	 * http://mihai-nita.net/2013/06/03/eclipse-plugin-ansi-in-console/
	 * </p>
	 * 
	 * @param calendarToPrint		a Calendar object
	 * @param periodDayArrayToPrint	an arrayList
	 */
	public static void printCalendar(Calendar calendarToPrint, ArrayList<Integer> periodDayArrayToPrint) {

		int daysInMonth = calendarToPrint.getActualMaximum(Calendar.DAY_OF_MONTH);

		System.out.println(new SimpleDateFormat("MMMM yyyy").format(calendarToPrint.getTime()));
		System.out.println("\n S  M  T  W  T  F  S \n");
		String initialSpace = "";

		for (int i = 0; i < getDayOfFirstOfMonth(calendarToPrint) - 1; i++) {
			initialSpace += " . ";
		}

		System.out.print(initialSpace);

		for (int i = 0, dayOfMonth = 1; dayOfMonth <= daysInMonth; i++) {

			// Initial condition for loop
			int j;
			if (i == 0)
				j = getDayOfFirstOfMonth(calendarToPrint) - 1;
			else
				j = 0;

			for (; j < 7 && (dayOfMonth <= daysInMonth); j++) {
				if (periodDayArrayToPrint.contains(dayOfMonth)) {
					System.out.printf("\u001b[1;31m%2d\u001b[0m ", dayOfMonth);
				} else
					System.out.printf("%2d ", dayOfMonth);
				dayOfMonth++;
			}
			System.out.println();
		}
	}

	/**
	 * <p>
	 * Adds a calendar object of the following month
	 * </p>
	 * 
	 * @param calendarToIncrement	a Calendar object
	 * @return calendarToIncrement
	 */
	public static Calendar getNextMonthCalendar(Calendar calendarToIncrement) {
		calendarToIncrement.add(Calendar.MONTH, 1); 
		return calendarToIncrement;
	}
	
	/**
	 * <p>
	 * Removes the elements from the periodDays 
	 * and nextMonthPeriodDays arrayLists. 
	 * </p>
	 */
	public static void resetArrays() {
		periodDays.clear();
		nextMonthPeriodDays.clear();
	}

	/**
	 * <p>
	 * Sets the first day of the month to the correct weekday.
	 * </p>
	 * 
	 * @param c		a Calendar object
	 * @return cal	a GeorgianCalendar object 
	 */
	private static int getDayOfFirstOfMonth(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		GregorianCalendar cal = new GregorianCalendar(year + 1900, month, 0);
		return cal.get(GregorianCalendar.DAY_OF_WEEK);
	}
}
