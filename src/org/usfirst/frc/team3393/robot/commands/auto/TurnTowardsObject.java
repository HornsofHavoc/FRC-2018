package org.usfirst.frc.team3393.robot.commands.auto;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnTowardsObject extends CommandGroup {
	

	private static double gyroStart;
	
	private static double disparityTarget = 0;
	
	private static boolean rotPositive;
	private static boolean turned;

	/**
	 * Turns the bot for a given amount of degrees.
	 * 
	 * @param degrees degrees to rotate.
	 */
	public TurnTowardsObject(){
		this.requires(Robot.drivetrain);
	}
	
	@Override
	protected void initialize() {
//		turned = false;
//		rotPositive = false;
//		if(disparityTarget>=0) {
//			rotPositive = true;
//		}
//		gyroStart = Robot.drivetrain.getGyro().getAngle();
	}

	@Override
	/**
	 * Oh boy left is negative, right positive.
	 */
	public void execute() {
		double center = Robot.center;
		if((center>=5.0) && (center<=80)) {
			Robot.drivetrain.getDrivetrain().tankDrive(0.55, -0.55);
		} else if((center>=240) && (center<=315)) {
			Robot.drivetrain.getDrivetrain().tankDrive(-0.55, 0.55);
		} else {
			Robot.drivetrain.getDrivetrain().tankDrive(0, 0);
		}
		Robot.center = 0;
//		double gyroCurrent = Robot.drivetrain.getGyro().getAngle();
//		double disparityCurrent = gyroCurrent - gyroStart;
//		double ratio = (disparityTarget-disparityCurrent)/disparityTarget;
//		if(rotPositive) {
//			SmartDashboard.putString("disparities", disparityCurrent+", "+disparityTarget);
//			if(disparityCurrent<=(disparityTarget)) {
//				Robot.drivetrain.getDrivetrain().tankDrive(-0.5+(-0.05*ratio), 0.5+(0.05*ratio));
//			} else {
//				Robot.drivetrain.getDrivetrain().tankDrive(0, 0);
//				turned = true;
//			}
//		} else {
//			if(disparityCurrent>=(disparityTarget)) {
//				Robot.drivetrain.getDrivetrain().tankDrive(0.5+(0.05*ratio), -0.5+(-0.05*ratio));
//			} else {
//				Robot.drivetrain.getDrivetrain().tankDrive(0, 0);
//				turned = true;
//			}
//		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
