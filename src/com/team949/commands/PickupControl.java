package com.team949.commands;

import javax.xml.bind.helpers.AbstractUnmarshallerImpl;

import org.omg.CORBA.WCharSeqHolder;

import com.team949.Constants;
import com.team949.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PickupControl extends Command {

	private final static double Z_THRESHOLD = 0.9;
	private final static double WRIST_MULTIPLIER = 0.5;

	public PickupControl() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.hand);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// double[] leftRightInputs =
		// Constants.arcadeToTank(Robot.oi.getOperatorY(),
		// Robot.oi.getOperatorZ());
		// Robot.pickup.setPickup(leftRightInputs[0], leftRightInputs[1]);
		int pov = Robot.oi.getOperatorPOV();

		// Hand logic
		if (Robot.oi.isOperatorButtonDown(12)) // Out
		{
			Robot.hand.setIntake(-1.0);
		} else if (Robot.oi.isOperatorButtonDown(2)) // slow out
		{
			Robot.hand.setIntake(-.9);
		} else if (Robot.oi.isOperatorButtonDown(11)) // In
		{
			Robot.hand.setIntake(1.0);
		} else {
			Robot.hand.stop();
		}

		// Wrist Logic (Down)
		double zInput = Robot.oi.getOperatorZ();
		zInput = (Math.abs(zInput) < Z_THRESHOLD ? 0
				: (Math.signum(zInput) * ((Math.abs(zInput) - Z_THRESHOLD) / (1 - Z_THRESHOLD))));
		Robot.hand.setWrist(WRIST_MULTIPLIER * zInput);

		// Grab logic
		// if (Robot.oi.operatorStick.getRawButtonPressed(1)) {
		// Robot.pickup.extend();
		// }
		// if (Robot.oi.operatorStick.getRawButtonPressed(2)) {
		// Robot.pickup.unextend();
		//
		// }
		// if (Robot.oi.operatorStick.getRawButtonReleased(1) ||
		// Robot.oi.operatorStick.getRawButtonReleased(2)) {
		// Robot.pickup.die();
		// }

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
