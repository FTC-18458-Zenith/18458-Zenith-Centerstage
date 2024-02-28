package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystem.FieldCentricDrive;

@TeleOp
public class FieldCentricTurning extends LinearOpMode {
    FieldCentricDrive fieldCentricDrive = new FieldCentricDrive(this);
    @Override
    public void runOpMode() throws InterruptedException {
        fieldCentricDrive.teleOp();
    }
}
