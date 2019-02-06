package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
public class SWATDrive {
    public static final double kDefaultDeadband = 0.02;
    public static final double kDefaultMaxOutput = 1.0;
  
    protected double m_deadband = kDefaultDeadband;
    protected double m_maxOutput = kDefaultMaxOutput;
   
    private double m_rightSideInvertMultiplier = 1.0;

    VictorSPX m_rearLeftMotor;
    TalonSRX m_frontLeftMotor;
    VictorSPX m_rearRightMotor;
    TalonSRX m_frontRightMotor;

    public SWATDrive(TalonSRX frontLeftMotor, VictorSPX rearLeftMotor, TalonSRX frontRightMotor, VictorSPX rearRightMotor) {
        m_frontLeftMotor = frontLeftMotor;
        m_frontRightMotor = frontRightMotor;
        m_rearLeftMotor = rearLeftMotor;
        m_rearRightMotor = rearRightMotor;
    }

    public void arcadeDrive(double xSpeed, double zRotation, boolean squareInputs) {
    
        xSpeed = limit(xSpeed);
        xSpeed = applyDeadband(xSpeed, m_deadband);
    
        zRotation = limit(zRotation);
        zRotation = applyDeadband(zRotation, m_deadband);
    
        // Square the inputs (while preserving the sign) to increase fine control
        // while permitting full power.
        if (squareInputs) {
          xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
          zRotation = Math.copySign(zRotation * zRotation, zRotation);
        }
    
        double leftMotorOutput;
        double rightMotorOutput;
    
        double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);
    
        if (xSpeed >= 0.0) {
          // First quadrant, else second quadrant
          if (zRotation >= 0.0) {
            leftMotorOutput = maxInput;
            rightMotorOutput = xSpeed - zRotation;
          } else {
            leftMotorOutput = xSpeed + zRotation;
            rightMotorOutput = maxInput;
          }
        } else {
          // Third quadrant, else fourth quadrant
          if (zRotation >= 0.0) {
            leftMotorOutput = xSpeed + zRotation;
            rightMotorOutput = maxInput;
          } else {
            leftMotorOutput = maxInput;
            rightMotorOutput = xSpeed - zRotation;
          }
        }
    
        m_frontLeftMotor.set(ControlMode.PercentOutput, limit(leftMotorOutput) * m_maxOutput);
        m_frontRightMotor.set(ControlMode.PercentOutput, limit(rightMotorOutput) * m_maxOutput * m_rightSideInvertMultiplier);
        m_rearLeftMotor.set(ControlMode.PercentOutput, limit(leftMotorOutput) * m_maxOutput);
        m_rearRightMotor.set(ControlMode.PercentOutput, limit(rightMotorOutput) * m_maxOutput * m_rightSideInvertMultiplier);
      }
      protected double limit(double value) {
        if (value > 1.0) {
          return 1.0;
        }
        if (value < -1.0) {
          return -1.0;
        }
        return value;
      }
      protected double applyDeadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
          if (value > 0.0) {
            return (value - deadband) / (1.0 - deadband);
          } else {
            return (value + deadband) / (1.0 - deadband);
          }
        } else {
          return 0.0;
        }
    }
    public void setDeadband(double deadband) {
        m_deadband = deadband;
      }    
}