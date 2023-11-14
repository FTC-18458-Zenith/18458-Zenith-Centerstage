package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Slides;

@Autonomous
public class SimpleRightAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Intake spinner = new Intake(this);
        Drive drive = new Drive(this);
        Arm arm = new Arm(this);
        Slides slides = new Slides(this);

        drive.stuffForAuto();

        //init
        waitForStart();
        drive.movingMotors(0.8,0.8,0.8,0.8,1000, 1000);
        spinner.spinnerAutoThing(0.5, 500);
        spinner.spinnerAutoThing(0, 500);
        sleep(10000);
        drive.movingMotors(0.8, 0.8, 0.8, 0.8, 1000, 0);
        drive.movingMotors(0.3, 0.3, 0.3, 0.3, 1000, 10000);
        sleep(250);
        arm.armAutoOuttake(1000);
    }
}