package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Claw;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.DroneLauncher;
import org.firstinspires.ftc.teamcode.subsystem.HangingMech;
import org.firstinspires.ftc.teamcode.subsystem.Slides;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
@TeleOp
public class TeleOpMain extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DroneLauncher droneLauncher = new DroneLauncher(this);
        HangingMech hangingMech = new HangingMech(this);
        Slides slides = new Slides(this);
        Intake intake = new Intake(this);
        Drive drive = new Drive(this);
        Claw claw = new Claw(this);
        Arm arm = new Arm(this);

        waitForStart();
        while (opModeIsActive()) {
            arm.teleOp();
            claw.teleOp();
            drive.teleOp();
            slides.teleOp();
            intake.teleOp();
            hangingMech.teleOp();
            droneLauncher.teleOp();

        }
    }
}
