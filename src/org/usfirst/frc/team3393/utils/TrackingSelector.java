package org.usfirst.frc.team3393.utils;

/**
 * Enumerates the available tracking options.
 */
public enum TrackingSelector{
	LEFT(0),
	AVERAGE(1),
	RIGHT(2);

	private final int value;

	TrackingSelector(final int newValue) {
		value = newValue;
	}

	/**
	 * @return The numeric value of a definition.
	 */
	public int getValue() { return value; }
}