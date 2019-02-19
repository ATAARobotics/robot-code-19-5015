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
    private boolean autoClimb = false;
    private boolean manualClimbPressed;
    private boolean manualClimb = false;
    //Gunner variables
    private boolean hatchOpen;
    private boolean hatchClosed;
    private boolean punchBall;
    private boolean secureBall;
    private double manualClimbLift;
    private boolean manualRearUp;
    private boolean manualFrontUp;
    public OI() {

    }
    public void checkInputs() {
        driverScheme = Settings.driverPreferences;
        gunnerScheme = Settings.gunnerPreferences;
        switch (driverScheme) {
            case "Default":
                XSpeed = driveStick.getY(Hand.kLeft);
                ZRotation = -driveStick.getX(Hand.kRight);
                gearShift = driveStick.getXButtonReleased();
                slow = driveStick.getAButtonReleased();
                autoClimbPressed = driveStick.getBumper(Hand.kRight);
                manualClimbPressed = driveStick.getBumper(Hand.kLeft);
                manualClimbLift = driveStick.getTriggerAxis(Hand.kLeft);
                manualFrontUp = driveStick.getBumperReleased(Hand.kLeft);
                manualRearUp = driveStick.getBumperReleased(Hand.kRight);
                break;
            
            case "Reverse Turning":
                XSpeed = -driveStick.getY(Hand.kLeft);
                ZRotation = driveStick.getX(Hand.kRight);
                gearShift = driveStick.getXButtonReleased();
                slow = driveStick.getAButtonReleased();
                autoClimbPressed = driveStick.getBumper(Hand.kRight);
                manualClimbPressed = driveStick.getBumper(Hand.kLeft);
                manualClimbLift = driveStick.getTriggerAxis(Hand.kLeft);
                manualFrontUp = driveStick.getBumperReleased(Hand.kLeft);
                manualRearUp = driveStick.getBumperReleased(Hand.kRight);
                break;    
    
            default:
                XSpeed = driveStick.getY(Hand.kLeft);
                ZRotation = -driveStick.getX(Hand.kRight);
                gearShift = driveStick.getXButtonReleased();
                slow = driveStick.getAButtonReleased();
                autoClimbPressed = driveStick.getBumper(Hand.kRight);
                manualClimbPressed = driveStick.getBumper(Hand.kLeft);
                manualClimbLift = driveStick.getTriggerAxis(Hand.kLeft);
                manualFrontUp = driveStick.getBumperReleased(Hand.kLeft);
                manualRearUp = driveStick.getBumperReleased(Hand.kRight);
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
        if(autoClimbPressed) {
            autoClimb = !autoClimb;
        }
        if(manualClimbPressed) {
            manualClimb = !manualClimb;
        }
        switch (gunnerScheme) {
            case "default":
            
                break;
    
            default:
                break;
        }
    }
    public boolean climbState() {
        if(manualClimb || autoClimb) {
            return true;
        }
        else {
            return false;
        }
    }
    public double getXSpeed() {
        if(!manualClimb && !autoClimb) {
            return XSpeed;
        }
        else {
            return 0;
        }
    }
    public double getZRotation() {
        if(!manualClimb && !autoClimb) {
            return ZRotation;
        }
        else {
            return 0;
        }
    }

    public boolean getGearShift() {
        if(!manualClimb && !autoClimb) {
            return gearShift;
        }
        else {
            return false;
        }
    }

    public boolean getSlow() {
        if(!manualClimb && !autoClimb) {
            return slow;
        }
        else {
            return false;
        }
    }

    public boolean getHatchOpen() {
        if(!manualClimb && !autoClimb) {
            return hatchOpen;
        }
        else {
            return false;
        }
    }
    public boolean getHatchClosed() {
        if(!manualClimb && !autoClimb) {
            return hatchClosed;
        }
        else {
            return false;
        }
    }
    public boolean getBallPunch() {
        if(!manualClimb && !autoClimb) {
            return punchBall;
        }
        else {
            return false;
        }
    }
    public boolean getBallSecure() {
        if(!manualClimb && !autoClimb) {
            return secureBall;
        }
        else {
            return false;
        }
    }
    public double elevatorSpeedDown() {
        if(manualClimb) {
            return manualClimbLift;
        }
        else {
            return 0;
        }
    }

    public boolean elevatorFrontUp() {
        if(manualClimb && manualFrontUp) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean elevatorRearUp() {
        if(manualClimb && manualRearUp) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean autoClimb() {
        return autoClimb;
    }
}