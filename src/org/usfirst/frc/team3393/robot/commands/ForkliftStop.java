package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ForkliftStop extends Command {
	
	private boolean finished;
	
	public ForkliftStop(){
		this.requires(Robot.forklift);
		finished = false;
	}
	
	@Override
	protected void initialize() {
		finished = false;
	}
	
	@Override
	protected void execute() {
		Robot.forklift.forkliftHoldPoint();
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
