package frc.robot;

public class Teleop {
    // Vairables for robot classes
    private SWATDrive driveTrain;
    private Intake intake;
    private Elevator elevator;
    private RobotMap robotMap;
    private OI joysticks;
    private Shooter shooter;

    /*UltrasonicCode
    private Ultrasonics ultrasonics;
    */

    public Teleop() {
        //Initialize Classes
        robotMap = new RobotMap();
        joysticks = new OI();
        driveTrain = new SWATDrive(robotMap.getLeftMotors(), robotMap.getRightMotors(), robotMap.getGearShift());
        intake = new Intake(robotMap.getHatchIntake());
        elevator = new Elevator(robotMap.getElevatorFront(), robotMap.getElevatorRear(), robotMap.getElevatorDrive());
        shooter = new Shooter();
    }
    public void teleopInit() {
        intake.hatchOff();
        //shooter.shooterOff();
    }

    public void TeleopPeriodic() {

        joysticks.checkInputs();
        //drive
        if(!joysticks.climbState()) {
            driveTrain.arcadeDrive(joysticks.getXSpeed(), joysticks.getZRotation());
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
            else if(joysticks.getBallPunch()) {
                shooter.punch();
            }
    /* public getUltrasonicRange(int direction) {
        ultrasonic.getRange(direction);
    }
    */
    }
}