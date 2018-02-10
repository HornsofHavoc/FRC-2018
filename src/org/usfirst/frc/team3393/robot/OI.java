package org.usfirst.frc.team3393.robot;

import org.usfirst.frc.team3393.robot.commands.ForkliftFullDown;
import org.usfirst.frc.team3393.robot.commands.ForkliftFullUp;
import org.usfirst.frc.team3393.robot.commands.ForkliftStop;
import org.usfirst.frc.team3393.robot.commands.GrabbieIn;
import org.usfirst.frc.team3393.robot.commands.GrabbieOut;
import org.usfirst.frc.team3393.robot.commands.GrabbieStop;
import org.usfirst.frc.team3393.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private static Joystick left;
	private static Joystick right;
	
	private static JoystickButton grabbieButton;
	private static JoystickButton forkliftUpButton;
	private static JoystickButton forkliftDownButton;
	private static JoystickButton forkliftUpButton2;
	private static JoystickButton forkliftDownButton2;
	
	public OI() {
		left = new Joystick(0);
		right = new Joystick(1);
		
		grabbieButton = new JoystickButton(left, 1);
		grabbieButton.whenPressed(new GrabbieIn());
		grabbieButton.whenReleased(new GrabbieStop());
		
		grabbieButton = new JoystickButton(right, 1);
		grabbieButton.whenPressed(new GrabbieOut());
		grabbieButton.whenReleased(new GrabbieStop());
		
		forkliftUpButton = new JoystickButton(left, 4);
		forkliftUpButton.whenPressed(new ForkliftFullUp());
		forkliftUpButton.whenReleased(new ForkliftStop());
		
		forkliftUpButton2 = new JoystickButton(right, 4);
		forkliftUpButton2.whenPressed(new ForkliftFullUp());
		forkliftUpButton2.whenReleased(new ForkliftStop());
		
		forkliftDownButton = new JoystickButton(left, 5);
		forkliftDownButton.whenPressed(new ForkliftFullDown());
		forkliftDownButton.whenReleased(new ForkliftStop());
		
		forkliftDownButton2 = new JoystickButton(right, 5);
		forkliftDownButton2.whenPressed(new ForkliftFullDown());
		forkliftDownButton2.whenReleased(new ForkliftStop());
		
		SmartDashboard.putData("Tank Drive", new TankDrive());
	}
	
	/**
	 * @return An instance of Joystick on USB port (0).
	 */
	public Joystick getLeftJoystick() {
		return left;
	}
	
	/**
	 * @return An instance of Joystick on USB port (1).
	 */
	public Joystick getRightJoystick() {
		return right;
	}
	
	/**
	 * Adds relevant class information to the FRC SmartDashboard.
	 */
	public void reportToSmartDashboard() {
		SmartDashboard.putString("Joystick Left", left.getY()+"");
		SmartDashboard.putString("Joystick Right", right.getY()+"");
	}
}
