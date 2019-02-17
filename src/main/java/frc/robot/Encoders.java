package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import java.lang.Math;

public class Encoders {
    
    //Creates left and right encoder objects and sets
    //them to their respective digital input channels
    public Encoder leftEncoder = new Encoder(0, 1);
    public Encoder rightEncoder = new Encoder(2, 3);
    public AHRS navX;
    public double ticksPerInch;
    private double wheelCircumference = 6 * Math.PI;

    public void initalizeNavX() {
        try
        {
            // Initializes the navX object on the roboRIO's MXP port and resets itw
            navX = new AHRS(SPI.Port.kMXP);
            navX.reset();
        } catch (RuntimeException ex)
        {
            DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
        }
    }

    public void setEncoderValues(double wheelCircumference) {
        leftEncoder.setDistancePerPulse(wheelCircumference / /*TODO Calculate ticks per rotation*/1);
        rightEncoder.setDistancePerPulse(wheelCircumference / /*TODO Calculate ticks per rotation*/1);
    }

    public double getDistance() {
        Robot.debugOut(Double.toString(leftEncoder.getDistance()), 1, "Left Encoder");
        Robot.debugOut(Double.toString(rightEncoder.getDistance()), 1, "Right Encoder");
        double leftEncoderDistance = leftEncoder.getDistance();
        double rightEncoderDistance = rightEncoder.getDistance();
        if (leftEncoderDistance == 0.0) {
            return Math.abs(rightEncoderDistance);
        }
        else if (rightEncoderDistance == 0.0) {
            return Math.abs(leftEncoderDistance);
        }
        else {
            return Math.abs((leftEncoderDistance + rightEncoderDistance) / 2);
        }
    }

    public void reset() {
        leftEncoder.reset();
        rightEncoder.reset();
    }
}
