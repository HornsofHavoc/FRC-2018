package org.usfirst.frc.team3393.robot.commands.auto;

import org.usfirst.frc.team3393.robot.Robot;
import org.usfirst.frc.team3393.utils.Maths;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A {@link Command} that rotates the robot for a given number of inches.
 */
public class DriveRotate extends Command {

	private static double gyroStart;
	
	private static double disparityTarget = 0;
	
	private static boolean rotPositive;
	private static boolean turned;

	/**
	 * Turns the robot for a given amount of degrees.
	 * 
	 * @param degrees Degrees to rotate.
	 */
	public DriveRotate(double degrees){
		//empirically derived meme
		disparityTarget = degrees - 3.9;
		this.requires(Robot.drivetrain);
	}
	
	@Override
	protected void initialize() {
		turned = false;
		rotPositive = false;
		if(disparityTarget>=0) {
			rotPositive = true;
		}
		gyroStart = Robot.drivetrain.getGyro().getAngle();
	}

	//left is negative, right is positive.
	@Override
	public void execute() {
		double gyroCurrent = Robot.drivetrain.getGyro().getAngle();
		double disparityCurrent = gyroCurrent - gyroStart;
		double ratio = (disparityTarget-disparityCurrent)/disparityTarget;
		if(rotPositive) {
			SmartDashboard.putString("disparities", disparityCurrent+", "+disparityTarget);
			if(disparityCurrent<=(disparityTarget)) {
				Robot.drivetrain.getDrivetrain().tankDrive(-0.5+(-0.05*ratio), 0.5+(0.05*ratio));
			} else {
				Robot.drivetrain.getDrivetrain().tankDrive(0, 0);
				turned = true;
			}
		} else {
			if(disparityCurrent>=(disparityTarget)) {
				Robot.drivetrain.getDrivetrain().tankDrive(0.5+(0.05*ratio), -0.5+(-0.05*ratio));
			} else {
				Robot.drivetrain.getDrivetrain().tankDrive(0, 0);
				turned = true;
			}
		}
	}
	
	@Override
	protected boolean isFinished() {
		return turned;
	}
	
}
