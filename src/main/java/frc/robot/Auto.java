package frc.robot;

//import frc.robot.pathweaver.PathFinder;
import frc.robot.Teleop;
/**
 * A file dedicated to all auto related code
 * 
 * @author Alexander Greco
 */
public class Auto {

    private Teleop teleop;

  public Auto(Teleop tele) {
    this.teleop = tele;
  }

  /**
   * Function that contains 'tasks' designed to be ran at initalization
   */
  public void AutoInit() {
  }

  /**
   * Periodic function that contains 'tasks' that are designed to be ran periodically.
   */
  public void AutoPeriodic() {
        teleop.TeleopPeriodic();
  }

  /**
   * Function that contains 'tasks' designed to be ran when the robot is disabled.
   */
  public void AutoDisabled() {
  }

}
