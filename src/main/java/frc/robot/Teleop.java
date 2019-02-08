package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Teleop {

    private Joystick gunnerStick = new Joystick(1);
    private Joystick driveStick = new Joystick(0);

    DoubleSolenoid hatchIntakeSolenoid = new DoubleSolenoid(0, 1);
    //DoubleSolenoid gearShiftSolenoid = new DoubleSolenoid(2, 3);
    private VictorSPX rearLeftMotor = new VictorSPX(1);
    private TalonSRX frontLeftMotor = new TalonSRX(0);
    private VictorSPX rearRightMotor = new VictorSPX(3);
    private TalonSRX frontRightMotor = new TalonSRX(2);
    private SWATDrive driveTrain = new SWATDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor); //add gearShifter param
    private Intake intake = new Intake(hatchIntakeSolenoid);

    public void init() {
        intake.hatchOff();
    }


    public void TeleopPeriodic() {
          driveTrain.arcadeDrive(driveStick.getRawAxis(1), driveStick.getRawAxis(4), true);
        intake.IntakePeriodic(gunnerStick.getRawButton(5), gunnerStick.getRawButton(6));
          if (driveStick.getRawButton(1)) {
            driveTrain.slow = !driveTrain.slow;
          } 
    }
}
