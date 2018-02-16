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
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * A {@link Subsystem} that handles methods related to moving the forklift and solenoid used to lift power cubes.
 */
public class Forklift extends Subsystem {
	
	private static boolean forkUp = false;
	private static boolean forkDown = false;
	
	private static Solenoid solenoidIn;
	private static Solenoid solenoidOut;
	//private static DigitalInput fLimitB;
	private static Counter switchCounter;
	
	private static VictorSPX fVictorSPXL;
	private static VictorSPX fVictorSPXR;
	private static VictorSPX fVictorSPXL1;
	private static VictorSPX fVictorSPXR1;
	
	public Forklift() {
		solenoidIn = new Solenoid(RobotMap.solenoidIn);
		solenoidOut = new Solenoid(RobotMap.solenoidOut);
		//fLimitB = new DigitalInput(RobotMap.fLimitB);
		//switchCounter = new Counter(fLimitB);
		fVictorSPXL = new VictorSPX(RobotMap.fVictorSPXL);
		fVictorSPXR = new VictorSPX(RobotMap.fVictorSPXR);
		fVictorSPXL1 = new VictorSPX(RobotMap.fVictorSPXL1);
		fVictorSPXR1 = new VictorSPX(RobotMap.fVictorSPXR1);
	}
	
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ForkliftStop());
	}
	
	/**
	 * Fills the air cannister attached to the solenoid, raising the {@link Grabbies}.
	 */
	public void forkliftSolenoidExtend() {
		solenoidIn.set(true);
		solenoidOut.set(false);
	}
	
	/**
	 * Empties the air cannister attached to the solenoid, lowering the {@link Grabbies}.
	 */
	public void forkliftSolenoidDistend() {
		solenoidIn.set(false);
		solenoidOut.set(true);
	}
	
	/**
	 * Sets the forklift motors to raise the {@link Grabbies}.
	 */
	public void forkliftFullExtend(double speed) {
		speed = speed*5;
		fVictorSPXL.set(ControlMode.PercentOutput, speed);
		fVictorSPXR.set(ControlMode.PercentOutput, speed);
		fVictorSPXL1.set(ControlMode.PercentOutput, speed);
		fVictorSPXR1.set(ControlMode.PercentOutput, speed);
		forkUp = true;
		forkDown = false;
	}

	/**
	 * Sets the forklift motors to lower the {@link Grabbies}.
	 */
	public void forkliftFullDistend(double speed) {
		speed = speed*5;
		fVictorSPXL.set(ControlMode.PercentOutput, speed);
		fVictorSPXR.set(ControlMode.PercentOutput, speed);
		fVictorSPXL1.set(ControlMode.PercentOutput, speed);
		fVictorSPXR1.set(ControlMode.PercentOutput, speed);
		forkUp = false;
		forkDown = true;
	}
	
	/**
	 * Used for debugging. Do not use in competition.
	 * @param b A Joyboi.
	 */
	public static void joystickBoi(Joystick b) {
		fVictorSPXL.set(ControlMode.PercentOutput, b.getY()*5);
		fVictorSPXR.set(ControlMode.PercentOutput, b.getY()*5);
		fVictorSPXL1.set(ControlMode.PercentOutput, b.getY()*5);
		fVictorSPXR1.set(ControlMode.PercentOutput, b.getY()*5);
	}
	
	/**
	 * Sets the forklift motors to hold their current position.
	 */
	public void forkliftHoldPoint() {
		fVictorSPXL.set(ControlMode.PercentOutput, 0);
		fVictorSPXR.set(ControlMode.PercentOutput, 0);
		fVictorSPXL1.set(ControlMode.PercentOutput, 0);
		fVictorSPXR1.set(ControlMode.PercentOutput, 0);
		forkUp = false;
		forkDown = false;
	}
	
	/**
	 * Checks if the switch has been pressed to prevent moving the forklift further.
	 * @return If the switch was activated.
	 */
	public boolean isSwitchSet() {
        return false;
    }
	
	/**
	 * Adds relevant class information to the FRC {@link SmartDashboard}.
	 */
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
