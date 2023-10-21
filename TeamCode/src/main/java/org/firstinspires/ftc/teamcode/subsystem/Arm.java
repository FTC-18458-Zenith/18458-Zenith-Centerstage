package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    private final Servo leftArmServo, rightArmServo;
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
        leftArmServo = (Servo) hardwareMap.get("LeftArmServo");
        rightArmServo = (Servo) hardwareMap.get("RightArmServo");
        this.gamepad2 = gamepad2;

        leftArmServo.setPosition(0);
        rightArmServo.setPosition(0);
        leftArmServo.setDirection(Servo.Direction.FORWARD);
        rightArmServo.setDirection(Servo.Direction.REVERSE);
    }
    public void teleOp() {
        //DEGREES = (GEAR_RATIO * READINGS_PER_REVOLUTION) / (DEGREES_OF_FREEDOM) * DEGREES I WISH, DO NOT DO YET
      if (gamepad2.a) moveIntake();
      else if (gamepad2.b) moveLow();
      else if (gamepad2.y) moveMid();
      else if (gamepad2.x) moveHigh();
    }
    public void setPosition(double position) {
        leftArmServo.setPosition(position);
        rightArmServo.setPosition(position);
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
