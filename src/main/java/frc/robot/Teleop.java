package frc.robot;

public class Teleop {
    // Vairables for robot classes
    private SWATDrive driveTrain;
    private Intake intake;
    private Elevator elevator;
    private RobotMap robotMap;
    private OI joysticks;
    private Shooter shooter;
    private boolean autoShoot = true;
    private boolean shooterDone = true;
    private boolean punchDone = true;

    /*UltrasonicCode
    private Ultrasonics ultrasonics;
    */

    public Teleop() {
        //Initialize Classes
        robotMap = new RobotMap();
        joysticks = new OI();
        driveTrain = new SWATDrive(robotMap);
        intake = new Intake(robotMap.getHatchIntake(), robotMap.getHatchPunch());
        elevator = new Elevator(robotMap);
        shooter = new Shooter(robotMap);
    }
    public void teleopInit() {
        //intake.hatchOff();
        shooter.shooterInit();
    }

    public void TeleopPeriodic() {
        joysticks.checkInputs();
        //drive
        if(joysticks.autoClimbPressed()) {
            elevator.setAutoClimb();

        }

        if(!elevator.getClimbing()) {
            driveTrain.arcadeDrive(joysticks.getXSpeed() * driveTrain.getMaxStraightSpeed(), joysticks.getZRotation() * driveTrain.getMaxTurnSpeed());
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
            /*if (joysticks.getHatchPunchOut()) {
                intake.punchOut();
            }
            else if (joysticks.getHatchPunchIn()) {
                intake.punchIn();
            }*/
            if (joysticks.getAutoPunch() || !punchDone) {
                punchDone = intake.autoPunch();
            }
            else;

            if(joysticks.getAutoShoot() || !shooterDone) {
                shooterDone = shooter.autoShoot();
            }

            else if(joysticks.getBallSecure()) {
                    shooter.gate();
            }
            else if(joysticks.getBallPunch() && !autoShoot) {
                shooter.punch();
            }
            else;

            elevator.elevatorDown(joysticks.elevatorSpeedDown());
            //Elevator up
            if(joysticks.elevatorFrontUp()) {
                elevator.frontElevatorUp(0.5);
            }

            if(joysticks.elevatorRearDown() < -0.2) {
                elevator.rearElevatorDown(0.5);
            }

            else if(joysticks.elevatorRearUp()) {
                elevator.rearElevatorUp(0.5);
            }

            //Drives forward on back elevator wheels
            if(joysticks.elevatorDrive()) {
                elevator.driveElevator();
            }
            else {
                elevator.stopDrive();
            }
        }
        elevator.elevatorPeriodic();
    /* public getUltrasonicRange(int direction) {
        ultrasonic.getRange(direction);
    }
    */
    }
	public void drive(double speedA, double speedB, boolean arcade) {
        if(arcade) {
            driveTrain.arcadeDrive(speedA, speedB);
        }
        else {
            driveTrain.tankDrive(speedA, speedB);
        }
	}
	public double getInches() {
		return robotMap.getEncoders().getDistance();
    }
    public void hatch(boolean outake) {
        if(outake) {
            intake.HatchOpen();
        }
        else {
            intake.HatchClose();
        }
    }
	public void TestPeriodic() {
        joysticks.checkInputs();
	}
}