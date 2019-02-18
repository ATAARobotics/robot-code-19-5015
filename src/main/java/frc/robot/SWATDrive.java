package frc.robot;

// import libraries
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


public class SWATDrive {

    DifferentialDrive driveTrain;

    //slow variable
    private boolean slow = false;
    //low gear variable
    private static boolean lowGear = true;
    //create the pneumatics variable
    DoubleSolenoid m_gearShifter;

    private WPI_TalonSRX frontLeftMotor;
    private WPI_VictorSPX rearLeftMotor;
    private WPI_TalonSRX frontRightMotor;
    private WPI_VictorSPX rearRightMotor;
    private SpeedControllerGroup rightMotors;
    private SpeedControllerGroup leftMotors;
    private Encoders encoders;
    
    public SWATDrive(DoubleSolenoid gearShifter) {
        //Drive Controllers
        frontLeftMotor = new WPI_TalonSRX(0);
        rearLeftMotor = new WPI_VictorSPX(1);
        frontRightMotor = new WPI_TalonSRX(2);
        rearRightMotor = new WPI_VictorSPX(3);
        
        rightMotors = new SpeedControllerGroup(frontRightMotor, rearRightMotor);
        leftMotors = new SpeedControllerGroup(frontLeftMotor, rearLeftMotor);

        driveTrain = new DifferentialDrive(leftMotors, rightMotors);
        m_gearShifter = gearShifter;

        encoders = new Encoders(frontLeftMotor, frontRightMotor);
    }
    public void gearShift() {
        lowGear = !lowGear;
        if(lowGear) {
            m_gearShifter.set(DoubleSolenoid.Value.kReverse);
        }
        else {
            m_gearShifter.set(DoubleSolenoid.Value.kForward);
        }
    }
    public void slow() {
        slow = !slow;
        if(slow) {
            driveTrain.setMaxOutput(0.4);
        }
        else {
            driveTrain.setMaxOutput(1);
        }
    }

    public void arcadeDrive(double speed, double rotation) {
        driveTrain.arcadeDrive(speed, rotation);
    }

    public void tankDrive(double lSpeed, double rSpeed) {
        driveTrain.tankDrive(lSpeed, rSpeed);
    }

    public void debugEncoders() {
        encoders.encoderDebug();
    }
}