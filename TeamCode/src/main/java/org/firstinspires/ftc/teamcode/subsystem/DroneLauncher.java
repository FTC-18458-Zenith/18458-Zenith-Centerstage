package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DroneLauncher {
    private final Gamepad gamepad2;
    private final Servo servoLauncher;
    public DroneLauncher(HardwareMap hardwareMap, Gamepad gamepad2) {
        servoLauncher = (Servo) hardwareMap.get("servoLauncher");
        this.gamepad2 = gamepad2;
        servoLauncher.setPosition(0);
        servoLauncher.setDirection(Servo.Direction.FORWARD);
    }
    public void teleOp() {
        if (gamepad2.right_trigger <= 1) servoPosition(1);
    }
    public void servoPosition(double position) {
        servoLauncher.setPosition(position);
    }
}
