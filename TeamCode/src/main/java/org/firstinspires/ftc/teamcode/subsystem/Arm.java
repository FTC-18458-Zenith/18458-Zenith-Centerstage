package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Arm {
    //TODO: PROGRAM THE ARM, WHEN THE AXON MOVE IN THE SAME DIRECTION, IT MOVES, BUT IN THE OPPOSITE DIRECTION THE CLAW ROTATES.
    public final Servo leftArmServo, rightArmServo;
    public final Servo wrist;
    public final Gamepad gamepad2;
    public final Gamepad gamepad1;
    public HardwareMap hardwareMap;
    public Telemetry telemetry;
    public static double outtake = 0.20846;
    public static double intake = 0.85;
    public Arm(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.gamepad2 = opMode.gamepad2;
        this.gamepad1 = opMode.gamepad1;
        this.telemetry = opMode.telemetry;

        leftArmServo = (Servo) hardwareMap.get("leftArmServo");
        rightArmServo = (Servo) hardwareMap.get("rightArmServo");
        wrist = (Servo) hardwareMap.get("wrist");

        leftArmServo.setDirection(Servo.Direction.REVERSE);
        rightArmServo.setDirection(Servo.Direction.FORWARD);
        wrist.setDirection(Servo.Direction.REVERSE);
        // every quarter adds 88.75
        // less than 0.25
        // Need to get into 30 degrees

    }
    public void teleOp() {
        if (gamepad2.triangle) moving();
        else if (gamepad2.square) rotating();

        else if (gamepad2.cross) outtakeWrist();
        else if (gamepad2.circle) intakeWrist();
    }
    public void moving() {
        leftArmServo.setPosition(outtake);
        rightArmServo.setPosition(outtake);
    }
    public void rotating() {
        leftArmServo.setPosition(intake);
        rightArmServo.setPosition(intake);
    }
    public void outtakeWrist() {
        wrist.setPosition(0.4);
    }
    public void intakeWrist() {
        wrist.setPosition(0.5);
    }

    public void periodic() {
        telemetry.addData("Current Position", (leftArmServo.getPosition() + rightArmServo.getPosition()) / 2);

    }
}