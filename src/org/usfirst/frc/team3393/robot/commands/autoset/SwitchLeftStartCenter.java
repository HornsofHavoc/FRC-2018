package org.usfirst.frc.team3393.robot.commands.autoset;

import org.usfirst.frc.team3393.robot.commands.ForkliftSolenoidUp;
import org.usfirst.frc.team3393.robot.commands.GrabbieUp;
import org.usfirst.frc.team3393.robot.commands.auto.DriveRotateNegative;
import org.usfirst.frc.team3393.robot.commands.auto.DriveRotatePositive;
import org.usfirst.frc.team3393.robot.commands.auto.DriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchLeftStartCenter extends CommandGroup{
	
	public SwitchLeftStartCenter() {
		this.addSequential(new GrabbieUp());
		this.addSequential(new DriveRotateNegative(90));
		this.addSequential(new ForkliftSolenoidUp());
		this.addSequential(new DriveStraight(44));
		this.addSequential(new DriveRotatePositive(90));
		this.addSequential(new AlignedTrackAuto());
	}
}
