package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import java.lang.Math;

public class Encoders {
    
    //Creates left and right encoder objects
    private final WPI_TalonSRX leftMotor;
    private final WPI_TalonSRX rightMotor;

    private final double leftTicksPerInch;
    private final double rightTicksPerInch;
    private final double wheelCircumference = 6 * Math.PI;

    public Encoders(final WPI_TalonSRX leftMotor, final WPI_TalonSRX rightMotor) {
        leftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;

        this.leftMotor.setSelectedSensorPosition(0);
        this.rightMotor.setSelectedSensorPosition(0);

        leftTicksPerInch = 4096 / wheelCircumference;
        rightTicksPerInch = 4096 / wheelCircumference;

    }
    public double getTicks() {

        final double leftEncoderDistance = leftMotor.getSelectedSensorPosition();
        final double rightEncoderDistance = rightMotor.getSelectedSensorPosition();
        //return leftMotor.getSelectedSensorPosition();
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
    public double getDistance() {
        final double leftEncoderDistance = leftMotor.getSelectedSensorPosition()/leftTicksPerInch;
        final double rightEncoderDistance = rightMotor.getSelectedSensorPosition()/rightTicksPerInch;
        //return leftMotor.getSelectedSensorPosition();
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
}
