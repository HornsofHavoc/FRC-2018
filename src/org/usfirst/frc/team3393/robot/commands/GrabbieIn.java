package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@link Command} that sets the Grabbies' motors to pull the box in.
 */
public class GrabbieIn extends Command {
	
	private static double grabSpeed;
	
	/**
	 * Runs the {@link Grabbies} at the given speed until the {@link Grabbies} Subsystem in passed a new {@link Command}.
	 * @param speed The speed (by percent between 0.0 and 1.0) at which the {@link Grabbies} should operate.
	 */
	public GrabbieIn(double speed){
		this.requires(Robot.grabbies);
		grabSpeed = speed;
	}
	
	/**
	 * Runs the {@link Grabbies} at the given speed until the time has elapsed past the current time plus seconds (for Autonomous).
	 * @param speed The speed (by percent between 0.0 and 1.0) at which the {@link Grabbies} should operate.
	 * @param seconds The seconds for which the {@link Grabbies} shoudl run before stopping.
	 */
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
