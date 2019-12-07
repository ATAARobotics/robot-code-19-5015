package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class OI {

    public OI() {

    }

    XboxController driver = new XboxController(0);
    
    public double getx () {
        return driver.getX (Hand.kRight);

    }
    public double getY () {
        return driver.getY (Hand.kLeft);

    }
}