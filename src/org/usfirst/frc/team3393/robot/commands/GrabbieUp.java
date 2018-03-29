package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@link Command} that sets the Grabbies solenoid to extend.
 */
public class GrabbieUp extends Command {
	
	private boolean finished;
	
	public GrabbieUp(){
		this.requires(Robot.grabbies);
		finished = false;
	}
	
	@Override
	protected void initialize() {
		finished = false;
	}
	
	@Override
	protected void execute() {
		Robot.grabbies.grabbieUp();
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
