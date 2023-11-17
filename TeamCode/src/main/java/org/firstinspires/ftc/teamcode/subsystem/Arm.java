package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Arm {
    private final Servo leftArmServo, rightArmServo;
    private final Gamepad gamepad2;
    private final Gamepad gamepad1;
    private static volatile double OUTTAKE = 1;
    private static volatile double INTAKE = 0.24;

    //TODO:EDIT THESE VALUES TO CONSTRAINTS OF SERVO
    private static double GEAR_RATIO = 0;
    private static double READINGS_PER_REVOLUTION = 0;
    private static double DEGREES_OF_FREEDOM = 355;
    private static double GOAL_DEGREES = 0;
    private HardwareMap hardwareMap;
    public Arm(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.gamepad2 = opMode.gamepad2;
        this.gamepad1 = opMode.gamepad1;

        leftArmServo = (Servo) hardwareMap.get("LeftArmServo");
        rightArmServo = (Servo) hardwareMap.get("RightArmServo");


        leftArmServo.setPosition(INTAKE - 0.52);
        rightArmServo.setPosition(INTAKE);
        leftArmServo.setDirection(Servo.Direction.FORWARD);
        rightArmServo.setDirection(Servo.Direction.FORWARD);
    }
    public void soloTeleOp() throws InterruptedException {
        if (gamepad1.triangle) outtake();
        else if (gamepad1.square) intake();
    }
    public void teleOp() throws InterruptedException {
        //DEGREES = (GEAR_RATIO * READINGS_PER_REVOLUTION) / (DEGREES_OF_FREEDOM) * DEGREES I WISH, DO NOT DO YET
        if (gamepad2.triangle) outtake();
        else if (gamepad2.square) intake();
    }

    public void setPosition(double position) {
        leftArmServo.setPosition(position);
        rightArmServo.setPosition(position);
    }
    public void outtake() throws InterruptedException {
        leftArmServo.setPosition(-OUTTAKE);
        rightArmServo.setPosition(OUTTAKE);
        Thread.sleep(100);
        intake();
    }
    public void intake() {
        leftArmServo.setPosition(-INTAKE);
        rightArmServo.setPosition(INTAKE);
    }
    public void armAutoOuttake(long durationOfAction) throws InterruptedException {
        leftArmServo.setPosition(-OUTTAKE);
        rightArmServo.setPosition(OUTTAKE);
        Thread.sleep(durationOfAction);
        leftArmServo.setPosition(-INTAKE);
        rightArmServo.setPosition(INTAKE);
    }
    public void armAutoIntake() throws InterruptedException {
        leftArmServo.setPosition(-INTAKE);
        rightArmServo.setPosition(INTAKE);
    }
}
