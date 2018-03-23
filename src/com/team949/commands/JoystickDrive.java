package com.team949.commands;

import com.team949.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Teleop command to drive the robot via Joystick.
 */
public class JoystickDrive extends Command {

	private final static double Y_THRESHOLD = 0.3;
	private final static double Z_THRESHOLD = 0.5;

	private final static double Y_NERF = 1.0;
	private final static double Z_NERF = 0.6;

	public JoystickDrive() {
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double yInput = Robot.oi.getDriveY();
		double zInput = Robot.oi.getDriveZ();

//		yInput = Y_NERF * (Math.abs(yInput) < Y_THRESHOLD ? 0 : yInput); 
//		zInput = Z_NERF * (Math.abs(zInput) < Z_THRESHOLD ? 0 : zInput);

//		if(yInput != 0.0)
//			yInput = (Math.signum(yInput) * ((Math.abs(yInput)- Y_THRESHOLD) / (1 - Y_THRESHOLD)));
//		if(zInput != 0.0)
//			zInput = (Math.signum(zInput) * ((Math.abs(zInput)- Z_THRESHOLD) / (1 - Z_THRESHOLD)));

		yInput = Y_NERF * (Math.abs(yInput) < Y_THRESHOLD ? 0 : (Math.signum(yInput) * ((Math.abs(yInput)- Y_THRESHOLD) / (1 - Y_THRESHOLD)))); 
		zInput = Z_NERF * (Math.abs(zInput) < Z_THRESHOLD ? 0 : (Math.signum(zInput) * ((Math.abs(zInput)- Z_THRESHOLD) / (1 - Z_THRESHOLD))));
		
//		System.out.printf("yinput: %f , zinput %f \n", yInput, zInput);
		Robot.driveTrain.arcade(yInput, zInput);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
