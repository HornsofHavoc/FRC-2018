package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GrabbieOpen extends Command {
	
	public GrabbieOpen(){
		this.requires(Robot.grabbies);
	}
	
	@Override
	protected void execute() {
		Robot.grabbies.openGrabbies();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
