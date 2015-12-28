/*
 * Copyright (C) 2015, C. Ramakrishnan / Illposed Software.
 * All rights reserved.
 *
 * This code is licensed under the BSD 3-Clause license.
 * See file LICENSE (or LICENSE.html) for more information.
 */

package com.illposed.osc.argument.handler;

import com.illposed.osc.argument.OSCMidiMessage;
import com.illposed.osc.OSCParseException;
import com.illposed.osc.OSCSerializeException;
import java.io.IOException;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class MidiMessageArgumentHandlerTest {

	private static OSCMidiMessage reparse(final OSCMidiMessage orig)
			throws IOException, OSCSerializeException, OSCParseException
	{
		return ColorArgumentHandlerTest.reparse(
				MidiMessageArgumentHandler.INSTANCE,
				OSCMidiMessage.NUM_CONTENT_BYTES,
				orig);
	}

	private static void testReparse(final OSCMidiMessage orig)
			throws IOException, OSCSerializeException, OSCParseException
	{
		Assert.assertEquals(orig, reparse(orig));
	}

	@Test(expected = NullPointerException.class)
	public void testReparseNull() throws IOException, OSCSerializeException, OSCParseException {

		final OSCMidiMessage orig = null;
		Assert.assertEquals(orig, reparse(orig));
	}

	private static void testContent(final byte[] content) {
		OSCMidiMessage.valueOf(content);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContentBytesZero() {
		testContent(new byte[0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContentBytesOne() {
		testContent(new byte[1]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContentBytesTwo() {
		testContent(new byte[2]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContentBytesThree() {
		testContent(new byte[3]);
	}

	@Test
	public void testContentBytesfour() {
		testContent(new byte[4]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContentBytesFive() {
		testContent(new byte[5]);
	}

	@Test
	public void testReparseZeros() throws Exception {
		testReparse(OSCMidiMessage.valueOf(new byte[] {0, 0, 0, 0}));
	}

	@Test
	public void testReparsePositive() throws Exception {
		testReparse(OSCMidiMessage.valueOf(new byte[] {127, 127, 127, 127}));
	}

	@Test
	public void testReparseNegative() throws Exception {
		testReparse(OSCMidiMessage.valueOf(new byte[] {-128, -128, -128, -128}));
	}

	@Test
	public void testReparseRandom() throws Exception {

		final long contentRandomSeed = new Random().nextLong();
		System.out.println(MidiMessageArgumentHandlerTest.class.getSimpleName()
				+ "#testReparseRandom:contentRandomSeed: " + contentRandomSeed);
		final Random contentRandom = new Random(contentRandomSeed);
		final byte[] content = new byte[4];
		contentRandom.nextBytes(content);
		testReparse(OSCMidiMessage.valueOf(content));
	}
}