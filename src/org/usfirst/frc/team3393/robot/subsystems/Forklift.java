package org.usfirst.frc.team3393.robot.subsystems;

import org.usfirst.frc.team3393.robot.commands.ForkliftStop;
import org.usfirst.frc.team3393.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3393.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
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
	
	private static VictorSPX fVictorSPXL;
	private static VictorSPX fVictorSPXR;
	private static VictorSPX fVictorSPXL1;
	private static VictorSPX fVictorSPXR1;
	
	public Forklift() {
		solenoidIn = new Solenoid(RobotMap.solenoidIn);
		solenoidOut = new Solenoid(RobotMap.solenoidOut);
		fLimitB = new DigitalInput(RobotMap.fLimitB);
		switchCounter = new Counter(fLimitB);
		fVictorSPXL = new VictorSPX(RobotMap.fVictorSPXL);
		fVictorSPXR = new VictorSPX(RobotMap.fVictorSPXR);
		fVictorSPXL1 = new VictorSPX(RobotMap.fVictorSPXL1);
		fVictorSPXR1 = new VictorSPX(RobotMap.fVictorSPXR1);
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
		fVictorSPXL.set(ControlMode.PercentOutput, 0.8);
		fVictorSPXR.set(ControlMode.PercentOutput, 0.8);
		fVictorSPXL1.set(ControlMode.PercentOutput, 0.8);
		fVictorSPXR1.set(ControlMode.PercentOutput, 0.8);
		forkUp = true;
		forkDown = false;
	}
	
	public void forkliftFullDistend() {
		forkliftSolenoidDistend();
		fVictorSPXL.set(ControlMode.PercentOutput, -0.8);
		fVictorSPXR.set(ControlMode.PercentOutput, -0.8);
		fVictorSPXL1.set(ControlMode.PercentOutput, -0.8);
		fVictorSPXR1.set(ControlMode.PercentOutput, -0.8);
		forkUp = false;
		forkDown = true;
	}
	
	public void forkliftHoldPoint() {
		fVictorSPXL.set(ControlMode.PercentOutput, 0);
		fVictorSPXR.set(ControlMode.PercentOutput, 0);
		fVictorSPXL1.set(ControlMode.PercentOutput, 0);
		fVictorSPXR1.set(ControlMode.PercentOutput, 0);
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
