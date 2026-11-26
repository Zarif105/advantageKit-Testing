// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * This class defines the runtime mode used by AdvantageKit. The mode is always "real" when running
 * on a roboRIO. Change the value of "simMode" to switch between "sim" (physics sim) and "replay"
 * (log replay from a file).
 */
public final class Constants {
  public static final Mode simMode = Mode.SIM;
  public static final Mode currentMode = RobotBase.isReal() ? Mode.REAL : simMode;

  public static enum Mode {
    /** Running on a real robot. */
    REAL,

    /** Running a physics simulator. */
    SIM,

    /** Replaying from a log file. */
    REPLAY
  }

  public static class ElevatorConstants {
    public static final int leftMotorID = 18;
    public static final int rightMotorID = 19;
    public static final int elevatorCANCoderID = 17;

    public static final int lowerLimitSwitchPort = 9;
    public static final int upperLimitSwitchPort = 8;

    // elevator motor PID
    public static final double ELEVATOR_P = 0.15;
    public static final double ELEVATOR_I = 0;
    public static final double ELEVATOR_D = 0;
    public static final double ELEVATOR_SFF = 0; // static feedforward
    public static final double ELEVATOR_VFF = 0; // velocity feedforward
    public static final double ELEVATOR_AFF = 0; // acceleration feedforward
    public static final double ELEVATOR_GFF = 0.296; // gravity feedforward 0.296
    public static final double ELEVATOR_MIN_OUTPUT = -1;
    public static final double ELEVATOR_MAX_OUTPUT = 1;

    // elevator setpoints (inches)
    public static final double homePosition = -2;
    public static final double L1Position = 19;
    public static final double L2Position = 31;
    public static final double L3Position = 44;
    public static final double L4Position = 67;
    public static final double positionTolerance = .1;
    public static final double softLimitMinPosition = 0;
    public static final double softLimitMaxPosition = 0;
    public static final double incrementMeasurement = 1.5;

    public static final double elevatorGearing = 14 / 60; // inches
    public static final double elevatorSprocketRadius = 1.037;
    public static final double elevatorSprocketCircumference =
        2 * Math.PI * elevatorSprocketRadius; // inches
    public static final double elevatorEncoderToMechanismRatio =
        (1 / elevatorSprocketCircumference) / 3;
  }
}
