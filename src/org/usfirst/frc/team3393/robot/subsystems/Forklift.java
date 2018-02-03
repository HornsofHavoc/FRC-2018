package org.usfirst.frc.team3393.robot.subsystems;

import org.usfirst.frc.team3393.robot.commands.ForkliftStop;
import org.usfirst.frc.team3393.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3393.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;

public class Forklift extends Subsystem {
	
	private static boolean forkUp = false;
	private static boolean forkDown = false;
	
	private static Solenoid solenoidIn;
	private static Solenoid solenoidOut;
	private static DigitalInput fLimitB;
	private static Counter switchCounter;
	
	private static WPI_TalonSRX fTalonL;
	private static WPI_TalonSRX fTalonR;
	private static Spark fSparkL;
	private static Spark fSparkR;
	
	public Forklift() {
		solenoidIn = new Solenoid(RobotMap.solenoidIn);
		solenoidOut = new Solenoid(RobotMap.solenoidOut);
		fLimitB = new DigitalInput(RobotMap.fLimitB);
		switchCounter = new Counter(fLimitB);
		fTalonL = new WPI_TalonSRX(RobotMap.fTalonL);
		fTalonR = new WPI_TalonSRX(RobotMap.fTalonR);
		fSparkL = new Spark(RobotMap.fSparkL);
		fSparkR = new Spark(RobotMap.fSparkR);
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ForkliftStop());
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
		forkUp = true;
		forkDown = false;
	}
	
	public void forkliftFullDistend() {
		forkliftSolenoidDistend();
		fTalonL.set(-0.8);
		fTalonR.set(-0.8);
		fSparkL.set(-0.8);
		fSparkR.set(-0.8);
		forkUp = false;
		forkDown = true;
	}
	
	public void forkliftHoldPoint() {
		fTalonL.set(0);
		fTalonR.set(0);
		fSparkL.set(0);
		fSparkR.set(0);
		forkUp = false;
		forkDown = false;
	}
	
	public boolean isSwitchSet() {
        return switchCounter.get() > 0;
    }
	
	public void reportToSmartDashboard(){
		if(forkUp&&!forkDown) {
			SmartDashboard.putString("Forklift State", "Up");
		} else if(forkDown&&!forkUp) {
			SmartDashboard.putString("Forklift State", "Down");
		} else {
			SmartDashboard.putString("Forklift State", "Hold");
		}
	}

}
