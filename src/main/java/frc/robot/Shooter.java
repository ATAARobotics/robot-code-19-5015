package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;

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

	public void shooterInit() {
        roboMap.getPunchSolenoid().set(Value.kForward);
        roboMap.getGateSolenoid().set(Value.kForward);
    }
    
    public boolean autoShoot() {
        System.out.println("StepNumber: " + stepNumber);
        switch (stepNumber) {
            case 0:
                gate();
                stepNumber++;
                return false;
            case 9:
                punch();
                stepNumber++;
                return false;
            case 19:
                punch();
                stepNumber = 0;
                return true;
            default:
                if(stepNumber > 19) {
                    stepNumber = 0;
                    return true;
                }
                else {
                    stepNumber++;
                    return false;
                }
        }
    }
}
