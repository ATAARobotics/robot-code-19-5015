package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;


public class Teleop {

    private Joystick gunnerStick = new Joystick(1);
    private Joystick driveStick = new Joystick(0);
    private SWATDrive driveTrain;
    private Intake intake;
    public Teleop() {
        //Drive Controllers
        WPI_VictorSPX rearLeftMotor = new WPI_VictorSPX(1);
        WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(0);
        WPI_VictorSPX rearRightMotor = new WPI_VictorSPX(3);
        WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(2);
        //Group Drive
        SpeedControllerGroup rightMotors = new SpeedControllerGroup(rearRightMotor, frontRightMotor);
        SpeedControllerGroup leftMotors = new SpeedControllerGroup(rearLeftMotor, frontLeftMotor);
        //Add pneumatics
        DoubleSolenoid hatchIntakeSolenoid = new DoubleSolenoid(0, 1);  
        DoubleSolenoid m_gearShiftSolenoid = new DoubleSolenoid(2, 3);  
        driveTrain = new SWATDrive(leftMotors, rightMotors, m_gearShiftSolenoid);
        intake = new Intake(hatchIntakeSolenoid);
    }
    public void teleopInit() {

        intake.hatchOff();
    }
    public void TeleopPeriodic() {

          driveTrain.arcadeDrive(driveStick.getRawAxis(1), driveStick.getRawAxis(4));
          if(driveStick.getRawButton(3)) {
            driveTrain.gearShift();
          }
          if (driveStick.getRawButton(1)) {
            driveTrain.slow();
          } 
        intake.IntakePeriodic(gunnerStick.getRawButton(5), gunnerStick.getRawButton(6));


        intake.IntakePeriodic(gunnerStick.getRawButton(5), gunnerStick.getRawButton(6));
        
    }
}