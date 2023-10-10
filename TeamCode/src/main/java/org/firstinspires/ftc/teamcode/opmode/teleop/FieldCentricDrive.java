package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class FieldCentricDrive extends LinearOpMode {
    private DcMotor leftFront, leftRear, rightRear, rightFront, leftSlide, rightSlide, spinner;
    private Servo lClaw, rClaw;
    private IMU imu;
    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = (DcMotor) hardwareMap.get("leftFront");
        leftRear = (DcMotor) hardwareMap.get("leftRear");
        rightFront = (DcMotor) hardwareMap.get("rightFront");
        rightRear = (DcMotor) hardwareMap.get("rightRear");
        leftSlide = (DcMotor) hardwareMap.get("leftSlide");
        rightSlide = (DcMotor) hardwareMap.get("rightSlide");
        lClaw = (Servo) hardwareMap.get("lClaw");
        rClaw = (Servo) hardwareMap.get("rClaw");
        spinner = (DcMotor) hardwareMap.get("spinner");

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);

        spinner.setDirection(DcMotorSimple.Direction.FORWARD);
        spinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //TODO: RUN_WITHOUT_ENCODER for all drivetrainmotors
        lClaw.setDirection(Servo.Direction.REVERSE);
        rClaw.setDirection(Servo.Direction.FORWARD);
        clawServo(0,0);
        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        //TODO:Test for the other mode Float
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setTargetPosition(0);
        rightSlide.setTargetPosition(0);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setPower(1);
        rightSlide.setPower(1);
/*
- is Up, + is Down for Y axis sticks
 */
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.right_bumper) moveDrive(0.5);
            else moveDrive(1);
            if (gamepad1.options) {
                imu.resetYaw();
            }
            if (gamepad2.right_trigger >= 0.1) clawServo(1, 1);
            else if (gamepad2.left_trigger >= 0.1) clawServo(0,0);

            //Depends on number of ticks per revolution
            if (gamepad2.a) slideMotors(1000);
            else if (gamepad2.b)slideMotors(500);
            else if (gamepad2.y) slideMotors(250);
            else if (gamepad2.x) slideMotors(0);

            if (gamepad2.right_bumper) spinnerTHing(0.5);
            else if (gamepad2.left_bumper) spinnerTHing(-0.5);
            else spinnerTHing(0);
        }
    }

    public void moveDrive(double driveTrainPower) {
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double rotX = gamepad1.left_stick_x * Math.cos(-botHeading) - gamepad1.left_stick_y * Math.sin(-botHeading);
        double rotY = gamepad1.left_stick_x * Math.cos(-botHeading) + gamepad1.left_stick_y * Math.sin(-botHeading);
        rotX = rotX * 1.1;
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(gamepad1.right_stick_x), 1);

        leftFront.setPower(((-gamepad1.left_stick_y  + gamepad1.right_stick_x + gamepad1.left_stick_x) * driveTrainPower)/denominator);
        leftRear.setPower(((-gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x) * driveTrainPower)/denominator);
        rightRear.setPower(((-gamepad1.left_stick_y -gamepad1.right_stick_x + gamepad1.left_stick_x) * driveTrainPower)/denominator);
        rightFront.setPower(((-gamepad1.left_stick_y -gamepad1.right_stick_x - gamepad1.left_stick_x) * driveTrainPower)/denominator);
    }

    public void clawServo(double setPositionRight, double setPositionLeft) {
        rClaw.setPosition(setPositionRight);
        lClaw.setPosition(setPositionLeft);
    }

    public void slideMotors(int targetPosition) {
        leftSlide.setTargetPosition(targetPosition);
        rightSlide.setTargetPosition(targetPosition);
    }
    public void spinnerTHing (double powerSpinner) {
        spinner.setPower(powerSpinner);
    }
}
