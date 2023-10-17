package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {
    private final Servo leftArmServo, rightArmServo;
    private final Gamepad gamepad2;
    public Arm(HardwareMap hardwareMap, Gamepad gamepad2) {
        leftArmServo = (Servo) hardwareMap.get("LeftArmServo");
        rightArmServo = (Servo) hardwareMap.get("RightArmServo");
        this.gamepad2 = gamepad2;

        leftArmServo.setPosition(0);
        rightArmServo.setPosition(0);
    }
    public void teleOp() {
        //TODO: Edit these values when bot made, and do the formulas to convert ticks, DEGREES = (GEAR_RATIO * READINGS_PER_REVOLUTION) / 360
      if (gamepad2.a) armServos(0, 0);
      else if (gamepad2.b) armServos(0.5, 0.5);
      else if (gamepad2.y) armServos(0.75, 0.75);
      else if (gamepad2.x) armServos(1,1);
    } public void armServos(double positionLeft, double positionRight) {
        leftArmServo.setPosition(positionLeft);
        rightArmServo.setPosition(positionRight);
    }
}
