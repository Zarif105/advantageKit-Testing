// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Elevator;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase {

  private ElevatorIO elevatorIO;
  private ElevatorIOInputsAutoLogged elevatorInputs;
  private double wantedElevatorSetpoint;
  /** Creates a new Elevator. */

  public enum WantedElevatorState{
    Home,
    L1Position,
    L2Position,
    L3Position,
    L4Position,
  }

  public enum CurrentElevatorState{
    Home,
    L1Position,
    L2Position,
    L3Position,
    L4Position
  }

  WantedElevatorState wantedElevatorState = WantedElevatorState.Home;
  CurrentElevatorState currentElevatorState = CurrentElevatorState.Home;


  public Elevator() {

    elevatorInputs= new ElevatorIOInputsAutoLogged();

    switch (Constants.currentMode) {
      case REAL:
        elevatorIO = new ElevatorIOReal();
        break;

      case SIM:
        elevatorIO = new ElevatorIOSim();
        break;

      case REPLAY:
        elevatorIO = new ElevatorIO() {};
        break;
    }
  }

   public void setWantedElevatorState(WantedElevatorState wantedElevatorState){
    this.wantedElevatorState = wantedElevatorState;

    switch (wantedElevatorState) {
      case Home:
        currentElevatorState = CurrentElevatorState.Home;
        break;
      case L1Position:
        currentElevatorState = CurrentElevatorState.L1Position;
        break;
      case L2Position:
        currentElevatorState = CurrentElevatorState.L2Position;
        break;
      case L3Position:
        currentElevatorState = CurrentElevatorState.L3Position;
        break;
      case L4Position:
        currentElevatorState = CurrentElevatorState.L4Position;
        break;

      default: currentElevatorState = CurrentElevatorState.Home;
        break;
    }
  }

  public void applyElevatorState(CurrentElevatorState currentElevatorState){
    this.currentElevatorState = currentElevatorState;
    
    switch (currentElevatorState) {
      case Home:
        elevatorIO.setElevatorHeight(ElevatorConstants.homePosition);
        wantedElevatorSetpoint = ElevatorConstants.homePosition;
        break;
      case L1Position:
        elevatorIO.setElevatorHeight(ElevatorConstants.L1Position);
        wantedElevatorSetpoint = ElevatorConstants.L1Position;
        break;
      case L2Position:
        elevatorIO.setElevatorHeight(ElevatorConstants.L2Position);
        wantedElevatorSetpoint = ElevatorConstants.L2Position;
        break;
      case L3Position:
        elevatorIO.setElevatorHeight(ElevatorConstants.L3Position);
        wantedElevatorSetpoint = ElevatorConstants.L3Position;
        break;
      case L4Position:
        elevatorIO.setElevatorHeight(ElevatorConstants.L4Position);
        wantedElevatorSetpoint = ElevatorConstants.L4Position;
        break;
      default: elevatorIO.setElevatorHeight(0);
        break;
    }

  }

  @Override
  public void periodic() {
    elevatorIO.updateInputs(elevatorInputs);
    Logger.processInputs("Elevator", elevatorInputs);
    // This method will be called once per scheduler run

    applyElevatorState(currentElevatorState);
  }
}
