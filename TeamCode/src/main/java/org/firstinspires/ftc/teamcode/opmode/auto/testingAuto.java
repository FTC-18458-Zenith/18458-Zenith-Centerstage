package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Slides;

@Autonomous
public class testingAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        Arm arm = new Arm(this);
        Slides slides = new Slides(this);

        slides.moveLow();
        arm.armAutoOuttake(1000);
        sleep(1000);

    }
}