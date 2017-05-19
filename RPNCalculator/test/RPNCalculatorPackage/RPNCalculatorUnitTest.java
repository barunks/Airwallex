package RPNCalculatorPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class RPNCalculatorUnitTest {

	@Test
	public void test() {
		RPNCalculator calculator = new RPNCalculator();
		
		// Test Push
		calculator.processRPNStackOperation("5 2");
		assertEquals(5, (int)calculator.getRPNStackElementAtIndex(0), 0);
        assertEquals(2, (int)calculator.getRPNStackElementAtIndex(1), 0);
        
     // Subtraction
        calculator.processRPNStackOperation("clear");
        calculator.processRPNStackOperation("5 2 -");
        assertEquals(1, calculator.getRPNStackSize());
        assertEquals(3, (int)calculator.getRPNStackElementAtIndex(0), 0);
        calculator.processRPNStackOperation("3 -");
        assertEquals(1, calculator.getRPNStackSize());
        assertEquals(0, (int)calculator.getRPNStackElementAtIndex(0), 0);
        calculator.processRPNStackOperation("3 -");
        assertEquals(1, calculator.getRPNStackSize());
        assertEquals(-3, (int)calculator.getRPNStackElementAtIndex(0), 0);
        
        //multiplication
        calculator.processRPNStackOperation("clear");
        calculator.processRPNStackOperation("1 2 3 4 5");
        calculator.processRPNStackOperation("* * *");
        assertEquals(2, calculator.getRPNStackSize());
        assertEquals(1, (int)calculator.getRPNStackElementAtIndex(0), 0);
        assertEquals(120, (int)calculator.getRPNStackElementAtIndex(1), 0);
        calculator.processRPNStackOperation("*");
        assertEquals(1, calculator.getRPNStackSize());
        assertEquals(120, (int)calculator.getRPNStackElementAtIndex(0), 0);
       
        
     // division
        calculator.processRPNStackOperation("clear");
        calculator.processRPNStackOperation("7 12 2 /");
        assertEquals(7, (int)calculator.getRPNStackElementAtIndex(0), 0);
        assertEquals(6, (int)calculator.getRPNStackElementAtIndex(1), 0);
        calculator.processRPNStackOperation("*");
        assertEquals(1, calculator.getRPNStackSize());
        assertEquals(42, (int)calculator.getRPNStackElementAtIndex(0), 0);
        calculator.processRPNStackOperation("4 /");
        assertEquals(1, calculator.getRPNStackSize());
        assertEquals(10.5, calculator.getRPNStackElementAtIndex(0), 0);
        
        // test for Infinity double value
        calculator.processRPNStackOperation("clear");
        calculator.processRPNStackOperation("7 0 /");
        assertEquals(1, calculator.getRPNStackSize());
        assertTrue(Double.isInfinite(calculator.getRPNStackElementAtIndex(0)));
        calculator.processRPNStackOperation("undo");
        assertEquals(2, calculator.getRPNStackSize());
        assertEquals(7, (int)calculator.getRPNStackElementAtIndex(0), 0);
        assertEquals(0, (int)calculator.getRPNStackElementAtIndex(1), 0);
        
        // sqrt
        calculator.processRPNStackOperation("clear");
        calculator.processRPNStackOperation("9 9 * sqrt");
        assertEquals(9, (int)calculator.getRPNStackElementAtIndex(0), 0);
        calculator.processRPNStackOperation("undo");
        assertEquals(81, (int)calculator.getRPNStackElementAtIndex(0), 0);
        calculator.processRPNStackOperation("undo");
        assertEquals(2, calculator.getRPNStackSize());
        assertEquals(9, (int)calculator.getRPNStackElementAtIndex(0), 0);
        assertEquals(9, (int)calculator.getRPNStackElementAtIndex(1), 0);
        
        // test for NAN double value
        calculator.processRPNStackOperation("clear");
        calculator.processRPNStackOperation("-15 sqrt");
        assertEquals(1, calculator.getRPNStackSize());
        assertTrue(Double.isNaN(calculator.getRPNStackElementAtIndex(0)));
		
	}

	@Test
	 public void testSqrt() throws Exception {
		 RPNCalculator calculator = new RPNCalculator();
	     calculator.processRPNStackOperation("2 sqrt");
	     calculator.processRPNStackOperation("clear 9 sqrt");
	     assertEquals(1, calculator.getRPNStackSize());
	     assertEquals(3, (int)calculator.getRPNStackElementAtIndex(0), 0);
	 }
	 
	 @Test
	 public void testInsuficientParameters() {
		 RPNCalculator calculator = new RPNCalculator();
	     try {
	         calculator.processRPNStackOperation("1 2 3 * 5 + * * 6 5");
	     } catch (Exception ex) {
	         assertEquals("operator * (position: 15): insuffcient parameters", ex.getMessage());
	     }
	     assertEquals(1, calculator.getRPNStackSize());
	     assertEquals(11, (int)calculator.getRPNStackElementAtIndex(0), 0);
	 }
	 
	 @Test
	 public void testUndoOperation() throws Exception {
	     RPNCalculator calculator = new RPNCalculator();
	     calculator.processRPNStackOperation("5 4 3 2");
	     assertEquals(4, calculator.getRPNStackSize());
	     calculator.processRPNStackOperation("undo undo *");
	     assertEquals(1, calculator.getRPNStackSize());
	     assertEquals(20, (int)calculator.getRPNStackElementAtIndex(0), 0);
	     calculator.processRPNStackOperation("5 *");
	     assertEquals(1, calculator.getRPNStackSize());
	     assertEquals(100, (int)calculator.getRPNStackElementAtIndex(0), 0);
	     calculator.processRPNStackOperation("undo");
	     assertEquals(2, calculator.getRPNStackSize());
	     assertEquals(20, (int)calculator.getRPNStackElementAtIndex(0), 0);
	     assertEquals(5, (int)calculator.getRPNStackElementAtIndex(1), 0);
	     
	 }
	 
	 @Test
	 public void testIllegalArgumentOperation() throws IllegalArgumentException {
	 try {
	         RPNCalculator calculator = new RPNCalculator();
	         calculator.processRPNStackOperation("+ +");
	     } catch (IllegalArgumentException iArgEx) {
	      	  assertEquals("operator + (position: 1): insuffcient parameters", iArgEx.getMessage()); 
		 }
		     
	 }
	 
	 @Test
	 public void testIllegalDoubleValueInput() throws IllegalArgumentException {
	 try {
	         RPNCalculator calculator = new RPNCalculator();
	         calculator.processRPNStackOperation("7 6 + 5 3 * 76e +");
	     } catch (IllegalArgumentException ilEx) {
	         assertEquals("Error for input: 76e at position: 13", ilEx.getMessage());
	     }  
	 }
	 
	 @Test
	 public void testIllegalStateOperation() throws IllegalStateException {
	 try {
	         RPNCalculator calculator = new RPNCalculator();
	         calculator.processRPNStackOperation("4 2 +");
	         calculator.processRPNStackOperation("undo undo undo undo undo +");
	     } catch (IllegalStateException ilEx) {
	         assertEquals("Undo operation is not possible as current stack is empty", ilEx.getMessage());
	     }  
	 }
}
