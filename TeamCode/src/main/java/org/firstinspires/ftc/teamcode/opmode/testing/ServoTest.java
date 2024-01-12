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
    Servo servo;
    public static double testingPower = 0.6;
    public static double otherPostion = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        servo = (Servo) hardwareMap.get("servo");
        servo.setDirection(Servo.Direction.FORWARD);

        waitForStart();
        while (opModeIsActive()) {
            servo.setPosition(testingPower);
            if (gamepad1.a) servo.setPosition(otherPostion);
            else if (gamepad1.b) servo.setPosition(testingPower);
        }
    }

}
