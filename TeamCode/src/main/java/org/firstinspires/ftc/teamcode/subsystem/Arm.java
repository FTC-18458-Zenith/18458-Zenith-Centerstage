package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Config
public class Arm {
    private final Servo leftArmServo, rightArmServo;
    private final Gamepad gamepad2;
    private static volatile double HIGH = 1;
    private static volatile double MID = 0.5;
    private static volatile double LOW = 0.25;
    private static volatile double INTAKE = 0.24;

    //TODO:EDIT THESE VALUES TO CONSTRAINTS OF SERVO
    private static double GEAR_RATIO = 0;
    private static double READINGS_PER_REVOLUTION = 0;
    private static double DEGREES_OF_FREEDOM = 355;
    private static double GOAL_DEGREES = 0;
    public Arm(HardwareMap hardwareMap, Gamepad gamepad2) {
        leftArmServo = (Servo) hardwareMap.get("LeftArmServo");
        rightArmServo = (Servo) hardwareMap.get("RightArmServo");
        this.gamepad2 = gamepad2;

        leftArmServo.setPosition(INTAKE);
        rightArmServo.setPosition(INTAKE);
        leftArmServo.setDirection(Servo.Direction.FORWARD);
        rightArmServo.setDirection(Servo.Direction.FORWARD);
    }
    public void teleOp() {
        //DEGREES = (GEAR_RATIO * READINGS_PER_REVOLUTION) / (DEGREES_OF_FREEDOM) * DEGREES I WISH, DO NOT DO YET
        if (gamepad2.triangle) moveHigh();
        else if (gamepad2.square) moveIntake();
    }
    public void setPosition(double position) {
        leftArmServo.setPosition(position);
        rightArmServo.setPosition(position);
    }
    public void moveHigh() {
        leftArmServo.setPosition(-HIGH);
        rightArmServo.setPosition(HIGH);
    }
    public void moveMid() {
        leftArmServo.setPosition(-MID);
        rightArmServo.setPosition(MID);
    }
    public void moveLow() {
        leftArmServo.setPosition(-LOW);
        rightArmServo.setPosition(LOW);
    }
    public void moveIntake() {
        leftArmServo.setPosition(-INTAKE);
        rightArmServo.setPosition(INTAKE);
    }
}
