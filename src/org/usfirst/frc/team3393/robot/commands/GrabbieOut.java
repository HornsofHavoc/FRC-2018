package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@link Command} that sets the Grabbies' motors to push the box out.
 */
public class GrabbieOut extends Command {
	
	public GrabbieOut(){
		this.requires(Robot.grabbies);
	}
	
	@Override
	protected void execute() {
		Robot.grabbies.pushOut();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
