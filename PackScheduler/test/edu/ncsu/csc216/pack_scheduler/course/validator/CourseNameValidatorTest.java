package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests the CourseNameValidator class for funtionality
 * @author jhdav
 *
 */
public class CourseNameValidatorTest {

	/**
	 * Tests the isValid method in the CourseNameValidator class for several scenarios of naming
	 */
	@Test
	public void testIsValid() {
		CourseNameValidator cnv = new CourseNameValidator();
		String goodName = "CSC216";
		String nameLLLL = "CSCS226";
		String nameLL = "CS216";
		String nameL = "E102";
		String nameWithSuffix = "CSC216A";
		String nameWithLongSuffix = "CSC216AA";
		String nameWithSuffixWithNumber = "CSC216A2";
		String nameDDDD = "CSC2116";
		String nameLLLLL = "CSCSC216";
		String nameDDL = "CSC21C";
		String nameDL = "CSC2C";
		String symbol = "#";
		String nameD = "5";
		boolean testValidity;
		try {
		testValidity = cnv.isValid(goodName);
		assertTrue(testValidity);
		} catch(InvalidTransitionException e) {
			fail();
		}
		
		try {
			testValidity = cnv.isValid(nameLLLL);
			assertTrue(testValidity);
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		try {
			testValidity = cnv.isValid(nameL);
			assertTrue(testValidity);
		} catch(InvalidTransitionException e) {
			fail();
		}
		
		try {
			testValidity = cnv.isValid(nameWithSuffix);
			assertTrue(testValidity);
		} catch(InvalidTransitionException e) {
			fail();
		}
		
		try {
			testValidity = cnv.isValid(nameDDDD);
			assertFalse(testValidity);
		} catch(InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		
		try {
			testValidity = cnv.isValid(nameLLLLL);
			assertFalse(testValidity);
		} catch(InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		
		try {
			testValidity = cnv.isValid(nameDDL);
			assertFalse(testValidity);
		} catch(InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
			
		try {
			testValidity = cnv.isValid(nameDL);
			assertFalse(testValidity);
		} catch(InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		try {
			testValidity = cnv.isValid(nameLL);
			assertTrue(testValidity);
		} catch(InvalidTransitionException e) {
			fail();
		}
		
		try {
			testValidity = cnv.isValid(symbol);
			assertFalse(testValidity);
		} catch(InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		try {
			testValidity = cnv.isValid(nameD);
			assertFalse(testValidity);
		} catch(InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		
		try {
			testValidity = cnv.isValid(nameWithLongSuffix);
			assertFalse(testValidity);
		} catch(InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		
		try {
			testValidity = cnv.isValid(nameWithSuffixWithNumber);
			assertFalse(testValidity);
		} catch(InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
		
		try {
			testValidity = cnv.isValid("#####"); //long sequence of symbols
			assertFalse(testValidity);
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
	}
}
