/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  
    Teleop teleop = new Teleop();

    @Override
    public void robotInit() {
        teleop.teleopInit();
    }

    /**
    * This function is called every robot packet, no matter the mode. Use
    * this for items like diagnostics that you want ran during disabled,
    * autonomous, teleoperated and test.
    *
    * <p>This runs after the mode specific periodic functions, but before
    * LiveWindow and SmartDashboard integrated updating.
    * 
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
    }

    /**
    * This function is called periodically during autonomous.
    */
    @Override
    public void autonomousPeriodic() {
    }

    /**
    * This function is called periodically during operator control.
    */
    @Override
    public void teleopPeriodic() {
        teleop.TeleopPeriodic();
    }
    
    public void testPeriodic() {
        teleop.TestPeriodic();
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
