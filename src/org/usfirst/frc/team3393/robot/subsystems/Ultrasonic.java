package org.usfirst.frc.team3393.robot.subsystems;

import org.usfirst.frc.team3393.robot.RobotMap;
import org.usfirst.frc.team3393.robot.commands.GrabbieStop;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Ultrasonic extends Subsystem {
	
	 private static final AnalogInput ultrasonic = new AnalogInput(RobotMap.sUltrasonic);
	 
	 private static final double getInches = 1.0;
	
	 public static double getVoltage() {
		 return ultrasonic.getVoltage();
	 }
	 
	  public static double getDistance() {
		  return getVoltage() * getInches;
		 
	  }
	  
	  public static void reportToSmartDashBoard() {
		  SmartDashboard.putNumber("Distance (volts)", getVoltage());
		  SmartDashboard.putNumber("Distance (distance)", getDistance());
	  }
	  
	  @Override
	  protected void initDefaultCommand() {
		  this.setDefaultCommand(null);
	  }

}
