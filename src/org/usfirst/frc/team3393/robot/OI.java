package org.usfirst.frc.team3393.robot;

import org.usfirst.frc.team3393.robot.commands.ForkliftSolenoidDown;
import org.usfirst.frc.team3393.robot.commands.ForkliftSolenoidUp;
import org.usfirst.frc.team3393.robot.commands.ForkliftStop;
import org.usfirst.frc.team3393.robot.commands.ForkliftWinchDown;
import org.usfirst.frc.team3393.robot.commands.ForkliftWinchUp;
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
	
	private static JoystickButton slowBoiUp;
	private static JoystickButton slowBoiDown;
	
	public OI() {
		left = new Joystick(0);
		right = new Joystick(1);
		
		grabbieButton = new JoystickButton(left, 1);
		grabbieButton.whenPressed(new GrabbieIn());
		grabbieButton.whenReleased(new GrabbieStop());
		
		grabbieButton = new JoystickButton(right, 1);
		grabbieButton.whenPressed(new GrabbieOut());
		grabbieButton.whenReleased(new GrabbieStop());
		
		forkliftUpButton = new JoystickButton(left, 3);
		forkliftUpButton.whenPressed(new ForkliftSolenoidUp());
		
		forkliftUpButton2 = new JoystickButton(right, 3);
		forkliftUpButton2.whenPressed(new ForkliftWinchUp(0.8));
		forkliftUpButton2.whenReleased(new ForkliftStop());
		
		forkliftDownButton = new JoystickButton(left, 2);
		forkliftDownButton.whenPressed(new ForkliftSolenoidDown());
		
		forkliftDownButton2 = new JoystickButton(right, 2);
		forkliftDownButton2.whenPressed(new ForkliftWinchDown(-0.8));
		forkliftDownButton2.whenReleased(new ForkliftStop());
		
		
		slowBoiUp = new JoystickButton(right, 11);
		slowBoiUp.whenPressed(new ForkliftWinchUp(0.2));
		slowBoiUp.whenReleased(new ForkliftStop());
		
		slowBoiDown = new JoystickButton(right, 10);
		slowBoiDown.whenPressed(new ForkliftWinchDown(-0.2));
		slowBoiDown.whenReleased(new ForkliftStop());
		
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
