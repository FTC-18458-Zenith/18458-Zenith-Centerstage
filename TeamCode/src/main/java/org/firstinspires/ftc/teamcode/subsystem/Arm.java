package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Config
public class Arm {
    private final CRServo leftArmServo, rightArmServo;
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
        leftArmServo = (CRServo) hardwareMap.get("LeftArmServo");
        rightArmServo = (CRServo) hardwareMap.get("RightArmServo");
        this.gamepad2 = gamepad2;

        leftArmServo.setPower(INTAKE - 0.52);
        rightArmServo.setPower(INTAKE);
        leftArmServo.setDirection(DcMotorSimple.Direction.FORWARD);
        rightArmServo.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    public void teleOp() {
        //DEGREES = (GEAR_RATIO * READINGS_PER_REVOLUTION) / (DEGREES_OF_FREEDOM) * DEGREES I WISH, DO NOT DO YET
        if (gamepad2.triangle) moveHigh();
        else if (gamepad2.square) moveIntake();
    }
    public void setPosition(double position) {
        leftArmServo.setPower(position);
        rightArmServo.setPower(position);
    }
    public void moveHigh() {
        leftArmServo.setPower(-HIGH);
        rightArmServo.setPower(HIGH);
    }
    public void moveMid() {
        leftArmServo.setPower(-MID);
        rightArmServo.setPower(MID);
    }
    public void moveLow() {
        leftArmServo.setPower(-LOW);
        rightArmServo.setPower(LOW);
    }
    public void moveIntake() {
        leftArmServo.setPower(-INTAKE);
        rightArmServo.setPower(INTAKE);
    }
}
