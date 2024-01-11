package org.firstinspires.ftc.teamcode.opmode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Vision;

@Autonomous
public class CameraTest2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Vision vision = new Vision(this, Vision.AllianceColor.BLUE);
        while (!isStarted()) {
            telemetry.update();
        }
        waitForStart();

    }
}
