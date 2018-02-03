package org.usfirst.frc.team3393.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FollowObject extends CommandGroup {
	
	public FollowObject(int inches) {
		this.addSequential(new TurnTowardObject());
		this.addSequential(new DriveTowardObject(24+9));
	}

}
