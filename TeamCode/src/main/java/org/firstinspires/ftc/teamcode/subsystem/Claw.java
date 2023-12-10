package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Claw {
    public Servo lClaw, rClaw;
    public final Gamepad gamepad2;
    public HardwareMap hardwareMap;
    public final double OPENED = 0.42;
    public final double CLOSED = 0.59;
    public Claw(OpMode opMode) {
        lClaw = (Servo) hardwareMap.get("lClaw");
        rClaw = (Servo) hardwareMap.get("rClaw");

        this.hardwareMap = opMode.hardwareMap;
        this.gamepad2 = opMode.gamepad2;

        lClaw.setDirection(Servo.Direction.REVERSE);
        rClaw.setDirection(Servo.Direction.FORWARD);
        clawServo(OPENED);
    }
    public void teleOp() {
        if (gamepad2.cross) clawServo(OPENED);
        else if (gamepad2.circle) clawServo(CLOSED);
    }
    public void clawServo(double position) {
        rClaw.setPosition(position);
        lClaw.setPosition(position);
    }
}
