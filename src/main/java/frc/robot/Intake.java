package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Intake{

    DoubleSolenoid m_hatchIntake;
    boolean hatchOpen;
    public Intake(DoubleSolenoid hatchIntake){
        m_hatchIntake = hatchIntake;
    }

    public void HatchOpen(){
        m_hatchIntake.set(DoubleSolenoid.Value.kReverse);
    }
    public void HatchClose(){
        m_hatchIntake.set(DoubleSolenoid.Value.kForward);
    }

    public void hatchOff(){
        m_hatchIntake.set(DoubleSolenoid.Value.kOff);
    }
}