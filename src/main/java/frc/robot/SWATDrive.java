package frc.robot;

// import libraries
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class SWATDrive extends DifferentialDrive {
    //slow variable
    private boolean slow = false;
    //low gear variable
    private static boolean lowGear = true;
    //create the pneumatics variable
    DoubleSolenoid m_gearShifter;
    private double maxTurnSpeed;
    private double maxStraightSpeed;
    
    public SWATDrive(SpeedController leftMotor, SpeedController rightMotor, DoubleSolenoid gearShifter) {
        super(leftMotor, rightMotor);
        m_gearShifter = gearShifter;
        maxTurnSpeed = 0.8;
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
            setMaxOutput(Settings.driveSpeedSlowAll);
            maxTurnSpeed = Settings.driveSpeedSlowTurning;
            maxStraightSpeed = Settings.driveSpeedSlowStraight;
        }
        else {
            setMaxOutput(Settings.driveSpeedNormalAll);
            maxTurnSpeed = Settings.driveSpeedNormalTurning;
            maxStraightSpeed = Settings.driveSpeedNormalStraight;
        }
    }
    
    public double getMaxTurnSpeed() {
        return maxTurnSpeed;
    }
    public double getMaxStraightSpeed() {
        return maxStraightSpeed;
    }
}