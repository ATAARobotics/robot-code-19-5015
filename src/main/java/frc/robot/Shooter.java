package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class Shooter {

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
    
    public void punch() {
        switch (stepNumber) {
            case 0:
                roboMap.getGateSolenoid().set(Value.kReverse);
                stepNumber++;
                punch();
                break;
            case 3:
                roboMap.getPunchSolenoid().set(Value.kForward);
                stepNumber++;
                punch();
                break;
            case 5:
                    roboMap.getPunchSolenoid().set(Value.kReverse);
                    stepNumber = 0;
                    break;
            default:
                if(stepNumber <= 5) {
                    stepNumber++;
                    punch();
                }
                else {
                    stepNumber = 0;
                }
                break;
        }
    }
}
