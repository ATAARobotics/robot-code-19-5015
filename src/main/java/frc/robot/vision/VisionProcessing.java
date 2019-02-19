package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;


/**
 * VisionProcessing
 */
public class VisionProcessing {
    public VisionProcessing(){
    }

    //Vision Values
    ShuffleboardTab dynamicSettingsTab = Shuffleboard.getTab("Dynamic Settings");
    
    //Creates Shuffleboard Items for Vision
    NetworkTableEntry DRIVE_VISION_ENTRY = dynamicSettingsTab.addPersistent("Drive Value", 0).getEntry();
    NetworkTableEntry SPEED_VISION_ENTRY = dynamicSettingsTab.addPersistent("Speed Value", 0.5).getEntry();
    NetworkTableEntry VISION_ACTIVE_ENTRY_SHUFFLE = dynamicSettingsTab.addPersistent("Vision Active", false).getEntry();

    //Creates object for LedRing Relay
    Relay ledRelay = new Relay(1);

    //Access Rotation From Shuffleboard
    public double getVisionRotation(){
        return(DRIVE_VISION_ENTRY.getDouble(0));
    }
    
    //Access Speed From Shuffleboard
    public double getVisionSpeed(){
        return(SPEED_VISION_ENTRY.getDouble(0.5));
    }

    //Starts Vision on Pi and Enables LED Ring
    public void startVision(){
        VISION_ACTIVE_ENTRY_SHUFFLE.setBoolean(true);
        ledRelay.set(Value.kForward);
        
    }

    //Stops Vision on Pi and Disables LED Ring 
    public void stopVision(){
        VISION_ACTIVE_ENTRY_SHUFFLE.setBoolean(false);
        ledRelay.set(Value.kReverse);
    }

}