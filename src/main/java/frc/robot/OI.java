package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
class OI {
    private XboxController driveStick = new XboxController(0);
    private Joystick gunnerStick = new Joystick(1);
    private String driverScheme = "default";
    private String gunnerScheme = "default";
    //Driver Variables
    private double XSpeed;
    private double ZRotation;
    private boolean gearShift;
    private boolean slow;
    private boolean autoClimbPressed;

    //Gunner variables
    private boolean hatchOpen;
    private boolean hatchClosed;
    private boolean punchBall;
    private boolean secureBall;
    private double manualClimbLift;
    private boolean manualRearUp;
    private boolean manualFrontUp;
    private boolean manualDrive;
    private boolean manualControl;
    public OI() {

    }
    public void checkInputs() {
        //driverScheme = Settings.driverPreferences;
        //gunnerScheme = Settings.gunnerPreferences;
        switch (driverScheme) {
            case "Default":
                XSpeed = driveStick.getY(Hand.kLeft);
                ZRotation = -driveStick.getX(Hand.kRight);
                gearShift = driveStick.getXButtonReleased();
                slow = driveStick.getAButtonReleased();
                autoClimbPressed = driveStick.getBumperReleased(Hand.kRight);
                manualClimbLift = driveStick.getTriggerAxis(Hand.kLeft);
                manualFrontUp = driveStick.getAButton();
                manualRearUp = driveStick.getBackButton();
                manualDrive = driveStick.getStartButton();
                manualControl = driveStick.getAButton();
                break;
            
            case "Reverse Turning":
                XSpeed = -driveStick.getY(Hand.kLeft);
                ZRotation = driveStick.getX(Hand.kRight);
                gearShift = driveStick.getXButtonReleased();
                slow = driveStick.getAButtonReleased();
                autoClimbPressed = driveStick.getBumperReleased(Hand.kRight);
                manualClimbLift = driveStick.getTriggerAxis(Hand.kLeft);
                manualFrontUp = driveStick.getBackButton();
                manualRearUp = driveStick.getStartButton();
                manualDrive = driveStick.getYButton();
                manualControl = driveStick.getAButton();
                break;    
    
            default:
                XSpeed = driveStick.getY(Hand.kLeft);
                ZRotation = -driveStick.getX(Hand.kRight);
                gearShift = driveStick.getXButtonReleased();
                slow = driveStick.getAButtonReleased();
                autoClimbPressed = driveStick.getBumperReleased(Hand.kRight);
                manualClimbLift = driveStick.getTriggerAxis(Hand.kLeft);
                manualFrontUp = driveStick.getBackButton();
                manualRearUp = driveStick.getStartButton();
                manualDrive = driveStick.getYButton();
                manualControl = driveStick.getAButton();
                break;
        }
        switch (gunnerScheme) {
            case "Default":
                hatchOpen = gunnerStick.getRawButtonReleased(5);
                hatchClosed = gunnerStick.getRawButtonReleased(6);
                secureBall = gunnerStick.getRawButtonReleased(7);
                punchBall = gunnerStick.getRawButtonReleased(8);
                break;
            case "Reverse Hatch":
                hatchOpen = gunnerStick.getRawButtonReleased(6);
                hatchClosed = gunnerStick.getRawButtonReleased(5);
                secureBall = gunnerStick.getRawButtonReleased(7);
                punchBall = gunnerStick.getRawButtonReleased(8);
                break;
            case "Reverse Ball":
                hatchOpen = gunnerStick.getRawButtonReleased(5);
                hatchClosed = gunnerStick.getRawButtonReleased(6);
                secureBall = gunnerStick.getRawButtonReleased(8);
                punchBall = gunnerStick.getRawButtonReleased(7);
                break;
            case "Reverse All":
                hatchOpen = gunnerStick.getRawButtonReleased(6);
                hatchClosed = gunnerStick.getRawButtonReleased(5);
                secureBall = gunnerStick.getRawButtonReleased(8);
                punchBall = gunnerStick.getRawButtonReleased(7);
                break;    
            default:
                hatchOpen = gunnerStick.getRawButtonReleased(5);
                hatchClosed = gunnerStick.getRawButtonReleased(6);
                secureBall = gunnerStick.getRawButtonReleased(7);
                punchBall = gunnerStick.getRawButtonReleased(8);
                break;
        }
        
    }
    public double getXSpeed() {
        return XSpeed;
    }
    public double getZRotation() {
        return ZRotation;
    }

    public boolean getGearShift() {
        return gearShift;
    }

    public boolean getSlow() {
        return slow;
    }

    public boolean getHatchOpen() {
        return hatchOpen;
    }
    public boolean getHatchClosed() {
        return hatchClosed;
    }
    public boolean getBallPunch() {
        return punchBall;
    }
    public boolean getBallSecure() {
        return secureBall;
    }
    public double elevatorSpeedDown() {
        return manualClimbLift;
    }

    public boolean elevatorFrontUp() {
        if(manualFrontUp) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean elevatorRearUp() {
        if(manualRearUp) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean elevatorDrive() {
        if(manualDrive) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean autoClimbPressed() {
        return autoClimbPressed;
    }
	public boolean setManualControl() {
		return manualControl;
	}
}