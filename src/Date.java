/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author soumyava
 */
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date implements Proj1Constants {

	private static final int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30,
			31, 30, 31 };
	private static final int LEAP_YEAR = 366;
	private static final int NON_LEAP_YEAR = 365;
    private static final int LAST_MONTH =12;

	private final int month; // month (between 1 and 12)
	private final int day; // day (between 1 and DAYS[month])
	private final int year; // year

	/**
	 * Constructor: default; returns today's date
	 */
	public Date() {
		GregorianCalendar c = new GregorianCalendar();
		month = c.get(Calendar.MONTH) + 1; // our month starts from 1
		day = c.get(Calendar.DAY_OF_MONTH);
		year = c.get(Calendar.YEAR);
	}

	/**
	 * Constructor: Does bounds-checking to ensure object represents a valid
	 * date
	 * 
	 * @param m
	 *            represents the month between 1 and 12
	 * @param d
	 *            represents the date between 1 and the corresponding number
	 *            from array DAYS
	 * @param y
	 *            represents the year
	 * @exception RuntimeException
	 *                if the date is invalid
	 */
	public Date(int m, int d, int y) {
		if (!isValid(m, d, y))
			throw new RuntimeException("Invalid date");
		month = m;
		day = d;
		year = y;
	}

	/**
	 * Constructor: Does bounds-checking to ensure string represents a valid
	 * date
	 * 
	 * @param sDate
	 *            represents a date given in format mm-dd-yyyy
	 * @exception RuntimeException
	 *                if the date is invalid
	 */
	public Date(String sDate) {
		int m, d, y;
		String[] chopDate = sDate.split("-");
		m = Integer.parseInt(chopDate[ZEROI]);
		d = Integer.parseInt(chopDate[ONEI]);
		y = Integer.parseInt(chopDate[TWOI]);
		if (!isValid(m, d, y))
			throw new RuntimeException("Invalid date");
		month = m;
		day = d;
		year = y;
	}

	/**
	 * constructor: creates a date with a given year; fills in valid month and
	 * day;
	 * 
	 * @param y
	 *            represents a date given in year as integer
	 */
	public Date(int y) {

		month = LAST_MONTH;
		day = DAYS[LAST_MONTH];
		year = y;
	}

	/**
	 * create a date with a given month and year; fills in valid day;
	 * 
	 * @param m
	 *            represents the month between 1 and 12
	 * @param y
	 *            represents the year
	 * @exception RuntimeException
	 *                if the date is invalid
	 */
	public Date(int m, int y) {

		if (!isValid(m, DAYS[m], y))
			throw new RuntimeException("Invalid date; correct input");
		month = m;
		day = DAYS[m];
		year = y;
	}

	/**
	 * Is the given date valid?
	 * 
	 * @param month
	 *            , day, and year
	 * @return false if month exceeds 12 or is less than 1
	 * @return false if day exceeds the corresponding days for a month from
	 *         array DAYS
	 * @return false if the year is not a leap year and has 29 days
	 */
	private static boolean isValid(int m, int d, int y) {
		if (m < 1 || m > 12)
			return false;
		if (d < 1 || d > DAYS[m])
			return false;
		if (m == 2 && d == 29 && !isLeapYear(y))
			return false;
		return true;
	}

	/**
	 * is y a leap year?
	 * 
	 * @param y
	 *            represents the year specified
	 * @return true if year divisible by 400
	 * @return false if year divisible by 100 and not by 400
	 */
	private static boolean isLeapYear(int y) {
		if (y % 400 == 0)
			return true;
		if (y % 100 == 0)
			return false;
		return (y % 4 == 0);
	}

	/**
	 * is (m, y) a leap month?
	 * 
	 * @param m
	 *            represents the month specified
	 * @param y
	 *            represents the year specified
	 * @return true if it is a leap month
	 * @return false otherwise
	 */
	private static boolean isLeapMonth(int m, int y) {
		if (isLeapYear(y))
			return ((m == 2) ? true : false);
		return false;
	}

	// return the next Date
	/**
	 * adds a day and returns a new Date object
	 * 
	 * @return returns a new Date object adding a day
	 */
	public Date next() {
		if (isValid(month, day + 1, year))
			return new Date(month, day + 1, year);
		else if (isValid(month + 1, 1, year))
			return new Date(month + 1, 1, year);
		else
			return new Date(1, 1, year + 1);
	}

	// is this Date after b?
	/**
	 * compares two Date objects
	 * 
	 * @param b
	 *            Date object
	 * @return true if this Date is after Date b
	 */
	public boolean isAfter(Date b) {
		return compareTo(b) > 0;
	}

	// is this Date a before b?
	/**
	 * compares two date objects
	 * 
	 * @param b
	 *            Date object
	 * @return true if this Date is before Date b
	 */
	public boolean isBefore(Date b) {
		return compareTo(b) < 0;
	}

	// is this partial Date (mm/yyyy) after b?
	/**
	 * compares two partial Date objects
	 * 
	 * @param b
	 *            Date object
	 * @return true if this Date is after Date B
	 */
	public boolean isPartialAfter(Date b) {
		return comparePartialTo(b) > 0;
	}

	// is this partial Date (mm/yyyy) before b?
	/**
	 * compares two partial Date objects
	 * 
	 * @param b
	 *            Date object
	 * @return true if this Date is before Date B
	 */
	public boolean isPartialBefore(Date b) {
		return comparePartialTo(b) < 0;
	}

	// comparison function between two dates
	/**
	 * compares two Date objects
	 * 
	 * @param b
	 *            Date object
	 * @return 0 if this Date is the same as Date b <br>
	 *         negative integer if this Date is earlier than Date b <br>
	 *         positive integer if this Date is after Date b
	 */
	public int compareTo(Date b) {
		if (year != b.year)
			return year - b.year;
		if (month != b.month)
			return month - b.month;
		return day - b.day;
	}

	// comparison function between two partial dates
	/**
	 * compares two partial Date objects (mm/yyyy)
	 * 
	 * @param b
	 *            Date object
	 * @return 0 if this Date is the same as Date b <br>
	 *         negative integer if this Date is earlier than Date b <br>
	 *         positive integer if this Date is after Date b
	 */
	public int comparePartialTo(Date b) {
		if (year != b.year)
			return year - b.year;
		if (month != b.month)
			return month - b.month;
		return 0;
	}

	// advance date by number of months
	/**
	 * advances the date by m months (fixed a bug on 2/22/2010)
	 * 
	 * @param m
	 *            represents the months to advance
	 * @return new Date object (as the original obj is immutable)
	 */
	// remember to modify this code today
	 public Date addMonths(int m) {
            if (m < 0) {
			throw new RuntimeException(
					"Month should not be the negative value!");
		}


		int newMonth = (month + (m % 12)) > 12 ? ((month + (m % 12)) % 12) : month
				+ (m % 12);
		int newYear = (month + (m % 12)) > 12 ? year + (m / 12) + 1 : year
				+ (m / 12);
                int newDay = 0;
                // IF the new month has less total days in it than the starting month
                // and the date being added to is the last day of the month, adjust
                // and make newDay correct with its corresponding month.
               
                // IF not leap year and not Feb. make newDay correct.
                if (day > DAYS[newMonth] && (newMonth != 2)) {
                    newDay = (DAYS[newMonth]);
                    //System.out.println("1");
                }       
               
                // IF not leap year but not Feb. make newDay 28. (This is usually the case for Feb.)
                else if ((day > DAYS[newMonth]) && (isLeapYear(newYear) == false) && (newMonth == 2)){
                    newDay = (DAYS[newMonth] - 1);
                }
               
                // IF leap year and is Feb. make newDay 29.
                else if ((day > DAYS[newMonth]) && isLeapMonth(newMonth, newYear) == true){
                    newDay = DAYS[newMonth];
                }

                // if day is not gretaer than the last day of the new month, make no change.
                else {
                    newDay = day;
                } 
		return (new Date(newMonth, newDay, newYear));
	}


	// subtracts two dates to return the difference in months
	/**
	 * @param endDate
	 *            represents the second date
	 * @return int months - the difference between the two dates
	 */
	public int monthsBetween(Date endDate) {
		// int monthDiff = 0;
		// if (month <= endDate.month) {
		// if (day <= endDate.day) {
		// monthDiff = (endDate.month - month)
		// + (endDate.year - year) * 12;
		// } else {
		// monthDiff = (endDate.month - month - 1)
		// + (endDate.year - year) * 12;
		// }
		// } else {
		// if (day <= endDate.day) {
		// monthDiff = (12 - month + endDate.month)
		// + (endDate.year - year - 1) * 12;
		// } else {
		// monthDiff = (11 - month + endDate.month)
		// + (endDate.year - year - 1) * 12;
		// }
		// }
		// return monthDiff;

		// Yuanzhe Begin:
		int monthsBetween = 0;

		if (compareTo(endDate) <= 0) {
			int tmpMonths = 0;
			while (true) {
				tmpMonths++;
				Date tmpDate = this.addMonths(tmpMonths);
				if (tmpDate.compareTo(endDate) <= 0) {
					monthsBetween++;
				} else {
					break;
				}
			}

			return monthsBetween;
		} else {
			int tmpMonths = 0;
			while (true) {
				tmpMonths++;
				Date tmpDate = endDate.addMonths(tmpMonths);
				if (tmpDate.compareTo(this) <= 0) {
					monthsBetween++;
				} else {
					break;
				}
			}
			return -monthsBetween;
		}
		// Yuanzhe End:
	}

	// checks if a month is valid between a given pair of dates
	// NOT needed for this project
	/**
	 * 
	 * @return true if the given month falls between two dates false if the
	 *         month is not between the given dates
	 */
	public boolean isValidMonth(int inputMonth, Date endDate) {
		if (inputMonth < 1 || inputMonth > 12)
			return false;
		if (endDate.year < year)
			return false;
		if (endDate.year == year) {
			if ((inputMonth >= month) && (inputMonth <= endDate.month))
				return true;
			else
				return false;
		} else if (endDate.year == year + 1) {
			if ((inputMonth >= month) || (inputMonth <= endDate.month))
				return true;
			else
				return false;
		}
		return true;
	}

	// returns the number of days between two dates
	/**
	 * @ param endDate is the second date @ return the number of days between
	 * two dates - 1 if start date is after the end date +ve #days if the start
	 * date is before end date
	 * 
	 */
	public int daysBetween(Date endDate) {

		if (this.isAfter(endDate))
			return -1;
		int totDays = 0;
		for (int y = this.year; y < endDate.year; y++)
			totDays += (isLeapYear(y) ? LEAP_YEAR : NON_LEAP_YEAR);

		int daysBeforeFirst = 0;
		for (int m = 1; m < this.month; m++)
			daysBeforeFirst += (m == 2 && !isLeapMonth(m, this.year)) ? DAYS[m] - 1
					: DAYS[m];
		daysBeforeFirst += this.day;

		int daysInSecond = 0;
		for (int me = 1; me < endDate.month; me++)
			daysInSecond += (me == 2 && !isLeapMonth(me, endDate.year) ? DAYS[me] - 1
					: DAYS[me]);
		daysInSecond += endDate.day;

		return totDays - daysBeforeFirst + daysInSecond;
	}
        public int getYear(){
            return year;
        }
	// return a string representation of this date
	/**
	 * replaces the default toString of Object class
	 */
	public String toString() {
		return "[" + month + "-" + day + "-" + year + "]";
	}

	// sample client for testing
	/**
	 * Code for testing the Date class
	 * 
	 * @param args
	 *            Array of String arguments
	 */
	public static void main(String[] args) {
		Date today = new Date(2, 26, 2012);
		System.out.println("Input date is " + today);
		System.out.println("Printing the next 10 days after " + today);
		for (int i = 0; i < 10; i++) {
			today = today.next();
			System.out.println(today);
		}
		Date expiry = new Date(2011);
		System.out.println("testing year 2011 as input:" + expiry);

		Date todayDate = new Date();
		System.out.println("todays date: " + todayDate);
		System.out.println("current month:" + todayDate.month);

		// testing isValidMonth function
		Date start = new Date("08-01-2010");
		Date end1 = new Date("09-01-2010");
		boolean param1 = start.isValidMonth(4, end1);
		System.out.println("is April valid between: " + start + " and " + end1
				+ ": " + param1);
		Date end2 = new Date("02-01-2011");
		boolean param2 = start.isValidMonth(2, end2);
		System.out.println("is feb valid between: " + start + " and " + end2
				+ ": " + param2);
		boolean param3 = start.isValidMonth(8, start);
		System.out.println("is aug valid between: " + start + " and " + start
				+ ": " + param3);
		param3 = start.isValidMonth(4, start);
		System.out.println("is april valid between: " + start + " and " + start
				+ ": " + param3);
		Date end3 = new Date("02-01-2010");
		boolean param4 = start.isValidMonth(8, end3);
		System.out.println("is aug valid between: " + start + " and " + end3
				+ ": " + param4);
		 
		Date lease = new Date("1-01-2012");
		Date expiry1 = new Date("12-31-2012");

		// testing daysBetween
		System.out
				.println("\nTESTING daysBetween method\n------------------------------");
		int count = lease.daysBetween(expiry);
		System.out.println("Days between " + lease + " and " + expiry + "is: "
				+ count);
        count = new Date("1-01-2011").daysBetween(new Date("12-31-2011"));
		System.out.println("Days between [1-01-2011]  and [12-31-2011]" + "is: "
				+ count);
		count = lease.daysBetween(expiry1);
		System.out.println("Days between " + lease + " and " + expiry1 + "is: "
				+ count);
		Date testDate = new Date("12-31-2013");
		count = lease.daysBetween(testDate);
		System.out.println("Days between " + lease + " and [12-31-2013] "
				+ "is: " + count);
		count = lease.daysBetween(lease);
		System.out.println("Days between " + lease + " and " + lease + "is: "
				+ count);
        count = lease.daysBetween(new Date("1-10-2012"));
		System.out.println("Days between " + lease + " and [1-10-2012" + "is: "
				+ count);
        
		count = testDate.daysBetween(lease);
		System.out.println("Days between " + testDate + " and " + lease + "is: "
				+ count);

		// testin isBefore
		System.out
				.println("\nTESTING isBefore method\n------------------------------");
		boolean isBefore = today.isBefore(today.next());
		System.out.println(today + "is before " + today.next() + ": "
				+ isBefore);
		isBefore = today.next().isBefore(today);
		System.out.println(today.next() + "is before " + today + ": "
				+ isBefore);
		isBefore = today.isBefore(today);
		System.out.println(today + "is before " + today + ": " + isBefore);
        isBefore = today.isBefore(today.addMonths(12));
		System.out.println(today + "is before " + today.addMonths(12) + ": " + isBefore);

		// testing addMonths
		System.out
				.println("\nTESTING addMonths method\n------------------------------");
		today = new Date("1-31-2011");
		Date newDate = today.addMonths(1);
		System.out
				.println("adding 1 months to " + today + " gives: " + newDate);
		newDate = today.addMonths(13);
		System.out.println("adding 13 months to " + today + " gives: "
				+ newDate);
		today = new Date("3-31-2010");
		newDate = today.addMonths(15);
		System.out.println("adding 15 months to " + today + " gives: "
				+ newDate);
		newDate = today.addMonths(23);
		System.out.println("adding 23 months to " + today + " gives: "
				+ newDate);
		newDate = today.addMonths(49);
		System.out.println("adding 49 months to " + today + " gives: "
				+ newDate);
		newDate = today.addMonths(0);
		System.out
				.println("adding 0 months to " + today + " gives: " + newDate);
		
		// testing monthsBetween
		System.out
				.println("\nTESTING monthsBetween method\n------------------------------");
		int monthDiff = today.monthsBetween(today.addMonths(1));
		System.out.println("months between " + today + " and " + today.addMonths(1)
				+ ": " + monthDiff);
		monthDiff = today.next().monthsBetween(today);
		System.out.println("months between " + today.next() + " and " + today
				+ ": " + monthDiff);
		today = new Date("09-30-2011");
		Date endDate = new Date("2-29-2012");
		monthDiff = today.monthsBetween(endDate);
		System.out.println("months between " + today + " and " + endDate + ": "
				+ monthDiff);
		today = new Date();
		Date endDate1 = new Date("12-04-2011");
		monthDiff = today.monthsBetween(endDate1);
		System.out.println("months between " + today + " and " + endDate1
				+ ": " + monthDiff);
		today = new Date();
		Date endDate2 = new Date("12-22-2010");
		monthDiff = today.monthsBetween(endDate2);
		System.out.println("months between " + today + " and " + endDate2
				+ ": " + monthDiff);
		
		// following should generate exception as date is invalid!
		// today = new Date(13, 13, 2010);

		// expiry = new Date("2-29-2009");
        // newDate = today.addMonths(-11);

	}
}
