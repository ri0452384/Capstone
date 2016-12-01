package com.mymath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class LargerIntegerTest {

	@Test
	public void testConstructor() throws Exception {
		try {
			LargerInteger notAnInteger = new LargerInteger("xas1k2l1");
			fail(notAnInteger + " is not a valid integer");
		} catch (IllegalArgumentException ex) {
			assertEquals("String value xas1k2l1 is not a valid integer",ex.getMessage());
		}
		
	}

	@Test
	public void testIsNegative() throws Exception {
		LargerInteger first = new LargerInteger(
				"-555555555555555555555555555555555555555555555555555555555555555555555555551");

		assertTrue(first.isNegative());
		assertEquals("-555555555555555555555555555555555555555555555555555555555555555555555555551", first.toString());
	}

	@Test
	public void testSubtract() throws Exception {
		LargerInteger minuend = new LargerInteger(
				"555555555555555555555555555555555555555555555555555555555555555555555555553");
		LargerInteger subtrahend = new LargerInteger(
				"444444444444444444444444444444444444444444444444444444444444444444444444442");

		assertEquals("111111111111111111111111111111111111111111111111111111111111111111111111111",
				minuend.subtract(subtrahend).toString());

		minuend = new LargerInteger("6569");
		subtrahend = new LargerInteger("4321");
		assertEquals("2248", minuend.subtract(subtrahend).toString());

		minuend = new LargerInteger("6569");
		subtrahend = new LargerInteger("321");
		assertEquals("6248", minuend.subtract(subtrahend).toString());

		minuend = new LargerInteger("6569");
		subtrahend = new LargerInteger("-4321");
		assertEquals("10890", minuend.subtract(subtrahend).toString());

		minuend = new LargerInteger("-6569");
		subtrahend = new LargerInteger("-321");
		assertEquals("-6248", minuend.subtract(subtrahend).toString());

		minuend = new LargerInteger("-321");
		subtrahend = new LargerInteger("6569");
		assertEquals("-6890", minuend.subtract(subtrahend).toString());
		
		minuend = new LargerInteger("-6569");
		subtrahend = new LargerInteger("-32100");
		assertEquals("25531", minuend.subtract(subtrahend).toString());

		minuend = new LargerInteger("4321");
		subtrahend = new LargerInteger("6569");
		assertEquals("-2248", minuend.subtract(subtrahend).toString());

		minuend = new LargerInteger("10000000");
		subtrahend = new LargerInteger("6569");
		assertEquals("9993431", minuend.subtract(subtrahend).toString());

		minuend = new LargerInteger("11111111111111111111111111111111111");
		subtrahend = new LargerInteger("6569");
		assertEquals("11111111111111111111111111111104542", minuend.subtract(subtrahend).toString());
	}

	@Test
	public void testIsGreaterThan() throws Exception {
		LargerInteger first = new LargerInteger("6569");
		LargerInteger second = new LargerInteger("4321");
		assertTrue(first.isGreaterThan(second));

		first = new LargerInteger("6569");
		second = new LargerInteger("-4321");
		assertTrue(first.isGreaterThan(second));

		first = new LargerInteger("-6569");
		second = new LargerInteger("4321");
		assertFalse(first.isGreaterThan(second));

		first = new LargerInteger("669");
		second = new LargerInteger("4321");
		assertFalse(first.isGreaterThan(second));

		first = new LargerInteger(7669L);
		assertTrue(first.isGreaterThan(421L));

		first = new LargerInteger("-7669");
		second = new LargerInteger("-4211");
		assertFalse(first.isGreaterThan(second));

		first = new LargerInteger("-769");
		second = new LargerInteger("-4211");
		assertTrue(first.isGreaterThan(second));
		
		first = new LargerInteger("-4211");
		second = new LargerInteger("-7692");
		assertTrue(first.isGreaterThan(second));

		first = new LargerInteger("-7169");
		second = new LargerInteger("-411");
		assertFalse(first.isGreaterThan(second));

		first = new LargerInteger("7777777777777777777777777777777777775");
		second = new LargerInteger("7777777777777777777777777777777777774");
		assertTrue(first.isGreaterThan(second));

		first = new LargerInteger("7777777777777777777777777777777777775");
		second = new LargerInteger("7777777777777777777777777777777777775");
		assertFalse(first.isGreaterThan(second));

		first = new LargerInteger("-7777777777777777777777777777777777775");
		second = new LargerInteger("-7777777777777777777777777777777777774");
		assertFalse(first.isGreaterThan(second));
	}

	@Test
	public void testIsGreaterThanOrEqualTo() throws Exception {
		LargerInteger first = new LargerInteger("6569");
		LargerInteger second = new LargerInteger("4321");
		assertTrue(first.isGreaterThan(second));

		first = new LargerInteger("6569");
		second = new LargerInteger("-4321");
		assertTrue(first.isGreaterThanOrEqualTo(second));

		first = new LargerInteger("-6569");
		second = new LargerInteger("4321");
		assertFalse(first.isGreaterThanOrEqualTo(second));

		first = new LargerInteger("669");
		second = new LargerInteger("4321");
		assertFalse(first.isGreaterThanOrEqualTo(second));

		first = new LargerInteger("7669");
		second = new LargerInteger("421");
		assertTrue(first.isGreaterThanOrEqualTo(second));

		first = new LargerInteger("-7669");
		second = new LargerInteger("-4211");
		assertFalse(first.isGreaterThanOrEqualTo(second));

		first = new LargerInteger("-769");
		second = new LargerInteger("-4211");
		assertTrue(first.isGreaterThanOrEqualTo(second));

		first = new LargerInteger("-7169");
		second = new LargerInteger("-411");
		assertFalse(first.isGreaterThanOrEqualTo(second));

		first = new LargerInteger("7777777777777777777777777777777777775");
		second = new LargerInteger("7777777777777777777777777777777777774");
		assertTrue(first.isGreaterThanOrEqualTo(second));

		first = new LargerInteger("7777777777777777777777777777777777775");
		second = new LargerInteger("7777777777777777777777777777777777775");
		assertTrue(first.isGreaterThanOrEqualTo(second));

		first = new LargerInteger("-7777777777777777777777777777777777775");
		second = new LargerInteger("-7777777777777777777777777777777777774");
		assertFalse(first.isGreaterThanOrEqualTo(second));
	}

	@Test
	public void testIsLessThan() throws Exception {
		LargerInteger first = new LargerInteger("6569");
		LargerInteger second = new LargerInteger("4321");
		assertFalse(first.isLessThan(second));

		first = new LargerInteger("6569");
		second = new LargerInteger("-4321");
		assertFalse(first.isLessThan(second));

		first = new LargerInteger("-6569");
		second = new LargerInteger("4321");
		assertTrue(first.isLessThan(second));

		first = new LargerInteger("669");
		assertTrue(first.isLessThan(4321L));

		first = new LargerInteger("7669");
		second = new LargerInteger("421");
		assertFalse(first.isLessThan(second));

		first = new LargerInteger("-7669");
		second = new LargerInteger("-4211");
		assertTrue(first.isLessThan(second));

		first = new LargerInteger("-769");
		second = new LargerInteger("-4211");
		assertFalse(first.isLessThan(second));

		first = new LargerInteger("-7169");
		second = new LargerInteger(-411L);
		assertTrue(first.isLessThan(second));
		
		first = new LargerInteger("-7169");
		assertTrue(first.isLessThan(-411L));

		first = new LargerInteger("7777777777777777777777777777777777775");
		second = new LargerInteger("7777777777777777777777777777777777775");
		assertFalse(first.isLessThan(second));
	}

	@Test
	public void testIsLessThanOrEqualTo() throws Exception {
		LargerInteger first = new LargerInteger("6569");
		LargerInteger second = new LargerInteger("4321");
		assertFalse(first.isLessThanOrEqualTo(second));

		first = new LargerInteger("6569");
		second = new LargerInteger("-4321");
		assertFalse(first.isLessThanOrEqualTo(second));

		first = new LargerInteger("-6569");
		second = new LargerInteger("4321");
		assertTrue(first.isLessThanOrEqualTo(second));

		first = new LargerInteger("669");
		second = new LargerInteger("4321");
		assertTrue(first.isLessThanOrEqualTo(second));

		first = new LargerInteger("7669");
		second = new LargerInteger("421");
		assertFalse(first.isLessThanOrEqualTo(second));

		first = new LargerInteger("-7669");
		second = new LargerInteger("-4211");
		assertTrue(first.isLessThanOrEqualTo(second));

		first = new LargerInteger("-769");
		second = new LargerInteger("-4211");
		assertFalse(first.isLessThanOrEqualTo(second));

		first = new LargerInteger("-7169");
		second = new LargerInteger("-411");
		assertTrue(first.isLessThanOrEqualTo(second));

		first = new LargerInteger("7777777777777777777777777777777777775");
		second = new LargerInteger("7777777777777777777777777777777777775");
		assertTrue(first.isLessThanOrEqualTo(second));
	}

	@Test
	public void testAdd() throws Exception {
		LargerInteger first = new LargerInteger(
				"555555555555555555555555555555555555555555555555555555555555555555555555553");
		LargerInteger second = new LargerInteger(
				"444444444444444444444444444444444444444444444444444444444444444444444444442");

		assertEquals("999999999999999999999999999999999999999999999999999999999999999999999999995",
				first.add(second).toString());

		first = new LargerInteger("6569");
		second = new LargerInteger("4321");
		assertEquals("10890", first.add(second).toString());

		first = new LargerInteger("6569");
		second = new LargerInteger("321");
		assertEquals("6890", first.add(second).toString());

		first = new LargerInteger("6569");
		second = new LargerInteger("-4321");
		assertEquals("2248", first.add(second).toString());

		first = new LargerInteger("-6569");
		second = new LargerInteger("-321");
		assertEquals("-6890", first.add(second).toString());

		first = new LargerInteger("-321");
		second = new LargerInteger("6569");
		assertEquals("6248", first.add(second).toString());

		first = new LargerInteger("4321");
		second = new LargerInteger("6569");
		assertEquals("10890", first.add(second).toString());

		first = new LargerInteger("131380");
		second = new LargerInteger("1970700");
		assertEquals("2102080", first.add(second).toString());

		first = new LargerInteger("4321");
		second = new LargerInteger("-6569");
		assertEquals("-2248", first.add(second).changeToNegative().toString());
	}

	@Test
	public void testToLongValue() throws Exception {
		LargerInteger toLongValue = new LargerInteger("9223372036854775807");
		assertEquals(Long.valueOf(Long.MAX_VALUE), toLongValue.toLongValue());

		toLongValue = new LargerInteger("-9223372036854775808");
		assertEquals(Long.valueOf(Long.MIN_VALUE), toLongValue.toLongValue());

		try {
			toLongValue = new LargerInteger("-9223372036854775809");
			toLongValue.toLongValue();
			fail("Illegal Argument exception is expected");
		} catch (IllegalArgumentException ex) {
			assertEquals("-9223372036854775809 has a value that is beyond the range of Long", ex.getMessage());
		}

		try {
			toLongValue = new LargerInteger("9223372036854775808");
			toLongValue.toLongValue();
			fail("Illegal Argument exception is expected");
		} catch (IllegalArgumentException ex) {
			assertEquals("9223372036854775808 has a value that is beyond the range of Long", ex.getMessage());
		}
	}

	@Test
	public void testMultiply() throws Exception {
		LargerInteger multiplicand = new LargerInteger("6569");
		LargerInteger multiplier = new LargerInteger("4325");
		assertEquals("28410925", multiplicand.multiply(multiplier).toString());

		multiplicand = new LargerInteger("-6569");
		multiplier = new LargerInteger("4325");
		assertEquals("-28410925", multiplicand.multiply(multiplier).toString());

		multiplicand = new LargerInteger("6569");
		multiplier = new LargerInteger("-4325");
		assertEquals("-28410925", multiplicand.multiply(multiplier).toString());

		multiplicand = new LargerInteger("-6569");
		multiplier = new LargerInteger(new LargerInteger("-4325"));
		assertEquals("28410925", multiplicand.multiply(multiplier).toString());
		
		multiplicand = new LargerInteger("569");
		multiplier = new LargerInteger(new LargerInteger("4325"));
		assertEquals("2460925", multiplicand.multiply(multiplier).toString());

		multiplicand = new LargerInteger("-656932131231231231255234534827491274892398472389472");
		multiplier = new LargerInteger("43254234235623456252163478901289472389412678");
		assertEquals("-28415096281183003068624557751108739872351039168723469240670649172482551474612848215077350526016",
				multiplicand.multiply(multiplier).toString());
	}
	
	@Test
	public void testDivide() throws Exception {
		LargerInteger dividend = new LargerInteger("4325");
		LargerInteger divisor = new LargerInteger("4325");
		assertEquals("1", dividend.divide(divisor).toString());
		
		dividend = new LargerInteger("8649");
		divisor = new LargerInteger("4325");
		assertEquals("1", dividend.divide(divisor).toString());

		dividend = new LargerInteger("4325");
		divisor = new LargerInteger("8650");
		assertEquals("0", dividend.divide(divisor).toString());
		
		dividend = new LargerInteger("3492048");
		divisor = new LargerInteger("456");
		assertEquals("7658", dividend.divide(divisor).toString());
		
		dividend = new LargerInteger("29476790171");
		divisor = new LargerInteger("7654321");
		assertEquals("3851", dividend.divide(divisor).toString());
		
		dividend = new LargerInteger("29476790171");
		divisor = new LargerInteger("-7654321");
		assertEquals("-3851", dividend.divide(divisor).toString());
		
		dividend = new LargerInteger("-29476790171");
		divisor = new LargerInteger("7654321");
		assertEquals("-3851", dividend.divide(divisor).toString());

		dividend = new LargerInteger("-29476790171");
		divisor = new LargerInteger("-7654321");
		assertEquals("3851", dividend.divide(divisor).toString());
		
		dividend = new LargerInteger("29476795172");
		divisor = new LargerInteger("7654321");
		assertEquals("3851", dividend.divide(divisor).toString());
		
		dividend = new LargerInteger("29476795172");
		assertEquals("3851", dividend.divide(7654321).toString());

		dividend = new LargerInteger("28415096281183003068624557751108739872351039168723469240670649172482551474612848215077350526016");
		divisor = new LargerInteger("43254234235623456252163478901289472389412678");
		assertEquals("656932131231231231255234534827491274892398472389472", dividend.divide(divisor).toString());
		
		dividend = new LargerInteger("-28415096281183003068624557751108739872351039168723469240670649172482551474612848215077350526016");
		divisor = new LargerInteger("43254234235623456252163478901289472389412678");
		assertEquals("-656932131231231231255234534827491274892398472389472", dividend.divide(divisor).toString());
		
		dividend = new LargerInteger("28415096281183003068624557751108739872351039168723469240670649172482551474612848215077350526016");
		divisor = new LargerInteger("-43254234235623456252163478901289472389412678");
		assertEquals("-656932131231231231255234534827491274892398472389472", dividend.divide(divisor).toString());
		
		dividend = new LargerInteger("-28415096281183003068624557751108739872351039168723469240670649172482551474612848215077350526016");
		divisor = new LargerInteger("-43254234235623456252163478901289472389412678");
		assertEquals("656932131231231231255234534827491274892398472389472", dividend.divide(divisor).toString());
	}
	
	@Test
	public void testHashCodeAndEquals() throws Exception {
		Set<LargerInteger> set = new HashSet<>();
		set.add(new LargerInteger("6569"));
		set.add(new LargerInteger(321L));
		set.add(new LargerInteger("6569"));
		set.add(new LargerInteger(new LargerInteger(Long.MAX_VALUE)));
		assertEquals(3, set.size());
		
		LargerInteger thisLargerInteger = new LargerInteger(Long.MAX_VALUE);
		assertTrue(thisLargerInteger.equals(thisLargerInteger));
		assertFalse(thisLargerInteger.equals(null));
		assertFalse(thisLargerInteger.equals(new Long(1L)));
		assertFalse(thisLargerInteger.equals("just a string"));
		assertTrue(thisLargerInteger.equals(new Long(Long.MAX_VALUE)));
	}
	
	@Test
	public void testComparareTo() throws Exception {
		List<LargerInteger> list = new ArrayList<>();
		list.add(new LargerInteger("6569"));
		list.add(new LargerInteger(321L));
		list.add(new LargerInteger("65692378947238941241234124235253467547856472389"));
		list.add(new LargerInteger(new LargerInteger(Long.MAX_VALUE)));
		list.add(new LargerInteger(new LargerInteger(Long.MAX_VALUE)));
		list.add(new LargerInteger(new LargerInteger(Long.MIN_VALUE)));
		
		Collections.sort(list);
		assertTrue(list.get(0).equals(new LargerInteger(new LargerInteger(Long.MIN_VALUE))));
		assertTrue(list.get(1).equals(321L));
		assertTrue(list.get(2).equals(6569L));
		assertTrue(list.get(3).equals(Long.MAX_VALUE));
		assertTrue(list.get(4).equals(Long.MAX_VALUE));
		assertTrue(list.get(5).equals(new LargerInteger("65692378947238941241234124235253467547856472389")));
	}
}
