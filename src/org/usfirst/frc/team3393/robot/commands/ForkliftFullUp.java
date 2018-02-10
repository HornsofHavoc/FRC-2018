package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@link Command} that sets the Forklift's solenoid to extend and it's motors to extend it further.
 */
public class ForkliftFullUp extends Command {
	
	public ForkliftFullUp(){
		this.requires(Robot.forklift);
	}
	
	@Override
	protected void initialize() {
		
	}
	
	@Override
	protected void execute() {
		Robot.forklift.forkliftFullExtend();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
