package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
public class Drivetrain {
    private final DcMotor leftFront, leftRear, rightRear, rightFront;
    private final Gamepad gamepad1;
    private final IMU imu;

    public Drivetrain(HardwareMap hardwareMap, Gamepad gamepad1) {
        leftFront = (DcMotor) hardwareMap.get("leftFront");
        leftRear = (DcMotor) hardwareMap.get("leftRear");
        rightFront = (DcMotor) hardwareMap.get("rightFront");
        rightRear = (DcMotor) hardwareMap.get("rightRear");

        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        imu = hardwareMap.get(IMU.class, "imu");
        this.gamepad1 = gamepad1;
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);
    }
    public void teleOp() {
        if (gamepad1.right_bumper) moveDrive(0.5);
        else moveDrive(1);
        if (gamepad1.options) {
            imu.resetYaw();
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
}
