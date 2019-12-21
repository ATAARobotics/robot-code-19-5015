package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import java.lang.Math;

public class Encoders {
    
    //Creates left and right encoder objects
    private WPI_TalonSRX leftMotor;
    private WPI_TalonSRX rightMotor;

    private double leftTicksPerInch;
    private double rightTicksPerInch;
    private double wheelCircumference = 6 * Math.PI;

    public Encoders(WPI_TalonSRX leftMotor, WPI_TalonSRX rightMotor) {

        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;

        this.leftMotor.setSelectedSensorPosition(0);
        this.rightMotor.setSelectedSensorPosition(0);

        leftTicksPerInch = 30400 / wheelCircumference;
        rightTicksPerInch = 30700 / wheelCircumference;
    }
    public double getRight() {
        return rightMotor.getSelectedSensorPosition();
    }
    public double getLeft() {
        return leftMotor.getSelectedSensorPosition();
    }
    public double getLeftDistance() {
        return leftMotor.getSelectedSensorPosition()/leftTicksPerInch;     
    }

    public double getRightDistance() {
        return rightMotor.getSelectedSensorPosition()/rightTicksPerInch;
    }

    public void reset() {
        leftMotor.setSelectedSensorPosition(0);
        rightMotor.setSelectedSensorPosition(0);
    }
}
