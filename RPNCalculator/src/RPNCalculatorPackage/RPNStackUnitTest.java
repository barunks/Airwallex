package RPNCalculatorPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class RPNStackUnitTest {

	@Test
	public void test() {
		RPNStack<Double> resultStack = new RPNStack<>();
		int numberOfPushes = 6;
        for (int i = 0; i < numberOfPushes; i++) {
        	resultStack.push(i * Math.random());
        }
        assertFalse(resultStack.isEmpty());
        assertEquals(resultStack.size(), numberOfPushes, 0);	
	}
	
	@Test
	public void testPushAndPopOperation() throws Exception {
		RPNStack<Double> resultStack = new RPNStack<>();
        assertTrue(resultStack.isEmpty());
        resultStack.push(100.00);
        assertFalse(resultStack.isEmpty());
        assertEquals(resultStack.size(), 1, 0);
        double val = resultStack.pop();
        assertEquals(val, 100.00, 0.00);
        assertTrue(resultStack.isEmpty());
        resultStack.push(500.00);
        resultStack.push(600.00);
        resultStack.pushOperator("Push");
        assertEquals(resultStack.size(), 2, 0);
	}
	
	@Test
	public void testUndo() throws Exception {
		RPNStack<Double> resultStack = new RPNStack<>();
		assertFalse(resultStack.canUndo());
		int numberOfPushes = 6;
        for (int i = 0; i < numberOfPushes; i++) {
        	resultStack.push(i * Math.random());
        }
        assertFalse(resultStack.isEmpty());
        assertEquals(resultStack.size(), numberOfPushes, 0);
        assertTrue(resultStack.canUndo());
        resultStack.undo();
        resultStack.undo();
        resultStack.undo();
        assertEquals(resultStack.size(), numberOfPushes - 3, 0);
        resultStack.push(20.00);
        assertEquals(resultStack.size(), numberOfPushes - 2, 0);
        assertEquals(resultStack.pop(), 20.00, 0.00);
	}
	
	@Test
	public void testException() throws IllegalStateException {
		try {
			RPNStack<Double> resultStack = new RPNStack<>();
			assertFalse(resultStack.canUndo());
			int numberOfPushes = 3;
	        for (int i = 0; i < numberOfPushes; i++) {
	        	resultStack.push(i * Math.random());
	        }
	        assertFalse(resultStack.isEmpty());
	        assertEquals(resultStack.size(), numberOfPushes, 0);
	        assertTrue(resultStack.canUndo());
	        resultStack.undo();
	        resultStack.undo();
	        resultStack.undo();
		} catch (IllegalStateException ilEx) {
	    	assertEquals("Undo operation is not possible as current stack is empty", ilEx.getMessage());
		}  
	}

}
