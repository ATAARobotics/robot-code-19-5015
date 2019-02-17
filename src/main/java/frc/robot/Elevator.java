package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
public class Elevator {

    public static final int frontElevatorUpLimitContacted = 1;
    public static final int frontElevatorDownLimitContacted = 2;
    public static final int rearElevatorUpLimitContacted = 4;
    public static final int rearElevatorDownLimitContacted = 8;
    SpeedController ElevatorFrontLift;
    SpeedController ElevatorRearLift;
    BaseMotorController ElevatorDrive;
    private double elevatorSpeedFront;
    private double elevatorSpeedRear;
    private DigitalInput frontElevatorUpLimit;
    private DigitalInput frontElevatorDownLimit;
    private DigitalInput rearElevatorUpLimit;
    private DigitalInput rearElevatorDownLimit;
    private boolean climbing;
    private int climbState;
    Encoders encoders;

    public Elevator(SpeedController FrontMotor, SpeedController RearMotor, BaseMotorController DriveMotor, Encoders encoders) {
        this.encoders = encoders;
        ElevatorFrontLift = FrontMotor;
        ElevatorRearLift = RearMotor;
        ElevatorDrive = DriveMotor;
        frontElevatorUpLimit = new DigitalInput(0);
        frontElevatorDownLimit = new DigitalInput(0);
        rearElevatorUpLimit = new DigitalInput(0);
        rearElevatorDownLimit = new DigitalInput(0);
        climbing = false;
        climbState = 0;
    }

    //Sets climbing to the input value provided by teleop
    public void setAutoClimb(boolean climbing) {
        this.climbing = climbing;
        if (!climbing) {
            climbState = 0;
        }
        else {
            climbState = 1;
        }
    }

    //Calls autoClimb if the robot is climbing and the driver hasn't stopped the climb
    public boolean elevatorPeriodic() {
        if (climbing) {
            return autoClimb();
        }
        return false;
    }

    //Sets motor speeds
    private void activateElevator(){
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
        activateElevator();
    }

    public void rearElevatorUp(double speed) {
        elevatorSpeedRear = speed;
        activateElevator();
    }

    public void elevatorDown(double speed) {
        //Positive because of wiring
        elevatorSpeedFront = speed;
        elevatorSpeedRear = -1 * speed;
        activateElevator();
    }

    public int checkLimitSwitches() {
        int returnValue = 0;
        if (frontElevatorUpLimit.get() && elevatorSpeedFront < 0.0) {
                elevatorSpeedFront = 0;
                returnValue += frontElevatorUpLimitContacted;
        }
        if (frontElevatorDownLimit.get() && elevatorSpeedFront > 0.0) {
                elevatorSpeedFront = 0;
                returnValue += frontElevatorDownLimitContacted;
        }
        if (rearElevatorUpLimit.get() && elevatorSpeedRear > 0.0) {
                elevatorSpeedFront = 0;
                returnValue += rearElevatorUpLimitContacted;
        }
        if (rearElevatorDownLimit.get() && elevatorSpeedRear < 0.0) {
                elevatorSpeedFront = 0;
                returnValue += rearElevatorDownLimitContacted;
        }
        activateElevator();
        return returnValue;
    }

    public boolean getClimbing() {
        return climbing;
    }

    private void nextState() {
        encoders.reset();
        climbState++;

    }

    private boolean autoClimb() {

        boolean returnValue = false;

        //Elevator down
        switch (climbState) {

            //Elevator down
            case 1:
                elevatorDown(0.5);
                if (checkLimitSwitches() == (frontElevatorDownLimitContacted + rearElevatorDownLimitContacted)) {
                    nextState();
                }
                break;

            //Drive forward
            case 2:
                driveElevator();
                //TODO Check time required to get to a point where we can retract the front elevator
                /* if () {
                    nextState();
                } */
                break;
            
            //Retract front elevator
            case 3:
                frontElevatorUp(0.5);
                if (checkLimitSwitches() == frontElevatorUpLimitContacted) {
                    nextState();
                } 
                break;
            
            //Drive forward
            case 4:
                driveElevator();
                returnValue = true;
                //TODO Check encoder distance
                if (encoders.getDistance() > 12.5) {
                    nextState();
                }
                break;

            //Retract rear elevator
            case 5:
                stopDrive();
                rearElevatorUp(0.5);
                if (checkLimitSwitches() == rearElevatorUpLimitContacted) {
                    nextState();
                }
                break;

            //Drive forward
            case 6:
                returnValue = true;
                //TODO Check encoder distance
                if (encoders.getDistance() > 14) {
                    setAutoClimb(false);
                }
                break;

            default:
                stopDrive();
                setAutoClimb(false);
                break;
        }
        return returnValue;
    }
}