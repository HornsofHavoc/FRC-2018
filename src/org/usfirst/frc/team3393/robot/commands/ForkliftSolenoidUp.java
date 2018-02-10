package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@link Command} that sets the Forklift's solenoid to extend.
 */
public class ForkliftSolenoidUp extends Command {
	
	private boolean finished;
	
	public ForkliftSolenoidUp(){
		this.requires(Robot.forklift);
		finished = false;
	}
	
	@Override
	protected void initialize() {
		finished = false;
	}
	
	@Override
	protected void execute() {
		Robot.forklift.forkliftSolenoidExtend();
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
