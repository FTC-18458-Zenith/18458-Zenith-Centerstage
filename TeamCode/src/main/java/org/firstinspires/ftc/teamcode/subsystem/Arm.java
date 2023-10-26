package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    private final CRServo leftArmServo, rightArmServo;
    private final Gamepad gamepad2;
    private static final double HIGH = 1;
    private static final double MID = 0.5;
    private static final double LOW = 0.25;
    private static final double INTAKE = 0;

    //TODO:EDIT THESE VALUES TO CONSTRAINTS OF SERVO
    private static double GEAR_RATIO = 0;
    private static double READINGS_PER_REVOLUTION = 0;
    private static double DEGREES_OF_FREEDOM = 355;
    private static double GOAL_DEGREES = 0;
    public Arm(HardwareMap hardwareMap, Gamepad gamepad2) {
        leftArmServo = (CRServo) hardwareMap.get("LeftArmServo");
        rightArmServo = (CRServo) hardwareMap.get("RightArmServo");
        this.gamepad2 = gamepad2;

        leftArmServo.setPower(1);
        rightArmServo.setPower(1);
        leftArmServo.setDirection(DcMotorSimple.Direction.FORWARD);
        rightArmServo.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void teleOp() {
        //DEGREES = (GEAR_RATIO * READINGS_PER_REVOLUTION) / (DEGREES_OF_FREEDOM) * DEGREES I WISH, DO NOT DO YET
      if (gamepad2.cross) moveIntake();
      else if (gamepad2.circle) moveLow();
      else if (gamepad2.square) moveMid();
      else if (gamepad2.triangle) moveHigh();
    }
    public void setPosition(double position) {
        leftArmServo.setPower(position);
        rightArmServo.setPower(position);
    }
    public void moveHigh() {
        setPosition(HIGH);
    }
    public void moveMid() {
        setPosition(MID);
    }
    public void moveLow() {
        setPosition(LOW);
    }
    public void moveIntake() {
        setPosition(INTAKE);
    }
}
