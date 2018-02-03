package org.usfirst.frc.team3393.robot.subsystems;

import org.usfirst.frc.team3393.robot.RobotMap;
import org.usfirst.frc.team3393.robot.commands.GrabbieIn;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Grabbies extends Subsystem {
	
	private static WPI_TalonSRX grabbieL;
	private static WPI_TalonSRX grabbieR;
	
	private static boolean grabbieIn = true;
	
	public Grabbies() {
		grabbieL = new WPI_TalonSRX(RobotMap.grabbieL);
		grabbieR = new WPI_TalonSRX(RobotMap.grabbieR);
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new GrabbieIn());
	}
	
	public void pullIn(){
		grabbieL.set(-1.0);
		grabbieR.set(1.0);
		grabbieIn = true;
	}
	
	public void pushOut() {
		grabbieL.set(1.0);
		grabbieR.set(-1.0);
		grabbieIn = false;
	}
	
	public void reportToSmartDashboard(){
		if(grabbieIn) {
			SmartDashboard.putString("Grabbie State", "Intake");
		} else {
			SmartDashboard.putString("Grabbie State", "Output");
		}
	}

}
