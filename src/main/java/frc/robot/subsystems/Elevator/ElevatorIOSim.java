// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Elevator;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorIOSim implements ElevatorIO {

  private ElevatorSim elevatorSim;
  private EncoderSim elevatorEncoder;
  private PIDController elevatorController;
  private double voltage;
  private double desiredHeight;

  /** Creates a new ElevatorIOSim. */
  public ElevatorIOSim() {
    elevatorSim = new ElevatorSim(DCMotor.getKrakenX60(2), 0, 0, 0, 0, 0, true, 0, null);
    elevatorEncoder = new EncoderSim(new Encoder(0, 1));
    elevatorController = new PIDController(0, 0, 0);
    elevatorEncoder.setDistancePerPulse(ElevatorConstants.elevatorEncoderToMechanismRatio);
  }

  @Override
  public void updateInputs(ElevatorIOInputs inputs) {
    inputs.elevatorLeftCurrent = elevatorSim.getCurrentDrawAmps() / 2;
    inputs.elevatorRightCurrent = elevatorSim.getCurrentDrawAmps() / 2;
    inputs.elevatorHeight = elevatorSim.getPositionMeters();
  }

  @Override
  public void setElevatorHeight(double height) {
    desiredHeight = height;
    voltage = elevatorController.calculate(elevatorEncoder.getDistance(), desiredHeight);
    elevatorSim.setInputVoltage(voltage);
  }

  @Override
  public void incrementElevatorHeight(double increment) {
    desiredHeight = desiredHeight + increment;
    setElevatorHeight(desiredHeight);
  }

  @Override
  public void stopElevatorMotors() {
    elevatorSim.setInputVoltage(0);
  }
}
