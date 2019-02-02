package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Elevator{
    public TalonSRX ElevatorDriveTalon = new TalonSRX(4);
    public TalonSRX ElevatorLeftLiftTalon = new TalonSRX (5);
    public TalonSRX ElevatorRightLiftTalon = new TalonSRX (6);

    public void liftRobot(){

        ElevatorLeftLiftTalon.set(ControlMode.PercentOutput, 0.7);
        ElevatorRightLiftTalon.set(ControlMode.PercentOutput, 0.7);
    }

    public void lowerRobot(){

        ElevatorLeftLiftTalon.set(ControlMode.PercentOutput, -0.7);
        ElevatorRightLiftTalon.set(ControlMode.PercentOutput, -0.7);

    }

    public void driveForward(){

        ElevatorDriveTalon.set(ControlMode.PercentOutput, 0.7);

    }

    public void driveBackward(){

        ElevatorDriveTalon.set(ControlMode.PercentOutput, -0.7);

    }

}