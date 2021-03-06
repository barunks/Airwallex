# FX calulator - create a console-based currency converter application

## Introduction:

### The FX calculator allows an user to convert an amount in a specific currency to the equivalent amount in another currency.

### FX calculator requires a user input in `“<ccy1> <amount1> in <ccy2>”` where ccy1 is the known currency with amount as amount1, and FX Calculator will do necessary conversion to provide end result as the equivalent amount in another currency. 

### FX calculator parse console input like `“<ccy1> <amount1> in <ccy2>”`, and provide response in the console as `"<ccy1> <amount1> = <ccy2> <amount2>"`


### Instruction: Programming done using Java Language implemented by TDD in Eclipse IDE.

## Implementation Steps: 

### 1. A new class FXCalculator is created. 
 ```
  RPNSTackUnitTest.java file is included based on which RPNStack is created.
  
  Added Static List :
  CurrencyList : CurrencyList includes all currency in the order it is defined currency cross-via matrix. 
  FXConverterTable : This is Cross-via matrix currency table which is referenced for any cross-via currency conversion
  FxConversionMap :  FxConversionMap is Currency Conversion Map based on currency pairs. Key is Currency Pair and Value is conversion factor
  

  Private Functions:
  getFXCrossViaMatrixValue() : This function references to Cross-via matrix currency table and returns corrosponding currency which is used in  currency conversion. This take two parameter as fromCurrency and toCurrency which is needed currency pair conversion. 
  getConversionFactor() : This function computes conversion factor based on fromCurrency and toCurrency. Based on conversion factor, This converts fromCurrency Amount to  toCurrencyAmount by multiplying with the conversion factor. This function does required decimal formatting as needed.
  fxCurrencyConversion() : This function computes conversion factor based on fromCurrency and toCurrency and next it Converts fromCurrencyAmount to  toCurrencyAmount by multiplying with the conversion factor. This function does required decimal formatting before display of output 

  Public Functions: 
  fxProcessInput() : This function parse user inputs in string for fromCurrency, from Currency amount, and toCurrency and then next it calls function fxCurrencyConversion to return required formatted output as string.
  isCurrencyExist() : Check if currency exists in the Currency List. 

  class FXCalculator File name : FXCalculator.java
  Unit Test File name : FXCalculatorUnitTest.java 
 ```
### 2. FXCalculatorMain class is created to have main() run it as Java application to take input from user and process input for FX Calculator operation.
 ```
   Public Functions : 
   main() -> Main function to take input from users and process input for FX Calculator operation and print output to the console.

   class FXCalculatorMain File name : FXCalculatorMain.java
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
Welcome to FX Currency Converter.
Please Input Currency Input as example Format 'AUD 100.00 IN USD' and type 'exit' if you want to exit. 
============================================================================================== 
AUD 100.00 in USD
AUD 100.00 = USD 83.71
 ```
### Example 2
 ```
Welcome to FX Currency Converter.
Please Input Currency Input as example Format 'AUD 100.00 IN USD' and type 'exit' if you want to exit. 
==============================================================================================  
AUD 100.00 in AUD
AUD 100.00 = AUD 100.00
 ```
### Example 3
 ``` 
Welcome to FX Currency Converter.
Please Input Currency Input as example Format 'AUD 100.00 IN USD' and type 'exit' if you want to exit. 
==============================================================================================  
AUD 100.00 in DKK
AUD 100.00 = DKK 505.76
 ```
### Example 4
 ```
Welcome to FX Currency Converter.
Please Input Currency Input as example Format 'AUD 100.00 IN USD' and type 'exit' if you want to exit. 
============================================================================================== 
JPY 100 in USD
JPY 100 = USD 0.83
 ``` 
### Example 5
 ```
Welcome to FX Currency Converter.
Please Input Currency Input as example Format 'AUD 100.00 IN USD' and type 'exit' if you want to exit. 
============================================================================================== 
KRW 1000.00 in FJD
Unable to find rate for KRW/FJD
 ```
### Example 6
 ```
Welcome to FX Currency Converter.
Please Input Currency Input as example Format 'AUD 100.00 IN USD' and type 'exit' if you want to exit. 
============================================================================================== 
AUD 1.00 in JPY
AUD 1.00 = JPY 100
 ```
