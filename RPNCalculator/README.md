# Reverse Polish notation calulator

## Introduction:

### The RPN calculator receives user input in form of strings containing whitespace separated lists of numbers and operators.

### The RPN calculator has a stack that contains real number.
### Numbers are pushed on to the stack. Operators operate on numbers that are on the stack.

### Available operators are +, -, *, /, sqrt, clear, undo

### +, -, *, and / are binary operators and it requires minimum two numbers for its operation on the top two items from the stack.. 

### sqrt is single operator operation and it requires minimum one number

### undo operator will undo previous operation

### clear will remove all items from the stacks.

### If an operator cannot find a sufficient number of parameters on the stack, a warning is displayed:

### operator <operator> (position: <pos>): insufficient parameters

### Instruction: Programming done using Java Language implemented by TDD in Eclipse IDE.

## Implementation Steps: 

### 1 A new class RPNStack is created which extends Generic Stack class to support Undo operation. 
### RPNSTackUnitTest.java file is included based on which RPNStack is created.
 ``` 
  New public Functions added: undo(), canUndo(). 
  Extended functions: push() and pop()

  class RPNStack File name : RPNStack.java
  Unit Test File name : RPNSTackUnitTest.java
 ``` 

### 2. RPNCalculator class is created which ensures RPN calculator computation for Binary Operation(+, -, *, /,), Unary operation(sqrt), undo and clear. Programming is done in extensible way than any new operations and functionality can be added without much change in the code.
```
   Public Functions : 
   processRPNStackOperation() -> Take String input and process for RPN Calculator Operation (+, -, *, /, sqrt, clear, undo)
   printRPNStack() -> print RPNStack as instructed
   getRPNStackElementAtIndex() -> It returns indexed element for RPNCalculator stack
   getRPNStackSize() -> return RPNCalculator stack size
   
   
   Protected Functions for further extension:
   rpnCalcBinaryOps() -> Java BiFunction to support Binary Operation (+, -, *, /,)
   rpnCalcUnaryOps() -> Java Function to support Unary Operation (sqrt)

   class RPNcalculator File name : RPNcalculator.java
   Unit Test File name : RPNcalculatorUnitTest.java
```
### 3.  RPNCalculatorMain class is created to have main() run it as Java application to take input from user and process input for RPN Calculator operation.
```
    Public Functions : 
    main() -> Main function to take input from users and process input for RPN Calculator operation and print output to the console.

    class RPNCalculatorMain File name : RPNCalculatorMain.java
```
## Requirements
```
Implemented and tested using Java 8

Test require JUnit4 test framework.

Project dependencies and compiling managed by Maven
```
## Test Results:

### Example 1
```
Welcome to RPN Calculator.
Please Input Your Operation for '+', '-', '*', '/'. 'sqrt'.  Type 'exit' if you want to exit. 
==============================================================================================
5 2 -
5 2
stack: 5 2
```
### Example 2
```
Welcome to RPN Calculator.
Please Input Your Operation for '+', '-', '*', '/'. 'sqrt'.  Type 'exit' if you want to exit. 
==============================================================================================
5 2 -
2 sqrt
stack: 1.4142135623
clear 9 sqrt
stack: 3
```

### Example 3
```
Welcome to RPN Calculator.
Please Input Your Operation for '+', '-', '*', '/'. 'sqrt'.  Type 'exit' if you want to exit. 
==============================================================================================
5 2 -
stack: 3
3 -
stack: 0
clear
stack:
```
### Example 4
```
Welcome to RPN Calculator.
Please Input Your Operation for '+', '-', '*', '/'. 'sqrt'.  Type 'exit' if you want to exit. 
==============================================================================================
5 4 3 2
stack: 5 4 3 2
undo undo *
stack: 20
5 *
stack: 100
undo
stack: 20 5
```
### Example 5
```
Welcome to RPN Calculator.
Please Input Your Operation for '+', '-', '*', '/'. 'sqrt'.  Type 'exit' if you want to exit. 
==============================================================================================
7 12 2 /
stack: 7 6
*
stack: 42
4 /
stack: 10.5
```
### Example 6
```
Welcome to RPN Calculator.
Please Input Your Operation for '+', '-', '*', '/'. 'sqrt'.  Type 'exit' if you want to exit. 
==============================================================================================
1 2 3 4 5
stack: 1 2 3 4 5
*
stack: 1 2 3 20
clear 3 4 -
stack: - 1
```
### Example 7
```
Welcome to RPN Calculator.
Please Input Your Operation for '+', '-', '*', '/'. 'sqrt'.  Type 'exit' if you want to exit. 
==============================================================================================
1 2 3 4 5
stack: 1 2 3 4 5
* * * *
stack: 120
```
### Example 8
```
Welcome to RPN Calculator.
Please Input Your Operation for '+', '-', '*', '/'. 'sqrt'.  Type 'exit' if you want to exit. 
==============================================================================================
1 2 3 * 5 + * * 6 5
operator * (position: 15): insuffcient parameters
stack: 11
```
