package frc.robot;

//import frc.robot.pathweaver.PathFinder;
import frc.robot.Teleop;

public class Auto {

    private Teleop teleop;
    private RobotMap robotMap;
    private int step;
    private boolean auto = true;
    private OI joysticks;

  public Auto(Teleop tele) {
    this.teleop = tele;
    //robotMap = new RobotMap();
  }

  /**
   * Function that contains 'tasks' designed to be ran at initalization
   */
  public void AutoInit() {
      step = 0;
      //robotMap.getGyro().initalizeNavX();
  }

  /**
   * Periodic function that contains 'tasks' that are designed to be ran periodically.
   */
  public void AutoPeriodic() {
    if(true) {
        auto = false;
    }
    if(auto) {  
    switch(step) {
        case 0:
            teleop.drive(0.7, 0, true);
            
            if(robotMap.getEncoders().getDistance() > 144) {
                step++;
            }
            break;
        case 1:
            teleop.hatch(true);
            step++;
            break;

        case 2:
            teleop.drive(-0.7, 0, true);
            
            if(robotMap.getEncoders().getDistance() > 12) {
                step++;
            }
            break;
        case 3:
            teleop.drive(0, 0.7, true);
            double angle = robotMap.getGyro().getAngle();
            if(angle >= 90) {
                if(angle > 95) {
                    teleop.drive(0, 0.6, true);
                }
                else {
                    step++;
                }
            }
            break;   
        case 4:
            teleop.drive(-0.7, 0, true);

            if(robotMap.getEncoders().getDistance() > 120) {
                step++;
            }
            break;
        case 5:
            teleop.drive(0, 0, true);
            teleop.hatch(false);
            break;
        default:
            teleop.TeleopPeriodic();
            break;
      }
    }
    else {
        teleop.TeleopPeriodic();
    }
      
  }

  /**
   * Function that contains 'tasks' designed to be ran when the robot is disabled.
   */
  public void AutoDisabled() {
  }

}
