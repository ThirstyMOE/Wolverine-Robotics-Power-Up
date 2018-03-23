package com.team949.commands;

import java.io.File;

import com.team949.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 */
public class JoyStickArm extends Command {

	private final static double Y_THRESHOLD = 0.9;
	private final static double DEFAULT_MULTIPLIER = 1.0;
	private final static double NERF_MULTIPLIER = 0.5;

	public JoyStickArm() {
		requires(Robot.arm);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double input = -Robot.oi.getOperatorY();
		if (Math.abs(input) >= Y_THRESHOLD) // Down
		{
			/*
			 * Needs set up for encoders first.
			 */
//			if (Robot.arm.getEncoderPosition() > (100. / 360 * 4096)) {
//				input *= NERF_MULTIPLIER;
//			}
//			if (Robot.arm.getEncoderPosition() < (30. / 360 * 4096)) {
//				input = NERF_MULTIPLIER * Math.max(input, 0);
//			}
			
			Robot.arm.move(DEFAULT_MULTIPLIER * Math.signum(input) * ((Math.abs(input)-Y_THRESHOLD) / (1 - Y_THRESHOLD))); // TODO: Not finalized
														// control system yet.
		}
		else 
		{
			Robot.arm.move(0);
		}
		//System.out.println(Robot.arm.getEncoderPosition());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
