package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystem.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.Slides;

@TeleOp
public class FieldCentricDrive extends LinearOpMode {
    private DcMotor spinner;
    private Servo lClaw, rClaw;


    @Override
    public void runOpMode() throws InterruptedException {
        Drivetrain drivetrain = new Drivetrain(hardwareMap, gamepad1);
        Slides slides = new Slides(hardwareMap, gamepad2);

        lClaw = (Servo) hardwareMap.get("lClaw");
        rClaw = (Servo) hardwareMap.get("rClaw");
        spinner = (DcMotor) hardwareMap.get("spinner");

        spinner.setDirection(DcMotorSimple.Direction.FORWARD);
        spinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lClaw.setDirection(Servo.Direction.REVERSE);
        rClaw.setDirection(Servo.Direction.FORWARD);
        clawServo(0,0);
/*
- is Up, + is Down for Y axis sticks
 */
        waitForStart();
        while (opModeIsActive()) {
            drivetrain.teleOp();
            slides.teleOp();

            if (gamepad2.right_trigger >= 0.1) clawServo(1, 1);
            else if (gamepad2.left_trigger >= 0.1) clawServo(0,0);

            if (gamepad2.right_bumper) spinnerIntakeThing(0.5);
            else if (gamepad2.left_bumper) spinnerIntakeThing(-0.5);
            else spinnerIntakeThing(0);
        }
    }
    public void clawServo(double setPositionRight, double setPositionLeft) {
        rClaw.setPosition(setPositionRight);
        lClaw.setPosition(setPositionLeft);
    }
    public void spinnerIntakeThing(double powerSpinner) {
        spinner.setPower(powerSpinner);
    }
}
