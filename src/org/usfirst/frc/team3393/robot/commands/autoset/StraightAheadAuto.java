package org.usfirst.frc.team3393.robot.commands.autoset;

import org.usfirst.frc.team3393.robot.commands.ForkliftSolenoidUp;
import org.usfirst.frc.team3393.robot.commands.GrabbieOut;
import org.usfirst.frc.team3393.robot.commands.GrabbieStop;
import org.usfirst.frc.team3393.robot.commands.auto.ActiveDelay;
import org.usfirst.frc.team3393.robot.commands.auto.DriveTowardObject;
import org.usfirst.frc.team3393.robot.commands.auto.TurnTowardObject;
import org.usfirst.frc.team3393.utils.TrackingSelector;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * An autonomous {@link Command} that drives from a base to the tracking points on the switch and place the power cube.
 */
public class StraightAheadAuto extends CommandGroup {
	
	public StraightAheadAuto() {
		this.addSequential(new ForkliftSolenoidUp());
		this.addSequential(new ActiveDelay(2.0));
		//this.addSequential(new FollowObject(TrackingSelector.AVERAGE, TrackingSelector.AVERAGE, 20));
		this.addSequential(new TurnTowardObject(TrackingSelector.AVERAGE));
		this.addSequential(new DriveTowardObject(TrackingSelector.AVERAGE, 10+9));
		this.addSequential(new GrabbieOut(0.5, 2.0));
		this.addSequential(new GrabbieStop());
	}

}
