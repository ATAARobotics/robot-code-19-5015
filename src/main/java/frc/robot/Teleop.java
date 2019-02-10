package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
public class Teleop {
    // Controllers for drivers
    private XboxController gunnerStick = new XboxController(1);
    private XboxController driveStick = new XboxController(0);
    // Vairables for robot classes
    private SWATDrive driveTrain;
    private Intake intake;
    private Elevator elevator;
    private double elevatorSpeedFront;
    private double elevatorSpeedRear;
    public Teleop() {
        //Set All Variables for parts on the robot

        //Drive Controllers
        WPI_VictorSPX rearLeftMotor = new WPI_VictorSPX(1);
        WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(0);
        WPI_VictorSPX rearRightMotor = new WPI_VictorSPX(3);
        WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(2);
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
        
        //Initialize Classes
        driveTrain = new SWATDrive(leftMotors, rightMotors, m_gearShiftSolenoid);
        intake = new Intake(hatchIntakeSolenoid);
        elevator = new Elevator(ElevatorFrontLift, ElevatorRearLift, ElevatorDrive);
    }
    public void teleopInit() {

        intake.hatchOff();
    }
    public void TeleopPeriodic() {
        driveTrain.arcadeDrive(driveStick.getY(Hand.kLeft), driveStick.getX(Hand.kRight));
        if(driveStick.getXButton()) {
            driveTrain.gearShift();
        }
        if (driveStick.getAButton()) {
            driveTrain.slow();
        }
        else; 
        
        if (gunnerStick.getXButton()) {
            intake.HatchOpen();
        }
        else if (gunnerStick.getBButton()) {
            intake.HatchClose();
        }
        else;
        elevatorSpeedFront = gunnerStick.getTriggerAxis(Hand.kLeft);
        elevatorSpeedFront = gunnerStick.getTriggerAxis(Hand.kRight);
        if(gunnerStick.getBumper(Hand.kLeft)) {
            elevatorSpeedFront = -0.5;
        }
        if(gunnerStick.getBumper(Hand.kRight)) {
            elevatorSpeedRear = -0.5;
        }
        elevator.elevatorControl(elevatorSpeedFront, elevatorSpeedRear);
        elevator.driveElevator(gunnerStick.getY(Hand.kLeft));
    }
}