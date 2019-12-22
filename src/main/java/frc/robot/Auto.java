package frc.robot;

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
  /**
   * Function that contains 'tasks' designed to be ran at initalization
   */
  public void AutoInit() {
      encoder.reset();
  }

  /**
   * Periodic function that contains 'tasks' that are designed to be ran periodically.
   */
    public void AutoPeriodic() {
        double lSpeed = 0;
        double rSpeed = 0;
        if(encoder.getLeftDistance() < 110.4) {
            lSpeed = 1; 
        }
        if(encoder.getRightDistance() < 110.4) {
            rSpeed = 1; 
        }
        swatDrive.tankDrive(lSpeed, rSpeed);
  }

  /**
   * Function that contains 'tasks' designed to be ran when the robot is disabled.
   */
  public void AutoDisabled() {
  }

}
