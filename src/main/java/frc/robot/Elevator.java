package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import edu.wpi.first.wpilibj.SpeedController;
public class Elevator{
    SpeedController ElevatorFrontLift;
    SpeedController ElevatorRearLift;
    BaseMotorController ElevatorDrive;
    public Elevator(SpeedController FrontMotor, SpeedController RearMotor, SpeedController DriveMotor) {

    }
    public void elevatorControl(double speedFront, double speedRear){
        ElevatorFrontLift.set(speedFront);
        ElevatorRearLift.set(speedRear);
    }
    public void driveElevator(double speed){
        ElevatorDrive.set(ControlMode.PercentOutput, speed);
    }

}