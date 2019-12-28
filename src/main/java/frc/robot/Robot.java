/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.*;

public class Robot extends TimedRobot {
    //Create objects to run auto and teleop code
    public static Teleop teleop = new Teleop();
    Auto auto = new Auto();

    @Override
    public void robotInit() {
        teleop.teleopInit();
        auto.AutoInit();
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

    @Override
    public void disabledPeriodic() {
    }
    
    @Override
    public void autonomousInit() {
        auto.AutoInit();
    }

    /**
    * This function is called periodically during autonomous.
    */
    @Override
    public void autonomousPeriodic() {
        auto.AutoPeriodic();
    }

    /**
    * This function is called periodically during operator control.
    */
    @Override
    public void teleopInit() {
        teleop.teleopInit();
    }
    @Override
    public void teleopPeriodic() {
        teleop.TeleopPeriodic();
    }
    
    public void testPeriodic() {
        teleop.TestPeriodic();
    }
}
