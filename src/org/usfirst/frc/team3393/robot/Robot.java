package org.usfirst.frc.team3393.robot;

import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3393.robot.commands.auto.DriveStraight;
import org.usfirst.frc.team3393.robot.commands.auto.DriveTowardObject;
import org.usfirst.frc.team3393.robot.commands.auto.FollowObject;
import org.usfirst.frc.team3393.robot.commands.auto.TurnTowardObject;
import org.usfirst.frc.team3393.robot.commands.autoset.AlignedTrackAuto;
import org.usfirst.frc.team3393.robot.commands.autoset.StraightAheadAuto;
import org.usfirst.frc.team3393.robot.commands.autoset.SwitchLeftStartCenter;
import org.usfirst.frc.team3393.robot.commands.autoset.SwitchRightStartCenter;
import org.usfirst.frc.team3393.robot.subsystems.Climber;
import org.usfirst.frc.team3393.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3393.robot.subsystems.Forklift;
import org.usfirst.frc.team3393.robot.subsystems.Grabbies;
import org.usfirst.frc.team3393.robot.vision.CameraBoi;
import org.usfirst.frc.team3393.robot.vision.GripPipeline;
import org.usfirst.frc.team3393.utils.FRCNet;
import org.usfirst.frc.team3393.utils.Maths;
import org.usfirst.frc.team3393.utils.TrackingSelector;

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
 *  * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * If just loaded from git you'll need to manually add the wpi nature in the .project file
 * <nature>edu.wpi.first.wpilib.plugins.core.nature.FRCProjectNature</nature>
 * @author MccluAS
 *
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
	public static Climber climber;
	public static OI oi;
	
	public static PowerDistributionPanel pdp;
	public static Compressor compressor;
	
	Command autonomousCommand = null;
	Command autoComm = null;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	public static double centerX;
	public static double center;
	public static double distI;
	public static double dist;
	
	public static double centerX2;
	public static double center2;
	public static double distI2;
	public static double dist2;
	
	public static String gameData;

	/**
	 * This function is run when the robot is first started up and contains initialization code.
	 */
	@Override
	public void robotInit() {
		pdp = new PowerDistributionPanel(0);
		compressor = new Compressor(0);
		
		drivetrain = new DriveTrain();
		forklift = new Forklift();
		grabbies = new Grabbies();
		climber = new Climber();
		
		oi = new OI();
		//chooser.addDefault("Drive Rotate", new DriveRotate(-1440.0));
		//chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(forklift);
		SmartDashboard.putData(grabbies);
		SmartDashboard.putData(climber);
		
		camera = new CameraBoi(CameraServer.getInstance().startAutomaticCapture(0));
		camera.getCamera().setVideoMode(PixelFormat.kMJPEG, 640, 480, 30);
		camera.getCamera().setExposureManual(18);
		camera.getCamera().setBrightness(18);
		contourServer = new MjpegServer("contours", 1189);
		contourSource = new CvSource("contours", PixelFormat.kGray, 640, 480, 10);
		SmartDashboard.putString("Contour Server", contourServer.getListenAddress()+":"+contourServer.getPort());
		contourServer.setSource(contourSource);
//		contourImage = new Mat(320, 240, CvType.CV_32SC1);
		visionThread = new VisionThread(camera.getCamera(), new GripPipeline(), new VisionRunner.Listener<GripPipeline>() {
			//double centerX;
			@Override
			public void copyPipelineOutputs(GripPipeline pipeline) {
				if (pipeline.filterContoursOutput().size()>0) {
		            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
		            synchronized (imgLock) {
		                centerX = r.x + (r.width / 2);
		                distI = 0;
		                if(r.area()>30) {
		                	distI = Maths.getObjectDistance(r.width, 2.0);
		                }
		                SmartDashboard.putNumber("Object (0) Width (Pixels)", r.width);
		                SmartDashboard.putNumber("Object (0) Distance (Inches)", Maths.getObjectDistance(r.width, 2.0));
		                //FRCNet.readNetworkTableContours();
		            }
		        } else {
		        	synchronized (imgLock) {
		                centerX = 0;
		                distI = 0;
		                //FRCNet.readNetworkTableContours();
		            }
		        }
				if (pipeline.filterContoursOutput().size()>1) {
		            Rect r2 = Imgproc.boundingRect(pipeline.filterContoursOutput().get(1));
		            synchronized (imgLock) {
		                centerX2 = r2.x + (r2.width / 2);
		                distI2 = 0;
		                if(r2.area()>30) {
		                	distI2 = Maths.getObjectDistance(r2.width, 2.0);
		                }
		                SmartDashboard.putNumber("Object (1) Width (Pixels)", r2.width);
		                SmartDashboard.putNumber("Object (1) Distance (Inches)", Maths.getObjectDistance(r2.width, 2.0));
		                //FRCNet.readNetworkTableContours();
		            }
		        } else {
		        	synchronized (imgLock) {
		                centerX2 = 0;
		                distI2 = 0;
		                //FRCNet.readNetworkTableContours();
		            }
		        }
//				List<MatOfPoint> hulls = pipeline.findContoursOutput();

                // Draw all the contours such that they are filled in.
//                for (int i = 0; i < hulls.size(); i++) {
//                    Imgproc.drawContours(contourImage, hulls, i, new Scalar(255), 3);
//                }
                contourSource.putFrame(pipeline.hsvThresholdOutput());
                SmartDashboard.putNumber("center", (centerX2+centerX)/2);
//                contourImage = new Mat(320, 240, CvType.CV_32SC1);
			}
		});
	    visionThread.start();
		
	}
	
	/**
	 * This function must be called no matter the reasoning for the robot starting, whether in autonomous or teleop.
	 */
	public static void onEnabled() {
		drivetrain.resetEncoders();
		dslink = DriverStation.getInstance();
		FRCNet.resetData();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called at the start of the autonomous phase of competition.
	 * It runs the appropriate command for the placement and objective of the robot provided FMS information.
	 */
	@Override
	public void autonomousInit() {
		onEnabled();
		autonomousCommand = chooser.getSelected();
		
		SmartDashboard.putString("Near Switch", ""+FRCNet.getNearSwitch());
		SmartDashboard.putString("Scale", ""+FRCNet.getScale());
		SmartDashboard.putString("Far Switch", ""+FRCNet.getFarSwitch());
		//SmartDashboard.putString("Game Specific Message", dslink.getGameSpecificMessage());
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		// schedule the autonomous command (example)
		//autoComm = new FollowObject(TrackingSelector.AVERAGE, TrackingSelector.AVERAGE, 24);
		if(FRCNet.getNearSwitch()=='L') {
			autoComm = new SwitchLeftStartCenter();
		} else if (FRCNet.getNearSwitch()=='R') {
			autoComm = new SwitchRightStartCenter();
		}
		autoComm.start();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		synchronized (imgLock) {
			this.center = this.centerX;
			this.dist = this.distI;
			
//			String fms;
//			fms = DriverStation.getInstance().getGameSpecificMessage ();
//			if(fms.charAt(0) == '')
//			{
//			//Put left auto code here
//			} else {
//			//Put right auto code here
//			}
		}
		//if(!autoComm.isRunning()) {
			//autoComm = new FollowObject(TrackingSelector.AVERAGE, TrackingSelector.AVERAGE, 24);
			//autoComm.start();
		//}
		Timer.delay(.0001);
	}

	/**
	 * This function is called at the beginning of the teleoperated phase of competition.
	 */
	@Override
	public void teleopInit() {
		onEnabled();
		SmartDashboard.putString("Near Switch", ""+FRCNet.getNearSwitch());
		SmartDashboard.putString("Scale", ""+FRCNet.getScale());
		SmartDashboard.putString("Far Switch", ""+FRCNet.getFarSwitch());
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
		reportToSmartDashboard();
		Timer.delay(0.0001);
	}
	
	public static void reportToSmartDashboard() {
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
	}
}