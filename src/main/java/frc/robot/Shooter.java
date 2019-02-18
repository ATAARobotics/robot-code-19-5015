package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class Shooter {
    private DoubleSolenoid punchSolenoid = new DoubleSolenoid(4, 5);
    private DoubleSolenoid gateSolenoid = new DoubleSolenoid(6, 7);
    private Timer shooterTimer = new Timer();
    private int stepNumber = 0;
    public Shooter() {
    }

    public void shooterOff() {
        punchSolenoid.set(Value.kOff);
        gateSolenoid.set(Value.kOff);
    }
    public void gate() {
        gateSolenoid.set(Value.kForward);
    }
    
    public boolean punch() {
        switch (stepNumber) {
            case 0:
                shooterTimer.reset();
                shooterTimer.start();
                gateSolenoid.set(Value.kReverse);
                stepNumber++;
                return false;
            case 1:
                if(shooterTimer.get() >= 0.75) {
                    shooterTimer.stop();
                    shooterTimer.reset();
                    shooterTimer.start();
                    punchSolenoid.set(Value.kForward);
                    stepNumber++;
                    return false;
                }
            case 2:
                if(shooterTimer.get() >= 0.75) {
                    shooterTimer.stop();
                    shooterTimer.reset();
                    shooterTimer.start();
                    punchSolenoid.set(Value.kReverse);
                    stepNumber = 0;
                    return true;
                }
            default:
                return true;
        }
    }
}
