package com.mymath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * An Immutable Integer implementation that holds integer values that is way
 * larger than {@link Long#MAX_VALUE}.
 * 
 * @author Zainal Limpao
 *
 */
public class LargerInteger implements Comparable<LargerInteger> {

	private static final char NEGATIVE_SIGN_CHAR = '-';

	private boolean isNegative = false;

	private int[] intArr;

	/**
	 * The default constructor with a default value of 0.
	 * 
	 */
	public LargerInteger() {
		this(0);
	}

	/**
	 * The constructor supporting a long primitive type.
	 * 
	 * @param longVal
	 */
	public LargerInteger(long longVal) {
		this(String.valueOf(longVal));
	}

	/**
	 * The constructor for another {@link LargerInteger}.
	 * 
	 * @param itself
	 */
	public LargerInteger(LargerInteger itself) {
		this(itself.toString());
	}

	/**
	 * The base constructor that accepts String value.
	 * 
	 * @param stringVal
	 */
	public LargerInteger(String stringVal) {
		// Check first if string is an actual integer.
		if (!isNumber(stringVal.replace("-", ""))) {
			throw new IllegalArgumentException("String value " + stringVal + " is not a valid integer");
		}

		char[] charArray = stringVal.toCharArray();
		int indexOfLastDigit = 0;

		// Check if value is a negative integer.
		if (charArray[indexOfLastDigit] == NEGATIVE_SIGN_CHAR) {
			isNegative = true;
			indexOfLastDigit++;
		}

		intArr = new int[charArray.length - indexOfLastDigit];

		int index = 0;
		while (index < charArray.length - indexOfLastDigit) {
			intArr[index] = Character.getNumericValue(charArray[index + indexOfLastDigit]);
			index++;
		}
	}

	/**
	 * Check if string value is a real integer.
	 * 
	 * @param stringVal
	 * @return
	 */
	private boolean isNumber(String stringVal) {
		String regex = "\\d+";
		return stringVal.matches(regex);
	}

	/**
	 * Is negative?
	 * 
	 * @return
	 */
	public boolean isNegative() {
		return isNegative;
	}

	/**
	 * Since it is immutable, this will create a new instance that is positive.
	 * This is also similar to changing the value to absolute.
	 * 
	 * @return new instance of LargerInteger
	 */
	public LargerInteger changeToPositive() {
		if (isNegative()) {
			String newValue = this.toString().replace("-", "");
			return new LargerInteger(newValue);
		}

		return this;
	}

	/**
	 * Since it is immutable, this will create a new instance that is negative.
	 * 
	 * @return new instance of LargerInteger
	 */
	public LargerInteger changeToNegative() {
		if (!isNegative()) {
			StringBuilder sb = new StringBuilder(this.toString());
			sb.reverse().append("-").reverse();
			return new LargerInteger(sb.toString());
		}

		return this;
	}

	/**
	 * Since it is immutable, this will create a new instance which represents
	 * the sum of this instance and the addend.
	 * 
	 * @param addend
	 * @return new instance of LargerInteger
	 */
	public LargerInteger add(LargerInteger addend) {

		// X + (-Y)
		if (addend.isNegative() && !this.isNegative) {
			return this.subtract(addend.changeToPositive());
		}

		// -X + Y
		else if (!addend.isNegative() && this.isNegative) {
			return addend.subtract(this.changeToPositive());
		}

		// -X + (-Y)
		else if (addend.isNegative() && this.isNegative) {
			LargerInteger thisAddend = this.changeToPositive();
			return thisAddend.add(addend.changeToPositive()).changeToNegative();
		}

		// X + Y
		else {
			int[] addendArr = addend.intArr;

			int addendFirstDigitIndex = addend.lastDigitIndex();
			int thisFirstDigitIndex = this.lastDigitIndex();

			List<Integer> newSumList = new ArrayList<>();

			boolean addOneToNext = false;
			while (addendFirstDigitIndex != -1 && thisFirstDigitIndex != -1) {

				int addend1 = addendArr[addendFirstDigitIndex];
				int addend2 = this.intArr[thisFirstDigitIndex];

				int newSumForThisDigit = addend1 + addend2;
				if (addOneToNext) {
					addOneToNext = false;
					newSumForThisDigit++;
				}

				if (newSumForThisDigit >= 10) {
					newSumForThisDigit = newSumForThisDigit - 10;
					addOneToNext = true;
				}

				newSumList.add(newSumForThisDigit);
				addendFirstDigitIndex--;
				thisFirstDigitIndex--;
			}

			while (addendFirstDigitIndex != -1) {
				int carryOverDigit = addendArr[addendFirstDigitIndex];
				if (addOneToNext) {
					carryOverDigit++;
					addOneToNext = false;
				}

				newSumList.add(carryOverDigit);
				addendFirstDigitIndex--;
			}

			while (thisFirstDigitIndex != -1) {
				int carryOverDigit = this.intArr[thisFirstDigitIndex];
				if (addOneToNext) {
					carryOverDigit++;
					addOneToNext = false;
				}

				newSumList.add(carryOverDigit);

				thisFirstDigitIndex--;
			}

			if (addOneToNext) {
				newSumList.add(1);
			}

			Collections.reverse(newSumList);
			return convertListOfIntegersToLargerInteger(newSumList);
		}
	}

	public int lastDigitIndex() {
		return this.intArr.length - 1;
	}

	/**
	 * Check if this {@link LargerInteger} is lesser in value than the given
	 * LargerInteger.
	 * 
	 * @param li
	 * @return true or false
	 */
	public boolean isLessThan(LargerInteger li) {
		return !this.equals(li) && !isGreaterThan(li);
	}

	/**
	 * Check if this {@link LargerInteger} is lesser in value than the given
	 * Long value.
	 * 
	 * @param li
	 * @return true or false
	 */
	public boolean isLessThan(Long longVal) {
		return !isGreaterThan(new LargerInteger(longVal));
	}

	/**
	 * Check if this {@link LargerInteger} is lesser or equal in value than the
	 * given LargerInteger.
	 * 
	 * @param li
	 * @return true or false
	 */
	public boolean isLessThanOrEqualTo(LargerInteger li) {
		return (this.equals(li) || !isGreaterThan(li));
	}

	/**
	 * Check if this {@link LargerInteger} is lesser or equal in value than the
	 * given Long value.
	 * 
	 * @param li
	 * @return true or false
	 */
	public boolean isLessThanOrEqualTo(Long longVal) {
		LargerInteger other = new LargerInteger(longVal);
		return (this.equals(other) || !isGreaterThan(other));
	}

	/**
	 * Check if this {@link LargerInteger} is greater or equal in value than the
	 * given Long value.
	 * 
	 * @param longVal
	 * @return true or false
	 */
	public boolean isGreaterThanOrEqualTo(Long longVal) {
		return this.isGreaterThanOrEqualTo(new LargerInteger(longVal));
	}

	/**
	 * Check if this {@link LargerInteger} is greater or equal in value than the
	 * given LargerInteger.
	 * 
	 * @param longVal
	 * @return true or false
	 */
	public boolean isGreaterThanOrEqualTo(LargerInteger li) {
		return (this.equals(li) || this.isGreaterThan(li));
	}

	/**
	 * Check if this {@link LargerInteger} is greater in value than the given
	 * Long value.
	 * 
	 * @param longVal
	 * @return true or false
	 */
	public boolean isGreaterThan(Long longVal) {
		return this.isGreaterThan(new LargerInteger(longVal));
	}

	/**
	 * Check if this {@link LargerInteger} is greater in value than the given
	 * LargerInteger.
	 * 
	 * @param li
	 * @return true or false
	 */
	public boolean isGreaterThan(LargerInteger li) {
		boolean isGreaterThan = false;
		if (li.isNegative() && !this.isNegative()) {
			isGreaterThan = true;
		} else if (this.isNegative() && !li.isNegative()) {
			isGreaterThan = false;
		} else if (this.isNegative() && li.isNegative()) {
			int myDigits = this.intArr.length;
			int liDigits = li.intArr.length;

			if (liDigits > myDigits) {
				isGreaterThan = true;
			} else if (myDigits > liDigits) {
				isGreaterThan = false;
			} else if (myDigits == liDigits) {
				for (int i = 0; i < myDigits; i++) {
					if (this.intArr[i] == li.intArr[i]) {
						continue;
					}
					else if (this.intArr[i] < li.intArr[i]) {
						isGreaterThan = true;
						break;
					}
					else {
						break;
					}
				}
			}
		} else if (!this.isNegative() && !li.isNegative()) {
			int myDigits = this.intArr.length;
			int liDigits = li.intArr.length;

			if (liDigits < myDigits) {
				isGreaterThan = true;
			} else if (myDigits < liDigits) {
				isGreaterThan = false;
			} else if (myDigits == liDigits) {
				for (int i = 0; i < myDigits; i++) {
					if (this.intArr[i] == li.intArr[i]) {
						continue;
					}
					else if (this.intArr[i] > li.intArr[i]) {
						isGreaterThan = true;
						break;
					} 
					else {
						break;
					}
				}
			}
		}

		return isGreaterThan;
	}

	public Long toLongValue() {
		Long longValue = 0L;
		if (this.isGreaterThanOrEqualTo(Long.MIN_VALUE) && this.isLessThanOrEqualTo(Long.MAX_VALUE)) {
			longValue = new Long(this.toString());
		} else {
			throw new IllegalArgumentException(this + " has a value that is beyond the range of Long");
		}

		return longValue;
	}

	/**
	 * Since it is immutable, this will create a new instance which represents
	 * the difference between this instance and the subtrahend.
	 * 
	 * @param subtrahend
	 * @return new instance of LargerInteger
	 */
	public LargerInteger subtract(LargerInteger subtrahend) {
		LargerInteger absMinuend = this.changeToPositive();
		LargerInteger absSubtrahend = subtrahend.changeToPositive();

		// Minuend is positive, Subtrahend is negative
		// X - (-Y)
		// X + Y
		if (subtrahend.isNegative() && !this.isNegative) {
			return this.add(subtrahend.changeToPositive());
		}

		// Minuend is negative and subtrahend is positive
		// - X - Y
		else if (!subtrahend.isNegative() && this.isNegative) {
			return absMinuend.add(absSubtrahend).changeToNegative();
		}

		// Minuend is negative and subtrahend is negative
		// - X - (-Y)
		// - X + Y
		// Y - X
		else if (subtrahend.isNegative() && this.isNegative) {
			if (absSubtrahend.isGreaterThan(absMinuend)) {
				return absSubtrahend.subtract(absMinuend);
			} else {
				return absMinuend.subtract(absSubtrahend).changeToNegative();
			}
		}

		// Both are positive
		else {
			// if subtrahend is larger than the minuend
			if (absSubtrahend.isGreaterThan(absMinuend)) {
				return absSubtrahend.subtract(absMinuend).changeToNegative();
			}

			int[] subtrahendArr = subtrahend.intArr;

			int subtrahendFirstDigitIndex = subtrahend.lastDigitIndex();
			int thisFirstDigitIndex = this.lastDigitIndex();

			List<Integer> newDifferenceList = new ArrayList<>();

			boolean borrowOneFromTheNext = false;
			while (subtrahendFirstDigitIndex != -1 && thisFirstDigitIndex != -1) {

				int thisDigitMinuend = this.intArr[thisFirstDigitIndex];
				if (borrowOneFromTheNext) {
					thisDigitMinuend--;
					borrowOneFromTheNext = false;
				}

				int thisDigitSubtrahend = subtrahendArr[subtrahendFirstDigitIndex];

				if (thisDigitSubtrahend > thisDigitMinuend) {
					thisDigitMinuend = thisDigitMinuend + 10;
					borrowOneFromTheNext = true;
				}

				newDifferenceList.add(thisDigitMinuend - thisDigitSubtrahend);
				subtrahendFirstDigitIndex--;
				thisFirstDigitIndex--;
			}

			while (thisFirstDigitIndex != -1) {
				int carryOverDigit = this.intArr[thisFirstDigitIndex];
				if (borrowOneFromTheNext) {
					if (carryOverDigit == 0) {
						carryOverDigit = 9;
						borrowOneFromTheNext = true;
					} else {
						carryOverDigit--;
						borrowOneFromTheNext = false;
					}
				}

				newDifferenceList.add(carryOverDigit);
				thisFirstDigitIndex--;
			}

			Collections.reverse(newDifferenceList);

			// Remove all zeroes on the left side
			if (newDifferenceList.get(0) == 0) {
				newDifferenceList.remove(0);
			}
			return convertListOfIntegersToLargerInteger(newDifferenceList);
		}
	}

	/**
	 * Since it is immutable, this will create a new instance which represents
	 * the product of this instance and the multiplier.
	 * 
	 * @param multiplier
	 * @return new instance of LargerInteger
	 */
	public LargerInteger multiply(LargerInteger multiplier) {
		boolean isProductNegative = false;
		if ((this.isNegative && !multiplier.isNegative) || (!this.isNegative && multiplier.isNegative)) {
			isProductNegative = true;
		}

		List<LargerInteger> standingProducts = new ArrayList<>();

		int digits = 0;
		for (int i = multiplier.intArr.length - 1; i >= 0; i--) {
			List<Integer> results = new ArrayList<>();
			int thisDigitMultiplier = multiplier.intArr[i];
			int carryOver = 0;
			for (int j = this.intArr.length - 1; j >= 0; j--) {
				int thisDigitMultiplicand = this.intArr[j];

				int result = carryOver + thisDigitMultiplier * thisDigitMultiplicand;
				carryOver = result / 10;
				int thisDigitResult = result - (carryOver * 10);
				results.add(thisDigitResult);

				if (j == 0) {
					results.add(carryOver);
				}
			}

			Collections.reverse(results);
			for (int k = 0; k < digits; k++) {
				results.add(0);
			}

			standingProducts.add(convertListOfIntegersToLargerInteger(results));
			digits++;
		}

		LargerInteger standingSum = new LargerInteger();
		for (LargerInteger standingProduct : standingProducts) {
			standingSum = standingSum.add(standingProduct);
		}

		if (isProductNegative) {
			standingSum = standingSum.changeToNegative();
		}

		return standingSum;
	}

	/**
	 * Since it is immutable, this will create a new instance which represents
	 * the product of this instance and the multiplier in long format.
	 * 
	 * @param multiplier
	 * @return new instance of LargerInteger
	 */
	public LargerInteger multiply(long multiplier) {
		return this.multiply(new LargerInteger(multiplier));
	}
	
	/**
	 * Since it is immutable, this will create a new instance which represents
	 * the quotient of this instance and the divisor in long format.
	 * 
	 * @param multiplier
	 * @return new instance of LargerInteger
	 */
	public LargerInteger divide(long divisor) {
		return this.divide(new LargerInteger(divisor));
	}

	/**
	 * Since it is immutable, this will create a new instance which represents
	 * the quotient of this instance and the divisor.
	 * 
	 * @param divisor
	 * @return new instance of LargerInteger
	 */
	public LargerInteger divide(LargerInteger divisor) {
		LargerInteger absDividend = this.changeToPositive();
		LargerInteger absDivisor = divisor.changeToPositive();

		LargerInteger quotient = null;

		// If divisor > dividend regardless of the sign
		if (absDivisor.isGreaterThan(absDividend)) {
			quotient = new LargerInteger(0);
		}

		// If divisor is exactly equal to the dividend or quotient is less than 2
		// Quotient in between 1.0 to 2.0 is considered as 1 when dividing 2 integers (without casting to double)
		else if (absDivisor.equals(absDividend) || absDivisor.multiply(2L).isGreaterThan(absDividend)) {
			quotient = new LargerInteger(1);
		}

		else {
			// List of digits of the resulting LargerInteger
			List<Integer> resultsList = new ArrayList<>();

			// Divisor can only take values within the Long object range.
			int dividendDigitPointer = absDivisor.intArr.length - 1;

			List<Integer> dividendToDivideList = new ArrayList<>();
			for (int i = 0; i <= dividendDigitPointer; i++) {
				dividendToDivideList.add(absDividend.intArr[i]);
			}

			while (true) {
				// Call the recursive method that solves for the partial result
				ResultsAndPointerPlaceHolder partialResultPointer = solvePartialQuotients(absDividend, absDivisor, dividendDigitPointer, dividendToDivideList);
				
				// Partial result is just between 1 to 9
				int partialResult = partialResultPointer.getPartialResult().intValue();
				
				// Add the partial result into the list
				// This represents the digits of the outstanding quotient
				resultsList.add(partialResult);

				// Partial Result x the Divisor
				LargerInteger partialProduct = new LargerInteger(new Long(partialResult)).multiply(absDivisor);
				LargerInteger partialDifference = convertListOfIntegersToLargerInteger(dividendToDivideList).subtract(partialProduct);
				dividendDigitPointer = partialResultPointer.getDividendPointer();

				// Move the pointer to the next
				dividendDigitPointer++;
				// Break the loop if dividendDigitPointer reaches the end most digit
				if (dividendDigitPointer == (absDividend.intArr.length)) {
					break;
				}

				dividendToDivideList = partialDifference.convertDigitsToList();
				dividendToDivideList.add(absDividend.intArr[dividendDigitPointer]);
			}

			quotient = convertListOfIntegersToLargerInteger(resultsList);
		}

		// Resolve the signs involved
		boolean isQuotientNegative = ((this.isNegative && !divisor.isNegative)
				|| (!this.isNegative && divisor.isNegative));
		if (isQuotientNegative) {
			quotient = quotient.changeToNegative();
		}

		return quotient;
	}

	// Converts the digits of this LargerInteger into a List of integer { X... }
	private List<Integer> convertDigitsToList() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < this.intArr.length; i++) {
			list.add(this.intArr[i]);
		}
		return list;
	}

	// Solve for the quotient per N number of digits starting from the left going all the way to the right
	private ResultsAndPointerPlaceHolder solvePartialQuotients(LargerInteger absDividend, LargerInteger divisor,
			int dividendPointer, List<Integer> dividendToDivideList) {
		Long partialResult = solveForPartialResult(convertListOfIntegersToLargerInteger(dividendToDivideList), divisor);
		if (partialResult == 0) {
			dividendPointer++;
			dividendToDivideList.add(absDividend.intArr[dividendPointer]);
			// Recursive until partialResult is not zero (0)
			// Bring down the next digit
			return solvePartialQuotients(absDividend, divisor, dividendPointer, dividendToDivideList);
		}
		// Contain both the pointer and the partial result into a place holder object
		return new ResultsAndPointerPlaceHolder(dividendPointer, partialResult);
	}
	
	// Try if partialDividend can be divided by the divisor, with possible result of only 0 to 9
	private Long solveForPartialResult(LargerInteger partialDividend, LargerInteger divisor) {
		Long partialResult = 0L;
		while(partialResult != 10L) {
			LargerInteger difference = partialDividend.subtract(divisor.multiply(partialResult));
			if (divisor.isGreaterThan(difference)) {
				break;
			}
			partialResult++;
		}
		return partialResult;
	}

	/**
	 * A container data structure that will hold the pointer on what digit from
	 * the dividend we are currently looking at.
	 */
	private class ResultsAndPointerPlaceHolder {
		private int dividendPointer;
		private Long partialResult;

		public ResultsAndPointerPlaceHolder(int dividendPointer, Long partialResult) {
			this.dividendPointer = dividendPointer;
			this.partialResult = partialResult;
		}

		public int getDividendPointer() {
			return dividendPointer;
		}

		public Long getPartialResult() {
			return partialResult;
		}
	}

	/**
	 * A utility that will convert a list of integers to a LargerInteger. Sample
	 * a list of {1, 2, 3} will become LargerInteger(123).
	 * 
	 * @param list
	 * @return a new instance of LargerInteger
	 */
	private static LargerInteger convertListOfIntegersToLargerInteger(List<Integer> list) {
		StringBuilder sb = new StringBuilder();
		for (Integer i : list) {
			sb.append(i);
		}
		return new LargerInteger(sb.toString());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (isNegative) {
			sb.append("-");
		}

		for (Integer intVal : intArr) {
			sb.append(intVal);
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(intArr);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) {
			if (Long.class.isAssignableFrom(obj.getClass())) {
				return this.equals(new LargerInteger((Long) obj));
			}
			return false;
		}
		LargerInteger other = (LargerInteger) obj;
		if (!Arrays.equals(intArr, other.intArr))
			return false;
		return true;
	}

	@Override
	public int compareTo(LargerInteger other) {
		int compareTo;
		if (this.equals(other)) {
			compareTo = 0;
		} else if (this.isGreaterThan(other)) {
			compareTo = 1;
		} else {
			compareTo = -1;
		}
		return compareTo;
	}
}
