package org.usfirst.frc.team3393.robot.subsystems;

import org.usfirst.frc.team3393.robot.RobotMap;
import org.usfirst.frc.team3393.robot.commands.ClimberStop;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	
	private static VictorSP cVictorSPXL;
	private static VictorSP cVictorSPXR;
	private static VictorSP cVictorSPXL1;
	private static VictorSP cVictorSPXR1;
	
	public Climber() {
		cVictorSPXL = new VictorSP(RobotMap.cVictorSPXL);
		cVictorSPXR = new VictorSP(RobotMap.cVictorSPXR);
		cVictorSPXL1 = new VictorSP(RobotMap.cVictorSPXL1);
		cVictorSPXR1 = new VictorSP(RobotMap.cVictorSPXR1);
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ClimberStop());
	}
	
	public void climbUp() {
		cVictorSPXL.set(1.0);
		cVictorSPXR.set(1.0);
		cVictorSPXL1.set(-1.0);
		cVictorSPXR1.set(-1.0);
	}
	
	public void climbDown() {
		cVictorSPXL.set(-1.0);
		cVictorSPXR.set(-1.0);
		cVictorSPXL1.set(1.0);
		cVictorSPXR1.set(1.0);
	}
	
	public void climbStop() {
		cVictorSPXL.set(0);
		cVictorSPXR.set(0);
		cVictorSPXL1.set(0);
		cVictorSPXR1.set(0);
	}

}
