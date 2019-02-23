package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.Encoders;
import com.kauailabs.navx.frc.*;

public class RobotMap {
    private WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(0);
    private WPI_VictorSPX rearLeftMotor = new WPI_VictorSPX(1);
    private WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(2);
    private WPI_VictorSPX rearRightMotor = new WPI_VictorSPX(3);
    
    //Group Drive
    private SpeedControllerGroup rightMotors = new SpeedControllerGroup(rearRightMotor, frontRightMotor);
    private SpeedControllerGroup leftMotors = new SpeedControllerGroup(rearLeftMotor, frontLeftMotor);

    //Add drivetrain
    private DifferentialDrive driveTrain = new DifferentialDrive(leftMotors, rightMotors);
    
    //Add pneumatics
    private DoubleSolenoid hatchIntakeSolenoid = new DoubleSolenoid(0, 1);  
    private DoubleSolenoid gearShiftSolenoid = new DoubleSolenoid(2, 3);

    private DoubleSolenoid punchSolenoid = new DoubleSolenoid(4, 5);
    private DoubleSolenoid gateSolenoid = new DoubleSolenoid(6, 7);
    //Add Elevator Variables
    private CANSparkMax ElevatorFrontLift = new CANSparkMax(4, MotorType.kBrushless);
    private CANSparkMax ElevatorRearLift = new CANSparkMax(5, MotorType.kBrushless);
    private WPI_VictorSPX ElevatorDrive = new WPI_VictorSPX(6);
    
    private DigitalInput upperFrontSwitch = new DigitalInput(0);
    private DigitalInput upperRearSwitch = new DigitalInput(1);
    private DigitalInput lowerFrontSwitch = new DigitalInput(2);
    private DigitalInput lowerRearSwitch = new DigitalInput(3);

    private Gyro NavX = new Gyro();

    //Add encoders
    private Encoders encoders = new Encoders(frontLeftMotor, frontRightMotor);

    public RobotMap() {
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        Shuffleboard.getTab("LiveWindow").add(camera);
        camera.setFPS(30);
        rearLeftMotor.follow(frontLeftMotor);
        rearRightMotor.follow(frontRightMotor);
    }
    public SpeedControllerGroup getLeftMotors() {
        return leftMotors;
    }

    public SpeedControllerGroup getRightMotors() {
        return rightMotors;
    }

    public DoubleSolenoid getHatchIntake() {
        return hatchIntakeSolenoid;
    }
    
    public DoubleSolenoid getGearShift() {
        return gearShiftSolenoid;
    }

    public CANSparkMax getElevatorFront() {
        return ElevatorFrontLift;
    }
    
    public CANSparkMax getElevatorRear() {
        return ElevatorRearLift;
    }

    public VictorSPX getElevatorDrive() {
        return ElevatorDrive;
    }

    public DigitalInput getFrontElevatorUpLimit() {
        return upperFrontSwitch;
    }

    public DigitalInput getRearElevatorUpLimit() {
        return upperRearSwitch;
    }

    public DigitalInput getFrontElevatorDownLimit() {
        return lowerFrontSwitch;
    }

    public DigitalInput getRearElevatorDownLimit() {
        return lowerRearSwitch;
    }

    public Encoders getEncoders() {
        return encoders;
    }

    public DifferentialDrive getDriveTrain() {
        return driveTrain;
    }

    public DoubleSolenoid getGateSolenoid() {
        return gateSolenoid;
    }

    public DoubleSolenoid getPunchSolenoid() {
        return punchSolenoid;
    }

    public Gyro getGyro() {
        return NavX;
    }
}