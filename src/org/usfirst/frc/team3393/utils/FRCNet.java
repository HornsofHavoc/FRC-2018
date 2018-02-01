package org.usfirst.frc.team3393.utils;

import java.util.List;

import edu.wpi.first.networktables.NetworkTable;

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

}
