package org.firstinspires.ftc.teamcode.opmode.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Vision;
@TeleOp
public class CameraTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Vision vision = new Vision(this);

        waitForStart();
        while (opModeIsActive()) {
            vision.streaming();
        }
    }
}
