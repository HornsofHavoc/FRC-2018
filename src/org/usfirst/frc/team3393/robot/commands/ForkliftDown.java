package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ForkliftDown extends Command {
	
	public ForkliftDown(){
		this.requires(Robot.forklift);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
