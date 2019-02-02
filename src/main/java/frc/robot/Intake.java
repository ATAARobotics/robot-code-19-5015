package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Intake{

    DoubleSolenoid m_hatchIntake = new DoubleSolenoid(0,1);

    public Intake(DoubleSolenoid hatchIntake){
        m_hatchIntake = hatchIntake;
    }

    
    public void setHatchState(boolean state){
        if(state){
            m_hatchIntake.set(DoubleSolenoid.Value.kForward);
        }
        if(!state){
            m_hatchIntake.set(DoubleSolenoid.Value.kReverse);
        }

        
    }


    

}