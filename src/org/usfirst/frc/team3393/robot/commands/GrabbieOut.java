package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@link Command} that sets the Grabbies' motors to push the box out.
 */
public class GrabbieOut extends Command {
	
	private static double grabSpeed;
	
	public GrabbieOut(double speed){
		this.requires(Robot.grabbies);
		grabSpeed = speed;
	}
	
	public GrabbieOut(double speed, double seconds){
		this.requires(Robot.grabbies);
		grabSpeed = speed;
		this.setTimeout(seconds);
	}
	
	@Override
	protected void execute() {
		Robot.grabbies.pushOut(grabSpeed);
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

}
