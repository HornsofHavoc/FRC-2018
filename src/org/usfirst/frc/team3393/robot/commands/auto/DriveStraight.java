package org.usfirst.frc.team3393.robot.commands.auto;

import org.usfirst.frc.team3393.robot.Robot;
import org.usfirst.frc.team3393.utils.Maths;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A {@link Command} that drives the robot straight for a given number of inches.
 */
public class DriveStraight extends Command {
	
	private double lEStart;
	private double rEStart;
	private double gyroStart;
	
	private double disparityTarget = 0;
	
	private boolean traveled;

	/**
	 * Drive's the robot straight for a given amount of inches.
	 * @param disparousInches Inches to drive straight.
	 */
	public DriveStraight(double disparousInches){
		disparityTarget = disparousInches;
		this.requires(Robot.drivetrain);
	}
	
	@Override
	protected void initialize() {
		traveled = false;
		lEStart = Maths.getEncoderInches(Robot.drivetrain.getLeftEncoder());
		rEStart = Maths.getEncoderInches(Robot.drivetrain.getRightEncoder());
		gyroStart = Robot.drivetrain.getGyro().getAngle();
	}

	@Override
	public void execute() {
		double lEDisparity = Maths.getEncoderInches(Robot.drivetrain.getLeftEncoder())-lEStart;
		double rEDisparity = Maths.getEncoderInches(Robot.drivetrain.getRightEncoder())-rEStart;
		double driveLeft = 0;
		double driveRight = 0;
		if(lEDisparity <= disparityTarget) driveLeft = -0.195*(((disparityTarget)-lEDisparity)/disparityTarget);
		if(rEDisparity <= disparityTarget) driveRight = -0.195*(((disparityTarget)-rEDisparity)/disparityTarget);
		if(gyroStart-Robot.drivetrain.getGyro().getAngle() > 0.01) {
			driveLeft = driveLeft + (driveLeft*(gyroStart-Robot.drivetrain.getGyro().getAngle())/2);
		} else if(gyroStart-Robot.drivetrain.getGyro().getAngle()<0.01) {
			driveRight = driveRight + (driveRight*Math.abs((gyroStart-Robot.drivetrain.getGyro().getAngle())/2));
		}
		Robot.drivetrain.getDrivetrain().tankDrive(driveLeft-0.56, driveRight-0.56);
		if(driveLeft==0||driveRight==0) traveled = true;
	}
	
	@Override
	protected boolean isFinished() {
		return traveled;
	}

}
