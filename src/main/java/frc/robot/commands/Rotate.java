// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

public class Rotate extends CommandBase {

  double direction;
  double inches;
  Chassis chassis;
  
  /** Creates a new Rotate. */
  public Rotate(Integer angle) {
    chassis = RobotContainer.chassis;
    addRequirements(chassis);

    inches = Math.abs(angle) * Math.PI * Constants.WHEEL_TRACK / 360.0;
    direction = 0.10 * Math.signum(angle);
 
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    chassis.reset();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    chassis.altDrive(0.6, "rotate");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    chassis.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(chassis.getLeftDistance()) >= inches;
  }
}
