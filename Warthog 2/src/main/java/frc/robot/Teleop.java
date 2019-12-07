package frc.robot;

/**
 * Teleop
 */
public class Teleop {
    OI joystickinput = new OI();
    public Teleop () {

    }

    public void TeleopPeriodic() {
        joystickinput.getx();
    }
    
}