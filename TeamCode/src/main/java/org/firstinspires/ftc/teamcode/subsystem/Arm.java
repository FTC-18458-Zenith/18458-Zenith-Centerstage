package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.R;

@Config
public class Arm {
    public final Servo leftArmServo, rightArmServo;
    public final Gamepad gamepad2;
    public final Gamepad gamepad1;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    double position;
    public static volatile double OUTTAKE = 1;
    public static volatile double INTAKE = 0.05;
    public Arm(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.gamepad2 = opMode.gamepad2;
        this.gamepad1 = opMode.gamepad1;
        this.telemetry = opMode.telemetry;

        leftArmServo = (Servo) hardwareMap.get("LeftArmServo");
        rightArmServo = (Servo) hardwareMap.get("RightArmServo");

        leftArmServo.setPosition(INTAKE);
        rightArmServo.setPosition(INTAKE);
        leftArmServo.setDirection(Servo.Direction.FORWARD);
        rightArmServo.setDirection(Servo.Direction.REVERSE);

    }
    public void teleOp() throws InterruptedException {
        //DEGREES = (GEAR_RATIO * READINGS_PER_REVOLUTION) / (DEGREES_OF_FREEDOM) * DEGREES I WISH, DO NOT DO YET
        if (gamepad2.triangle) outtake();
        else if (gamepad2.square) intake();
    }
    public void soloTeleOp() throws InterruptedException {
        if (gamepad1.triangle) outtake();
        else if (gamepad1.square) intake();
    }
    public void setPosition(double position) {
        leftArmServo.setPosition(position);
        rightArmServo.setPosition(position);
    }
    public void outtake() {
        leftArmServo.setPosition(OUTTAKE);
        rightArmServo.setPosition(OUTTAKE);
    }
    public void intake() {
        leftArmServo.setPosition(INTAKE);
        rightArmServo.setPosition(INTAKE);
    }
    public void armAutoOuttake(long durationOfAction) throws InterruptedException {
        leftArmServo.setPosition(-OUTTAKE);
        rightArmServo.setPosition(OUTTAKE);
        Thread.sleep(durationOfAction);
        leftArmServo.setPosition(-INTAKE);
        rightArmServo.setPosition(INTAKE);
    }
}
