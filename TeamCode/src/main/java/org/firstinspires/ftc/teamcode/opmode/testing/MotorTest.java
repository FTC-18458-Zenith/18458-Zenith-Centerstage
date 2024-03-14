package org.firstinspires.ftc.teamcode.opmode.testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Config
@TeleOp
public class MotorTest extends LinearOpMode {
    DcMotor motorTester;
    public static double testingPower = 0.6;
    @Override
    public void runOpMode() throws InterruptedException {
        motorTester = (DcMotor) hardwareMap.get("motor");
        motorTester.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();
        while (opModeIsActive()) {
            motorTester.setPower(testingPower);
        }
    }
}
