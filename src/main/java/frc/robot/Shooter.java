package frc.robot;

import edu.wpi.first.wpilibj.Spark;


public class Shooter {
    
    public Spark ShooterSpark = new Spark(0);  
    public Spark IntakeSpark = new Spark(1);

    //two motors turn opposite direction to squeeze the ball out
    public void shootBall(){
        ShooterSpark.set( -1);
        IntakeSpark.set(1);
    }
    
    //one motor turns to get the ball into the shooter
    public void intakeBall(){
        IntakeSpark.set(0.7);
    }

}
