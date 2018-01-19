package org.usfirst.frc.team3393.robot.subsystems;

import org.usfirst.frc.team3393.robot.RobotMap;
import org.usfirst.frc.team3393.robot.commands.TankDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem{
	
	private static WPI_TalonSRX talonLeft;
	private static WPI_TalonSRX talonRight;
	
	private static Spark sparkLeft;
	private static Spark sparkRight;
	private static SpeedControllerGroup driveLeft;
	private static SpeedControllerGroup driveRight;
	private static DifferentialDrive drivetrain;
	private Encoder encoderLeft;
	private Encoder encoderRight;
	
	public DriveTrain(){
		encoderLeft = new Encoder(RobotMap.dEncoderL1, RobotMap.dEncoderL2);
		encoderRight = new Encoder(RobotMap.dEncoderR1, RobotMap.dEncoderR2);
		encoderLeft.setDistancePerPulse((1.0/360.0));
		encoderRight.setDistancePerPulse((1.0/360.0));
		talonLeft = new WPI_TalonSRX(RobotMap.dTalonL);
		talonRight = new WPI_TalonSRX(RobotMap.dTalonR);
		sparkLeft = new Spark(RobotMap.dSparkL);
		sparkRight = new Spark(RobotMap.dSparkR);
		driveLeft = new SpeedControllerGroup(talonLeft, sparkLeft);
		driveRight = new SpeedControllerGroup(talonRight, sparkRight);
		drivetrain = new DifferentialDrive(driveLeft, driveRight);
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new TankDrive());
	}
	
	public double getLeftEncoderTicks(){
		return encoderLeft.getRaw();
	}
	
	public double getRightEncoderTicks(){
		return encoderRight.getRaw();
	}
	
	public void resetEncoders(){
		encoderLeft.reset();
		encoderRight.reset();
	}

	public WPI_TalonSRX getTalonLeft() {
		return talonLeft;
	}

	public void setTalonLeft(WPI_TalonSRX talonLeft) {
		DriveTrain.talonLeft = talonLeft;
	}

	public WPI_TalonSRX getTalonRight() {
		return talonRight;
	}

	public void setTalonRight(WPI_TalonSRX talonRight) {
		DriveTrain.talonRight = talonRight;
	}

	public Spark getSparkLeft() {
		return sparkLeft;
	}

	public void setSparkLeft(Spark sparkLeft) {
		DriveTrain.sparkLeft = sparkLeft;
	}

	public Spark getSparkRight() {
		return sparkRight;
	}

	public void setSparkRight(Spark sparkRight) {
		DriveTrain.sparkRight = sparkRight;
	}

	public SpeedControllerGroup getDriveLeft() {
		return driveLeft;
	}

	public void setDriveLeft(SpeedControllerGroup driveLeft) {
		DriveTrain.driveLeft = driveLeft;
	}

	public SpeedControllerGroup getDriveRight() {
		return driveRight;
	}

	public void setDriveRight(SpeedControllerGroup driveRight) {
		DriveTrain.driveRight = driveRight;
	}

	public DifferentialDrive getDrivetrain() {
		return drivetrain;
	}

	public void setDrivetrain(DifferentialDrive drivetrain) {
		DriveTrain.drivetrain = drivetrain;
	}

	public Encoder getLeftEncoder() {
		return encoderLeft;
	}
	
	public Encoder getRightEncoder() {
		return encoderRight;
	}
	
	public void reportToSmartDashboard(){
		SmartDashboard.putNumber("Left Encoder", encoderLeft.getRaw());
		SmartDashboard.putNumber("Left Encoder Distance", encoderLeft.getDistance());
		SmartDashboard.putNumber("Right Encoder", encoderRight.getRaw());
		SmartDashboard.putNumber("Right Encoder Distance", encoderRight.getDistance());
	}

}
