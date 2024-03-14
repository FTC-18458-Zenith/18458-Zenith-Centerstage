package org.firstinspires.ftc.teamcode.opmode.testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@Config
@TeleOp
public class ServoTest extends LinearOpMode {
    Servo servo1, servo2;
    public static double testingPower1 = 0.9;
    public static double testingPower2 = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        servo1 = (Servo) hardwareMap.get("servo1");
        servo2 = (Servo) hardwareMap.get("servo2");
        servo1.setDirection(Servo.Direction.FORWARD);
        servo2.setDirection(Servo.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) servoTesting(testingPower1);
            if (gamepad1.b) servoTesting(testingPower2);
        }
    }
    public void servoTesting(double position) {
        servo1.setPosition(position);
        servo2.setPosition(position);
    }
}
