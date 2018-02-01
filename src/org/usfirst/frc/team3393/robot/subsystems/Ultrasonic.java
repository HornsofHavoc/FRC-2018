package org.usfirst.frc.team3393.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Ultrasonic extends Subsystem {
	
	 private static final AnalogInput ultrsonic = new AnalogInput(0);
	 
	 private static final double getInches = 0.997;
	
	 public static double getVoltage() {
		 return ultrsonic.getVoltage();
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
		// TODO Auto-generated method stub
		
	}

}
