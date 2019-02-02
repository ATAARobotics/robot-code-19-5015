package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Intake{

    DoubleSolenoid m_hatchIntake = new DoubleSolenoid(0,1);

    public Intake(DoubleSolenoid hatchIntake){
        m_hatchIntake = hatchIntake;
    }

    
    public void setHatchState(boolean state){
        if(state){
            m_hatchIntake.set(DoubleSolenoid.Value.kReverse);
        }
        if(!state){
            m_hatchIntake.set(DoubleSolenoid.Value.kForward);
        }
    }

    public void IntakePeriodic(boolean hatchOpen, boolean HatchClosed){
        if(hatchOpen) {
            this.setHatchState(true);
          }
        else if(HatchClosed) {
            this.setHatchState(false);
        }
    }
}