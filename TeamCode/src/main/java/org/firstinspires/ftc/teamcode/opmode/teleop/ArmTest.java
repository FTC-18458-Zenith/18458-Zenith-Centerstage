package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Claw;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.DroneLauncher;
import org.firstinspires.ftc.teamcode.subsystem.HangingMech;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Slides;
@TeleOp
public class ArmTest extends LinearOpMode {
        @Override
        public void runOpMode() throws InterruptedException {
            Arm arm = new Arm(this);

            waitForStart();
            while (opModeIsActive()) {
                arm.teleOp();
            }
        }
    }
