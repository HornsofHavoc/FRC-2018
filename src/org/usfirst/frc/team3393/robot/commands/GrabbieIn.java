package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@link Command} that sets the Grabbies' motors to pull the box in.
 */
public class GrabbieIn extends Command {
	
	public GrabbieIn(){
		this.requires(Robot.grabbies);
	}
	
	@Override
	protected void execute() {
		Robot.grabbies.pullIn();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
