package FXCalculatorPackage;

import java.util.*;

/**
 * <h1> FXCalculator - Currency Converter </h1>
 * This enables a user to convert an amount in a specific currency to the equivalent amount in another currency
 * <p>
 * 
 * @author Barun Sharma
 * @version 1.0
 */
public class FXCalculator {
	
	 /**
	 * CurrencyList is in the order as how FXConverterTable is defined.
	 */
	private static final String[] CurrencyList = {"AUD", "CAD", "CNY", "CZK", "DKK", "EUR", "GBP", "JPY", "NOK", "NZD", "USD"};
	 
	 /**
	 * FXConverterTable is used for Cross Via Matrix table. This is fixed and supplied. 
	 * Based on program instruction, FxConversionMap is kept as static and final.
	 */
	private static final List<String> FXConverterTable;
	 static {
	        List<String> tmpList = new ArrayList<>();
	        tmpList.addAll(Arrays.asList("1:1", "USD", "USD", "USD", "USD", "USD", "USD", "USD", "USD", "USD", "D"));
	        tmpList.addAll(Arrays.asList("USD", "1:1", "USD", "USD", "USD", "USD", "USD", "USD", "USD", "USD", "D"));
	        tmpList.addAll(Arrays.asList("USD",  "USD", "1:1", "USD", "USD", "USD", "USD", "USD", "USD", "USD", "D"));
	        tmpList.addAll(Arrays.asList("USD",  "USD", "USD", "1:1",  "EUR", "Inv", "USD", "USD", "EUR", "USD", "EUR"));
	        tmpList.addAll(Arrays.asList("USD",  "USD", "USD",  "EUR", "1:1", "Inv", "USD", "USD", "EUR", "USD", "EUR"));
	        tmpList.addAll(Arrays.asList("USD",  "USD", "USD",  "D", "D", "1:1", "USD", "USD", "D", "USD", "D"));
	        tmpList.addAll(Arrays.asList("USD",  "USD", "USD",  "USD", "USD", "USD", "1:1", "USD", "USD", "USD", "D"));
	        tmpList.addAll(Arrays.asList("USD",  "USD", "USD",  "USD", "USD", "USD", "USD", "1:1", "USD", "USD", "Inv"));
	        tmpList.addAll(Arrays.asList("USD",  "USD", "USD",  "EUR", "EUR", "Inv",  "USD", "USD", "1:1", "USD", "EUR"));
	        tmpList.addAll(Arrays.asList("USD",  "USD", "USD",  "USD", "USD", "USD", "USD", "USD", "USD", "1:1", "D"));
	        tmpList.addAll(Arrays.asList("Inv",  "Inv", "Inv",  "EUR", "EUR", "Inv", "Inv", "D", "EUR", "Inv", "1:1"));
	        FXConverterTable = Collections.unmodifiableList(tmpList);
	 }
	 
	/**
	 * FxConversionMap is Currency Conversion Map based on currency pairs. Key is Currency Pair and Value is conversion factor.
	 * This is fixed and supplied. Although, This can be read from file and build dynamically.
	 */
	 private static final Map<String, Double> FxConversionMap;
	 static {
	    	Map<String, Double> tmpMap = new HashMap<String, Double>();
	        tmpMap.put("AUDUSD", 0.8371);
	        tmpMap.put("CADUSD", 0.8711);
	        tmpMap.put("USDCNY", 6.1715);
	        tmpMap.put("EURUSD", 1.2315);
	        tmpMap.put("GBPUSD", 1.5683);
	        tmpMap.put("NZDUSD", 0.7750);
	        tmpMap.put("USDJPY", 119.95);
	        tmpMap.put("EURCZK", 27.6028);
	        tmpMap.put("EURDKK", 7.4405);
	        tmpMap.put("EURNOK", 8.6651);
	        FxConversionMap = Collections.unmodifiableMap(tmpMap);
	 }
	 
	 /**
	  * Function getFXCrossViaMatrixValue returns CrossViaMatrix value in 
	  * FxConversionMap based on fromCurrency and  toCurrency ranking in the CurrencyList array 
	  * 
	 * @param fromCurrency
	 * @param toCurrency
	 * @return
	 */
	private String getFXCrossViaMatrixValue(String fromCurrency, String toCurrency) {
		 int fromIndex = Arrays.asList(CurrencyList).indexOf(fromCurrency);
		 int toIndex = Arrays.asList(CurrencyList).indexOf(toCurrency);
		 return FXConverterTable.get(fromIndex * CurrencyList.length + toIndex);
	 }
	 
