package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.DroneLauncher;
import org.firstinspires.ftc.teamcode.subsystem.HangingMech;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Slides;

@TeleOp
public class SoloTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Drive drive = new Drive(this);
        Arm arm = new Arm(this);
        Intake intake = new Intake(this);
        Slides slides = new Slides(this);
        DroneLauncher droneLauncher = new DroneLauncher(this);
        HangingMech hangingMech = new HangingMech(this);

        waitForStart();
        while (opModeIsActive()) {
            drive.teleOp();
            arm.soloTeleOp();
            intake.soloTeleOp();
            slides.soloTeleOp();
            droneLauncher.soloTeleOp();
            hangingMech.soloTeleOp();
        }
    }
}