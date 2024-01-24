package org.firstinspires.ftc.teamcode.opmode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystem.Vision.FFVision;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;
@Autonomous
public class VisionAuto extends MatchOpMode {
    private FFVision FFVision;

    @Override
    public void robotInit() {
        FFVision = new FFVision(hardwareMap, telemetry);
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