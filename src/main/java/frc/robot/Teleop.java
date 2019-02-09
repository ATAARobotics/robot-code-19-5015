package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.DoubleSolenoid;


public class Teleop {

    private Joystick gunnerStick = new Joystick(1);
    private Joystick driveStick = new Joystick(0);
    private Drive drive = new Drive();
    private Intake intake;
    public Teleop() {
        //Add pneumatics
        DoubleSolenoid hatchIntakeSolenoid = new DoubleSolenoid(0, 1);  
        DoubleSolenoid m_gearShiftSolenoid = new DoubleSolenoid(2, 3);  
        intake = new Intake(hatchIntakeSolenoid);
    }
    public void init() {
        intake.hatchOff();
    }
    public void TeleopInit() {
      
    }
  
    public void TeleopPeriodic() {
      drive.drive(driveStick);
      
      if(driveStick.getRawButton(3)) {
        drive.gearShift();
      }
    }
  
    public void ExtDrive(double leftValue, double rightValue) {
      drive.ExtDrive(leftValue, rightValue);
    }
}
  