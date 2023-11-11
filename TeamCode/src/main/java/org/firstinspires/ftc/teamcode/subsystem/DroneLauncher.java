package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DroneLauncher {
    private final Gamepad gamepad2;
    private final Gamepad gamepad1;
    private final Servo servoLauncher;
    private HardwareMap hardwareMap;
    public DroneLauncher(OpMode opMode) {
        servoLauncher = (Servo) hardwareMap.get("servoLauncher");
        servoLauncher.setPosition(0);
        servoLauncher.setDirection(Servo.Direction.FORWARD);
        this.hardwareMap = opMode.hardwareMap;
        this.gamepad2 = opMode.gamepad2;
        this.gamepad1 = opMode.gamepad1;
    }
    public void teleOp() {
        if (gamepad2.right_trigger <= 1) servoPosition(1);
    }
    public void soloTeleOp() {
        if (gamepad1.right_trigger <= 1 && gamepad1.left_trigger <=1) servoPosition(1);
    }
    public void servoPosition(double position) {
        servoLauncher.setPosition(position);
    }
}
