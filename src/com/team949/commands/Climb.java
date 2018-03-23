package com.team949.commands;

import com.team949.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climb extends Command {

    public Climb() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.isOperatorButtonDown(8)) // Up
    	{
    		Robot.climber.setBothMotors(-1.0);
    	}
    	else if(Robot.oi.isOperatorButtonDown(7)) // Down 
    	{
    		Robot.climber.setBothMotors(1.0);
    	}
    	else 
    	{
    		Robot.climber.setBothMotors(0.0);
    	}
    	
    	
    	// Servo Hook logic
    	if(Robot.oi.isOperatorButtonDown(4)) // Release
    	{
    		Robot.climber.releaseHook();
    	}
    	else if(Robot.oi.isOperatorButtonDown(6)) // Retract //DONT THINK WE NEED THIS
    	{
    		Robot.climber.lockHook();
    	}
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
