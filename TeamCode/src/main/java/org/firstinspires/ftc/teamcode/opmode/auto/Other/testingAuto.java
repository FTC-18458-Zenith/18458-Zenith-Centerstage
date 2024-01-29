package org.firstinspires.ftc.teamcode.opmode.auto.Other;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Basic.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Basic.Slides;

@Autonomous
public class testingAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        Arm arm = new Arm(this);
        Slides slides = new Slides(this);

        slides.moveLow();
        sleep(1000);

    }
}