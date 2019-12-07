package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
/**
 * File for the hatch intake system
 * 
 * @author Alexander Greco
 */

public class Intake{


    DoubleSolenoid m_hatchIntake;
    DoubleSolenoid m_punchSolenoid;
    int stepNumber = 0;

    boolean hatchOpen;
    public Intake(DoubleSolenoid hatchIntake, DoubleSolenoid punchSolenoid){
        m_hatchIntake = hatchIntake;
        m_punchSolenoid = punchSolenoid;
    }
    //set hatch state for reverse claw and punch
    public void HatchOpen(){
        m_hatchIntake.set(DoubleSolenoid.Value.kReverse);
    }
    public void HatchClose(){
        m_hatchIntake.set(DoubleSolenoid.Value.kForward);
    }
    public void punchIn(){
        m_punchSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    public void punchOut(){
        m_punchSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    public void hatchOff(){
        m_hatchIntake.set(DoubleSolenoid.Value.kOff);
    }
    // releases hatch and punches in and out
    public boolean autoPunch() {
        switch (stepNumber) {
            case 0:
                HatchClose();
                stepNumber++;
                return false;
            case 9:
                punchOut();
                stepNumber++;
                return false;
            case 19:
                punchIn();
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