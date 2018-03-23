package com.team949.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team949.Robot;
import com.team949.RobotMap;
import com.team949.commands.JoyStickArm;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

	// Initialize your subsystem here
	private WPI_TalonSRX armMotor;

	public Arm() {
		armMotor = new WPI_TalonSRX(RobotMap.armMotor);

		armMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		armMotor.setSensorPhase(false);
		armMotor.setSelectedSensorPosition(0, 0, 0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new JoyStickArm());
	}

	public double getEncoderPosition() {
		return armMotor.getSelectedSensorPosition(0);
	}

	public double getEncoderVelocity() {
		return armMotor.getSelectedSensorVelocity(0);
	}

	/**
	 * Default .set() method
	 * 
	 * @param moveValue
	 *            -1.0 to 1.0
	 */
	public void move(double moveValue) {
		armMotor.set(moveValue);
	}

}
