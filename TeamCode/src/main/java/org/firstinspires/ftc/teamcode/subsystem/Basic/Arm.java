package org.firstinspires.ftc.teamcode.subsystem.Basic;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Arm {
    //TODO: PROGRAM THE ARM, WHEN THE AXON MOVE IN THE SAME DIRECTION, IT MOVES, BUT IN THE OPPOSITE DIRECTION THE CLAW ROTATES.
    public final Servo leftArmServo, rightArmServo;
    public final CRServo wheel;
    public final Gamepad gamepad2;
    public final Gamepad gamepad1;
    public HardwareMap hardwareMap;
    public Telemetry telemetry;
    public static double OUTTAKE = 0.5;
    public static double INTAKE = 0;
    public Arm(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.gamepad2 = opMode.gamepad2;
        this.gamepad1 = opMode.gamepad1;
        this.telemetry = opMode.telemetry;

        leftArmServo = (Servo) hardwareMap.get("leftArmServo");
        rightArmServo = (Servo) hardwareMap.get("rightArmServo");
        wheel = (CRServo) hardwareMap.get("wheel");

        leftArmServo.setDirection(Servo.Direction.REVERSE);
        rightArmServo.setDirection(Servo.Direction.FORWARD);
        wheel.setDirection(DcMotorSimple.Direction.FORWARD);
        // every quarter adds 88.75
        // less than 0.25
        // Need to get into 30 degrees
    }
    public void teleOp() {
        if (gamepad2.triangle) outtake();
        else if (gamepad2.square) intake();

    }
    public void outtake() {
        leftArmServo.setPosition(OUTTAKE);
        rightArmServo.setPosition(OUTTAKE);
        wheel.setPower(0);
    }
    public void intake() {
        leftArmServo.setPosition(INTAKE);
        rightArmServo.setPosition(INTAKE);
        wheel.setPower(1);
    }
    public void periodic() {
        telemetry.addData("Current Position", (leftArmServo.getPosition() + rightArmServo.getPosition()) / 2);

    }
}