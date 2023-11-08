package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Spinner {
    private DcMotor spinner;
    private final Gamepad gamepad2;
    public Spinner (HardwareMap hardwareMap, Gamepad gamepad2) {
        spinner = (DcMotor) hardwareMap.get("Spinner");
        this.gamepad2 = gamepad2;
        spinner.setDirection(DcMotorSimple.Direction.FORWARD);
        spinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void teleOp() {
        if (gamepad2.right_bumper) spinnerIntakeThing(0.5);
        else if (gamepad2.left_bumper) spinnerIntakeThing(-0.5);
        else spinnerIntakeThing(0);
    }
    public void spinnerIntakeThing(double powerSpinner) {
        spinner.setPower(powerSpinner);
    }
}
