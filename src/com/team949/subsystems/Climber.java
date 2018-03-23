package com.team949.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team949.RobotMap;
import com.team949.commands.Climb;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	private WPI_TalonSRX climberMotor1;
	private WPI_TalonSRX climberMotor2;
	
	private Servo hookLock;
	
	public void initDefaultCommand() {
		setDefaultCommand(new Climb());
	}

	public Climber() {
		climberMotor1 = new WPI_TalonSRX(RobotMap.climberMotor1);
		climberMotor2 = new WPI_TalonSRX(RobotMap.climberMotor2);

		hookLock = new Servo(RobotMap.hookLockServo);
		
		// IMPORTANT TO RUN ONE BACKWARDS AND ONE FORWARDS (GEARBOX DESIGN)
		climberMotor1.setInverted(true);
		climberMotor2.setInverted(false);
	}

	/**
	 * Calls the .set() method on both pickup motors
	 * 
	 * @param rate
	 *            the double that goes in someMotor.set(rate); as parameter.
	 */
	public void setBothMotors(double rate) {
		climberMotor1.set(rate);
		climberMotor2.set(rate);
	}
	public void lockHook() 
	{
		hookLock.set(0);
	}
	public void releaseHook() 
	{
		hookLock.set(90);
	}
}
