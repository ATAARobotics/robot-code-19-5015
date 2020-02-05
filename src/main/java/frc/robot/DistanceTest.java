package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.cuforge.libcu.Lasershark;

class DistanceTest {
    Lasershark lasershark = new Lasershark(2);
    public void distancePeriodic() {
        SmartDashboard.putNumber("distance", lasershark.getDistanceInches());
    }
}
