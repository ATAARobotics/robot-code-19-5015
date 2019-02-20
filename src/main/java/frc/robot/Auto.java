package frc.robot;

//import frc.robot.pathweaver.PathFinder;
import frc.robot.Teleop;

public class Auto {

  private Teleop teleop;
  //private PathFinder pathFinder;

  public Auto(Teleop tele) {
    this.teleop = tele;

    //this.pathFinder = new PathFinder(teleop);

  }

  /**
   * Function that contains 'tasks' designed to be ran at initalization
   */
  public void AutoInit() {
    //pathFinder.pathRun();
  }

  /**
   * Periodic function that contains 'tasks' that are designed to be ran periodically.
   */
  public void AutoPeriodic() {
      
  }

  /**
   * Function that contains 'tasks' designed to be ran at disablization (Is that a word? Idk gonna use it though).
   */
  public void AutoDisabled() {
    //pathFinder.stop();
  }

}
