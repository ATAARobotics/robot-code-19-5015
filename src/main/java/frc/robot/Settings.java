package frc.robot;

import java.io.File;
//import java.util.Map;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

/**
 * Settings class that contains all shuffleboard required code so it's all in a 
 * centralized locaton.
 * 
 * @author Cool Kornak
 */

public class Settings {
    
	static SettingsFile settingsFile = new SettingsFile(new File("settings.txt"));
	String settingsActive = settingsFile.toString();

    static public boolean LOGGING_ENABLED = settingsFile.getBooleanProperty("LOGGING_ENABLED", false);

    static public int DRIVER_CONTROLLER_PORT = settingsFile.getIntProperty("DRIVER_CONTROLLER_PORT", 0);

    static public int CARGO_BUTTON_PORT = settingsFile.getIntProperty("CARGO_BUTTON_PORT", 0);

    static public int GEAR_SOLENOID_PORT_1 = settingsFile.getIntProperty("GEAR_SOLENOID_PORT_1", 1);
    static public int GEAR_SOLENOID_PORT_2 = settingsFile.getIntProperty("GEAR_SOLENOID_PORT_2", 0);

    static public int FRONT_LEFT_MOTOR_PORT = settingsFile.getIntProperty("FRONT_LEFT_MOTOR_PORT", 0);
    static public int REAR_LEFT_MOTOR_PORT = settingsFile.getIntProperty("REAR_LEFT_MOTOR_PORT", 1);
    static public int FRONT_RIGHT_MOTOR_PORT = settingsFile.getIntProperty("FRONT_RIGHT_MOTOR_PORT", 2);
    static public int REAR_RIGHT_MOTOR_PORT = settingsFile.getIntProperty("REAR_RIGHT_MOTOR_PORT", 3);

    static public int CARGO_OUTTAKE_LEFT_PORT = settingsFile.getIntProperty("CARGO_OUTTAKE_LEFT_PORT", 0);
    static public int CARGO_OUTTAKE_RIGHT_PORT = settingsFile.getIntProperty("CARGO_OUTTAKE_RIGHT_PORT", 1);
    static public int INTAKE_ROTATE_PORT_1 = settingsFile.getIntProperty("INTAKE_ROTATE_PORT_1", 2);
    static public int INTAKE_ROTATE_PORT_2 = settingsFile.getIntProperty("INTAKE_ROTATE_PORT_2", 3);

    static public int HATCH_SOLENOID_PORT_1 = settingsFile.getIntProperty("HATCH_SOLENOID_PORT_1", 2);
    static public int HATCH_SOLENOID_PORT_2 = settingsFile.getIntProperty("HATCH_SOLENOID_PORT_2", 3);

    static public int MECHANUM_MOTOR_PORT = settingsFile.getIntProperty("MECHANUM_MOTOR_PORT", 4);
    static public int MECHANUM_SOLENOID_PORT_1 = settingsFile.getIntProperty("MECHANUM_SOLENOID_PORT_1", 7);
    static public int MECHANUM_SOLENOID_PORT_2 = settingsFile.getIntProperty("MECHANUM_SOLENOID_PORT_2", 6);

    static public boolean REVERSABLE_CONTROLS = settingsFile.getBooleanProperty("REVERSABLE_CONTROLS", false);

    static public double POTENTIOMETER_OFFSET = settingsFile.getDoubleProperty("POTENTIOMETER_OFFSET", -1520);

    static public double DRIVE_SPEED = settingsFile.getDoubleProperty("DRIVE_SPEED", 1);
    static public double TURN_SPEED = settingsFile.getDoubleProperty("TURN_SPEED", 1);
    static public double TURN_CURVE = settingsFile.getDoubleProperty("TURN_CURVE", 2.4);
    static public double INTAKE_ROTATE_SPEED = settingsFile.getDoubleProperty("INTAKE_ROTATE_SPEED", 1);
    static public double LOW_GEAR_THRESHOLD = settingsFile.getDoubleProperty("LOW_GEAR_THRESHOLD", 0.1);

    static public double TEST = settingsFile.getDoubleProperty("TEST", 0.5);
    // Example of ShuffleboardTab setup.
    private ShuffleboardTab dashboardTab = Shuffleboard.getTab("Dashboard");
    private ShuffleboardTab outputTab = Shuffleboard.getTab("Output");

    //NetworkTableEntry LOW_GEAR_TURN_THRESHOLD_ENTRY;
    //static public double LOW_GEAR_TURN_THRESHOLD;

    static public NetworkTableEntry DRIVE_DIRECTION_ENTRY;

    /**
     * Creates the specified objects for shuffleboard, and updates the variables that only
     * needs to be updated at initialization.
     */
    public void ShuffleInit(Teleop teleop) {
        UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture("Microsoft LifeCam HD-3000", 0);

        camera1.setResolution(320, 240);
        camera1.setFPS(30);
            dashboardTab.add(camera1);
            //dashboardTab.add(teleop.drive.driveTrain);

        DRIVE_DIRECTION_ENTRY = dashboardTab.addPersistent("Drive Direction", true).getEntry();

    }

    /**
     * Updates the variables designed to be updated while the robot is powered.
     */
    public void settingsValueUpdate() {
        LOGGING_ENABLED = settingsFile.getBooleanProperty("LOGGING_ENABLED", false);
        DRIVE_SPEED = settingsFile.getDoubleProperty("DRIVE_SPEED", 1);
        TURN_SPEED = settingsFile.getDoubleProperty("TURN_SPEED", 1);
        TURN_CURVE = settingsFile.getDoubleProperty("TURN_CURVE", 2.4);
        INTAKE_ROTATE_SPEED = settingsFile.getDoubleProperty("INTAKE_ROTATE_SPEED", 1);
        LOW_GEAR_THRESHOLD = settingsFile.getDoubleProperty("LOW_GEAR_THRESHOLD", 0.1);
        TEST = settingsFile.getDoubleProperty("TEST", 0.5);
        // LOW_GEAR_TURN_THRESHOLD = LOW_GEAR_SPEED_THRESHOLD_ENTRY.getDouble(0.25);
    }

    public void settingsPeriodic() {
        try {
			settingsFile.reload();
		} catch (NullPointerException e) {
			Timer.delay(0.25);
        }
        
        if (!settingsActive.equalsIgnoreCase(settingsFile.toString())) {
            System.out.println("Reloading Settings");
            settingsValueUpdate();
            settingsActive = settingsFile.toString();
        }
    }

}