package RPNCalculatorPackage;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.function.*;

/**
 * <h1> RPNCalculator - a command-line based RPN calculator </h1>
 *  Reverse Polish Notation Calculator computes a real number stack.
 *  Operations supported are '+', '-,' '*, '/,',  'sqrt', 'undo;', 'clear'
 *  The ‘+’, ‘-’, ‘*’, ‘/’ operators requires top two items from the Stack
 *  'sqrt' is a square root operation on the top item from the stack
 *  The ‘undo’ operator undoes the previous operation.
 *  The ‘clear’ operator removes all items from the stack.
 * 
 * @author Barun Sharma
 * @version 1.0
 */
public class RPNCalculator 
{
	final int UNARYPARAMETER = 1;
	final int BINARYPARAMETER = 2;
	static int currentIndex = 0;
	static String currentOperation = null;
	private RPNStack<Double> resultStack = new RPNStack<>();
	/**
	 * rpnCalcBinaryOps computes binary operations based on supplied BiFunction Operation
	 * 
	 * @param numStack
	 * @param operation
	 * @return RPNStack
	 */
	protected RPNStack<Double> rpnCalcBinaryOps(BiFunction<Double, Double, Double> operation) {
		if (resultStack.size() < BINARYPARAMETER) {
			throw new IllegalArgumentException("operator " + RPNCalculator.currentOperation + " (position: " + (RPNCalculator.currentIndex + 1) + "): insuffcient parameters");
		}
		
		resultStack.push(operation.apply(resultStack.pop(), resultStack.pop()));
		resultStack.pushOperator(currentOperation);
        return resultStack;
    }
	
	/**
	 * rpnCalcBinaryOps computes binary operations based on supplied BiFunction Operation
	 * 
	 * @param numStack
	 * @param func
	 * @return RPNStack<Double>
	 */
	protected RPNStack<Double> rpnCalcUnaryOps(Function<Double, Double> func) {
		if (resultStack.size() < UNARYPARAMETER) {
			throw new IllegalArgumentException("operator " + RPNCalculator.currentOperation + " (position: " + (RPNCalculator.currentIndex + 1) + "): insuffcient parameters");
		}
		
		resultStack.push(func.apply(resultStack.pop()));
		resultStack.pushOperator(currentOperation);
        return resultStack;
    }
	
	/**
	 * printRPNStack Prints RPNStack value as instructed in programming exercise
	 * 
	 */
	public void printRPNStack() {
	     //System.out.println("Stack: " + Arrays.toString(myStack.toArray()));
		DecimalFormat decFmt = new DecimalFormat("0.##########");
        System.out.print("Stack: ");
        
        resultStack.stream().forEach(number -> {
        	if (Double.isFinite(number))
                System.out.print(decFmt.format(number));
         	else
         	   System.out.print(number);
             System.out.print(" ");
        });
        
        System.out.printf("%n");
	}
	
	/**
	 * processRPNStackOperation is the main function which push value into stack after each number operation or no operation
	 * and supports other operations like clear and undo.
	 * 
	 * @param resultStack
	 * @param input
	 */
	public void processRPNStackOperation(String input) {
		
		if(input == null || input.isEmpty())
			return;
		
		currentIndex = 0;
		Arrays.asList(input.split(" ")).stream().forEach(number -> {
			currentOperation = number;
			currentIndex = (currentIndex > 0) ?input.indexOf(number, currentIndex + 1) : currentIndex;
			switch(number) {
	        	case "+":
	        		rpnCalcBinaryOps((n1, n2) -> n2 + n1);
	                break;
	        	case "-":
	        		rpnCalcBinaryOps((n1, n2) -> n2 - n1);
	                break;
	            case "*":
	            	rpnCalcBinaryOps((n1, n2) -> n2 * n1);
	                break;
	            case "/":
	            	rpnCalcBinaryOps((n1, n2) -> n2 / n1);
	                break;
	            case "clear":
	                resultStack.clear();
	                break;
	            case "sqrt":
	            	rpnCalcUnaryOps((n1) -> Math.sqrt(n1));
	                break;
	            case "undo":
	                resultStack.undo();	
	                break;
	            default:
	            	try {
	                       resultStack.push(Double.parseDouble(number));
	            	} catch (Exception ex){
	            		throw new IllegalArgumentException("Error for input: " + number + " at position: " + (RPNCalculator.currentIndex + 1));
	            	}
		    }
			currentIndex = (currentIndex == 0) ? (currentIndex + 1) : currentIndex;
		});
	}
	
	/**
	 * @param idx
	 * @return indexed element for RPNStack stack
	 */
	public double getRPNStackElementAtIndex(int idx)
	{
		return resultStack.get(idx).doubleValue();
	}
	
	/**
	 * @return RPN Stack size
	 */
	public int getRPNStackSize()
	{
		return resultStack.size();
	}	
}