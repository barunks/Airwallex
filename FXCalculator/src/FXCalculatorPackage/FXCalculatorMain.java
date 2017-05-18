package FXCalculatorPackage;

import java.util.*;

public class FXCalculatorMain {
	
	
	private static void usage() {
		System.out.println("Welcome to FX Currency Converter.");
		System.out.println("Please Input Currency Input as example Format 'AUD 100.00 IN USD' and type 'exit' if you want to exit. ");
		System.out.println("==============================================================================================");
	} 
	 /**
	  * FXCalculatorMain process user inputs for FX Currency Calculator 
	 * @param args
	 */
	public static void main(String[] args) 
	 { 
		FXCalculator fxCalculator = new  FXCalculator();
		Scanner inScan = new Scanner(System.in);
		 boolean isExiting = false;
		 final String EXIT = "exit";
		 usage();
		 try {
			 while (!isExiting) {
	             String input = inScan.nextLine();
	             if ( input.isEmpty())
	            	 continue;
				 if (EXIT.equals(input)) { 
						isExiting = true;
						continue;
				 }
				 try { 
				     //Compute Currency Conversion and print to Console
					 System.out.println(fxCalculator.fxProcessInput(input));
				 } catch (NumberFormatException iNumEx) {
					 System.out.println("Error: " + iNumEx.getMessage() + " expecting as real number");
				 } catch (IllegalArgumentException iArgsEx) {
					 System.out.println(iArgsEx.getMessage()); 
				 } catch (Exception ex) {
					 System.out.println("Error: "+  ex.getMessage()); 
				 } finally {
					 usage();
				 }
			 }
		 } catch (Exception ex) {
			 System.out.println("Exception Instructions received, ...Exiting.");
			 System.out.println("Error : " + ex.getMessage());
		 }
		 // close the scanner
		 System.out.println("Closing Scanner...");
		 inScan.close();
	 }
}