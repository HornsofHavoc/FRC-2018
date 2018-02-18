package org.usfirst.frc.team3393.robot.commands.auto;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Stops all activity in a {@link Command} thread for the given number of seconds.
 */
public class ActiveDelay extends Command {
	
	public ActiveDelay(double seconds){
		this.setTimeout(seconds);
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

}
