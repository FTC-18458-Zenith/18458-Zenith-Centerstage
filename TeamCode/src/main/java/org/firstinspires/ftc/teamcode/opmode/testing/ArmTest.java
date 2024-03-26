package org.firstinspires.ftc.teamcode.opmode.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.Basic.Arm;

@TeleOp(group = "testing")
public class ArmTest extends LinearOpMode {
        @Override
        public void runOpMode() throws InterruptedException {
            Arm arm = new Arm(this);

            waitForStart();
            while (opModeIsActive()) {
                arm.teleOp();
                arm.periodic();
                telemetry.update();
            }
        }
    }
