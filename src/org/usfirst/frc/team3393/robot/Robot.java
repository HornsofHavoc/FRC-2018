package org.usfirst.frc.team3393.robot;

import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3393.robot.commands.auto.DriveRotate;
import org.usfirst.frc.team3393.robot.commands.auto.DriveStraight;
import org.usfirst.frc.team3393.robot.commands.auto.TurnTowardsObject;
import org.usfirst.frc.team3393.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3393.robot.subsystems.Forklift;
import org.usfirst.frc.team3393.robot.subsystems.Grabbies;
import org.usfirst.frc.team3393.robot.subsystems.Ultrasonic;
import org.usfirst.frc.team3393.robot.vision.CameraBoi;
import org.usfirst.frc.team3393.robot.vision.GripPipeline;
import org.usfirst.frc.team3393.utils.FRCNet;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * If just loaded from git you'll need to manually add the wpi nature in the .project file
 * <nature>edu.wpi.first.wpilib.plugins.core.nature.FRCProjectNature</nature>
 */
@SuppressWarnings("unused")
public class Robot extends IterativeRobot {
	
	public static DriverStation dslink;
	public static CameraBoi camera;
	
	public static VisionThread visionThread;
	public static final Object imgLock = new Object();
	public static MjpegServer contourServer;
	public static CvSource contourSource;
	public static Mat contourImage;
	
	final boolean OPEN_GRABBIE = (false);
	final boolean CLOSE_GRABBIE = (true);

	public static DriveTrain drivetrain;
	public static Forklift forklift;
	public static Grabbies grabbies;
	public static OI oi;
	
	public static PowerDistributionPanel pdp;
	public static Compressor compressor;
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	public static double centerX;
	public static double center;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		pdp = new PowerDistributionPanel(0);
		compressor = new Compressor(0);
		
		drivetrain = new DriveTrain();
		forklift = new Forklift();
		grabbies = new Grabbies();
		
		oi = new OI();
		//chooser.addDefault("Drive Rotate", new DriveRotate(-1440.0));
		//chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(forklift);
		SmartDashboard.putData(grabbies);
		
		camera = new CameraBoi(CameraServer.getInstance().startAutomaticCapture(0));
		camera.getCamera().setVideoMode(PixelFormat.kMJPEG, 320, 240, 30);
		contourServer = new MjpegServer("contours", 1189);
		contourSource = new CvSource("contours", PixelFormat.kMJPEG, 320, 240, 10);
		contourImage = new Mat(320, 240, 11);
		visionThread = new VisionThread(camera.getCamera(), new GripPipeline(), new VisionRunner.Listener<GripPipeline>() {
			//double centerX;
			@Override
			public void copyPipelineOutputs(GripPipeline pipeline) {
				if (!pipeline.filterContoursOutput().isEmpty()) {
		            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
		            synchronized (imgLock) {
		                centerX = r.x + (r.width / 2);
		                List<MatOfPoint> hulls = pipeline.convexHullsOutput();
		                Mat image32S = new Mat();
		                contourImage.convertTo(image32S, CvType.CV_32SC1);

		                Imgproc.findContours(image32S, hulls, new Mat(), Imgproc.RETR_FLOODFILL, Imgproc.CHAIN_APPROX_SIMPLE);

		                // Draw all the contours such that they are filled in.
		                Mat contourImg = new Mat(image32S.size(), image32S.type());
		                for (int i = 0; i < hulls.size(); i++) {
		                    Imgproc.drawContours(contourImg, hulls, i, new Scalar(255, 255, 255), -1);
		                }
		                contourSource.putFrame(contourImg);
		                //FRCNet.readNetworkTableContours();
		            }
		        } else {
		        	synchronized (imgLock) {
		                centerX = 0;
		                //FRCNet.readNetworkTableContours();
		            }
		        }
			}
		});
	    visionThread.start();
		
	}
	
	public static void onEnabled() {
		drivetrain.resetEncoders();
		dslink = DriverStation.getInstance();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		onEnabled();
		autonomousCommand = chooser.getSelected();
		//SmartDashboard.putString("Game Specific Message", dslink.getGameSpecificMessage());
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		// schedule the autonomous command (example)
		new TurnTowardsObject().start();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		synchronized (imgLock) {
			this.center = this.centerX;
			
//			String fms;
//			fms = DriverStation.getInstance().getGameSpecificMessage ();
//			if(fms.charAt(0) == '')
//			{
//			//Put left auto code here
//			} else {
//			//Put right auto code here
//			}
		}
		Timer.delay(.0001);
	}

	@Override
	public void teleopInit() {
		onEnabled();
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		oi.reportToSmartDashboard();
		drivetrain.reportToSmartDashboard();
		forklift.reportToSmartDashboard();
		grabbies.reportToSmartDashboard();
		Ultrasonic.reportToSmartDashBoard();
		Timer.delay(0.0001);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}
}