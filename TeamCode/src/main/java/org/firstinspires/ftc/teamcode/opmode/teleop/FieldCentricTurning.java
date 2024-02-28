package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.FieldCentricDrive;

@TeleOp
public class FieldCentricTurning extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        FieldCentricDrive fieldCentricDrive = new FieldCentricDrive(this);

        waitForStart();
        while (opModeIsActive()){
            fieldCentricDrive.teleOp();
        }
    }
}
