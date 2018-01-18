package org.usfirst.frc.team3393.robot.subsystems;

import org.usfirst.frc.team3393.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Grabbies extends Subsystem {
	
	private static Solenoid grabbieOpen;
	private static Solenoid grabbieClose;
	
	private static String grabbieState = "Open";
	
	public Grabbies() {
		//grabbieOpen = new Solenoid(RobotMap.grabbieOpen);
		//grabbieClose = new Solenoid(RobotMap.grabbieClose);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void shutGrabbies(){
		grabbieOpen.set(true);
		grabbieClose.set(false);
		grabbieState = "Shut";
	}
	
	public void openGrabbies() {
		grabbieOpen.set(false);
		grabbieClose.set(true);
		grabbieState = "Open";
	}
	
	public void reportToSmartDashboard(){
		//SmartDashboard.putString("Grabbie State", grabbieState);
	}

}
