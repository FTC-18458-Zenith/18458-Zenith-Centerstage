package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Claw;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Slides;
import org.firstinspires.ftc.teamcode.subsystem.Spinner;
@TeleOp
public class TeleOpMain extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Drive drive = new Drive(this);
        //Drive code is all the way at the bottom of the Drive class
        Slides slides = new Slides(hardwareMap, gamepad2);
        Claw claw = new Claw(hardwareMap, gamepad2);
        Spinner spinner = new Spinner(gamepad2, hardwareMap);
        Arm arm = new Arm(hardwareMap, gamepad2);
/*
- is Up, + is Down for Y axis sticks
 */
        waitForStart();
        while (opModeIsActive()) {
            drive.teleOp();
            slides.teleOp();
            claw.teleOp();
            spinner.teleOp();
            arm.teleOp();
        }
    }
}
