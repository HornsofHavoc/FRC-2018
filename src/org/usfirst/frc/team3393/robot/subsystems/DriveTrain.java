package org.usfirst.frc.team3393.robot.subsystems;

import org.usfirst.frc.team3393.robot.RobotMap;
import org.usfirst.frc.team3393.robot.commands.TankDrive;
import org.usfirst.frc.team3393.utils.Maths;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

/**
 * A {@link Subsystem} that handles methods related to moving parts of the robot's drivetrain.
 */
public class DriveTrain extends Subsystem{
	
	private WPI_TalonSRX dTalonL;
	private WPI_TalonSRX dTalonR;
	private WPI_TalonSRX dTalonL1;
	private WPI_TalonSRX dTalonR1;
	private SpeedControllerGroup driveLeft;
	private SpeedControllerGroup driveRight;
	private DifferentialDrive drivetrain;
	private Encoder encoderLeft;
	private Encoder encoderRight;
	private ADXRS450_Gyro gyro;
	
	public DriveTrain(){
		encoderLeft = new Encoder(RobotMap.dEncoderL1, RobotMap.dEncoderL2);
		encoderRight = new Encoder(RobotMap.dEncoderR1, RobotMap.dEncoderR2);
		encoderLeft.setDistancePerPulse((1.0/360.0));
		encoderRight.setDistancePerPulse((1.0/360.0));
		encoderRight.setReverseDirection(true);
		gyro = new ADXRS450_Gyro();
		dTalonL = new WPI_TalonSRX(RobotMap.dTalonL);
		dTalonR = new WPI_TalonSRX(RobotMap.dTalonR);
		dTalonL1 = new WPI_TalonSRX(RobotMap.dTalonL1);
		dTalonR1 = new WPI_TalonSRX(RobotMap.dTalonR1);
		driveLeft = new SpeedControllerGroup(dTalonL, dTalonL1);
		driveRight = new SpeedControllerGroup(dTalonR, dTalonR1);
		drivetrain = new DifferentialDrive(driveLeft, driveRight);
		
		gyro.calibrate();
	}

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new TankDrive());
	}
	
	/**
	 * @return The number of rotations completed by the left drive encoder, {@link #encoderLeft}, since it was last reset.
	 */
	public double getLeftEncoderTicks(){
		return encoderLeft.getRaw();
	}
	
	/**
	 * @return The number of rotations completed by the right drive encoder, {@link #encoderRight}, since it was last reset.
	 */
	public double getRightEncoderTicks(){
		return encoderRight.getRaw();
	}
	
	/**
	 * Resets the drivetrain encoder ticks and the gyro direction. Called when Autonomous and Teleop are enabled.
	 */
	public void resetEncoders(){
		encoderLeft.reset();
		encoderRight.reset();
		gyro.reset();
	}

	/**
	 * Returns an instance of {@link DifferentialDrive} used to drive the robot.
	 * @return A DifferentialDrive made up of four two sets of {@link SpeedControllerGroup}
	 */
	public DifferentialDrive getDrivetrain() {
		return drivetrain;
	}

	/**
	 * @return An {@link Encoder}, {@link #encoderLeft}, associated with this instance of Drivetrain.
	 */
	public Encoder getLeftEncoder() {
		return encoderLeft;
	}
	
	/**
	 * @return An {@link Encoder}, {@link #encoderRight}, associated with this instance of Drivetrain.
	 */
	public Encoder getRightEncoder() {
		return encoderRight;
	}
	
	/**
	 * Adds relevant class information to the FRC {@link SmartDashboard}.
	 */
	public void reportToSmartDashboard(){
		SmartDashboard.putNumber("Left Encoder", encoderLeft.getRaw());
		SmartDashboard.putNumber("Left Encoder Distance", encoderLeft.getDistance());
		SmartDashboard.putNumber("Left Encoder Feet", Maths.getEncoderFeet(encoderLeft));
		SmartDashboard.putNumber("Right Encoder", encoderRight.getRaw());
		SmartDashboard.putNumber("Right Encoder Distance", encoderRight.getDistance());
		SmartDashboard.putNumber("Right Encoder Feet", Maths.getEncoderFeet(encoderRight));
		SmartDashboard.putNumber("Gyro Rate", gyro.getRate());
		SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
	}

	public ADXRS450_Gyro getGyro() {
		return gyro;
	}

	public WPI_TalonSRX getdTalonL() {
		return dTalonL;
	}

	public WPI_TalonSRX getdTalonR() {
		return dTalonR;
	}

	public WPI_TalonSRX getdTalonL1() {
		return dTalonL1;
	}

	public WPI_TalonSRX getdTalonR1() {
		return dTalonR1;
	}

}
