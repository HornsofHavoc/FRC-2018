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
	public static int fSparkL = 6;
	public static int fSparkR = 7;
	
	public static int solenoidIn = 0;
	public static int solenoidOut = 1;
	
	//sensors
	public static int fLimitB = 9;
	
	/*GRABBIE*/
	public static int grabbieL = 0;
	public static int grabbieR = 1;
	
	public static int grabbieUno = 2;
	public static int grabbieDos = 3;
	
	/*CLIMBER*/
	public static int cVictorSPXL = 2;
	public static int cVictorSPXR = 3;
	public static int cVictorSPXL1 = 4;
	public static int cVictorSPXR1 = 5;
	
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
	public static int dTalonL1 = 1;
	public static int dTalonR = 2;
	public static int dTalonR1 = 3;
	
}