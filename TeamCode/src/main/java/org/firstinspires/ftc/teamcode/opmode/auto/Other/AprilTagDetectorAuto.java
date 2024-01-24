package org.firstinspires.ftc.teamcode.opmode.auto.Other;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.vision;

@Autonomous
public class AprilTagDetectorAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        vision vision = new vision(this, org.firstinspires.ftc.teamcode.subsystem.vision.AllianceColor.BLUE);

        while (true) {
        }
    }
}
