package org.usfirst.frc.team3393.robot.commands.auto;

import org.usfirst.frc.team3393.utils.TrackingSelector;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FollowObject extends CommandGroup {
	
	public FollowObject(TrackingSelector rotationalSelector, TrackingSelector distanceSelector, int inches) {
		this.addSequential(new TurnTowardObject(rotationalSelector));
		this.addSequential(new DriveTowardObject(distanceSelector, 24+9));
	}

}
