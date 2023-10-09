package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
@TeleOp
public class AnalogDrive extends LinearOpMode {
    private DcMotor leftFront, leftRear, rightRear, rightFront;
    IMU imu = hardwareMap.get(IMU.class, "imu");

    @Override
    public void runOpMode() throws InterruptedException {

        leftFront = (DcMotor) hardwareMap.get("leftFront");
        leftRear = (DcMotor) hardwareMap.get("leftRear");
        rightFront = (DcMotor) hardwareMap.get("rightFront");
        rightRear = (DcMotor) hardwareMap.get("rightRear");
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

        }
    }
    public void moveDrive(double driveTrainPower) {

        IMU imu = hardwareMap.get(IMU.class, "imu");
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double rotX = gamepad1.left_stick_x * Math.cos(-botHeading) - gamepad1.left_stick_y * Math.sin(-botHeading);
        double rotY = gamepad1.left_stick_x * Math.cos(-botHeading) + gamepad1.left_stick_y * Math.sin(-botHeading);
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);
        rotX = rotX * 1.1;
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(gamepad1.right_stick_x), 1);

        leftFront.setPower(((-gamepad1.left_stick_y  + gamepad1.right_stick_x + gamepad1.left_stick_x) * driveTrainPower)/denominator);
        leftRear.setPower(((-gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x) * driveTrainPower)/denominator);
        rightRear.setPower(((-gamepad1.left_stick_y -gamepad1.right_stick_x + gamepad1.left_stick_x) * driveTrainPower)/denominator);
        rightFront.setPower(((-gamepad1.left_stick_y -gamepad1.right_stick_x - gamepad1.left_stick_x) * driveTrainPower)/denominator);
    }
}
