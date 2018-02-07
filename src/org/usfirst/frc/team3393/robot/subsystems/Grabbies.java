package org.usfirst.frc.team3393.robot.subsystems;

import org.usfirst.frc.team3393.robot.RobotMap;
import org.usfirst.frc.team3393.robot.commands.GrabbieIn;
import org.usfirst.frc.team3393.robot.commands.GrabbieStop;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Grabbies extends Subsystem {
	
	private static VictorSP grabbieL;
	private static VictorSP grabbieR;
	
	private static boolean grabbieIn = false;
	private static boolean grabbieOut = false;
	
	public Grabbies() {
		grabbieL = new VictorSP(RobotMap.grabbieL);
		grabbieR = new VictorSP(RobotMap.grabbieR);
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new GrabbieStop());
	}
	
	//left in
	public void pullIn(){
		grabbieL.set(1.0);
		grabbieR.set(-1.0);
		grabbieIn = true;
		grabbieOut = false;
	}
	
	//right out
	public void pushOut() {
		grabbieL.set(1.0);
		grabbieR.set(-1.0);
		grabbieIn = false;
		grabbieOut = true;
	}
	
	public void stop() {
		grabbieL.set(0);
		grabbieR.set(0);
		grabbieIn = false;
		grabbieOut = false;
	}
	
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
