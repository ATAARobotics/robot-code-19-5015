package frc.robot;


// import libraries
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;



public class SWATDrive {

    //create static variables that we can change
    public static final double kDefaultDeadband = 0.02;
    public static final double kDefaultMaxOutput = 1.0;

    // set changeable varibles to the static ones
    protected double m_deadband = kDefaultDeadband;
    protected double m_maxOutput = kDefaultMaxOutput;
    
    //slow variable
    public boolean slow = false;


    // create an inverter for the right side
    private double m_rightSideInvertMultiplier = 1.0;

    // create the motor controller varibles
    BaseMotorController m_rearLeftMotor;
    BaseMotorController m_frontLeftMotor;
    BaseMotorController m_rearRightMotor;
    BaseMotorController m_frontRightMotor;

    // constructor that creates the object
    public SWATDrive(BaseMotorController frontLeftMotor, BaseMotorController rearLeftMotor, BaseMotorController frontRightMotor, BaseMotorController rearRightMotor) {
        m_frontLeftMotor = frontLeftMotor;
        m_frontRightMotor = frontRightMotor;
        m_rearLeftMotor = rearLeftMotor;
        m_rearRightMotor = rearRightMotor;
    }
    //arcade drive method

    public void arcadeDrive(double xSpeed, double zRotation, boolean squareInputs) {

        //fix the speed varible
        xSpeed = limit(xSpeed);
        xSpeed = applyDeadband(xSpeed, m_deadband);

        // fix the rotation variable
        zRotation = limit(zRotation);
        zRotation = applyDeadband(zRotation, m_deadband);
    
        // Square the inputs (while preserving the sign) to increase fine control
        // while permitting full power.
        if (squareInputs) {
          xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
          zRotation = Math.copySign(zRotation * zRotation, zRotation);
        }
        
        // create motor output variables
        double leftMotorOutput;
        double rightMotorOutput;
        
        //create a maximum input variable
        double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);
        

        //if statements that only rotate if the motor has speed
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
        if(slow) {
          m_maxOutput = 0.4;
        }
        //set the motors to their proper values
        m_frontLeftMotor.set(ControlMode.PercentOutput, limit(leftMotorOutput) * m_maxOutput);
        m_frontRightMotor.set(ControlMode.PercentOutput, limit(rightMotorOutput) * m_maxOutput * m_rightSideInvertMultiplier);
        m_rearLeftMotor.set(ControlMode.PercentOutput, limit(leftMotorOutput) * m_maxOutput);
        m_rearRightMotor.set(ControlMode.PercentOutput, limit(rightMotorOutput) * m_maxOutput * m_rightSideInvertMultiplier);
      }

      //maximize the absolute value of a value
      protected double limit(double value) {
        if (value > 1.0) {
          return 1.0;
        }
        if (value < -1.0) {
          return -1.0;
        }
        return value;
      }

      //create a deadband
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

    //set a new deadband
    public void setDeadband(double deadband) {
        m_deadband = deadband;
      }    
}