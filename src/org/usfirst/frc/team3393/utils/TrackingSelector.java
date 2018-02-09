package org.usfirst.frc.team3393.utils;

public enum TrackingSelector{
	LEFT(0),
	AVERAGE(1),
	RIGHT(2);

	private final int value;

	TrackingSelector(final int newValue) {
		value = newValue;
	}

	public int getValue() { return value; }
}