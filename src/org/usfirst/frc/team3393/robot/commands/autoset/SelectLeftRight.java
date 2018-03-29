package org.usfirst.frc.team3393.robot.commands.autoset;

import org.usfirst.frc.team3393.robot.Robot;
import org.usfirst.frc.team3393.robot.commands.GrabbieUp;
import org.usfirst.frc.team3393.robot.commands.auto.DriveRotateNegative;
import org.usfirst.frc.team3393.robot.commands.auto.DriveRotatePositive;
import org.usfirst.frc.team3393.robot.commands.auto.DriveStraight;
import org.usfirst.frc.team3393.robot.commands.auto.DriveStraight2;
import org.usfirst.frc.team3393.utils.FRCNet;
import org.usfirst.frc.team3393.utils.Maths;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SelectLeftRight extends CommandGroup {
	
	public SelectLeftRight() {
//		if(FRCNet.getNearSwitch()=='L') {
//			this.addSequential(new GrabbieUp(1.0));
//			this.addSequential(new DriveStraight(50));
//			this.addSequential(new DriveRotateNegative(90));
//			this.addSequential(new DriveStraight2(80));
//			this.addSequential(new DriveRotatePositive(90));
//			this.addSequential(new AlignedTrackAuto());
//		} else if (FRCNet.getNearSwitch()=='R') {
//			this.addSequential(new GrabbieUp(1.0));
//			this.addSequential(new DriveStraight(50));
//			this.addSequential(new DriveRotatePositive(90));
//			this.addSequential(new DriveStraight2(66));
//			this.addSequential(new DriveRotateNegative(90));
//			this.addSequential(new AlignedTrackAuto());
//		}
	}
	
	@Override
	protected void initialize() {
		if(FRCNet.getNearSwitch()=='L') {
			this.addSequential(new GrabbieUp());
			this.addSequential(new DriveStraight(50));
			this.addSequential(new DriveRotateNegative(90));
			this.addSequential(new DriveStraight2(80));
			this.addSequential(new DriveRotatePositive(90));
			this.addSequential(new AlignedTrackAuto());
		} else if (FRCNet.getNearSwitch()=='R') {
			this.addSequential(new GrabbieUp());
			this.addSequential(new DriveStraight(50));
			this.addSequential(new DriveRotatePositive(90));
			this.addSequential(new DriveStraight2(66));
			this.addSequential(new DriveRotateNegative(90));
			this.addSequential(new AlignedTrackAuto());
		}
	}
	
}
