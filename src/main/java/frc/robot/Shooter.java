package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class Shooter {
    private DoubleSolenoid punchSolenoid = new DoubleSolenoid(4, 5);
    //private DoubleSolenoid gateSolenoid = new DoubleSolenoid(6, 7);
    //private boolean pneumaticShooter = true;
    private boolean punchOut;
    //private boolean gateClosed;
    public Shooter() {
    }

    public void shooterOff() {
        punchSolenoid.set(Value.kOff);
        //gateSolenoid.set(Value.kOff);
    }
    /*public void gate() {
        if(gateClosed) {
            gateSolenoid.set(Value.kReverse);
        }
        else {
            gateSolenoid.set(Value.kForward);
        }
        gateClosed = !gateClosed;
    } */
    
    public void punch() {
        if(punchOut) {
            punchSolenoid.set(Value.kReverse);
        }
        else {
            punchSolenoid.set(Value.kForward);
        }
        punchOut = !punchOut;
    }
}
