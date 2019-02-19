package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

class RobotMap {
    WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(0);
    WPI_VictorSPX rearLeftMotor = new WPI_VictorSPX(1);
    WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(2);
    WPI_VictorSPX rearRightMotor = new WPI_VictorSPX(3);
    //Group Drive
    SpeedControllerGroup rightMotors = new SpeedControllerGroup(rearRightMotor, frontRightMotor);
    SpeedControllerGroup leftMotors = new SpeedControllerGroup(rearLeftMotor, frontLeftMotor);

    //Add pneumatics
    DoubleSolenoid hatchIntakeSolenoid = new DoubleSolenoid(0, 1);  
    DoubleSolenoid gearShiftSolenoid = new DoubleSolenoid(2, 3);
    DoubleSolenoid punchSolenoid = new DoubleSolenoid(4,5);
    DoubleSolenoid gateSolenoid = new DoubleSolenoid(6,7);
    //Add Elevator Variables
    CANSparkMax ElevatorFrontLift = new CANSparkMax(4, MotorType.kBrushless);
    CANSparkMax ElevatorRearLift = new CANSparkMax(5, MotorType.kBrushless);
    WPI_VictorSPX ElevatorDrive = new WPI_VictorSPX(6);
    DigitalInput upperFrontSwitch = new DigitalInput(0);
    DigitalInput upperRearSwitch = new DigitalInput(1);
    DigitalInput lowerFrontSwitch = new DigitalInput(2);
    DigitalInput lowerRearSwitch = new DigitalInput(3);

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

    public DigitalInput getUpperFrontSwitch() {
        return upperFrontSwitch;
    }

    public DigitalInput getUpperRearSwitch() {
        return upperRearSwitch;
    }

    public DigitalInput getLowerFrontSwitch() {
        return lowerFrontSwitch;
    }

    public DigitalInput getLowerRearSwitch() {
        return lowerRearSwitch;
    }

    public DoubleSolenoid getPunch() {
        return punchSolenoid;
    }

    public DoubleSolenoid getGate() {
        return gateSolenoid;
    }
}