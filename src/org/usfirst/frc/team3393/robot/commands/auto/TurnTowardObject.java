package org.usfirst.frc.team3393.robot.commands.auto;

import org.usfirst.frc.team3393.robot.Robot;
import org.usfirst.frc.team3393.utils.TrackingSelector;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnTowardObject extends Command {
	
	private boolean finished = false;
	
	private static double gyroStart;
	private static int selectionType;
	
	private static double disparityTarget = 0;
	
	private static boolean rotPositive;
	private static boolean turned;

	/**
	 * Turns the bot for a given amount of degrees.
	 * 
	 * @param degrees degrees to rotate.
	 */
	public TurnTowardObject(TrackingSelector selector){
		this.requires(Robot.drivetrain);
		finished = false;
		selectionType = selector.getValue();
	}
	
	@Override
	protected void initialize() {
		finished = false;
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
		double center = 0;
		if(selectionType == 0 && Robot.center>0) {
			//left
			center = Robot.center;
		} else if(selectionType == 1) {
			//avg
			if(Robot.center>0 && Robot.center2>0) {
				center = (Robot.center+Robot.center2)/2;
			} else if(Robot.center>0) {
				center = Robot.center;
			} else if(Robot.center2>0) {
				center = Robot.center2;
			}
		} else if(selectionType == 2 && Robot.center2>0) {
			//right
			center = Robot.center2;
		}
		if((center>=5) && (center<=150)) {
			Robot.drivetrain.getDrivetrain().tankDrive(0.58, -0.58);
		} else if((center>=170) && (center<=315)) {
			Robot.drivetrain.getDrivetrain().tankDrive(-0.58, 0.58);
		} else {
			Robot.drivetrain.getDrivetrain().tankDrive(0, 0);
			finished = true;
		}
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
