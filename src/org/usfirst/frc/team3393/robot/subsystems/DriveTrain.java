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
	private Encoder driveEncoder;
	
	public DriveTrain(){
		driveEncoder = new Encoder(RobotMap.rEncoder1, RobotMap.rEncoder2);
		driveEncoder.setDistancePerPulse((1.0/360.0));
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
	
	public double getDriveEncoderTicks(){
		return driveEncoder.getRaw();
	}
	
	public void resetEncoders(){
		driveEncoder.reset();
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

	public Encoder getDriveEncoder() {
		return driveEncoder;
	}

	public void setDriveEncoder(Encoder driveEncoder) {
		this.driveEncoder = driveEncoder;
	}
	
	public void reportToSmartDashboard(){
		SmartDashboard.putNumber("Drive Encoder", driveEncoder.getRaw());
		SmartDashboard.putNumber("Encoder Distance", driveEncoder.getDistance());
	}

}
