package org.usfirst.frc.team3393.robot.commands;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@link Command} that sets the Forklift's solenoid to distend and it's motors to distend it further.
 */
public class ForkliftWinchDown extends Command {
	
	private double winchSpeed;
	
	public ForkliftWinchDown(double speed){
		this.requires(Robot.forklift);
		winchSpeed = -speed;
	}
	
	@Override
	protected void initialize() {
		
	}
	
	@Override
	protected void execute() {
		//if(!Robot.forklift.isSwitchSet()) {
		//	Robot.forklift.forkliftFullDistend(winchSpeed);
		//} else {
		//	//new ForkliftStop().start();
		//}
		Robot.forklift.forkliftFullDistend(winchSpeed);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
