package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class GearShifter{

    DoubleSolenoid m_gearShiftSolenoid;

    public GearShifter(DoubleSolenoid gearShiftSolenoid){
        m_gearShiftSolenoid = gearShiftSolenoid;
    }

    public void setSlow(boolean slow){
        if (slow){
            m_gearShiftSolenoid.set(DoubleSolenoid.Value.kForward);
        } 
        if (!slow){
            m_gearShiftSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void setOff(){
        m_gearShiftSolenoid.set(DoubleSolenoid.Value.kOff);
    }
}