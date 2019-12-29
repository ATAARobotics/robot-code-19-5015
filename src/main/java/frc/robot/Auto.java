package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.pathweaver.PathFinder;
import frc.robot.Teleop;
/**
 * A file dedicated to all auto related code
 * 
 * @author Alexander Greco and Jacob Guglielmin
 */
public class Auto {
    double P = 0.6;
    int I = 0;
    int D = 0;
    double rcw;
    double integral, previous_error,error,derivative = 0;
    double setpoint = 60;
    private Teleop teleop = Robot.teleop;
    private RobotMap robotMap = teleop.robotMap;
    private double lSpeed;
    private double rSpeed;

    //Adjusts motor speeds so that they match
    private final double LEFT_SPEED_CONSTANT = -0.851;
    private final double RIGHT_SPEED_CONSTANT = -1;

    Encoders encoders = RobotMap.getEncoder();
    SWATDrive swatDrive = new SWATDrive(robotMap);
    Gyro gyro = robotMap.getGyro();
  /**
   * Function that contains tasks designed to be ran at initalization
   */
  public void AutoInit() {
      encoder.reset();
      gyro.reset();
  }

  /**
   * Periodic function that contains tasks that are designed to be ran periodically.
   */
    public void AutoPeriodic() {
        SmartDashboard.putNumber("Encoder", encoder.getLeftDistance());
        double lSpeed = 0;
        double rSpeed = 0;
        if(-encoder.getLeftDistance() < 60) {
            lSpeed = -0.7; 
            PID();
            swatDrive.arcadeDrive(-rcw, (gyro.getAngle()/7));
        }
        else {
            swatDrive.arcadeDrive(0, 0);
        }
  }

  /**
   * Function that contains tasks designed to be ran when the robot is disabled.
   */
  public void AutoDisabled() {
  }
  public void PID() {
    error = setpoint + encoder.getLeftDistance();// Error = Target - Actual
    this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    derivative = (error - this.previous_error) / .02;
    this.rcw = P*error + I*this.integral + D*derivative;
  }

}
