package org.firstinspires.ftc.teamcode.subsystem.Basic;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class DroneLauncher {
    private final Gamepad gamepad2;
    private final Gamepad gamepad1;
    private final Servo servoLauncher;
    private HardwareMap hardwareMap;
    public static double holding = 0.2;
    public static double launch = 1;
    public DroneLauncher(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.gamepad2 = opMode.gamepad2;
        this.gamepad1 = opMode.gamepad1;

        servoLauncher = (Servo) hardwareMap.get("servoLauncher");
        servoLauncher.setDirection(Servo.Direction.FORWARD);
    }
    public void teleOp() {
        if (gamepad1.right_trigger >= 0.5) servoPosition(launch);
        else servoPosition(holding);
    }
    public void soloTeleOp() {
        if (gamepad1.right_trigger <= 1 && gamepad1.left_trigger <=1) servoPosition(launch);
    }
    public void servoPosition(double position) {
        servoLauncher.setPosition(position);
    }
}
