package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Slides;

@Autonomous
public class LeftAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Intake spinner = new Intake(this);
        Drive drive = new Drive(this);
        Arm arm = new Arm(this);
        Slides slides = new Slides(this);

        drive.stuffForAuto();

        waitForStart();
        drive.movingMotors(0.8,0.8, 0.8, 0.8, 1000, 1000);
    }
}
