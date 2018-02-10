package org.usfirst.frc.team3393.utils;

import java.util.List;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * Deals with networking between the field and robot systems.
 */
public class FRCNet {
	
	public String crArea;
	public String crCenterX;
	public String crCenterY;
	public String crWidth;
	public String crHeight;
	public String crSolidity;
	
	public static void readNetworkTableContours() {
		List<String> nt = NetworkTable.getHierarchy("GRIP/myContoursReport");
		for(String s: nt) {
			System.out.println(s);
		}
	}
	
	public static DriverStation getDriverStation() {
		return DriverStation.getInstance();
	}

}
