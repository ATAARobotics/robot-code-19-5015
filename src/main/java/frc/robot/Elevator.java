package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
public class Elevator{
    SpeedController ElevatorFrontLift;
    Encoder ElevatorFrontEncoder;
    SpeedController ElevatorRearLift;
    Encoder ElevatorRearEncoder;
    BaseMotorController ElevatorDrive;
    public Elevator(SpeedController FrontMotor, SpeedController RearMotor, BaseMotorController DriveMotor) {
        ElevatorFrontLift = FrontMotor;
        ElevatorRearLift = RearMotor;
        ElevatorDrive = DriveMotor;
    }
    public void elevatorControl(double speedFront, double speedRear){
        ElevatorFrontLift.set(speedFront);
        ElevatorRearLift.set(speedRear);
    }
    public void driveElevator(){
        ElevatorDrive.set(ControlMode.PercentOutput, 0.5);
    }
    public void stopDrive() {
        ElevatorDrive.set(ControlMode.PercentOutput, 0);
    }

}