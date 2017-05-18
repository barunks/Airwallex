package RPNCalculatorPackage;

import java.util.Scanner;

public class RPNCalculatorMain {
	
	private static void usage() {
		System.out.println("Welcome to RPN Calculator.");
		System.out.println("Please Input Your Operation for '+', '-', '*', '/'. 'sqrt'.  Type 'exit' if you want to exit. ");
		System.out.println("==============================================================================================");
	}
	
	/**
	 *  /**
	  * Main function to take input from users and process 
	 * @param args
	 */
	public static void main(String [] args)
	{
		RPNCalculator rpnCalculator = new RPNCalculator();
		Scanner inScan = new Scanner(System.in);
		boolean isExiting = false;
		final String EXIT = "exit";
		usage();
		try {
			while (!isExiting) {
				String input = inScan.nextLine();
				if ( input.isEmpty())
				{
					rpnCalculator.printRPNStack();
	            	continue;
				}
				if (EXIT.equals(input)) {
					isExiting = true;
					continue;
				}
				try {
					// Process Input for RPN Calculator operation
					rpnCalculator.processRPNStackOperation(input);
				} catch (IllegalArgumentException iArgsEx) {
					System.out.println(iArgsEx.getMessage());
				} catch (IllegalStateException iStateEx) {
					System.out.println(iStateEx.getMessage());
				} catch (Exception ex) {
					System.out.println("Error, Please retry or contact Support....");
					System.out.println("Error :" + ex.getMessage());
				} 
					
				// Print the result for each input line
				rpnCalculator.printRPNStack();
			}
		 } catch (Exception ex) {
			 System.out.println("Exception received, Exiting for :" + ex.getMessage());
		 }
		// close the scanner
		System.out.println("Closing Scanner...");
		inScan.close();
	}

}
