package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GrabbieClose extends Command {
	
	public GrabbieClose(){
		this.requires(Robot.grabbies);
	}
	
	@Override
	protected void execute() {
		Robot.grabbies.shutGrabbies();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
