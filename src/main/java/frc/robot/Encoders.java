package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import java.lang.Math;

public class Encoders {
    
    //Creates left and right encoder objects
    private WPI_TalonSRX leftMotor;
    private WPI_TalonSRX rightMotor;

    private AHRS navX;
    private double leftTicksPerInch;
    private double rightTicksPerInch;
    private double wheelCircumference = 6 * Math.PI;

    public Encoders(WPI_TalonSRX leftMotor, WPI_TalonSRX rightMotor) {

        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;

        this.leftMotor.setSelectedSensorPosition(0);
        this.rightMotor.setSelectedSensorPosition(0);

        leftTicksPerInch = 30700 / wheelCircumference;
        rightTicksPerInch = 30700 / wheelCircumference;
    }

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

    public double getDistance() {
        double leftEncoderDistance = leftMotor.getSelectedSensorPosition();
        double rightEncoderDistance = rightMotor.getSelectedSensorPosition();
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
        leftMotor.setSelectedSensorPosition(0);
        rightMotor.setSelectedSensorPosition(0);
    }

    public void encoderDebug() {
        Robot.debugOut(Double.toString(leftMotor.getSelectedSensorPosition() / leftTicksPerInch), 1, "Left Encoder");
        Robot.debugOut(Integer.toString(rightMotor.getSelectedSensorPosition()), 1, "Raw Right Encoder");
        Robot.debugOut(Double.toString(rightTicksPerInch), 1, "Ticks per inch");
        Robot.debugOut(Double.toString(rightMotor.getSelectedSensorPosition() / rightTicksPerInch), 1, "Right Encoder");
    }
}
