package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ForkliftFullDown extends Command {
	
	public ForkliftFullDown(){
		this.requires(Robot.forklift);
	}
	
	@Override
	protected void initialize() {
		
	}
	
	@Override
	protected void execute() {
		if(!Robot.forklift.isSwitchSet()) {
			Robot.forklift.forkliftFullDistend();
		} else {
			new ForkliftStop().start();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
