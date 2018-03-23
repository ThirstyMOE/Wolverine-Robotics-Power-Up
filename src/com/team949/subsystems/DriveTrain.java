package com.team949.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team949.Robot;
import com.team949.RobotMap;
import com.team949.commands.JoystickDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private DifferentialDrive drive;

	private final ADXRS450_Gyro g;

	private SpeedControllerGroup r;
	private SpeedControllerGroup l;

	public WPI_TalonSRX r0, r1, l0, l1;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new JoystickDrive());
	}

	public DriveTrain() {
		this.g = new ADXRS450_Gyro();
		gyroCalibrate();

		this.r0 = new WPI_TalonSRX(RobotMap.rightDriveMotor1);
		this.r1 = new WPI_TalonSRX(RobotMap.rightDriveMotor2);
		this.l0 = new WPI_TalonSRX(RobotMap.leftDriveMotor1);
		this.l1 = new WPI_TalonSRX(RobotMap.leftDriveMotor2);

		// Set slaves r1 and l1 to follow masters r0 and l0
		
		setUpEncoders();
		// 683 u/100ms, 745u/ 100ms 24% output
		// 0.358724, 0.328870
		l0.config_kF(0, 0.328870, 0);
		r0.config_kF(0, 0.358724, 0);
		l0.config_kP(0, 10./4096, 0);
		r0.config_kP(0, 10./4096, 0);
		l0.config_kD(0, 100000./4096, 0);
		r0.config_kD(0, 100000./4096, 0);
		
		this.r = new SpeedControllerGroup(r0, r1);
		this.l = new SpeedControllerGroup(l0, l1);

		this.r.setInverted(false);
		this.l.setInverted(false);

		this.drive = new DifferentialDrive(l, r);

		this.drive.setSafetyEnabled(false);
	}

	private void setUpEncoders() {
		r0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		r0.setSensorPhase(false);
		r0.setSelectedSensorPosition(0, 0, 0);
		l0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		l0.setSensorPhase(false);
		l0.setSelectedSensorPosition(0, 0, 0);
	}

	// Drive Methods
	public void arcade(double moveValue, double rotateValue) {
		this.drive.arcadeDrive(moveValue, rotateValue);
	}

	public void tank(double leftMoveValue, double rightMoveValue) {
		this.drive.tankDrive(leftMoveValue, rightMoveValue);
	}

	public void stop() {
		arcade(0.0, 0.0);
	}

	// ACCESSORS
	public double gyroRate() {
		return this.g.getRate();
	}

	public void gyroCalibrate() {
		this.g.calibrate();
	}

	public double getLeftVelocity() {
		return this.l0.getSelectedSensorVelocity(0);
	}

	public double getRightVelocity() {
		return this.r0.getSelectedSensorVelocity(0);
	}

	public double getLeftPosition() {
		return this.l0.getSelectedSensorPosition(0);
	}

	public double getRightPosition() {
		return this.r0.getSelectedSensorPosition(0);
	}

	public void gyroPTurn(double targetAngle) 
	{
		double turn = targetAngle - g.getAngle();
		final double maximumTurnValue = 0.6;
		final double kPTurn = 0.07;
		if ((turn * kPTurn) > maximumTurnValue) {
			this.arcade(0.0, maximumTurnValue);
		} else if ((turn * kPTurn) < -maximumTurnValue) {
			this.arcade(0.0, -maximumTurnValue);
		} else {
			this.arcade(0.0, turn * kPTurn);
		}

	}
	public boolean angleWithinTolerance(double targetAngle) 
	{
		final double tolerance = 3.0;
		DriverStation.reportWarning("" + g.getAngle(), false);
		return (Math.abs(targetAngle - g.getAngle()) <= tolerance) && (Math.abs(g.getRate()) < 1);
	}
}
