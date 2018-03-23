package com.team949.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team949.RobotMap;
import com.team949.commands.PickupControl;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hand extends Subsystem {
	private WPI_TalonSRX rightPickupMotor;
	private WPI_TalonSRX leftPickupMotor;

	private WPI_TalonSRX wristMotor;

	private Compressor compressor;
	private DoubleSolenoid handRotator;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new PickupControl());
	}

	public Hand() {
		this.rightPickupMotor = new WPI_TalonSRX(RobotMap.rightPickupMotor);
		this.leftPickupMotor = new WPI_TalonSRX(RobotMap.leftPickupMotor);
		this.wristMotor = new WPI_TalonSRX(RobotMap.wristMotor);

		compressor = new Compressor();
		handRotator = new DoubleSolenoid(RobotMap.handRotatorSolenoidChannelIn, RobotMap.handRotatorSolenoidChannelOut);

		rightPickupMotor.setInverted(true);
		leftPickupMotor.setInverted(false);
		compressor.setClosedLoopControl(true);
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void open() {
		handRotator.set(DoubleSolenoid.Value.kForward);
	}

	public void close() {
		handRotator.set(DoubleSolenoid.Value.kReverse);
	}

	public void die() {
		handRotator.set(DoubleSolenoid.Value.kOff);
	}

	/**
	 * Set both motors to 0.0
	 */
	public void stop() {
		setIntakeSeparate(0, 0);
	}

	/**
	 * Calls the .set() method with wheels rotating same direction.
	 * 
	 * @param rate
	 *            the double that goes in someMotor.set(rate); as parameter.
	 */
	public void setIntake(double rate) {
		setIntakeSeparate(rate, -rate);
	}

	public void setIntakeSeparate(double leftRate, double rightRate) {
		this.rightPickupMotor.set(leftRate);
		this.leftPickupMotor.set(rightRate);
	}

	/**
	 * Calls the .set() method with wristMotor.
	 * 
	 * @param rate
	 *            the double that goes in someMotor.set(rate); as parameter.
	 */
	public void setWrist(double rate) {
		this.wristMotor.set(rate);
	}

}
