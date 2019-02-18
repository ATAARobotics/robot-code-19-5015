package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.Spark;

public class Teleop {
    // Controllers for drivers

    private XboxController gunnerStick = new XboxController(1);
    private XboxController driveStick = new XboxController(0);
    // Vairables for robot classes
    private SWATDrive driveTrain;
    private Intake intake;
    private Elevator elevator;

    // private Shooter shooter;

    //private boolean pneumaticShooter;
    /*UltrasonicCode
    private Ultrasonics ultrasonics;
    */
    private UsbCamera camera;

    public Teleop() {
        //Set All Variables for parts on the robot

        //Add pneumatics
        DoubleSolenoid hatchIntakeSolenoid = new DoubleSolenoid(0, 1);  
        DoubleSolenoid m_gearShiftSolenoid = new DoubleSolenoid(2, 3);

        //Set Ball Shooter Variables

        //Spark shooterSpark = new Spark(7);
        //Spark intakeSpark = new Spark(8);
        //DoubleSolenoid punchSolenoid = new DoubleSolenoid(4, 5);
        //DoubleSolenoid gateSolenoid = new DoubleSolenoid(6, 7);
        //pneumaticShooter = true;
        camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(320, 240); 
        /*UltrasonicCode
        ultrasonics = new Ultrasonics();
        */

        //Initialize Classes
        driveTrain = new SWATDrive(m_gearShiftSolenoid);
        intake = new Intake(hatchIntakeSolenoid);
        elevator = new Elevator();

        /*if(pneumaticShooter) {

            shooter = new Shooter(punchSolenoid, gateSolenoid);
        }
        else {
            shooter = new Shooter(intakeSpark, shooterSpark);   
        }
        */
    }

    public void teleopInit() {

        intake.hatchOff();
        //shooter.shooterOff();

    }

    public void TeleopPeriodic() {

        driveTrain.debugEncoders();

        //Used in the auto climb
        if (elevator.elevatorPeriodic()) {
            ExtDrive(0.3, 0.3);
        }

        //If limit switch is contacted, stop elevator from moving in the direction of the limit switch
        elevator.checkLimitSwitches();

        //Toggles autoclimb on and off
        if (driveStick.getTriggerAxis(Hand.kLeft) != 0.0) {            
            elevator.setAutoClimb(!elevator.getClimbing());
        }

        //Disable manual control if climbing
        if (!elevator.getClimbing()) {
            driveTrain.arcadeDrive(driveStick.getY(Hand.kLeft), -driveStick.getX(Hand.kRight));

            //Speed limiters
            if(driveStick.getXButton()) {
                driveTrain.gearShift();
            }
            if (driveStick.getAButton()) {
                driveTrain.slow();
            }
            else; 
            
            //Hatch control
            if (gunnerStick.getBumper(Hand.kLeft)) {
                intake.HatchOpen();
            }
            else if (gunnerStick.getBumper(Hand.kRight)) {
                intake.HatchClose();
            }
            else;

            //Elevator down
            elevator.elevatorDown(driveStick.getTriggerAxis(Hand.kLeft));

            //Elevator up
            if(driveStick.getBumper(Hand.kLeft)) {
                elevator.frontElevatorUp(0.5);
            }
            else {
                elevator.frontElevatorUp(0.0);
            }

            if(driveStick.getBumper(Hand.kRight)) {
                elevator.rearElevatorUp(0.5);
            }
            else {
                elevator.rearElevatorUp(0.0);
            }

            //Drives forward on back elevator wheels
            if(driveStick.getYButton()) {
                elevator.driveElevator();
            }
            else {
                elevator.stopDrive();
            }
        }

        /*if(pneumaticShooter) {

            if(gunnerStick.getTriggerAxis(Hand.kLeft) > 0.2) {
                shooter.gate();
            }
            else if(gunnerStick.getTriggerAxis(Hand.kRight) >0.2) {
                shooter.punch();
            }
        }
        else {
            if(gunnerStick.getTriggerAxis(Hand.kLeft) > 0.2) {
                shooter.intakeBall();
            }
            else if(gunnerStick.getTriggerAxis(Hand.kRight) >0.2) {
                shooter.shootBall();
            }
        }
        */
    /* public getUltrasonicRange(int direction) {
        ultrasonic.getRange(direction);
    }
    */
    }
    
    /**
    * Drive function for external use in Auto.java
    * @param leftValue value for left motors, ranges from 1 to -1
    * @param rightValue value for right motors, ranges from 1 to -1
    * @return void
    */
    
    

    //Drives main wheels
    public void ExtDrive(double leftValue, double rightValue) {
        driveTrain.tankDrive(leftValue, rightValue);
    }

    //Drives elevator (Call ExtDrive without any parameters to drive elevator wheels)
    public void ExtDrive() {
        elevator.driveElevator();
    }
}
