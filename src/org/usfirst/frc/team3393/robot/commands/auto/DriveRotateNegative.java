package org.usfirst.frc.team3393.robot.commands.auto;

import org.usfirst.frc.team3393.robot.Robot;
import org.usfirst.frc.team3393.utils.Maths;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A {@link Command} that rotates the robot for a given number of inches.
 */
public class DriveRotateNegative extends Command {

	private double gyroStart;
	
	private double disparityTarget = 0;
	
	private boolean turned;

	/**
	 * Turns the robot for a given amount of degrees.
	 * 
	 * @param degrees Degrees to rotate.
	 */
	public DriveRotateNegative(double degrees){
		//empirically derived meme
		disparityTarget = -degrees + 2.9;
		this.requires(Robot.drivetrain);
	}
	
	@Override
	protected void initialize() {
		turned = false;
		gyroStart = Robot.drivetrain.getGyro().getAngle();
	}

	//left is negative, right is positive.
	@Override
	public void execute() {
		double gyroCurrent = Robot.drivetrain.getGyro().getAngle();
		double disparityCurrent = gyroStart + gyroCurrent;
		double ratio = (disparityTarget-disparityCurrent)/disparityTarget;
		SmartDashboard.putNumber("dt", disparityTarget);
		SmartDashboard.putNumber("dc", disparityCurrent);
		if(disparityCurrent>=(disparityTarget)) {
			Robot.drivetrain.getDrivetrain().tankDrive(0.71+(0.05*ratio), -0.86+(-0.05*ratio));
		} else {
			Robot.drivetrain.getDrivetrain().tankDrive(0, 0);
			turned = true;
		}
	}
	
	@Override
	protected boolean isFinished() {
		return turned;
	}
	
}
