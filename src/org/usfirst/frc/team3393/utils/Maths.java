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
	
	//can is 4.875in, empirically derived bs.
	public static double getObjectDistance(double pixels, double actualHeight) {
		double doNum = (double) pixels/actualHeight;
		//double calculated = (0.24*(doNum*doNum))-13.12*(doNum)+236.1;
		double calculated  = Math.pow(doNum, -1.1087957217319);
		calculated *= 78.867489071513;
		return calculated*actualHeight;
	}

}
