package org.usfirst.frc.team3393.robot;

import org.usfirst.frc.team3393.robot.commands.ClimberDown;
import org.usfirst.frc.team3393.robot.commands.ClimberStop;
import org.usfirst.frc.team3393.robot.commands.ClimberUp;
import org.usfirst.frc.team3393.robot.commands.ForkliftSolenoidDown;
import org.usfirst.frc.team3393.robot.commands.ForkliftSolenoidUp;
import org.usfirst.frc.team3393.robot.commands.ForkliftStop;
import org.usfirst.frc.team3393.robot.commands.ForkliftWinchDown;
import org.usfirst.frc.team3393.robot.commands.ForkliftWinchUp;
import org.usfirst.frc.team3393.robot.commands.GrabbieDown;
import org.usfirst.frc.team3393.robot.commands.GrabbieIn;
import org.usfirst.frc.team3393.robot.commands.GrabbieOut;
import org.usfirst.frc.team3393.robot.commands.GrabbieStop;
import org.usfirst.frc.team3393.robot.commands.GrabbieUp;
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
	
	private static JoystickButton grabbieButtonIn;
	private static JoystickButton grabbieButtonOut;
	
	private static JoystickButton grabbieUpButton;
	private static JoystickButton grabbieDownButton;
	
	private static JoystickButton forkliftUpButton;
	private static JoystickButton forkliftDownButton;
	private static JoystickButton forkliftUpButton2;
	private static JoystickButton forkliftDownButton2;
	
	//private static JoystickButton climberUp;
	//private static JoystickButton climberDown;
	
	private static JoystickButton slowBoiUp;
	private static JoystickButton slowBoiDown;
	
	public OI() {
		left = new Joystick(0);
		right = new Joystick(1);
		
		grabbieButtonIn = new JoystickButton(left, 1);
		grabbieButtonIn.whenPressed(new GrabbieIn(0.5));
		grabbieButtonIn.whenReleased(new GrabbieStop());
		
		grabbieButtonOut = new JoystickButton(right, 1);
		grabbieButtonOut.whenPressed(new GrabbieOut(0.5));
		grabbieButtonOut.whenReleased(new GrabbieStop());
		
		grabbieUpButton = new JoystickButton(left, 6);
		grabbieUpButton.whenPressed(new GrabbieUp());
		
		grabbieDownButton = new JoystickButton(left, 7);
		grabbieDownButton.whenPressed(new GrabbieDown());
		
		forkliftUpButton = new JoystickButton(left, 3);
		forkliftUpButton.whenPressed(new ForkliftSolenoidUp());
		
		forkliftUpButton2 = new JoystickButton(right, 3);
		forkliftUpButton2.whenPressed(new ForkliftWinchUp(0.8));
		forkliftUpButton2.whenReleased(new ForkliftStop());
		
		forkliftDownButton = new JoystickButton(left, 2);
		forkliftDownButton.whenPressed(new ForkliftSolenoidDown());
		
		forkliftDownButton2 = new JoystickButton(right, 2);
		forkliftDownButton2.whenPressed(new ForkliftWinchDown(0.8));
		forkliftDownButton2.whenReleased(new ForkliftStop());
		
//		climberUp = new JoystickButton(left, 6);
//		climberUp.whenPressed(new ClimberUp());
//		climberUp.whenReleased(new ClimberStop());
//		
//		climberDown = new JoystickButton(left, 7);
//		climberDown.whenPressed(new ClimberDown());
//		climberDown.whenReleased(new ClimberStop());
		
		slowBoiUp = new JoystickButton(right, 11);
		slowBoiUp.whenPressed(new ForkliftWinchUp(0.2));
		slowBoiUp.whenReleased(new ForkliftStop());
		
		slowBoiDown = new JoystickButton(right, 10);
		slowBoiDown.whenPressed(new ForkliftWinchDown(0.2));
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
