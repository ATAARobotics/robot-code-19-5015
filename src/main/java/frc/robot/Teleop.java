package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
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
    private double elevatorSpeedFront;
    private double elevatorSpeedRear;
    private DigitalInput testSwitch = new DigitalInput(0);
    //private boolean pneumaticShooter;
    /*UltrasonicCode
    private Ultrasonics ultrasonics;
    */
    private UsbCamera camera;
    public Teleop() {
        //Set All Variables for parts on the robot

        //Drive Controllers
        WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(0);
        WPI_VictorSPX rearLeftMotor = new WPI_VictorSPX(1);
        WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(2);
        WPI_VictorSPX rearRightMotor = new WPI_VictorSPX(3);

        //Group Drive
        SpeedControllerGroup rightMotors = new SpeedControllerGroup(rearRightMotor, frontRightMotor);
        SpeedControllerGroup leftMotors = new SpeedControllerGroup(rearLeftMotor, frontLeftMotor);
        //Add pneumatics
        DoubleSolenoid hatchIntakeSolenoid = new DoubleSolenoid(0, 1);  
        DoubleSolenoid m_gearShiftSolenoid = new DoubleSolenoid(2, 3);
        //Add Elevator Variables
        CANSparkMax ElevatorFrontLift = new CANSparkMax(4, MotorType.kBrushless);
        CANSparkMax ElevatorRearLift = new CANSparkMax(5, MotorType.kBrushless);
        WPI_VictorSPX ElevatorDrive = new WPI_VictorSPX(6);
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
        driveTrain = new SWATDrive(leftMotors, rightMotors, m_gearShiftSolenoid);
        intake = new Intake(hatchIntakeSolenoid);
        elevator = new Elevator(ElevatorFrontLift, ElevatorRearLift, ElevatorDrive);
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
    public void TestPeriodic() {
        String testSwitchState = Boolean.toString(testSwitch.get());
        Robot.debugOut(testSwitchState, 1, "Test Switch");
    }
    public void TeleopPeriodic() {
        driveTrain.arcadeDrive(driveStick.getY(Hand.kLeft), -driveStick.getX(Hand.kRight));
        if(driveStick.getXButton()) {
            driveTrain.gearShift();
        }
        if (driveStick.getAButton()) {
            driveTrain.slow();
        }
        else; 
        
        if (gunnerStick.getBumper(Hand.kLeft)) {
            intake.HatchOpen();
        }
        else if (gunnerStick.getBumper(Hand.kRight)) {
            intake.HatchClose();
        }
        else;
        elevatorSpeedFront = -driveStick.getTriggerAxis(Hand.kLeft);
        elevatorSpeedRear = driveStick.getTriggerAxis(Hand.kLeft);
        if(driveStick.getBumper(Hand.kLeft)) {
            elevatorSpeedFront = 0.5;
        }
        if(driveStick.getBumper(Hand.kRight)) {
            elevatorSpeedRear = -0.5;
        }
        elevator.elevatorControl(elevatorSpeedFront, elevatorSpeedRear);
        if(driveStick.getYButton()) {
            elevator.driveElevator();
        }
        else {
            elevator.stopDrive();
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
}