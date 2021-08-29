// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

public class Forward extends CommandBase {

  double distance;

  Chassis chassis;
  /** Creates a new Forward. */
  public Forward(double inches) {
    // Use addRequirements() here to declare subsystem dependencies.

    distance = inches;
    chassis = RobotContainer.chassis;

    addRequirements(chassis);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    chassis.reset();
    System.out.println("initialize()");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Drive at 30% speed, straight forward
    chassis.altDrive(1, "forward");
    System.out.println("execut()");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    chassis.stop();
    System.out.println("end()");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return chassis.getLeftDistance() >= distance;
  }
}
