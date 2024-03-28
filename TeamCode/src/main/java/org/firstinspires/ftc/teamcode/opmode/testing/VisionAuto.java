package org.firstinspires.ftc.teamcode.opmode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystem.Vision.BlueCLoseVision;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;
@Autonomous(group = "testing")
public class VisionAuto extends MatchOpMode {
    private BlueCLoseVision FFVision;

    @Override
    public void robotInit() {
        FFVision = new BlueCLoseVision(hardwareMap, telemetry);
    }

    @Override
    public void disabledPeriodic() {
        FFVision.setPosition(FFVision.getPosition());
        FFVision.periodic();
        telemetry.update();
    }

    public void matchStart() {
//        TeamMarkerPipeline.FFPosition position = vision.getPosition();
    }
}