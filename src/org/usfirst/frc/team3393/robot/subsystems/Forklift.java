package org.usfirst.frc.team3393.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3393.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;

public class Forklift extends Subsystem {
	
	public static Solenoid solenoidIn;
	public static Solenoid solenoidOut;
	
	public static WPI_TalonSRX fTalonL = new WPI_TalonSRX(RobotMap.fTalonL);
	public static WPI_TalonSRX fTalonR = new WPI_TalonSRX(RobotMap.fTalonR);
	public static Spark fSparkL = new Spark(RobotMap.fSparkL);
	public static Spark fSparkR = new Spark(RobotMap.fSparkR);
	
	public Forklift() {
		solenoidIn = new Solenoid(RobotMap.solenoidIn);
		solenoidOut = new Solenoid(RobotMap.solenoidOut);
		
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void reportToSmartDashboard(){
		//SmartDashboard.putNumber("Drive Encoder", driveEncoder.getRate());
	}
	
	public void forkliftSolenoidExtend() {
		solenoidIn.set(true);
		solenoidOut.set(false);
	}
	
	public void forkliftSolenoidDistend() {
		solenoidIn.set(false);
		solenoidOut.set(true);
	}
	
	public void forkliftFullExtend() {
		forkliftSolenoidExtend();
		fTalonL.set(0.8);
		fTalonR.set(0.8);
		fSparkL.set(0.8);
		fSparkR.set(0.8);
		SmartDashboard.putString("Forklift", "up");
	}
	
	public void forkliftFullDistend() {
		forkliftSolenoidDistend();
		fTalonL.set(-0.8);
		fTalonR.set(-0.8);
		fSparkL.set(-0.8);
		fSparkR.set(-0.8);
		SmartDashboard.putString("Forklift", "down");
	}

}
