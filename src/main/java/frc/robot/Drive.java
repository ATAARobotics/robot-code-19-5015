/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */

public class Drive extends Subsystem {

    //Creates and initializes solenoid and motor objects and
    //assigns them to their respective ports on the robot
    private DoubleSolenoid gearShiftSolenoid = new DoubleSolenoid(2, 3); //may need to add another solenoid, there is two gearboxes after all.
    private WPI_TalonSRX rearLeftMotor = new WPI_TalonSRX(1);
    private WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(0);
    private WPI_TalonSRX rearRightMotor = new WPI_TalonSRX(3);
    private WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(2);
    private SpeedControllerGroup leftSpeedControllerGroup = new SpeedControllerGroup(rearLeftMotor, frontLeftMotor);
    private SpeedControllerGroup rightSpeedControllerGroup = new SpeedControllerGroup(rearRightMotor, frontRightMotor);
    private DifferentialDrive driveTrain = new DifferentialDrive(leftSpeedControllerGroup, rightSpeedControllerGroup);
    //Sets the boolean lowGear equal to true
    private boolean lowGear = true;
    private boolean slow = false;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void drive(Joystick controller) {
    //Calls the arcadeDrive class in teleop
    double leftSpeed = controller.getRawAxis(1) - controller.getRawAxis(4);
    double rightSpeed = controller.getRawAxis(1) + controller.getRawAxis(4);
    driveTrain.tankDrive(leftSpeed, rightSpeed);
  }

  public void ExtDrive(double leftDrive, double rightDrive) {
    driveTrain.tankDrive(leftDrive, rightDrive);
  }

  public void gearShift() {
      lowGear = !lowGear;
      //need to add solenoids
  }
}
