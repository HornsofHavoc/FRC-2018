package org.usfirst.frc.team3393.robot.commands.auto;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveSquare extends CommandGroup {
	
	public DriveSquare() {
		this.addSequential(new DriveStraight(15));
		this.addSequential(new DriveRotate(90));
		this.addSequential(new DriveStraight(15));
		this.addSequential(new DriveRotate(90));
		this.addSequential(new DriveStraight(15));
		this.addSequential(new DriveRotate(90));
		this.addSequential(new DriveStraight(15));
		this.addSequential(new DriveRotate(90));
		this.requires(Robot.drivetrain);
	}

}