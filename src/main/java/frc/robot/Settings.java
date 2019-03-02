package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Settings {
    private OI joysticks;
    private ShuffleboardTab preferencesTab = Shuffleboard.getTab("Preferences");
    private NetworkTableEntry ElevatorEntry;
    static public double ElevatorSpeed = 0;
    public void shuffleInit() {
        ElevatorEntry = preferencesTab.addPersistent("Elevator", ElevatorSpeed).getEntry();
    }
    public void shufflePeriodic() {
        joysticks.checkInputs();
        ElevatorSpeed = joysticks.elevatorSpeedDown();
        ElevatorEntry = preferencesTab.addPersistent("Elevator", ElevatorSpeed).getEntry();
    }

}