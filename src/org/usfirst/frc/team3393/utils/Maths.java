package org.usfirst.frc.team3393.utils;

import edu.wpi.first.wpilibj.Encoder;

/**
 * Handles math operations used throughout competition.
 */
public class Maths {
	
	private static double teethCimGear = 12;
	private static double teethWheelGear = 26;
	
	/**
	 * Converts the value of encoder rotations to approximate traveled inches.
	 * @param e The encoder to read rotation data from.
	 * @return A double representing the distance traveled in inches.
	 */
	public static double getEncoderInches(Encoder e) {
		double encoderDistance = e.getDistance();
		return (encoderDistance/(teethWheelGear/teethCimGear))*(6.0*Math.PI);
	}
	
	/**
	 * Divides the value returned by {@link #getEncoderInches(Encoder)} by 12.0.
	 * @param e The encoder to read rotation data from.
	 * @return A double representing the distance traveled in feet.
	 */
	public static double getEncoderFeet(Encoder e) {
		double feet = getEncoderInches(e);
		return feet/12.0;
	}
	
	/**
	 * Calculates the distance from a visible object given its' width in pixels and width in inches.
	 * This method is designed to function solely with a Microsoft HD Lifecam 3000 at a resolution of 640x480 
	 * along with a very specific GRIP OpenCV calculation on the image.
	 * The camera's Brightness and Exposure should both be set to 18.
	 * @param pixelsWidth The number of pixels an object takes up horizontally after a pipeline convex hull resolution.
	 * @param actualWidth The number of inches an object takes up vertically.
	 * @return An approximation of the inches between the camera and an object.
	 */
	public static double getObjectDistance(double pixelsWidth, double actualWidth) {
		double doNum = (double) pixelsWidth/actualWidth;
		//double calculated = (0.24*(doNum*doNum))-13.12*(doNum)+236.1;
		double calculated  = Math.pow(doNum, -1.0890021870397);
		calculated *= 476.37614279388;
		return calculated*actualWidth;
	}

}
