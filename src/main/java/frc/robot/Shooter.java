package frc.robot;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath.Step;

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
    
    public void punch() {
        switch (stepNumber) {
            case 0:
                shooterTimer.reset();
                shooterTimer.start();
                gateSolenoid.set(Value.kReverse);
                stepNumber++;
                punch();
                break;
            case 1:
                if(shooterTimer.get() >= 0.75) {
                    shooterTimer.stop();
                    shooterTimer.reset();
                    shooterTimer.start();
                    punchSolenoid.set(Value.kForward);
                    stepNumber++;
                    punch();
                    break;
                }
            case 2:
                if(shooterTimer.get() >= 0.75) {
                    shooterTimer.stop();
                    shooterTimer.reset();
                    shooterTimer.start();
                    punchSolenoid.set(Value.kReverse);
                    stepNumber = 0;
                    break;
                }
            default:
                break;
        }
    }
}
