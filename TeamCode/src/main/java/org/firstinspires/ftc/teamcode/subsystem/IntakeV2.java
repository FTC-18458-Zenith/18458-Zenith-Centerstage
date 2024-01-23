package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class IntakeV2 extends SubsystemBase {
    private final MotorEx spinner;
    private final Telemetry telemetry;

    private boolean intakeOn;

    public static double Power = 0.5;

    public enum intakePower {
        ON, OFF
    }
    intakePower intakePower;

    public IntakeV2 (Telemetry telemetry, HardwareMap hardwareMap) {

        spinner = new MotorEx(hardwareMap, "spinner");

        spinner.setInverted(true);

        this.telemetry = telemetry;
        intakePower = intakePower.OFF;
    }

    @Override
    public void periodic() {
        telemetry.addLine("Motor Power");
        telemetry.addData("Motor Output", spinner.getVelocity());
    }

    public void IntakeOn() {
        spinner.set(Power);
        intakeOn = true;
        intakePower = intakePower.ON;
    }

    public void IntakeOff() {
        spinner.set(-Power);
        intakeOn = false;
        intakePower = intakePower.OFF;
    }
}
