package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class Shooter {

    private Timer shooterTimer = new Timer();
    private int stepNumber = 0;
    private RobotMap roboMap;
    public Shooter(RobotMap robotMap) {
        roboMap = robotMap;
    }

    public void shooterOff() {
        roboMap.getPunchSolenoid().set(Value.kOff);
        roboMap.getGateSolenoid().set(Value.kOff);
    }
    public void gate() {
        roboMap.getGateSolenoid().set(Value.kForward);
    }
    
    public boolean punch() {
        switch (stepNumber) {
            case 0:
                shooterTimer.reset();
                shooterTimer.start();
                roboMap.getGateSolenoid().set(Value.kReverse);
                stepNumber++;
                return false;
            case 1:
                if(shooterTimer.get() >= 0.75) {
                    shooterTimer.stop();
                    shooterTimer.reset();
                    shooterTimer.start();
                    roboMap.getPunchSolenoid().set(Value.kForward);
                    stepNumber++;
                    return false;
                }
            case 2:
                if(shooterTimer.get() >= 0.75) {
                    shooterTimer.stop();
                    shooterTimer.reset();
                    shooterTimer.start();
                    roboMap.getPunchSolenoid().set(Value.kReverse);
                    stepNumber = 0;
                    return true;
                }
            default:
                return true;
        }
    }
}
