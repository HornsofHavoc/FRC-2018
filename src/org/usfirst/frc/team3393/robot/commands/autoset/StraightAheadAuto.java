package org.usfirst.frc.team3393.robot.commands.autoset;

import org.usfirst.frc.team3393.robot.commands.auto.DriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StraightAheadAuto extends CommandGroup {
	
	public StraightAheadAuto() {
		this.addSequential(new DriveStraight(144));
	}

}
