package com.team949.auto;

import com.team949.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class HardWristMove extends TimedCommand {

	private double moveValue;
	
    public HardWristMove(double timeout, double moveValue) {
		super(timeout);
		// TODO Auto-generated constructor stub
		requires(Robot.hand);
		this.moveValue = moveValue;
	}
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.hand.setWrist(moveValue);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Called once after timeout
    protected void end() {
    	Robot.hand.setWrist(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
