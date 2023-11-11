package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.DroneLauncher;
import org.firstinspires.ftc.teamcode.subsystem.HangingMech;
import org.firstinspires.ftc.teamcode.subsystem.Slides;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
@TeleOp
public class TeleOpMain extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Drive drive = new Drive(this);
        //Drive code is all the way at the bottom of the Drive class
        Slides slides = new Slides(hardwareMap, gamepad2);
        Arm arm = new Arm(hardwareMap, gamepad2);
        DroneLauncher droneLauncher = new DroneLauncher(hardwareMap, gamepad2);
        Intake intake = new Intake(hardwareMap, gamepad2);
        HangingMech hangingMech =  new HangingMech(hardwareMap, gamepad1);

        waitForStart();
        while (opModeIsActive()) {
            drive.teleOp();
            slides.teleOp();
            arm.teleOp();
            droneLauncher.teleOp();
            intake.teleOp();
            hangingMech.teleOp();
        }
    }
}
