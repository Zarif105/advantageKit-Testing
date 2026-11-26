// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Elevator;

import org.littletonrobotics.junction.AutoLog;

public interface ElevatorIO {
  /** Creates a new ElevatorIO. */
  @AutoLog
  public class ElevatorIOInputs {
    public double elevatorHeight = 0.0;
    public double elevatorLeftCurrent = 0.0;
    public double elevatorRightCurrent = 0.0;
    public double elevatorLeftTemp = 0.0;
    public double elevatorRightTemp = 0.0;
  }

  public default void updateInputs(ElevatorIOInputs inputs) {}

  public default void setElevatorHeight(double height) {}

  public default void incrementElevatorHeight(double increment) {}

  public default void stopElevatorMotors() {}
}
