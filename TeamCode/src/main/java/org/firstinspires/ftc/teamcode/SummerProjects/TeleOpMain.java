package org.firstinspires.ftc.teamcode.SummerProjects;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystem.Basic.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Basic.Claw;
import org.firstinspires.ftc.teamcode.subsystem.Basic.Intake;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Basic.DroneLauncher;
import org.firstinspires.ftc.teamcode.subsystem.Basic.HangingMech;
import org.firstinspires.ftc.teamcode.subsystem.Basic.Slides;

@TeleOp
public class TeleOpMain extends LinearOpMode  {

    @Override
    public void runOpMode() throws InterruptedException {

        Realignment realignment = new Realignment(telemetry, hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            realignment.run();
        }
    }
}
