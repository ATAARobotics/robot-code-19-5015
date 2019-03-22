package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Timer;

public class Elevator {

    public static final int frontElevatorUpLimitContacted = 1;
    public static final int frontElevatorDownLimitContacted = 2;
    public static final int rearElevatorUpLimitContacted = 4;
    public static final int rearElevatorDownLimitContacted = 8;
    private double elevatorSpeedFront = 0.0;
    private double elevatorSpeedRear = 0.0;
    private boolean climbing = false;
    private int climbState = 0;
    private Timer timer;
    private RobotMap robotMap;

    public Elevator(RobotMap roboMap) {
        robotMap = roboMap;
        timer = new Timer();
    }

    //Sets climbing to the input value provided by teleop
    public void setAutoClimb() {
        climbing = !climbing;
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
        if (robotMap.getRearElevatorDownLimit().get()) {
            if(speed > 0) {
                speed = -speed;
            }
            elevatorSpeedRear = speed;
            activateElevator();
        }
    }

    public void elevatorDown(double speed) {
        if (robotMap.getFrontElevatorDownLimit().get()) {
            //Positive because of wiring
            elevatorSpeedFront = speed;
        }
        if (robotMap.getRearElevatorDownLimit().get()) {
            elevatorSpeedRear = -0.92 * speed;
        }
        activateElevator();
    }

    public int checkLimitSwitches() {
        int returnValue = 0;
        if (!robotMap.getFrontElevatorUpLimit().get() && elevatorSpeedFront < 0.0) {
                elevatorSpeedFront = 0;
                returnValue += frontElevatorUpLimitContacted;
        }
        if (!robotMap.getFrontElevatorDownLimit().get() && elevatorSpeedFront > 0.0) {
                elevatorSpeedFront = 0;
                returnValue += frontElevatorDownLimitContacted;
        }
        if (!robotMap.getRearElevatorUpLimit().get() && elevatorSpeedRear > 0.0) {
                elevatorSpeedFront = 0;
                returnValue += rearElevatorUpLimitContacted;
        }
        if (!robotMap.getRearElevatorDownLimit().get() && elevatorSpeedRear < 0.0) {
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
        robotMap.getEncoders().reset();
        stopDrive();
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
                    timer.reset();
                    timer.start();
                    nextState();
                }
                break;

            //Drive forward
            case 2:
                driveElevator();
                if (timer.get() > 1) {
                    nextState();
                }
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
                if (robotMap.getEncoders().getDistance() > 12.5) {
                    nextState();
                }
                break;

            //Retract rear elevator
            case 5:
                rearElevatorUp(0.5);
                if (checkLimitSwitches() == rearElevatorUpLimitContacted) {
                    nextState();
                }
                break;

            //Drive forward
            case 6:
                returnValue = true;
                if (robotMap.getEncoders().getDistance() > 14) {
                    nextState();
                }
                break;

            default:
                stopDrive();
                if(getClimbing()) {
                    setAutoClimb();
                }
                break;
        }
        return returnValue;
    }
}