	 /**
	  * Function getConversionFactor computes conversion factor based on 
	  * fromCurrency, toCurrency, and supplied cross Matrix Table in FxConversionMap
	  * 
	 * @param fromCurrency
	 * @param toCurrency
	 * @param crossViaMatrix
	 * @return
	 */
	private double getConversionFactor(String fromCurrency, String toCurrency, String  crossViaMatrix) {
		 double conversionFactor = 1.0;
		 switch(crossViaMatrix) {
	     	case "D":
	     		if (FxConversionMap.containsKey(fromCurrency+toCurrency))
	     		    conversionFactor = FxConversionMap.get(fromCurrency + toCurrency);
	             break;
	     	case "Inv":
	     		if (FxConversionMap.containsKey(toCurrency+fromCurrency))
	     			conversionFactor /= (FxConversionMap.get(toCurrency + fromCurrency));
	             break;
	         case "1:1":
	        	 conversionFactor = 1.0;
	             break;
	         default:
	         	 String fromCrossMatrix = getFXCrossViaMatrixValue(fromCurrency, crossViaMatrix);
	         	 String toCrossMatrix = getFXCrossViaMatrixValue(crossViaMatrix, toCurrency);
	         	 conversionFactor = getConversionFactor(fromCurrency, crossViaMatrix, fromCrossMatrix) * getConversionFactor(crossViaMatrix, toCurrency, toCrossMatrix);
	             break;
         }	 
		 return conversionFactor;
	 }
	 
	 /**
	  * Function fxCurrencyConversion computes conversion factor based on fromCurrency and toCurrency
	  * post function : Convert fromCurrencyAmount to  toCurrencyAmount by multiplying with the conversion factor
	  * This function does required decimal formatting before display of output
	  * 
	 * @param fromCurrency
	 * @param toCurrency
	 * @param fromCurrencyAmount
	 * @return formatted output string
	 */
	private String fxCurrencyConversion(String fromCurrency, String toCurrency, double fromCurrencyAmount) {
		 if (fromCurrency.isEmpty() || toCurrency.isEmpty())
			 throw new NullPointerException("Currency Input can't be empty");
		 
		 double convertedCurrencyAmount = fromCurrencyAmount * getConversionFactor(fromCurrency, toCurrency, getFXCrossViaMatrixValue(fromCurrency, toCurrency));
		 // Decimal formatting for fromCurrencyAmount
		 String fromCurrencyAmountFmt = (fromCurrency.equals("JPY")) ? (String.format("%d", Math.round(fromCurrencyAmount))) : (String.format("%.2f" , fromCurrencyAmount));
		 // Decimal formatting for convertedCurrencyNominal
		 String toCurrencyAmountFmt = (toCurrency.equals("JPY")) ? (String.format("%d", Math.round(convertedCurrencyAmount))) : (String.format("%.2f" , convertedCurrencyAmount));
		 // Defined output format for end user
		return (fromCurrency + " " + fromCurrencyAmountFmt + " = " + toCurrency + " " + toCurrencyAmountFmt);
	 }
	
	/**
	 * This function parse user inputs in string for fromCurrency, from Currency amount, and toCurrency. 
	 * post function : Calls function fxCurrencyConversion to return required formatted string output.
	 * 
	 * @param input string
	 * @return formatted output string
	 */
	public String fxProcessInput(String input) {
		
		if(input == null || input.isEmpty()){
			throw new NullPointerException("Input string can't be empty or null");
		}
		
		List<String> inputList = new ArrayList<>(Arrays.asList(input.split(" ")));
	    // fxFromCurrency is first indexed input value
	    String fxFromCurrency = inputList.get(0);
	    // fxToCurrency is fourth indexed input value
	    String fxToCurrency = inputList.get(3);
	    // Currency Amount is second indexed input value
	    double fromCurrencyAmount =  Double.parseDouble(inputList.get(1));
	    if (!isCurrencyExist(fxFromCurrency) || !isCurrencyExist(fxToCurrency))
			 throw new IllegalArgumentException("Unable to find rate for " + fxFromCurrency +"/" + fxToCurrency);
	    //Compute Currency Conversion
	    return fxCurrencyConversion(fxFromCurrency, fxToCurrency, fromCurrencyAmount);
	}
	
	/**
	 * isCurrencyExist check for currency existing in CurrencyList Array
	 * 
	 * @param currency
	 * @return boolean
	 */
	public boolean isCurrencyExist(String currency)
	{
		return Arrays.asList(CurrencyList).contains(currency);
	}
}