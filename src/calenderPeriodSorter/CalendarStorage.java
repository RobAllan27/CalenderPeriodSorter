package calenderPeriodSorter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * The calendar storage application is the core of the application. 
 * 
 * It gets an incoming date and works out the set of billing periods, using Java Calendar classes.
 * 
 * It then checks which period the data falls in, and prints out a result (formatting the result using Calendar objects) 
 *
 * We will use a tree set collection, as sets do not allow duplicates 
 * This will fish out the extra Saturdays is also a 1st of the month
 * The dayOfYearInQuestion is a numeric value to say the nth day of the year - 9th of Feb is 40th day
 * We need to store the year in question
 * 
 * @author Rob Allan
 */

public class CalendarStorage {

	/** A sorted set to hold all the periods
	 * 
	 */
	private TreeSet<Integer> sortedSet;
	
	
	/**
	 * The year in question
	 */
	private int yearinQuestion = 0;
	
	/** a numeric value to represent the number of the day of the year in question.
	*/
	private int dayOfYearInQuestion = 0;

	
	/**
	 * This works out the year in question (and the day of year)
	 * We then form a set of periods for that year.
	 * Finally we call a method to test which period we are in (and this will print out the result.) 
	 * 
	 * @param DateIncoming - this is date that we want to find the period for. As it could be for any year
	 * 
	 */
	public void datetoFindPeriodFor(String DateIncoming) {
		dayOfYearInQuestion = this.getdayofYearandYearForDate(DateIncoming);
		formSetOfPeriods();
		testandCallPrintOutResult();
	}
		

		
		/** This method checks our day of the year is smaller that than a range start point - if so we stop and we are ready to output the results 
		*/
		private void testandCallPrintOutResult(){
		
			int periodfound = 0;
			int startoffoundperiod = 1;
			int endoffoundperiod = 0;	
			
		for(Integer integerInSortedSet: sortedSet){
			
			if (dayOfYearInQuestion < integerInSortedSet.intValue()){
				endoffoundperiod = integerInSortedSet.intValue() - 1;
				break;
				
			}else{
				startoffoundperiod = integerInSortedSet.intValue();
				periodfound++;
			}
		}
		
		printoutresult(periodfound, startoffoundperiod, endoffoundperiod);
		
		}
		
		/** A method to render the results. Internally it uses a calendar object, sets the year and t day of the year. 
		 * It is able to format the result for the start and end of period dates. 
		 * 
		 * @param inperiodfound -  the period found
		 * @param instartoffoundperiod - the start of the found period, as integers for the day of year
		 * @param inendoffoundperiod -  the end of the found period, as integers for the day of year
		 */
		
		private void printoutresult(int inperiodfound, int instartoffoundperiod, int inendoffoundperiod){
		
		// Now we will build the output string using some calendar objects
		Calendar calendar = Calendar.getInstance();
		
		SimpleDateFormat format1 = new SimpleDateFormat("MMM dd");
		Date date;
		
		calendar.set(Calendar.DAY_OF_YEAR, instartoffoundperiod);
        calendar.set(Calendar.YEAR, yearinQuestion);
        date  = calendar.getTime();             
		String startperioddate = format1.format(date); 
		
		calendar.set(Calendar.DAY_OF_YEAR, inendoffoundperiod);
		date  = calendar.getTime();             
		String endperioddate = format1.format(date);
		
		System.out.println("\n Billing Period = " + yearinQuestion + "-" + inperiodfound + ":" + startperioddate + " - " + endperioddate);
	}
	
		/** This is a method to build a set of periods - all the Saturdays and 1st of the months, as list of daycounts
		 * It holds all the periods for that year.
		 * It needs to work out the 1st Saturday as well, and then can add all the Saturdays as days of the year.
		 * It also works out the first of each month as well.
		 * 
		 * using a Treeset allows gives us unique sorted set.
		 */
	private void formSetOfPeriods(){
		//build an array of dates for very saturday and every 1st of month
		Set<Integer> Integers  = new HashSet<Integer>();
		
		// let's get 1st saturday

		Calendar calendarSetable = Calendar.getInstance();
		calendarSetable.set(yearinQuestion, 0, 1);
		int dayofweek1stofyear = calendarSetable.get(Calendar.DAY_OF_WEEK);
		int dateof1ststaurday = 8 - dayofweek1stofyear; //(e.g. if first day or yera is a friday (which is a 6th of week) so Staursdya is 2nd
		Integer inttobeadded = new Integer(dateof1ststaurday);
		Integers.add(inttobeadded);
		
		//let's get the all the remaining saturdays of the year - as a day of year.
		
		for (int i=1; i< 52;i++) {
			Integer intfollowingSats = new Integer(i*7 + dateof1ststaurday);
			Integers.add(intfollowingSats);
		}
		// now we will add the 1st of the months
		// first get the 1st day of each month
		// then we will add x days to it depending on the day of the first day.
		
		for (int i=0; i< 12;i++) {			
			
			//Calendar calendarSetable = Calendar.getInstance();
			calendarSetable.set(this.yearinQuestion, i, 1);
			int daycount1stofmonth = calendarSetable.get(Calendar.DAY_OF_YEAR);
			Integers.add(daycount1stofmonth);
		}
		
		// now we will put this into a sorted set - this will remove duplicates
		sortedSet = new TreeSet<Integer>(Integers);
	}
	
	/** This method allow us to parse the date and get its year and the day of that year.
	 *
	 * @param dateInQuestion - pass in a date string
	 * @return integer for the date in question as day or year.
	 */
	private int getdayofYearandYearForDate(String dateInQuestion) {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	    //String dateString = "10-Jan-1993";
	    int incomingDayofYear = 0;
	    Date date;
	    try{
	    date = sdf.parse(dateInQuestion);
	    //System.out.println("Date: " + date);
	    Calendar calendar = Calendar.getInstance();
	    // Set calendar to the same time as date
	    calendar.setTime(date);
		incomingDayofYear = calendar.get(Calendar.DAY_OF_YEAR);
		yearinQuestion = calendar.get(Calendar.YEAR);		
	    }
	    catch (Exception e){
	    	System.out.println("Please enter valid Date");
	    	System.exit(0);
	    }
	    return incomingDayofYear;
	}
}