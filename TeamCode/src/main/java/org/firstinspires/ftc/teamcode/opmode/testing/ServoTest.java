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
    public static double testingPower1 = 0;
    public static double testingPower2 = 0.1;
    public static double otherPostion = 1;
    @Override
    public void runOpMode() throws InterruptedException {
        servo1 = (Servo) hardwareMap.get("servo1");
        servo2 = (Servo) hardwareMap.get("servo2");
        servo1.setDirection(Servo.Direction.FORWARD);
        servo2.setDirection(Servo.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {
            servo1.setPosition(testingPower1);
            servo2.setPosition(testingPower2);
            if (gamepad1.a) servo1.setPosition(otherPostion);
            else if (gamepad1.b) servo1.setPosition(testingPower1);
        }
    }

}
