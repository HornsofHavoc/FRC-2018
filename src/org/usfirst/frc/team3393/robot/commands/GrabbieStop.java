package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GrabbieStop extends Command {

	private boolean finished;
	
	public GrabbieStop(){
		this.requires(Robot.grabbies);
		finished = false;
	}
	
	@Override
	protected void initialize() {
		finished = false;
	}
	
	@Override
	protected void execute() {
		Robot.grabbies.stop();
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
