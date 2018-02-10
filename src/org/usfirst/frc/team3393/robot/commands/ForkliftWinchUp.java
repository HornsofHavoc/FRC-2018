package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@link Command} that sets the Forklift's solenoid to extend and it's motors to extend it further.
 */
public class ForkliftWinchUp extends Command {
	
	private static double winchSpeed;
	
	public ForkliftWinchUp(double speed){
		this.requires(Robot.forklift);
		winchSpeed = speed;
	}
	
	@Override
	protected void initialize() {
		
	}
	
	@Override
	protected void execute() {
		Robot.forklift.forkliftFullExtend(winchSpeed);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
