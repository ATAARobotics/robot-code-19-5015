package org.usfirst.frc.team5015.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator {
	private double upMax, downMax;
	WPI_TalonSRX elevatorMotor;
	DigitalInput topSwitch, bottomSwitch;
	
	public Elevator(double down_max, double up_max, WPI_TalonSRX motor, DigitalInput bottom_switch, DigitalInput top_switch){
		upMax = up_max;
		downMax = down_max;
		elevatorMotor = motor;
		topSwitch = top_switch;
		bottomSwitch = bottom_switch;
	}
	
	public void driveElevator(double power){
		double outputPower = 0;
		if(power > 0){
			if(topSwitch.get()){
				outputPower = power*upMax;
			}
			else{
				outputPower = 0;
			}
		} else{
			if(bottomSwitch.get()){
				outputPower = power*downMax;
			}
			else{
				outputPower = 0;
			}
		}
		elevatorMotor.set(outputPower*-1);
	}
}
