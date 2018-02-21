package org.usfirst.frc.team3393.robot.commands.auto;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A {@link CommandGroup} that subsequently calls {@link DriveStraight} and {@link DriveRotate} in order to drive in a square.
 */
public class DriveSquare extends CommandGroup {
	
	public DriveSquare() {
		this.addSequential(new DriveStraight(15));
		this.addSequential(new DriveRotatePositive(90));
		this.addSequential(new DriveStraight(15));
		this.addSequential(new DriveRotatePositive(90));
		this.addSequential(new DriveStraight(15));
		this.addSequential(new DriveRotatePositive(90));
		this.addSequential(new DriveStraight(15));
		this.addSequential(new DriveRotatePositive(90));
		this.requires(Robot.drivetrain);
	}

}
