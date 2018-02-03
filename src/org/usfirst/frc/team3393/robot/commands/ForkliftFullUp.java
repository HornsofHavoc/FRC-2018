package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ForkliftFullUp extends Command {
	
	private boolean finished;
	
	public ForkliftFullUp(){
		this.requires(Robot.forklift);
		finished = false;
	}
	
	@Override
	protected void initialize() {
		finished = false;
	}
	
	@Override
	protected void execute() {
		Robot.forklift.forkliftFullExtend();
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
