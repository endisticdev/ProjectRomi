// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {

  PWMVictorSPX leftMotor, rightMotor;
  Encoder leftEncoder, rightEncoder;
  DifferentialDrive differentialDrive;


  /** Creates a new Chassis. */
  public Chassis() {
    // Registers the motors as new constants of PWMVictorSPX
    // I presume PWMVictorSPX manages the controls of the controller,
    // and tells us the output of each joystick
    leftMotor = new PWMVictorSPX(Constants.LEFT_MOTOR_PORT);
    rightMotor = new PWMVictorSPX(Constants.RIGHT_MOTOR_PORT);

    // This will take care of the movement of the Romi,
    // by using the input from leftMotor and rightMotor
    differentialDrive = new DifferentialDrive(leftMotor, rightMotor);
    differentialDrive.setRightSideInverted(false);
    differentialDrive.setSafetyEnabled(false);
    // Creates new Encoder() for each encoder variables (leftEncoder & rightEncoder)
    leftEncoder = new Encoder(Constants.LEFT_ENCODER_A, Constants.LEFT_ENCODER_B);
    rightEncoder = new Encoder(Constants.RIGHT_ENCODER_A, Constants.RIGHT_ENCODER_B);

    // This will set the difference each motor should move every pulse
    leftEncoder.setDistancePerPulse(Constants.INCHES_PER_PULSE);
    rightEncoder.setDistancePerPulse(Constants.INCHES_PER_PULSE);

    // Takes the values from this Chassis into the dashboard,
    // when playing with the robot.
    addChild("leftEncoder", leftEncoder);
    addChild("rightEncoder", rightEncoder);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run


  }

  public void reset() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public void drive(double speed, double direction) {
    differentialDrive.arcadeDrive(speed, direction);
    System.out.println("im driving with speed " + speed + " and direction " + direction);
  }

  public void altDrive(double power, String moveType) {
    if(moveType.equals("rotate")) {
      differentialDrive.tankDrive(power, power + 0.1);
    } else if (moveType.equals("forward")) {
      differentialDrive.tankDrive(power * -1, power + 0.1);
    } else {
      System.out.println("Bad type!");
    }
    System.out.println("im tank driving with speed " + power + " and type " + moveType);
  }

  public void stop() {
    drive(0, 0);
  }

  public double getLeftDistance() {
	  return leftEncoder.getDistance();
  }


}
