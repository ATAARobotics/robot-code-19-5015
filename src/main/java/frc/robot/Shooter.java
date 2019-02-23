package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class Shooter {

    private int stepNumber = 0;
    private RobotMap roboMap;
    private boolean punch = true;
    private boolean gate = true;
    public Shooter(RobotMap robotMap) {
        roboMap = robotMap;
    }

    public void shooterOff() {
        roboMap.getPunchSolenoid().set(Value.kOff);
        roboMap.getGateSolenoid().set(Value.kOff);
    }
    public void gate() {
        gate = !gate;
        if(gate) {
            roboMap.getGateSolenoid().set(Value.kForward);
        }
        else {
            roboMap.getGateSolenoid().set(Value.kReverse);
        }
    }
    
    public void punch() {
        punch = !punch;
        if(punch) {
            roboMap.getPunchSolenoid().set(Value.kForward);
        }
        else {
            roboMap.getPunchSolenoid().set(Value.kReverse);
        }
        }
    }
