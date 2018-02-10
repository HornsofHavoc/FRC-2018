package org.usfirst.frc.team3393.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	/*FORKLIFT*/
	//lift
	public static int fVictorSPXL = 2;
	public static int fVictorSPXR = 3;
	public static int fVictorSPXL1 = 4;
	public static int fVictorSPXR1 = 5;
	public static int solenoidIn = 0;
	public static int solenoidOut = 1;
	
	//sensors
	public static int fLimitB = 0;
	
	/*GRABBIE*/
	public static int grabbieL = 0;
	public static int grabbieR = 1;
	
	/*DRIVETRAIN*/
	//encoders
	public static int dEncoderL1 = 0;
	public static int dEncoderL2 = 1;
	
	public static int dEncoderR1 = 2;
	public static int dEncoderR2 = 3;
	
	//sensors
	public static int sUltrasonic = 4;
	
	//drivemotors
	public static int dTalonL = 0;
	public static int dTalonR = 1;
	public static int dTalonL1 = 2;
	public static int dTalonR1 = 3;
	
}