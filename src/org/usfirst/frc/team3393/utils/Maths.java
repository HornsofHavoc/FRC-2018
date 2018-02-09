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
	public static double getObjectDistance(double pixelsWidth, double actualWidth) {
		double doNum = (double) pixelsWidth/actualWidth;
		//double calculated = (0.24*(doNum*doNum))-13.12*(doNum)+236.1;
		double calculated  = Math.pow(doNum, -1.0890021870397);
		calculated *= 476.37614279388;
		return calculated*actualWidth;
	}

}
