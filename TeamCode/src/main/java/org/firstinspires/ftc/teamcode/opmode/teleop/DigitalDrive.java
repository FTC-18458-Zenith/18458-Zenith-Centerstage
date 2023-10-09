package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class DigitalDrive extends LinearOpMode {
    private DcMotor leftFront, leftRear, rightRear, rightFront;
    private final DcMotor[] driveTrainMotors = {leftRear, leftFront, rightRear, rightFront};
    final DcMotor[] rightDriveTrainMotors = {rightRear, rightFront};
    final DcMotor[] leftDriveTrainMotors = {leftRear, leftFront};
    final DcMotor[] strafeLeft= {leftRear, rightFront};
    final DcMotor[] strafeRight = {rightRear, leftFront};
    private Servo claw;
    @Override
    public void runOpMode() throws InterruptedException {

        leftFront = (DcMotor) hardwareMap.get("leftFront");
        leftRear = (DcMotor) hardwareMap.get("leftRear");
        rightFront = (DcMotor) hardwareMap.get("rightFront");
        rightRear = (DcMotor) hardwareMap.get("rightRear");
        claw = (Servo) hardwareMap.get("claw");
/*
- is Up, + is Down for Y axis sticks
 */
        waitForStart();
        while (opModeIsActive()) {

            if (gamepad1.left_stick_y < -0.75) movingForwardPowers(1);
            else if (gamepad1.left_stick_y > 0.75) movingForwardPowers(-1);
            else if (gamepad1.right_stick_x > 0.75) turningDirectionsPowers(-1, 1);
            else if (gamepad1.right_stick_x < -0.75) turningDirectionsPowers(1,-1);
            else if (gamepad1.left_stick_x > -0.75) strafingDirectionsPowers(-1, 1);
            else if (gamepad1.left_stick_x < 0.75) strafingDirectionsPowers(1,-1);
            else movingForwardPowers(0);
        }
    }
    public void movingForwardPowers(double driveTrainPower) {
        for (DcMotor motor :driveTrainMotors ) {
            motor.setPower(driveTrainPower);
        }
    }
    public void turningDirectionsPowers(double leftDriveTrainPower, double rightDriveTrainPower) {

        for (DcMotor motor : leftDriveTrainMotors) {
            motor.setPower(leftDriveTrainPower);
        }
        for (DcMotor motor : rightDriveTrainMotors) {
            motor.setPower(rightDriveTrainPower);
        }
    }
    public void strafingDirectionsPowers(double strafeLeftPower, double strafeRightPower) {

        for (DcMotor motor : strafeLeft) {
            motor.setPower(strafeLeftPower);
        }
        for (DcMotor motor : strafeRight) {
            motor.setPower(strafeRightPower);
        }
    }
}
