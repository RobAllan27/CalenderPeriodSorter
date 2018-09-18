package calenderPeriodSorter;

/**
 * The basic runner class provides a mechanism to run the project and create data.
 *
 * @author Rob Allan
 */

public class BasicRunner {

	/** The main method here simply creates calendar storage entity.
	 * It then calls that entity with a variety of dates.
	 * @param args - no arguments
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalendarStorage cs = new CalendarStorage();
		cs.datetoFindPeriodFor("01-JAN-1993");
		cs.datetoFindPeriodFor("02-JAN-1993");
		cs.datetoFindPeriodFor("09-JAN-1993");
		cs.datetoFindPeriodFor("10-JAN-1995");
		cs.datetoFindPeriodFor("16-JAN-1993");
		cs.datetoFindPeriodFor("22-JAN-1993");
		cs.datetoFindPeriodFor("18-DEC-1995");
		cs.datetoFindPeriodFor("19-DEC-1993");
		cs.datetoFindPeriodFor("20-DEC-1993");
		cs.datetoFindPeriodFor("21-DEC-1993");
		cs.datetoFindPeriodFor("22-DEC-1993");
		cs.datetoFindPeriodFor("23-DEC-1993");
	}

}
