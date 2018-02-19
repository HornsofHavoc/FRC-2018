package org.usfirst.frc.team3393.utils;

import java.util.List;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Deals with networking between the field and robot systems.
 */
public class FRCNet {
	
	private static boolean readFieldData = false;
	
	private static String matchAlliance = "unavailable";
	private static String matchEvent = "unavailable";
	private static String matchFieldData = "unavailable";
	
	private static char fieldDataNS = 'U';
	private static char fieldDataS = 'U';
	private static char fieldDataFS = 'U';
	
	public static DriverStation getDriverStation() {
		return DriverStation.getInstance();
	}
	
	public static void readMatchData() {
		matchAlliance = getDriverStation().getAlliance().name();
		matchEvent = getDriverStation().getEventName();
		matchFieldData = getDriverStation().getGameSpecificMessage();
		fieldDataNS = matchFieldData.charAt(0);
		fieldDataS = matchFieldData.charAt(1);
		fieldDataFS = matchFieldData.charAt(2);
	}

	public static String getMatchAlliance() {
		if(!readFieldData) { readMatchData(); }
		return matchAlliance;
	}

	public static String getMatchEvent() {
		if(!readFieldData) { readMatchData(); }
		return matchEvent;
	}

	public static String getMatchFieldData() {
		if(!readFieldData) { readMatchData(); }
		return matchFieldData;
	}
	
	public static char getNearSwitch() {
		if(!readFieldData) { readMatchData(); }
		return fieldDataNS;
	}
	
	public static char getScale() {
		if(!readFieldData) { readMatchData(); }
		return fieldDataS;
	}

	public static char getFarSwitch() {
		if(!readFieldData) { readMatchData(); }
		return fieldDataFS;
	}
	
	public static void reportToSmartDashboard() {
		if(!readFieldData) { readMatchData(); }
		SmartDashboard.putString("Alliance", matchAlliance);
		SmartDashboard.putString("Event", matchEvent);
		SmartDashboard.putString("Field Data", matchFieldData);
	}
	
	public static void resetData() {
		readFieldData = false;
	}

}
