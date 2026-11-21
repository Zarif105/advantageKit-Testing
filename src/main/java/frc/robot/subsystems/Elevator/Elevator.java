// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Elevator;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Elevator.ElevatorIO.ElevatorIOInputs;

public class Elevator extends SubsystemBase {

  private ElevatorIO elevatorIO;
  private ElevatorIOInputsAutoLogged elevatorInputs;
  /** Creates a new Elevator. */
  public Elevator() {

  }

  @Override
  public void periodic() {
    elevatorIO.updateInputs(elevatorInputs);
    Logger.processInputs("Elevator", elevatorInputs);
    // This method will be called once per scheduler run
  }
}
