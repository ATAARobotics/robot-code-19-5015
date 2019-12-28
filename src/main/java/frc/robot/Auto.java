package frc.robot;

//import frc.robot.pathweaver.PathFinder;
import frc.robot.Teleop;
/**
 * A file dedicated to all auto related code
 * 
 * @author Alexander Greco and Jacob Guglielmin
 */
public class Auto {
    
    private Teleop teleop = Robot.teleop;
    private RobotMap robotMap = teleop.robotMap;
    private double lSpeed;
    private double rSpeed;

    //Adjusts motor speeds so that they match
    private final double LEFT_SPEED_CONSTANT = -0.851;
    private final double RIGHT_SPEED_CONSTANT = -1;

    Encoders encoders = RobotMap.getEncoder();
    SWATDrive swatDrive = new SWATDrive(robotMap);
  /**
   * Function that contains tasks designed to be ran at initalization
   */
  public void AutoInit() {
      encoders.reset();
  }

  /**
   * Periodic function that contains tasks that are designed to be ran periodically.
   */
    public void AutoPeriodic() {
        lSpeed = 0;
        rSpeed = 0;

        //The values in the if statements are how far the bot should go in inches
        if(encoders.getLeftDistance() < 110.4) {
            lSpeed = 0.5 * LEFT_SPEED_CONSTANT; 
        }
        if(encoders.getRightDistance() < 110.4) {
            rSpeed = 0.5 * RIGHT_SPEED_CONSTANT; 
        }
        swatDrive.tankDrive(lSpeed, rSpeed);

    }

  /**
   * Function that contains tasks designed to be ran when the robot is disabled.
   */
  public void AutoDisabled() {
  }

}
