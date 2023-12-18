package org.firstinspires.ftc.teamcode.opmode.auto.Other;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Vision;

@Autonomous
public class AprilTagDetectorAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Vision vision = new Vision(this);

        while (true) {
            vision.updateTagOfInterest();
        }
    }
}
