package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Elevator {

    private double elevatorSpeedFront = 0.0;
    private double elevatorSpeedRear = 0.0;
    private RobotMap robotMap;

    public Elevator(RobotMap roboMap) {
        robotMap = roboMap;
    }

    //Sets motor speeds
    private void activateElevator(){
        robotMap.getElevatorFront().set(elevatorSpeedFront);
        robotMap.getElevatorRear().set(elevatorSpeedRear);
    }

    //Drives forward on back elevator wheels
    public void driveElevator(){
        robotMap.getElevatorDrive().set(ControlMode.PercentOutput, 0.5);
    }

    //Stops driving forward on elevator wheels
    public void stopDrive() {
        robotMap.getElevatorDrive().set(ControlMode.PercentOutput, 0);
    }

    public void frontElevatorUp(double speed) {
        //Negative because of the wiring
        if (robotMap.getFrontElevatorUpLimit().get()) {
            elevatorSpeedFront = -1 * speed;
            activateElevator();
        }
    }

    public void rearElevatorUp(double speed) {
        if (robotMap.getRearElevatorUpLimit().get()) {
            if(speed < 0) {
                speed = -speed;
            }
            elevatorSpeedRear = speed;
            activateElevator();
        }
    }

    public void rearElevatorDown(double speed) {
            if(speed > 0) {
                speed = -speed;
            }
            elevatorSpeedRear = speed;
            activateElevator();
    }

    public void elevatorDown(double speed) {
        //Positive because of wiring
        elevatorSpeedFront = speed;
        elevatorSpeedRear = -0.92 * speed;
        activateElevator();
    }
}