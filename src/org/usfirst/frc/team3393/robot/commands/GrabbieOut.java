package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@link Command} that sets the Grabbies' motors to push the box out.
 */
public class GrabbieOut extends Command {
	
	private double grabSpeed;
	
	/**
	 * Runs the {@link Grabbies} at the given speed until the {@link Grabbies} Subsystem in passed a new {@link Command}.
	 * @param speed The speed (by percent between 0.0 and 1.0) at which the {@link Grabbies} should operate in reverse.
	 */
	public GrabbieOut(double speed){
		this.requires(Robot.grabbies);
		grabSpeed = speed;
	}
	
	/**
	 * Runs the {@link Grabbies} at the given speed until the time has elapsed past the current time plus seconds (for Autonomous).
	 * @param speed The speed (by percent between 0.0 and 1.0) at which the {@link Grabbies} should operate in reverse.
	 * @param seconds The seconds for which the {@link Grabbies} shoudl run before stopping.
	 */
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
