package org.usfirst.frc.team3393.robot.commands.auto;

import org.usfirst.frc.team3393.utils.TrackingSelector;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A {@link CommandGroup} used to follow an object using both {@link TurnTowardObject} and {@link DriveTowardObject}.
 */
public class FollowObject extends CommandGroup {
	
	/**
	 * Creates and instance of {@link FollowObject}
	 * @param rotationalSelector The {@link TrackingSelector} to be used by the {@link TurnTowardObject} command.
	 * @param distanceSelector The {@link TrackingSelector} to be used by the {@link DriveTowardObject} command.
	 * @param inches The target distance away to be used by the {@link DriveTowardObject} command.
	 */
	public FollowObject(TrackingSelector rotationalSelector, TrackingSelector distanceSelector, int inches) {
		this.addSequential(new TurnTowardObject(rotationalSelector));
		this.addSequential(new DriveTowardObject(distanceSelector, 24+9));
	}

}
