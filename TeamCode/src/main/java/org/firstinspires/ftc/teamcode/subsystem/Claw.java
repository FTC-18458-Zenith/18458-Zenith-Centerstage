package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    private Servo lClaw, rClaw;
    private final Gamepad gamepad2;
    public Claw(HardwareMap hardwareMap, Gamepad gamepad2) {
        lClaw = (Servo) hardwareMap.get("lClaw");
        rClaw = (Servo) hardwareMap.get("rClaw");
        this.gamepad2 = gamepad2;

        lClaw.setDirection(Servo.Direction.REVERSE);
        rClaw.setDirection(Servo.Direction.FORWARD);
        clawServo(0,0);
    }
    public void teleOp() {
        if (gamepad2.right_trigger >= 0.1) clawServo(1, 1);
        else if (gamepad2.left_trigger >= 0.1) clawServo(0,0);
    }
    public void clawServo(double setPositionRight, double setPositionLeft) {
        rClaw.setPosition(setPositionRight);
        lClaw.setPosition(setPositionLeft);
    }
}
