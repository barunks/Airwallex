package FXCalculatorPackage;

import static org.junit.Assert.*;
import org.junit.Test;

public class FxCalculatorUnitTest {

	@Test
	public void testFXCalculatorResult() throws Exception {
		
		FXCalculator fxCalculator = new FXCalculator();
		assertEquals("AUD 100.00 = USD 83.71", fxCalculator.fxProcessInput("AUD 100.00 in USD"));
		assertEquals("AUD 100.00 = AUD 100.00", fxCalculator.fxProcessInput("AUD 100.00 in AUD"));
		assertEquals("AUD 100.00 = DKK 505.76", fxCalculator.fxProcessInput("AUD 100.00 in DKK"));
		assertEquals("JPY 100 = USD 0.83", fxCalculator.fxProcessInput("JPY 100 in USD"));
		assertEquals("AUD 1.00 = JPY 100", fxCalculator.fxProcessInput("AUD 1 in JPY"));
		assertEquals("NOK 200.00 = USD 28.42", fxCalculator.fxProcessInput("NOK 200 IN USD"));
	}
	
	@Test
	public void testFXNotFoundCurrencyResult() throws IllegalArgumentException {
		try {
			FXCalculator fxCalculator = new FXCalculator();
			fxCalculator.fxProcessInput("KRW 1000.00 in FJD");
		} catch ( IllegalArgumentException iArgEx) {
			assertEquals("Unable to find rate for KRW/FJD", iArgEx.getMessage()); 
		}
	}
	
	@Test
	public void testFXCurrencyExistInClass() throws Exception {
	    FXCalculator fxCalculator = new FXCalculator();
		assertFalse(fxCalculator.isCurrencyExist("KRW"));
		assertTrue(fxCalculator.isCurrencyExist("USD"));
	}
	
	@Test(expected = Exception.class)
	public void testFXInvalidInputs()  {
		FXCalculator fxCalculator = new FXCalculator();
		fxCalculator.fxProcessInput("FJD JMP");
	}
	
	@Test
	public void testFXIllegalDoubleAmmount() throws NumberFormatException {
		try {
			FXCalculator fxCalculator = new FXCalculator();
			fxCalculator.fxProcessInput("JPY 12.0E in USD");
		} catch ( NumberFormatException iNumEx) {
			String errorMessage = "Error: " + iNumEx.getMessage() + " expecting as real number";
			assertEquals("Error: For input string: \"12.0E\" expecting as real number", errorMessage); 
		}
	}
}