package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class HorizontalExtension {
    public HardwareMap hardwareMap;
    public Gamepad gamepad2;
    public Servo leftServoExtend, rightServoExtend;
    public CRServo leftServoCR, rightServoCR;
    public double INTAKE = 1;
    public double OUTTAKE = 0;
    public double INTAKE_CR = 0.25;
    public double OUTTAKE_CR = -0.25;
    public HorizontalExtension(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.gamepad2 = opMode.gamepad2;

        leftServoExtend.setDirection(Servo.Direction.FORWARD);
        rightServoExtend.setDirection(Servo.Direction.REVERSE);
        leftServoExtend = (Servo) hardwareMap.get("leftServoExtend");
        rightServoExtend = (Servo) hardwareMap.get("rightServoExtend");

        leftServoCR = (CRServo) hardwareMap.get("leftServoExtend");
        rightServoCR = (CRServo) hardwareMap.get("rightServoExtend");
        leftServoCR.setDirection(DcMotorSimple.Direction.FORWARD);
        rightServoCR.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void teleOp() {
        if (gamepad2.right_trigger >= 0.5) extension(INTAKE);
        else extension(OUTTAKE);
    }
    public void crTeleOp() {
        if (gamepad2.right_trigger >= 0.5 && gamepad2.right_bumper) CRextension(INTAKE_CR);
        else if (gamepad2.left_trigger >= 0.5 && gamepad2.left_bumper) CRextension(OUTTAKE_CR);
        else CRextension(0);
    }
    public void extension(double position) {
        leftServoExtend.setPosition(position);
        rightServoExtend.setPosition(position);
    }
    public void CRextension(double power) {
        leftServoCR.setPower(power);
        rightServoCR.setPower(power);
    }
}
