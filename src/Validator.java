import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validator {
	
	/**
	 * Get any valid integer.
	 */
	public static int getInt(Scanner scnr, String prompt) {
		// This approach uses exception handling.
		System.out.print(prompt);
		try {
			int num = scnr.nextInt();
			scnr.nextLine();
			return num;
		} catch (InputMismatchException e) {
			System.out.println("Enter a whole number, using digits.");
			scnr.nextLine();
			return getInt(scnr, prompt);
		}
	}

	/**
	 * Get any valid double.
	 */
	public static double getDouble(Scanner scnr, String prompt) {
		// This approach use "hasNext" look ahead.
		boolean isValid = false;
		do {
			System.out.print(prompt);
			isValid = scnr.hasNextDouble();
			if (!isValid) {
				scnr.nextLine();
				System.out.println("Enter a number, in digits.");
			}
		} while (!isValid);
		
		double number = scnr.nextDouble();
		scnr.nextLine();
		return number;
	}
	
	/**
	 * Get any string.
	 */
	public static String getString(Scanner scnr, String prompt) {
		// This approach uses exception handling.
		System.out.print(prompt);
		return scnr.nextLine();
	}
	
	/**
	 * Get any valid integer between min and max.
	 */
	public static int getInt(Scanner scnr, String prompt, int min, int max) {
		boolean isValid = false;
		int number;
		do {
			number = getInt(scnr, prompt);
			
			if (number < min) {
				isValid = false;
				System.out.println("The number must be at least " + min);
			} else if (number > max) {
				isValid = false;
				System.out.println("The number must not be larger than " + max);
			} else {
				isValid = true;
			}
			
		} while (!isValid);
		return number;
	}
	
	/**
	 * Get any valid double between min and max.
	 */
	public static double getDouble(Scanner scnr, String prompt, double min, double max) {
		boolean isValid = false;
		double number;
		do {
			number = getDouble(scnr, prompt);
			
			if (number < min) {
				isValid = false;
				System.out.println("The number must be at least " + min);
			} else if (number > max) {
				isValid = false;
				System.out.println("The number must not be larger than " + max);
			} else {
				isValid = true;
			}
			
		} while (!isValid);
		return number;
	}
	
	/**
	 * Get a string of input from the user that must match the given regex.
	 */
	public static String getStringMatchingRegex(Scanner scnr, String prompt, String regex) {
		boolean isValid = false;
		String input;
		do {
			input = getString(scnr, prompt);
			
			if (input.matches(regex)) {
				isValid = true;
			} else {
				System.out.println("Input must match the appropriate format.");
				isValid = false;
			}
			
		} while (!isValid);
		return input;
	}

	/**
	 * Get a date from user input in the format mm/dd/yyyy
	 */
	public static Date getDate(Scanner scnr, String prompt) {
		SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
		format.setLenient(false);
		boolean isValid = false;
		Date date = null;
		String input;
		do {
			// Step 1: get the raw string
			input = getString(scnr, prompt);
			// Step 2: convert it to a date
			try {
				// format.parse throws a ParseException, which is a checked exception and MUST be caught.
				date = format.parse(input);
				// If exception doesn't occur, it's valid.
				isValid = true;
			} catch (ParseException ex) {
				// If exception occurs, it's invalid.
				isValid = false;
				System.out.println("Enter a valid date in format mm/dd/yyyy.");
			}
			
		} while (!isValid);
		return date;
	}
	
	
	public static boolean isValidExpiration(String s) {

	    // use SimpleDateFormat to parse values like '0609' as date 'June 2009'
	    java.text.DateFormat sdf = new java.text.SimpleDateFormat("MMyy");
	    
	    // establish current date as last day of previous month at 23:59:59
	    java.util.Calendar now = java.util.Calendar.getInstance(); 
	    now.set(now.get(java.util.Calendar.YEAR), now.get(java.util.Calendar.MONTH), 0, 23, 59, 59);

	    try {
	        // actual parsing of the date, wrapped in try/catch 
	        // parses as 1st day of month at midnight since only providing month and year
	        // e.g., '0609' becomes 'June 01, 2009 00:00:00'
	        java.util.Date exp = sdf.parse(s);

	        // if parsed date is before current month, then return invalid!
	        if (exp.before(now.getTime())) return false;
	    } catch (java.text.ParseException e) {
	        return false;  // if not MMYY then reject
	    }

	    // valid if made it this far, can do in reverse by using !exp.before and return true above.
	    return true; 
	}

}
