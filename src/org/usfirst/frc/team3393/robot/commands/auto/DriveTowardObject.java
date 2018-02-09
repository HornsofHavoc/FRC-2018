package org.usfirst.frc.team3393.robot.commands.auto;

import org.usfirst.frc.team3393.robot.Robot;
import org.usfirst.frc.team3393.utils.TrackingSelector;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTowardObject extends Command {
	
	private static double gyroStart;
	private static int selectionType;
	
	private static boolean finished;
	private static double distanceObjective;
	
	public DriveTowardObject(TrackingSelector selection, double inches){
		this.requires(Robot.drivetrain);
		selectionType = selection.getValue();
		finished = false;
		distanceObjective = inches;
	}
	
	@Override
	protected void initialize() {
		gyroStart = Robot.drivetrain.getGyro().getAngle();
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
		double distance = 0;
		if(selectionType == 0 && Robot.dist>0) {
			//left
			distance = Robot.dist;
		} else if(selectionType == 1) {
			//avg
			if(Robot.dist>0 && Robot.dist2>0) {
				distance = (Robot.dist+Robot.dist2)/2;
			} else if(Robot.dist>0) {
				distance = Robot.dist;
			} else if(Robot.dist2>0) {
				distance = Robot.dist2;
			}
		} else if(selectionType == 2 && Robot.dist2>0) {
			//right
			distance = Robot.dist2;
		}
		double driveLeft = -0.55;
		double driveRight = -0.55;
//		if(gyroStart-Robot.drivetrain.getGyro().getAngle() > 0.01) {
//			driveLeft = driveLeft + (driveLeft*(gyroStart-Robot.drivetrain.getGyro().getAngle())/8);
//		} else if(gyroStart-Robot.drivetrain.getGyro().getAngle()<0.01) {
//			driveRight = driveRight + (driveRight*Math.abs((gyroStart-Robot.drivetrain.getGyro().getAngle())/8));
//		}
		if(gyroStart - Robot.drivetrain.getGyro().getAngle() > 0.01) {
			driveLeft = -0.55;
			driveRight = -0.58;
		} else if(gyroStart - Robot.drivetrain.getGyro().getAngle() < 0.01) {
			driveLeft = -0.58;
			driveRight = -0.55;
		} else {
			driveLeft = -0.55;
			driveRight = -0.55;
		}
		
		if((distance>=distanceObjective)) {
			Robot.drivetrain.getDrivetrain().tankDrive(driveLeft, driveRight);
		} else {
			Robot.drivetrain.getDrivetrain().tankDrive(0, 0);
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
		return finished;
	}

}
