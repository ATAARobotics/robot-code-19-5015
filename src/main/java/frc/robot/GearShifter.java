package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class GearShifter{

    DoubleSolenoid m_leftGearShift;
    DoubleSolenoid m_rightGearShift;

    public GearShifter(DoubleSolenoid leftGearShift,DoubleSolenoid rightGearShift){
        m_leftGearShift = leftGearShift;
        m_rightGearShift = rightGearShift;
    }

    public void setSlow(boolean slow){
        if (slow){
            m_leftGearShift.set(DoubleSolenoid.Value.kForward);
            m_rightGearShift.set(DoubleSolenoid.Value.kForward);
        } 
        if (!slow){
            m_leftGearShift.set(DoubleSolenoid.Value.kReverse);
            m_rightGearShift.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void setOff(){
        m_leftGearShift.set(DoubleSolenoid.Value.kOff);
        m_rightGearShift.set(DoubleSolenoid.Value.kOff);
    }
}