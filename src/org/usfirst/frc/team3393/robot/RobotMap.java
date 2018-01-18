package org.usfirst.frc.team3393.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static int forkliftLeft = 4;
	public static int forkliftRight = 5;
	
	public static int grabbieOpen = 0;
	public static int grabbieClose = 1;
	
	/*DRIVETRAIN*/
	//encoder
	public static int rEncoder1 = 0;
	public static int rEncoder2 = 1;
	
	//drivemotors
	public static int dTalonL = 0;
	public static int dTalonR = 1;
	public static int dSparkL = 0;
	public static int dSparkR = 1;
	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
