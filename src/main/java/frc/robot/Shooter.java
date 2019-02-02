package org.usfirst.frc.team5015.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class shooter {
    public TalonSRX ShooterTalon = new TalonSRX(4);  
    public TalonSRX IntakeTalon = new TalonSRX(5);

    public void shootBall(){
        ShooterTalon.set (0.7);
    }
    
    public void intakeBall(){
        IntakeTalon.set (0.7);
    }

}
