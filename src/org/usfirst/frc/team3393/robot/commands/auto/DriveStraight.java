package org.usfirst.frc.team3393.robot.commands.auto;

import org.usfirst.frc.team3393.robot.Robot;
import org.usfirst.frc.team3393.utils.Maths;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends Command {
	
	private static double lEStart;
	private static double rEStart;
	
	private static double disparityTarget = 0;
	
	private static boolean traveled;

	/**
	 * Drive's the bot straight for a given amount of inches.
	 * 
	 * @param disparousInches inches to drive straight.
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
	}

	@Override
	/**
	 * Yes I realize that the cims aren't perfect but until I get the gyro into this mess it works.
	 */
	public void execute() {
		double lEDisparity = Maths.getEncoderInches(Robot.drivetrain.getLeftEncoder())-lEStart;
		double rEDisparity = Maths.getEncoderInches(Robot.drivetrain.getRightEncoder())-rEStart;
		double driveLeft = 0;
		double driveRight = 0;
		if(lEDisparity <= disparityTarget) driveLeft = -0.205*(((disparityTarget-2)-lEDisparity)/disparityTarget);
		if(rEDisparity <= disparityTarget) driveRight = -0.205*(((disparityTarget-2)-rEDisparity)/disparityTarget);
		Robot.drivetrain.getDrivetrain().tankDrive(driveLeft-0.35, driveRight-0.35);
		SmartDashboard.putString("AAAA", lEDisparity+", "+rEDisparity+"    "+driveLeft+", "+driveRight);
		if(driveLeft==0||driveRight==0) traveled = true;
	}
	
	@Override
	protected boolean isFinished() {
		return traveled;
	}

}
