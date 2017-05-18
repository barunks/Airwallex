package RPNCalculatorPackage;

import java.util.*;

/**
 * <h1> RPNStack extend Generic Stack class to support Undo operation!</h1>
 * The RPNStack class will enable Undo operation for select commands 'push' and 'pop'
 * Supported operations includes binary and unary operations in the list from the top of the stack items
 * 
 * <p>
 * 
 * @author Barun Sharma
 * @version 1.0
 * @param <T>
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class RPNStack<T>  extends Stack<T> {
	
	private static final long serialVersionUID = 1L;
	// Supported Binary Operations
	private static final String[] binaryOperation = {"+", "-", "/", "*"};
	// Supported Unary Operation
	private static final String[] unaryOperation = {"sqrt"};
	private Stack undoRPNStack;
	
    /**
     * post: construct an empty UndoRedoStack
     */
    public RPNStack() {
    	undoRPNStack = new Stack<>();
    }
    
    /**
     * post: pushes and returns the given value on top of the stack
     */
    @Override
    public T push(T value) {
        super.push(value);
        undoRPNStack.push(value);
        undoRPNStack.push("push");
        return value;
    }

    /**
     * post: pops and returns the value at the top of the stack
     */
    @Override
    public T pop() {
        T value = super.pop();
        undoRPNStack.push(value);
        undoRPNStack.push("pop");
        return value;
    }
    
    /**
     * post: clear Main stack and undoRPN Stack
     */
    @Override
    public void clear() {
       super.clear();
       undoRPNStack.clear();
    }
    
    
    /**
     * post: returns whether or not an undo can be done
     */
    public boolean canUndo() {
        return !undoRPNStack.isEmpty();
    }
    
    /**
     * pre : canUndo() (throws IllegalStateException if not)
     * post: undo the last stack push or pop command from main stack and undoRPNStack
     * post: For Unary Operations, Undo last one action from undoRPNStack and push to main stack
     * post: For Binary Operations, Undo last two actions from undoRPNStack and push to main stack in the order
     */
    public void undo() {
        if (!canUndo()) {
            throw new IllegalStateException("Undo operation is not possible as current stack is empty");
        }
        Object action = undoRPNStack.pop();
        if (action.equals("push")) {
            super.pop();
            undoRPNStack.pop();
        } else if (action.equals("pop")) {
        	 T value = (T) undoRPNStack.pop();         
             super.push(value);
             undoRPNStack.push(value);
             undoRPNStack.push("pop");
        }
        else
        {
        	if (Arrays.asList(unaryOperation).contains(action)){
        		super.pop();
        		popValueFromUndoStack();
        		T firstvalue = (T) popValueFromUndoStack();
        		super.push(firstvalue);
        	}
        	else if (Arrays.asList(binaryOperation).contains(action)) {
        		super.pop();
        		popValueFromUndoStack();
        		T firstvalue = (T) popValueFromUndoStack();
        		T secondvalue = (T) popValueFromUndoStack();
        		super.push(firstvalue);
        		super.push(secondvalue);
        	}
        }
    }

	/**
	 * post: pops from undoRPNStack and returns the value 
	 * @param operation push this to undoRPNStack
	 */
	public void pushOperator(String operation) {
		if (operation != null && !operation.isEmpty())
			undoRPNStack.push(operation);
	}
	
	/**
	 * post: pops from undoRPNStack and returns the value 
	 *  @return  return the value from top of the undoRPNStack
	 */
	private T popValueFromUndoStack() {
		undoRPNStack.pop();
		return (T) undoRPNStack.pop();
	}
}