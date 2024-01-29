package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Basic.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Basic.Claw;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Basic.DroneLauncher;
import org.firstinspires.ftc.teamcode.subsystem.Basic.HangingMech;
import org.firstinspires.ftc.teamcode.subsystem.Basic.Slides;

@TeleOp
public class TeleOpMain extends LinearOpMode  {

    @Override
    public void runOpMode() throws InterruptedException {
        DroneLauncher droneLauncher = new DroneLauncher(this);
        HangingMech hangingMech = new HangingMech(this);
        Slides slides = new Slides(this);
        Drive drive = new Drive(this);
        Claw claw = new Claw(this);
        Arm arm = new Arm(this);

        waitForStart();
        while (opModeIsActive()) {
            arm.teleOp();
            claw.teleOp();
            drive.teleOp();
            slides.teleOp();
            hangingMech.teleOp();
            droneLauncher.teleOp();

        }
    }
}
