package frc.robot.vision;

import frc.robot.SWATDrive;

/**
 * Vision
 */
public class Vision {

    SWATDrive drive;
    VisionProcessing visionProcessing = new VisionProcessing();

    public Vision(SWATDrive drive){
        this.drive = drive;
    }

    public void visionInit(){
        visionProcessing.startVision();
    }

    public void endVision(){
        visionProcessing.stopVision();
    }

    public void drive(){
        double turnAngle = visionProcessing.getVisionRotation();
        double speed = visionProcessing.getVisionSpeed();
        drive.arcadeDrive(speed, turnAngle);
    }


}