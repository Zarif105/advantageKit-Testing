// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Elevator;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.util.Kraken;

public class ElevatorIOReal implements ElevatorIO {

  private Kraken leftMotor, rightMotor;
  private CANcoder elevatorEncoder;
  private DigitalInput lowerLimitSwitch, upperLimitSwitch;
  private double desiredSetpoint; // desired setpoint of the encoder
  private double wantedElevatorSetpoint;
  private double desiredHeight = 0;

  /** Creates a new ElevatorIOReal. */
  public ElevatorIOReal() {

    leftMotor = new Kraken(ElevatorConstants.leftMotorID);
    rightMotor = new Kraken(ElevatorConstants.rightMotorID);
    elevatorEncoder = new CANcoder(ElevatorConstants.elevatorCANCoderID);

    lowerLimitSwitch = new DigitalInput(ElevatorConstants.lowerLimitSwitchPort);
    upperLimitSwitch = new DigitalInput(ElevatorConstants.upperLimitSwitchPort);

    leftMotor.restoreFactoryDefaults();
    rightMotor.restoreFactoryDefaults();

    leftMotor.setInverted();

    rightMotor.follow(ElevatorConstants.leftMotorID, MotorAlignmentValue.Aligned);

    leftMotor.addEncoder(elevatorEncoder);

    leftMotor.setRotorToSensorRatio(ElevatorConstants.elevatorGearing);
    leftMotor.setSensorToMechanismRatio(ElevatorConstants.elevatorEncoderToMechanismRatio);

    leftMotor.setPIDValues(
        ElevatorConstants.ELEVATOR_P,
        ElevatorConstants.ELEVATOR_I,
        ElevatorConstants.ELEVATOR_D,
        ElevatorConstants.ELEVATOR_SFF,
        ElevatorConstants.ELEVATOR_VFF,
        ElevatorConstants.ELEVATOR_AFF,
        ElevatorConstants.ELEVATOR_GFF);

    leftMotor.setBrakeMode();
    rightMotor.setBrakeMode();

    leftMotor.setMotorCurrentLimits(40);
    leftMotor.setSoftLimits(
        ElevatorConstants.softLimitMinPosition, // prevent us from overdriving the motor
        ElevatorConstants.softLimitMaxPosition);

    desiredSetpoint = ElevatorConstants.homePosition;
    elevatorEncoder.setPosition(0);
    leftMotor.setDesiredEncoderPosition(desiredSetpoint);
    // SignalLogger.start();
  }

  @Override
  public void updateInputs(ElevatorIOInputs inputs) {
    inputs.elevatorLeftCurrent = leftMotor.getSupplyCurrent();
    inputs.elevatorRightCurrent = rightMotor.getSupplyCurrent();
    inputs.elevatorHeight = leftMotor.getPosition();
    inputs.elevatorLeftTemp = leftMotor.getMotorTemperature();
    inputs.elevatorRightTemp = rightMotor.getMotorTemperature();
  }

  @Override
  public void setElevatorHeight(double height) {
    desiredHeight = height;
    leftMotor.setDesiredEncoderPosition(desiredSetpoint);
  }

  @Override
  public void incrementElevatorHeight(double increment) {
    desiredSetpoint += increment;
    leftMotor.setDesiredEncoderPosition(desiredSetpoint);
  }

  @Override
  public void stopElevatorMotors() {
    leftMotor.stopMotor();
  }
}
