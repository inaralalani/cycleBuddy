import java.util.Calendar;
import java.util.GregorianCalendar;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Visualization {
	public static Calendar ourCalendar = GregorianCalendar.getInstance();
	private static int day = ourCalendar.get(Calendar.DAY_OF_MONTH);
	static ArrayList<Integer> nextMonthPeriodDays = new ArrayList<>();
	static ArrayList<Integer> periodDays = new ArrayList<>();
	public static void periodDayArray(Person aPerson, Calendar ourCalendar) {
	
		int firstDayOfPeriod = day + (int) aPerson.DaysUntilNextPeriod();
		int in = 1;
		for (int i = firstDayOfPeriod; i < firstDayOfPeriod + 5; i++) {
			int maxForThisMonth = ourCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			if (i > maxForThisMonth) {
				nextMonthPeriodDays.add(in);
				in++;
			}
			else periodDays.add(i);
		}
	
	}

	public static void printCalendar(Person aPerson, Calendar calendarToPrint, ArrayList<Integer> periodDayArrayToPrint) {

		int daysInMonth = calendarToPrint.getActualMaximum(Calendar.DAY_OF_MONTH);

		System.out.println(new SimpleDateFormat("MMMM YYYY").format(calendarToPrint.getTime()));
		System.out.println("\n" + " S  M  T  W  T  F  S \n");
		String initialSpace = "";
		
		for (int i = 0; i < getDayOfFirstOfMonth(calendarToPrint) - 1; i++){
			initialSpace += "   ";
		}
		
		System.out.print(initialSpace);

		for (int i = 0, dayOfMonth = 1; dayOfMonth <= daysInMonth; i++) {
			for (int j = ((i == 0) ? getDayOfFirstOfMonth(calendarToPrint) - 1 : 0); j < 7 && (dayOfMonth <= daysInMonth); j++) {
				if (periodDayArrayToPrint.contains(dayOfMonth) == true) {
					System.out.printf("\u001b[1;31m%2d\u001b[0m ", dayOfMonth);
				} else
					System.out.printf("%2d ", dayOfMonth);
				// set color back to white
				dayOfMonth++;
			}
			System.out.println();
		}
		;
	}
	
	@SuppressWarnings("deprecation")
	private static int getDayOfFirstOfMonth(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = 1;
		Date firstDate = new Date(year, month, day);
		int dayOfFirstDate = firstDate.getDay();
		return dayOfFirstDate;
		
	}

	public static Calendar getNextMonthCalendar(Calendar c) {
		c.add(Calendar.MONTH, 1);
		return c;
		
		
	}
	public static void resetArrays() {
		periodDays.clear();
		nextMonthPeriodDays.clear();
	}

}

// if period days go into next month, initialize an array for the next month, a calendar object for the next month (using currentmonth.add,
// and print the calendar for the next month the same way as the one for the current month.
