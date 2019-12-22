package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.pathweaver.PathFinder;
import frc.robot.Teleop;
/**
 * A file dedicated to all auto related code
 * 
 * @author Alexander Greco
 */
public class Auto {
    
    private Teleop teleop = Robot.teleop;
    private RobotMap robotMap = teleop.robotMap;
    Encoders encoder = robotMap.getEncoder();
    SWATDrive swatDrive = new SWATDrive(robotMap);
    Gyro gyro = robotMap.getGyro();
  /**
   * Function that contains 'tasks' designed to be ran at initalization
   */
  public void AutoInit() {
      encoder.reset();
      gyro.reset();
  }

  /**
   * Periodic function that contains 'tasks' that are designed to be ran periodically.
   */
    public void AutoPeriodic() {
        SmartDashboard.putNumber("Encoder", encoder.getLeftDistance());
        double lSpeed = 0;
        double rSpeed = 0;
        if(encoder.getLeftDistance() < 60) {
            lSpeed = -0.7; 
        }

        swatDrive.arcadeDrive(lSpeed, gyro.getAngle());
  }

  /**
   * Function that contains 'tasks' designed to be ran when the robot is disabled.
   */
  public void AutoDisabled() {
  }

}
