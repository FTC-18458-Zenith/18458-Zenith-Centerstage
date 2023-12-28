package org.firstinspires.ftc.teamcode.subsystem;

import android.widget.Spinner;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class Intake {
    public DcMotor spinner;
    public final Gamepad gamepad2;
    public final Gamepad gamepad1;
    public HardwareMap hardwareMap;
    public static volatile double OUTTAKE = -0.5;
    public static volatile double INTAKE = 0.65;
    public Intake(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.gamepad2 = opMode.gamepad2;
        this.gamepad1 = opMode.gamepad1;

        spinner = (DcMotor) hardwareMap.get("Intake");
        spinner.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void teleOp() {
        if (gamepad2.right_trigger >= 0.5) spinnerIntakeThing(INTAKE);
        else if (gamepad2.left_trigger >= 0.5) spinnerIntakeThing(OUTTAKE);
        else spinnerIntakeThing(0);
    }
    public void soloTeleOp() {
        if (gamepad1.right_bumper && gamepad1.right_trigger <=1) spinnerIntakeThing(INTAKE);
        else if (gamepad1.left_bumper && gamepad1.left_trigger <=1) spinnerIntakeThing(OUTTAKE);
        else spinnerIntakeThing(0);
    }
    public void spinnerIntakeThing(double powerSpinner) {
        spinner.setPower(powerSpinner);
    }
    public void spinnerAutoThing(double powerSpinner, long durationOfActions) throws InterruptedException {
        spinner.setPower(powerSpinner);
        Thread.sleep(durationOfActions);
    }
}
