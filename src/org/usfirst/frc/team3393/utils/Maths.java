package org.usfirst.frc.team3393.utils;

import edu.wpi.first.wpilibj.Encoder;

public class Maths {
	
	private static double teethCimGear = 12;
	private static double teethWheelGear = 26;
	
	public static double getEncoderInches(Encoder e) {
		double encoderDistance = e.getDistance();
		return (encoderDistance/(teethWheelGear/teethCimGear))*(6.0*Math.PI);
	}
	
	public static double getEncoderFeet(Encoder e) {
		double feet = getEncoderInches(e);
		return feet/12.0;
	}

}
