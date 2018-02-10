package org.usfirst.frc.team3393.robot.vision;

import edu.wpi.cscore.UsbCamera;

public class CameraBoi {
	
	private UsbCamera camera;
	
	public CameraBoi(UsbCamera camera) {
		this.camera = camera;
	}

	public UsbCamera getCamera() {
		return camera;
	}

}
