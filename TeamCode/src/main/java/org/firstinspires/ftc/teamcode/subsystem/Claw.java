package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    private Servo lClaw, rClaw;
    private final Gamepad gamepad2;
    private HardwareMap hardwareMap;
    public Claw(OpMode opMode) {
        lClaw = (Servo) hardwareMap.get("lClaw");
        rClaw = (Servo) hardwareMap.get("rClaw");

        this.hardwareMap = opMode.hardwareMap;
        this.gamepad2 = opMode.gamepad2;

        lClaw.setDirection(Servo.Direction.REVERSE);
        rClaw.setDirection(Servo.Direction.FORWARD);
        clawServo(0.42,0.42);
    }
    public void teleOp() {
        if (gamepad2.cross) clawServo(0.59, 0.59);
        else if (gamepad2.circle) clawServo(0.4,0.4);
    }
    public void clawServo(double setPositionRight, double setPositionLeft) {
        rClaw.setPosition(setPositionRight);
        lClaw.setPosition(setPositionLeft);
    }
}
