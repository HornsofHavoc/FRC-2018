package org.usfirst.frc.team3393.robot.commands.auto;

import org.usfirst.frc.team3393.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ActiveDelay extends Command {
	
	private static double endTime;
	private static boolean finished;
	
	public ActiveDelay(double seconds){
		endTime = Timer.getFPGATimestamp()+seconds;
		finished = false;
	}
	
	@Override
	protected void execute() {
		if(endTime>0&&Timer.getFPGATimestamp()>=endTime) {
			finished = true;
		}
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

}
