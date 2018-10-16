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
	
	private boolean forkUp = false;
	private boolean forkDown = false;
	
	private Solenoid solenoidIn;
	private Solenoid solenoidOut;
	private DigitalInput fLimitB;
	
	private Spark fSparkL;
	private Spark fSparkR;
	
	public Forklift() {
		solenoidIn = new Solenoid(RobotMap.solenoidIn);
		solenoidOut = new Solenoid(RobotMap.solenoidOut);
		fLimitB = new DigitalInput(RobotMap.fLimitB);
		fSparkL = new Spark(RobotMap.fSparkL);
		fSparkR = new Spark(RobotMap.fSparkR);
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
		fSparkL.set(speed);
		fSparkR.set(speed);
		forkUp = true;
		forkDown = false;
	}

	/**
	 * Sets the forklift motors to lower the {@link Grabbies}.
	 */
	public void forkliftFullDistend(double speed) {
		speed = speed*5;
		fSparkL.set(speed);
		fSparkR.set(speed);
		forkUp = false;
		forkDown = true;
	}
	
	/**
	 * Used for debugging. Do not use in competition.
	 * @param b A Joyboi.
	 */
	public void joystickBoi(Joystick b) {
		fSparkL.set(b.getY()*5);
		fSparkR.set(b.getY()*5);
	}
	
	/**
	 * Sets the forklift motors to hold their current position.
	 */
	public void forkliftHoldPoint() {
		fSparkL.set(0);
		fSparkR.set(0);
		forkUp = false;
		forkDown = false;
	}
	
	/**
	 * Checks if the switch has been pressed to prevent moving the forklift further.
	 * @return If the switch was activated.
	 */
	public boolean isSwitchSet() {
        return !(fLimitB.get());
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
		SmartDashboard.putBoolean("Forklift Switch", isSwitchSet());
	}

}
