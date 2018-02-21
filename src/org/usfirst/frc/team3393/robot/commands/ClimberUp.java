package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberUp extends Command {

	public ClimberUp() {
		this.requires(Robot.climber);
	}
	
	@Override
	protected void initialize() {
		
	}
	
	@Override
	protected void execute() {
		Robot.climber.climbUp();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
