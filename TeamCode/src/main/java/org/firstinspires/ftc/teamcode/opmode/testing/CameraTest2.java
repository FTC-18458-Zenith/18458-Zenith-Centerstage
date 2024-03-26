package org.firstinspires.ftc.teamcode.opmode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.vision;

@Autonomous(group = "testing")
public class CameraTest2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        vision vision = new vision(this, org.firstinspires.ftc.teamcode.subsystem.vision.AllianceColor.BLUE);
        while (!isStarted()) {
            telemetry.update();
        }
        waitForStart();

    }
}
