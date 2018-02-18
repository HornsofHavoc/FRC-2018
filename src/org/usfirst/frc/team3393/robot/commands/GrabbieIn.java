package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@link Command} that sets the Grabbies' motors to pull the box in.
 */
public class GrabbieIn extends Command {
	
	private static double grabSpeed;
	
	public GrabbieIn(double speed){
		this.requires(Robot.grabbies);
		grabSpeed = speed;
	}
	
	public GrabbieIn(double speed, double seconds){
		this.requires(Robot.grabbies);
		grabSpeed = speed;
		this.setTimeout(seconds);
	}
	
	@Override
	protected void execute() {
		Robot.grabbies.pullIn(grabSpeed);
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

}
