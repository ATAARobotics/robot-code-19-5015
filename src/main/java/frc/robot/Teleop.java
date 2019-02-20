package frc.robot;

public class Teleop {
    // Vairables for robot classes
    private SWATDrive driveTrain;
    private Intake intake;
    private Elevator elevator;
    private RobotMap robotMap;
    private OI joysticks;
    private Shooter shooter;
    private boolean shooterDone = true;
    /*UltrasonicCode
    private Ultrasonics ultrasonics;
    */

    public Teleop() {
        //Initialize Classes
        robotMap = new RobotMap();
        joysticks = new OI();
        driveTrain = new SWATDrive(robotMap);
        intake = new Intake(robotMap.getHatchIntake());
        elevator = new Elevator(robotMap);
        shooter = new Shooter(robotMap);
    }
    public void teleopInit() {
        intake.hatchOff();
        //shooter.shooterOff();
    }

    public void TeleopPeriodic() {

        joysticks.checkInputs();
        //drive
        if(!joysticks.climbState()) {
            driveTrain.arcadeDrive(joysticks.getXSpeed() * driveTrain.getMaxStraightSpeed(), joysticks.getZRotation() * driveTrain.getMaxTurnSpeed());
        }
        //speed limiters

        if(joysticks.getGearShift()) {
            driveTrain.gearShift();
        }
        if (joysticks.getSlow()) {
            driveTrain.slow();
        }
        else; 
        

        //hatch control
        if (joysticks.getHatchOpen()) {
            intake.HatchOpen();
        }
        else if (joysticks.getHatchClosed()) {
            intake.HatchClose();
        }
        else;

        if(joysticks.getBallSecure()) {
                shooter.gate();
        }
        else if(joysticks.getBallPunch() || !shooterDone) {
            shooterDone = shooter.punch();
        }
        else;
        if(joysticks.autoClimb()) {
            elevator.elevatorDown(joysticks.elevatorSpeedDown());
        }

        //Elevator up
        if(joysticks.elevatorFrontUp()) {
            elevator.frontElevatorUp(0.5);
        }
        else {  
            elevator.frontElevatorUp(0.0);
        }

        if(joysticks.elevatorRearUp()) {
            elevator.rearElevatorUp(0.5);
        }
        else {
            elevator.rearElevatorUp(0.0);
        }

        //Drives forward on back elevator wheels
        if(joysticks.elevatorDrive()) {
            elevator.driveElevator();
        }
        else {
            elevator.stopDrive();
        }
    /* public getUltrasonicRange(int direction) {
        ultrasonic.getRange(direction);
    }
    */
    }
}