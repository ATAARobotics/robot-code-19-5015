package frc.robot;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Settings {
    private ShuffleboardTab preferencesTab = Shuffleboard.getTab("Preferences");
    private NetworkTableEntry driveSpeedNormalAllEntry;
    static public double driveSpeedNormalAll;
    private NetworkTableEntry driveSpeedNormalStraightEntry;
    static public double driveSpeedNormalStraight;
    private NetworkTableEntry driveSpeedNormalTurningEntry;
    static public double driveSpeedNormalTurning;
    private NetworkTableEntry driveSpeedSlowAllEntry;
    static public double driveSpeedSlowAll;
    private NetworkTableEntry driveSpeedSlowStraightEntry;
    static public double driveSpeedSlowStraight;
    private NetworkTableEntry driveSpeedSlowTurningEntry;
    static public double driveSpeedSlowTurning;
    private NetworkTableEntry driverPreferencesEntry;
    static public String driverPreferences;
    private NetworkTableEntry gunnerPreferencesEntry;
    static public String gunnerPreferences;
    public void shuffleInit() {
        driveSpeedNormalAllEntry = preferencesTab.addPersistent("Drive Speed Normal All", 1.0).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0, "max", 1)).getEntry();
        driveSpeedNormalTurningEntry = preferencesTab.addPersistent("Drive Speed Turning", 0.8).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0, "max", 1)).getEntry();
        driveSpeedNormalStraightEntry = preferencesTab.addPersistent("Drive Speed Straight", 1.0).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0, "max", 1)).getEntry();
        driveSpeedSlowAllEntry = preferencesTab.addPersistent("Drive Speed Slow All", 0.4).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0, "max", 1)).getEntry();
        driveSpeedSlowTurningEntry = preferencesTab.addPersistent("Drive Speed Slow Turning", 0.4).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0, "max", 1)).getEntry();
        driveSpeedSlowStraightEntry = preferencesTab.addPersistent("Drive Speed Slow Straight", 0.2).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("min", 0, "max", 1)).getEntry();  
        driverPreferencesEntry = preferencesTab.addPersistent("Driver Preferences", "Default").getEntry();
        gunnerPreferencesEntry = preferencesTab.addPersistent("Gunner Preferences", "Default").getEntry();      
    }
    public void shufflePeriodic() {
        driveSpeedNormalAll = driveSpeedNormalAllEntry.getDouble(0.1);
        driveSpeedNormalTurning = driveSpeedNormalTurningEntry.getDouble(0.8);
        driveSpeedNormalStraight = driveSpeedNormalStraightEntry.getDouble(0.1);
        driveSpeedSlowAll = driveSpeedSlowAllEntry.getDouble(0.1);
        driveSpeedSlowTurning = driveSpeedSlowTurningEntry.getDouble(0.8);
        driveSpeedSlowStraight = driveSpeedSlowStraightEntry.getDouble(0.1);
        driverPreferences = driverPreferencesEntry.getString("Default");
        gunnerPreferences = gunnerPreferencesEntry.getString("Default");
    }
}