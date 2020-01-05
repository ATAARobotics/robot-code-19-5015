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

    //Drive values
    double Dp = 0.05;
    double Di = 0;
    double Dd = 0.2;

    double speed = 0;

    //Turn values
    double Tp = 0.15;
    double Ti = 0;
    double Td = 0.2;

    double turn = 0;
    int step = 0;
    boolean turning = false;

    private Teleop teleop = Robot.teleop;
    private RobotMap robotMap = teleop.robotMap;
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

      step = 0;

      //Speed PID
      drivePID = new PIDSubsystem(Dp, Di, Dd){
      
          @Override
          protected void initDefaultCommand() {
              
          }
      
          @Override
          protected void usePIDOutput(double output) {
              speed = output;
              System.out.println("Speed PID: "+output);
              System.out.println("Encoder: "+encoders.getLeftDistance());
          }
      
          @Override
          protected double returnPIDInput() {
              return -encoders.getLeftDistance();
          }

          @Override
            public void enable() {
                //Enable PID
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
      drivePID.setAbsoluteTolerance(1.0);
      drivePID.setOutputRange(-1.0, 1.0);

      //Turn PID
      turnPID = new PIDSubsystem(Tp, Ti, Td){
      
        @Override
        protected void initDefaultCommand() {
            
        }
    
        @Override
        protected void usePIDOutput(double output) {
            turn = output;
        }
    
        @Override
        protected double returnPIDInput() {
            //GYRO
            return -gyro.getAngle();
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
    turnPID.setAbsoluteTolerance(2.0);
    turnPID.setOutputRange(-1.0, 1.0);
    turnPID.setSetpoint(0.0);
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
        System.out.println("Setpoint: "+drivePID.getSetpoint());
        System.out.println("Gyro: "+gyro.getAngle());

        switch (step) {
            case 0:
                turning = false;
                drivePID.setSetpoint(-150.0 * (30.0/29.0));
                turnPID.setSetpoint(0.0);

                if (drivePID.onTarget()) {
                    step++;
                }

                break;
            
            case 1:
                drivePID.disable();
                speed = 0;
                turning = true;
                turnPID.setSetpoint(90.0);

                if (turnPID.onTarget()) {
                    step++;
                }

                break;

            case 2:
                turning = false;
                drivePID.setSetpoint(-50.0 * (30.0/29.0));
                turnPID.setSetpoint(0.0);

                if (drivePID.onTarget()) {
                    step++;
                }

                break;

            default:
                break;
        }

        //Corrects all motor speed to be between 30% and 70% in each direction
        if (0.001 > speed && speed > -0.001) {
            speed = 0;
        } else if (speed>0) {
            speed = speed*0.40;
            speed += 0.3;
        } else {
            speed = speed*0.40;
            speed -= 0.3;
        }

        swatDrive.arcadeDrive(speed, turn);
        
        SmartDashboard.putNumber("Encoder", encoders.getLeftDistance());
    }

    /**
    * Function that contains tasks designed to be ran when the robot is disabled.
    */
    public void AutoDisabled() {
        drivePID.disable();
        turnPID.disable();
    }
}
