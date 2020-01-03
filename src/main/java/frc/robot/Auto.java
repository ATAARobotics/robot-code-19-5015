package frc.robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.pathweaver.PathFinder;
import frc.robot.Teleop;
/**
 * A file dedicated to all auto related code
 * 
 * @author Alexander Greco and Jacob Guglielmin
 */
public class Auto {
    //TODO Tune PIDs

    //Drive values
    double Dp = 0.1;
    double Di = 0;
    double Dd = 0;

    double speed = 0;

    //Turn values
    double Tp = 0.1;
    double Ti = 0;
    double Td = 0;

    double turn = 0;

    //double rcw;
    //double integral, previous_error,error,derivative = 0;
    //double setpoint = 60;
    private Teleop teleop = Robot.teleop;
    private RobotMap robotMap = teleop.robotMap;
    //private double lSpeed;
    //private double rSpeed;
    PIDSubsystem drivePID;
    PIDSubsystem turnPID;
    
    boolean driveIsEnabled;
    boolean turnIsEnabled;

    //Adjusts motor speeds so that they match
    //private final double LEFT_SPEED_CONSTANT = -0.851;
    //private final double RIGHT_SPEED_CONSTANT = -1;

    Encoders encoders = RobotMap.getEncoder();
    SWATDrive swatDrive = new SWATDrive(robotMap);
    Gyro gyro = robotMap.getGyro();
  /**
   * Function that contains tasks designed to be ran at initalization
   */
  public void AutoInit() {
      encoders.reset();
      gyro.reset();

      //Speed PID
      drivePID = new PIDSubsystem(Dp, Di, Dd){
      
          @Override
          protected void initDefaultCommand() {
              
          }
      
          @Override
          protected void usePIDOutput(double output) {
              speed = output;
              System.out.println("Speed PID: "+output);
          }
      
          @Override
          protected double returnPIDInput() {
              return -encoders.getLeftDistance();
          }

          @Override
            public void enable() {
                //Enabled PID
                super.enable();
                //Set enabled variable to true
                driveIsEnabled = true;
            }

            @Override
            public void disable() {
                //Disable PID
                super.disable();
                //Set enabled variable to false
                driveIsEnabled = false;
            }
      };
      drivePID.setAbsoluteTolerance(0.5);
      drivePID.setOutputRange(-0.5, 0.5);
      drivePID.setSetpoint(-20);

      //Turn PID
      turnPID = new PIDSubsystem(Tp, Ti, Td){
      
        @Override
        protected void initDefaultCommand() {
            
        }
    
        @Override
        protected void usePIDOutput(double output) {
            turn = -output;
        }
    
        @Override
        protected double returnPIDInput() {
            //GYRO
            return gyro.getAngle();
        }

        @Override
          public void enable() {
              //Enabled PID
              super.enable();
              //Set enabled variable to true
              turnIsEnabled = true;
          }

          @Override
          public void disable() {
              //Disable PID
              super.disable();
              //Set enabled variable to false
              turnIsEnabled = false;
          }
    };
    drivePID.setAbsoluteTolerance(1);
    drivePID.setOutputRange(-0.5, 0.5);
    drivePID.setSetpoint(0);
  }

  /**
   * Periodic function that contains tasks that are designed to be ran periodically.
   */
    public void AutoPeriodic() {
        if (!driveIsEnabled){
            drivePID.enable();
            turnPID.enable();
        }

        System.out.println("Speed: "+speed);
        System.out.println("Turn: "+turn);

        swatDrive.arcadeDrive(speed, turn);
        
        SmartDashboard.putNumber("Encoder", encoders.getLeftDistance());
        //double lSpeed = 0;
        //double rSpeed = 0;

        /*if(-encoders.getLeftDistance() < 60) {
            lSpeed = -0.7; 
            PID();
            //swatDrive.arcadeDrive(rcw, (gyro.getAngle()/7));
        }
        else {
            swatDrive.arcadeDrive(0, 0);
        }*/
        //System.out.println("RCW: " + rcw);
        //System.out.println("Encoder: " + encoders.getLeftDistance());

  }

  /**
   * Function that contains tasks designed to be ran when the robot is disabled.
   */
  public void AutoDisabled() {
      drivePID.disable();
  }



  /*There is a pid controller in wpilib, so i commented this out
  public void PID() {
    error = setpoint + encoders.getLeftDistance();// Error = Target - Actual
    this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
    derivative = (error - this.previous_error) / .02;
    this.rcw = P*error + I*this.integral + D*derivative;
  }*/

}
