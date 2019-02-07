/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private Joystick gunnerStick = new Joystick(1);
  private Joystick driveStick = new Joystick(0);
  DoubleSolenoid hatchIntakeSolenoid = new DoubleSolenoid(0,1);
  TalonSRX frontLeftMotor = new TalonSRX(0);
  VictorSPX rearLeftMotor = new VictorSPX(1);
  TalonSRX frontRightMotor = new TalonSRX(2);
  VictorSPX rearRightMotor = new VictorSPX(3);
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  SWATDrive driveTrain = new SWATDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    hatchIntakeSolenoid.set(DoubleSolenoid.Value.kOff);
    rearRightMotor.setInverted(true);
    frontRightMotor.setInverted(true);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // autoSelected = SmartDashboard.getString("Auto Selector",
    // defaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    if(gunnerStick.getRawButton(5)) {
      hatchIntakeSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    else if(gunnerStick.getRawButton(6)) {
      hatchIntakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    //Tank Drive
    //rearLeftMotor.set(ControlMode.PercentOutput, driveStick.getRawAxis(1));
    //frontLeftMotor.set(ControlMode.PercentOutput, driveStick.getRawAxis(1));
    //rearRightMotor.set(ControlMode.PercentOutput, driveStick.getRawAxis(5));
    //frontRightMotor.set(ControlMode.PercentOutput, driveStick.getRawAxis(5));
    driveTrain.arcadeDrive(driveStick.getRawAxis(1), driveStick.getRawAxis(4), true);
  }


  //Debug function to print strings to console and/or SmartDashboard
  public static void debugOut(String value, int logLevel, String key) {
    switch (logLevel) {
      case 0:
        System.out.println(value);

      case 1:
        SmartDashboard.putString(key, value);

      default:
        break;
    }
  }
  public static void debugOut(String value, int logLevel) {debugOut(value, logLevel, "log");}
  public static void debugOut(String value) {debugOut(value, 0);}
}
