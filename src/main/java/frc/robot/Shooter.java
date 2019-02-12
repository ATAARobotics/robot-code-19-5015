package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class Shooter {
    private SpeedController Shooter;  
    private SpeedController Intake;
    private DoubleSolenoid punchSolenoid = new DoubleSolenoid(4, 5);
    private DoubleSolenoid gateSolenoid = new DoubleSolenoid(6, 7);
    private boolean pneumaticShooter = true;
    private boolean punchOut;
    private boolean gateClosed;
    public Shooter(SpeedController Shooter, SpeedController Intake) {
        this.Shooter = Shooter;
        this.Intake = Intake;
        pneumaticShooter = false;
    }
    public Shooter(DoubleSolenoid gateSolenoid, DoubleSolenoid punchSolenoid) {
        this.gateSolenoid = gateSolenoid;
        this.punchSolenoid = punchSolenoid;
    }
    public void shootBall(){
        Shooter.set( -1);
        Intake.set(1);
    }
    
    //one motor turns to get the ball into the shooter
    public void intakeBall(){
        Intake.set(0.7);
    }

    public void gate() {
        if(gateClosed) {
            gateSolenoid.set(Value.kReverse);
        }
        else {
            gateSolenoid.set(Value.kForward);
        }
        gateClosed = !gateClosed;
    }
    
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
