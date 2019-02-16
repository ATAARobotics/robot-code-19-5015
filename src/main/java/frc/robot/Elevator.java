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
    private double elevatorSpeedFront;
    private double elevatorSpeedRear;
    public Elevator(SpeedController FrontMotor, SpeedController RearMotor, BaseMotorController DriveMotor) {
        ElevatorFrontLift = FrontMotor;
        ElevatorRearLift = RearMotor;
        ElevatorDrive = DriveMotor;
    }

    public void activateElevator(){
        ElevatorFrontLift.set(elevatorSpeedFront);
        ElevatorRearLift.set(elevatorSpeedRear);
    }

    //Drives forward on back elevator wheels
    public void driveElevator(){
        ElevatorDrive.set(ControlMode.PercentOutput, 0.5);
    }

    //Stops driving forward on elevator wheels
    public void stopDrive() {
        ElevatorDrive.set(ControlMode.PercentOutput, 0);
    }

    public void frontElevatorUp(double speed) {
        //Negative because of the wiring
        elevatorSpeedFront = -1 * speed;
    }

    public void rearElevatorUp(double speed) {
        elevatorSpeedRear = speed;
    }

    public void elevatorDown(double speed) {
        //Positive because of wiring
        elevatorSpeedFront = speed;
        elevatorSpeedRear = -1 * speed;
    }

}