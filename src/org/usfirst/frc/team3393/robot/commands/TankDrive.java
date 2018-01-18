package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command {
	
	public TankDrive(){
		this.requires(Robot.drivetrain);
	}

	@Override
	public void execute() {
		Robot.drivetrain.getDrivetrain().tankDrive(Robot.oi.getLeftJoystick().getY(), Robot.oi.getRightJoystick().getY());
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
