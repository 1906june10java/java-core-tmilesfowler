package com.revature.eval.java.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EvaluationServiceTest {

	private static final EvaluationService evaluationService = new EvaluationService();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/*******************************************************************
	 * Question 1
	 ******************************************************************/
	@Test
	public void basic() {
		final String phrase = "Portable Network Graphics";
		final String expected = "PNG";
		assertEquals(expected, evaluationService.acronym(phrase));
	}

	@Test
	public void punctuation() {
		final String phrase = "First In, First Out";
		final String expected = "FIFO";
		assertEquals(expected, evaluationService.acronym(phrase));
	}
	
	@Test
	public void punctuation2() {
		final String phrase = "Last In, First Out";
		final String expected = "LIFO";
		assertEquals(expected, evaluationService.acronym(phrase));
	}

	@Test
	public void NonAcronymAllCapsWord() {
		final String phrase = "GNU Image Manipulation Program";
		final String expected = "GIMP";
		assertEquals(expected, evaluationService.acronym(phrase));
	}

	@Test
	public void punctuationWithoutWhitespace() {
		final String phrase = "Complementary metal-oxide semiconductor";
		final String expected = "CMOS";
		assertEquals(expected, evaluationService.acronym(phrase));
	}

	/*******************************************************************
	 * Question 2
	 ******************************************************************/
	@Test
	public void testAValuableLetter() {
		assertEquals(4, evaluationService.getScrabbleScore("f"));
	}

	@Test
	public void testAShortValuableWord() {
		assertEquals(12, evaluationService.getScrabbleScore("zoo"));
	}

	@Test
	public void testAMediumWord() {
		assertEquals(6, evaluationService.getScrabbleScore("street"));
	}

	@Test
	public void testAMediumValuableWord() {
		assertEquals(22, evaluationService.getScrabbleScore("quirky"));
	}

	@Test
	public void testALongMixCaseWord() {
		assertEquals(41, evaluationService.getScrabbleScore("OxyphenButazone"));
	}

	/*******************************************************************
	 * Question 3
	 ******************************************************************/
	@Test
	public void cleansTheNumber() {
		final String expectedNumber = "2234567890";
		final String actualNumber = evaluationService.cleanPhoneNumber("(223) 456-7890");
		assertEquals(expectedNumber, actualNumber);
	}

	@Test
	public void cleansNumbersWithDots() {
		final String expectedNumber = "2234567890";
		final String actualNumber = evaluationService.cleanPhoneNumber("223.456.7890");
		assertEquals(expectedNumber, actualNumber);
	}

	@Test
	public void cleansNumbersWithMultipleSpaces() {
		final String expectedNumber = "2234567890";
		final String actualNumber = evaluationService.cleanPhoneNumber("223 456   7890   ");
		assertEquals(expectedNumber, actualNumber);
	}

	@Test
	public void invalidWhenMoreThan11Digits() {
		expectedException.expect(IllegalArgumentException.class);
		evaluationService.cleanPhoneNumber("321234567890");
	}

	@Test
	public void invalidWithNonNumeric() {
		expectedException.expect(IllegalArgumentException.class);
		evaluationService.cleanPhoneNumber("123-abc-7890");
		expectedException.expect(IllegalArgumentException.class);
		evaluationService.cleanPhoneNumber("123-@:!-7890");
	}

	/*******************************************************************
	 * Question 4
	 ******************************************************************/
	@Test
	public void countOneWord() {
		Map<String, Integer> expectedWordCount = new HashMap<>();
		expectedWordCount.put("word", 1);

		Map<String, Integer> actualWordCount = evaluationService.wordCount("word");
		assertEquals(expectedWordCount, actualWordCount);
	}

	@Test
	public void countOneOfEachWord() {
		Map<String, Integer> expectedWordCount = new HashMap<>();
		expectedWordCount.put("one", 1);
		expectedWordCount.put("of", 1);
		expectedWordCount.put("each", 1);

		Map<String, Integer> actualWordCount = evaluationService.wordCount("one of each");
		assertEquals(expectedWordCount, actualWordCount);
	}

	@Test
	public void multipleOccurrencesOfAWord() {
		Map<String, Integer> expectedWordCount = new HashMap<>();
		expectedWordCount.put("one", 1);
		expectedWordCount.put("fish", 4);
		expectedWordCount.put("two", 1);
		expectedWordCount.put("red", 1);
		expectedWordCount.put("blue", 1);

		Map<String, Integer> actualWordCount = evaluationService.wordCount("one fish two fish red fish blue fish");
		assertEquals(expectedWordCount, actualWordCount);
	}

	@Test
	public void handlesCrampedLists() {
		Map<String, Integer> expectedWordCount = new HashMap<>();
		expectedWordCount.put("one", 1);
		expectedWordCount.put("two", 1);
		expectedWordCount.put("three", 1);

		Map<String, Integer> actualWordCount = evaluationService.wordCount("one,two,three");
		assertEquals(expectedWordCount, actualWordCount);
	}

	@Test
	public void handlesExpandedLists() {
		Map<String, Integer> expectedWordCount = new HashMap<>();
		expectedWordCount.put("one", 1);
		expectedWordCount.put("two", 1);
		expectedWordCount.put("three", 1);

		Map<String, Integer> actualWordCount = evaluationService.wordCount("one,\ntwo,\nthree");
		assertEquals(expectedWordCount, actualWordCount);
	}

	/*******************************************************************
	 * Question 5
	 ******************************************************************/
	@Test
	public void findsAValueInTheMiddleOfAnArray() {
		List<String> sortedList = Collections.unmodifiableList(Arrays.asList("1", "3", "4", "6", "8", "9", "11"));

		EvaluationService.BinarySearch<String> search = new EvaluationService.BinarySearch<>(sortedList);

		assertEquals(3, search.indexOf("6"));
	}

	@Test
	public void findsAValueAtTheBeginningOfAnArray() {
		List<Integer> sortedList = Collections.unmodifiableList(Arrays.asList(1, 3, 4, 6, 8, 9, 11));

		EvaluationService.BinarySearch<Integer> search = new EvaluationService.BinarySearch<>(sortedList);

		assertEquals(0, search.indexOf(1));
	}

	@Test
	public void findsAValueAtTheEndOfAnArray() {
		List<Integer> sortedList = Collections.unmodifiableList(Arrays.asList(1, 3, 4, 6, 8, 9, 11));

		EvaluationService.BinarySearch<Integer> search = new EvaluationService.BinarySearch<>(sortedList);

		assertEquals(6, search.indexOf(11));
	}

	@Test
	public void findsAValueInAnArrayOfOddLength() {
		List<Integer> sortedListOfOddLength = Collections
				.unmodifiableList(Arrays.asList(1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 634));

		EvaluationService.BinarySearch<Integer> search = new EvaluationService.BinarySearch<>(sortedListOfOddLength);

		assertEquals(9, search.indexOf(144));
	}

	@Test
	public void findsAValueInAnArrayOfEvenLength() {
		List<Integer> sortedListOfEvenLength = Collections
				.unmodifiableList(Arrays.asList(1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377));

		EvaluationService.BinarySearch<Integer> search = new EvaluationService.BinarySearch<>(sortedListOfEvenLength);

		assertEquals(5, search.indexOf(21));
	}


	/*******************************************************************
	 * Question 6
	 ******************************************************************/
	@Test
	public void singleDigitsAreArmstrongNumbers() {
		int input = 5;

		assertTrue(evaluationService.isArmstrongNumber(input));
	}

	@Test
	public void noTwoDigitArmstrongNumbers() {
		int input = 10;

		assertFalse(evaluationService.isArmstrongNumber(input));
	}

	@Test
	public void threeDigitNumberIsArmstrongNumber() {
		int input = 153;

		assertTrue(evaluationService.isArmstrongNumber(input));
	}

	@Test
	public void threeDigitNumberIsNotArmstrongNumber() {
		int input = 100;

		assertFalse(evaluationService.isArmstrongNumber(input));
	}

	@Test
	public void fourDigitNumberIsArmstrongNumber() {
		int input = 9474;

		assertTrue(evaluationService.isArmstrongNumber(input));
	}

	/*******************************************************************
	 * Question 7
	 ******************************************************************/

	@Test
	public void testPrimeNumber() {
		assertEquals(Collections.singletonList(2L), evaluationService.calculatePrimeFactorsOf(2L));
	}

	@Test
	public void testSquareOfAPrime() {
		assertEquals(Arrays.asList(3L, 3L), evaluationService.calculatePrimeFactorsOf(9L));
	}

	@Test
	public void testCubeOfAPrime() {
		assertEquals(Arrays.asList(2L, 2L, 2L), evaluationService.calculatePrimeFactorsOf(8L));
	}

	@Test
	public void testProductOfPrimesAndNonPrimes() {
		assertEquals(Arrays.asList(2L, 2L, 3L), evaluationService.calculatePrimeFactorsOf(12L));
	}

	@Test
	public void testProductOfPrimes() {
		assertEquals(Arrays.asList(5L, 17L, 23L, 461L), evaluationService.calculatePrimeFactorsOf(901255L));
	}

	/*******************************************************************
	 * Question 8
	 ******************************************************************/

	@Test
	public void testEncodeYes() {
		assertEquals("bvh", EvaluationService.AtbashCipher.encode("yes"));
	}

	@Test
	public void testEncodeOmgInCapital() {
		assertEquals("lnt", EvaluationService.AtbashCipher.encode("OMG"));
	}

	@Test
	public void testEncodeMindBlowingly() {
		assertEquals("nrmwy oldrm tob", EvaluationService.AtbashCipher.encode("mindblowingly"));
	}

	@Test
	public void testEncodeNumbers() {
		assertEquals("gvhgr mt123 gvhgr mt", EvaluationService.AtbashCipher.encode("Testing,1 2 3, testing."));
	}

	@Test
	public void testEncodeDeepThought() {
		assertEquals("gifgs rhurx grlm", EvaluationService.AtbashCipher.encode("Truth is fiction."));
	}

	@Test
	public void testEncodeAllTheLetters() {
		assertEquals("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt",
				EvaluationService.AtbashCipher.encode("The quick brown fox jumps over the lazy dog."));
	}

	/*******************************************************************
	 * Question 9
	 ******************************************************************/
	@Test
	public void testDecodeExercism() {
		assertEquals("exercism", EvaluationService.AtbashCipher.decode("vcvix rhn"));
	}

	@Test
	public void testDecodeASentence() {
		assertEquals("anobstacleisoftenasteppingstone",
				EvaluationService.AtbashCipher.decode("zmlyh gzxov rhlug vmzhg vkkrm thglm v"));
	}

	@Test
	public void testDecodeNumbers() {
		assertEquals("testing123testing", EvaluationService.AtbashCipher.decode("gvhgr mt123 gvhgr mt"));
	}

	@Test
	public void testDecodeAllTheLetters() {
		assertEquals("thequickbrownfoxjumpsoverthelazydog",
				EvaluationService.AtbashCipher.decode("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt"));
	}

	/*******************************************************************
	 * Question 10
	 ******************************************************************/
	@Test
	public void testSingleAddition1() {
		assertEquals(2, evaluationService.solveWordProblem("What is 1 plus 1?"));
	}

	@Test
	public void testSingleAdditionWithNegativeNumbers() {
		assertEquals(-11, evaluationService.solveWordProblem("What is -1 plus -10?"));
	}

	@Test
	public void testSingleSubtraction() {
		assertEquals(16, evaluationService.solveWordProblem("What is 4 minus -12?"));
	}

	@Test
	public void testSingleMultiplication() {
		assertEquals(-75, evaluationService.solveWordProblem("What is -3 multiplied by 25?"));
	}

	@Test
	public void testSingleDivision() {
		assertEquals(-11, evaluationService.solveWordProblem("What is 33 divided by -3?"));
	}

}
