import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Visualization {
	public static Calendar ourCalendar = GregorianCalendar.getInstance();
	private static final int day = ourCalendar.get(Calendar.DAY_OF_MONTH);
	public static ArrayList<Integer> nextMonthPeriodDays = new ArrayList<>();
	public static ArrayList<Integer> periodDays = new ArrayList<>();

	public static void periodDayArray(Person aPerson, Calendar ourCalendar) {

		int firstDayOfPeriod = day + (int) aPerson.daysUntilNextPeriod();
		for (int i = firstDayOfPeriod; i < firstDayOfPeriod + 5; i++) {
			int maxForThisMonth = ourCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			if (i > maxForThisMonth) {
				int in = i - maxForThisMonth;
				nextMonthPeriodDays.add(in);
			} else
				periodDays.add(i);
		}
	}

	public static void printCalendar(Calendar calendarToPrint, ArrayList<Integer> periodDayArrayToPrint) {

		int daysInMonth = calendarToPrint.getActualMaximum(Calendar.DAY_OF_MONTH);

		System.out.println(new SimpleDateFormat("MMMM yyyy").format(calendarToPrint.getTime()));
		System.out.println("\n S  M  T  W  T  F  S \n");
		String initialSpace = "";

		for (int i = 0; i < getDayOfFirstOfMonth(calendarToPrint) - 1; i++) {
			initialSpace += " • ";
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
				// set color back to white
				dayOfMonth++;
			}
			System.out.println();
		}
	}

	public static Calendar getNextMonthCalendar(Calendar calendarToIncrement) {
		calendarToIncrement.add(Calendar.MONTH, 1);
		return calendarToIncrement;
	}

	public static void resetArrays() {
		periodDays.clear();
		nextMonthPeriodDays.clear();
	}

	private static int getDayOfFirstOfMonth(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		GregorianCalendar cal = new GregorianCalendar(year + 1900, month, 0);
		return cal.get(GregorianCalendar.DAY_OF_WEEK);
	}
}

// if period days go into next month, initialize an array for the next month, a calendar object for the next month (using currentmonth.add,
// and print the calendar for the next month the same way as the one for the current month.
