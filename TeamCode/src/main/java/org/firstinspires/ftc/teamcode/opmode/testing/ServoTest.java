package org.firstinspires.ftc.teamcode.opmode.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoTest extends LinearOpMode {
    Servo servo;
    public double testingPower = 0.6;
    @Override
    public void runOpMode() throws InterruptedException {
        servo = (Servo) hardwareMap.get("servo");
        servo.setDirection(Servo.Direction.FORWARD);

        waitForStart();
        while (opModeIsActive()) {
            servo.setPosition(testingPower);
        }
    }

}
