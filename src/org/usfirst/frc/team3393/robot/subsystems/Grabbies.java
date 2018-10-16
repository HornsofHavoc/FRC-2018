package org.usfirst.frc.team3393.robot.subsystems;

import org.usfirst.frc.team3393.robot.RobotMap;
import org.usfirst.frc.team3393.robot.commands.GrabbieStop;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A {@link Subsystem} that handles methods related to moving the robots power cubes pickup and placing system.
 */
public class Grabbies extends Subsystem {
	
	private VictorSP grabbieL;
	private VictorSP grabbieR;
	
	private Solenoid grabbieReleaseIn;
	private Solenoid grabbieReleaseOut;
	
	private boolean grabbieIn = false;
	private boolean grabbieOut = false;
	private boolean grabbieUp = false;
	private boolean grabbieDown = false;
	
	public Grabbies() {
		grabbieL = new VictorSP(RobotMap.grabbieL);
		grabbieR = new VictorSP(RobotMap.grabbieR);
		
		grabbieReleaseIn = new Solenoid(RobotMap.grabbieUno);
		grabbieReleaseOut = new Solenoid(RobotMap.grabbieDos);
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new GrabbieStop());
	}
	
	/**
	 * Sets motors operating the pickup to pull the power cube in. 
	 */
	public void pullIn(double speed){
		grabbieL.set(-speed);
		grabbieR.set(-speed);
		grabbieIn = true;
		grabbieOut = false;
	}
	
	
	/**
	 * Sets motors operating the pickup to push the power cube out.
	 */
	public void pushOut(double speed) {
		grabbieL.set(speed);
		grabbieR.set(speed);
		grabbieIn = false;
		grabbieOut = true;
	}
	
	public void grabbieUp() {
		grabbieUp = true; 
		grabbieDown = false;
		grabbieReleaseOut.set(true);
		grabbieReleaseIn.set(false);
	}
	
	public void grabbieDown() {
		grabbieUp = false;
		grabbieDown = true;
		grabbieReleaseOut.set(false);
		grabbieReleaseIn.set(true);
	}
	
	/**
	 * Sets motors operating the pickup to hold their position.
	 */
	
	public void stop() {
		grabbieL.set(0);
		grabbieR.set(0);
		grabbieIn = false;
		grabbieOut = false;
		
	}
	
	public void grabbieReleaseStop() {
		grabbieUp = false;
		grabbieDown = false;
	}
	
	/**
	 * Adds relevant class information to the FRC {@link SmartDashboard}.
	 */
	public void reportToSmartDashboard(){
		if(grabbieIn&&!grabbieOut) {
			SmartDashboard.putString("Grabbie State", "Intake");
		} else if(grabbieOut&&!grabbieIn) {
			SmartDashboard.putString("Grabbie State", "Output");
		} else {
			SmartDashboard.putString("Grabbie State", "Hold");
		}
	}
		
}


