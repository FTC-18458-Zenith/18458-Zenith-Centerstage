package org.firstinspires.ftc.teamcode.subsystem.CommandBased;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class IntakeV2 extends SubsystemBase {
    private final MotorEx spinner;
    private final ServoEx leftIntake, rightIntake;
    private final Telemetry telemetry;

    private boolean intakeOn;

    public static double Power = 0.5;
    public static double intakeDropDown = 1;
    public static double outtakeDropDowm = 0;

    public enum intakePower {
        ON, OFF
    }
    intakePower intakePower;

    public IntakeV2 (Telemetry telemetry, HardwareMap hardwareMap) {

        spinner = new MotorEx(hardwareMap, "spinner");
        leftIntake = new SimpleServo(hardwareMap, "leftIntakeServo", 0, 360);
        rightIntake = new SimpleServo(hardwareMap, "rightIntakeServo", 0 , 360);

        leftIntake.setInverted(true);
        rightIntake.setInverted(false);

        spinner.setInverted(true);

        this.telemetry = telemetry;
        intakePower = intakePower.OFF;
    }

    @Override
    public void periodic() {
        telemetry.addLine("Motor Power");
        telemetry.addData("Motor Output", spinner.getVelocity());
        telemetry.addData("Servo positions", leftIntake.getPosition());
    }

    public void IntakeOn() {
        spinner.set(Power);
        intakeOn = true;
        intakePower = intakePower.ON;

        leftIntake.setPosition(intakeDropDown);
        rightIntake.setPosition(intakeDropDown);
    }

    public void IntakeOff() {
        spinner.set(-Power);
        intakeOn = false;
        intakePower = intakePower.OFF;

        leftIntake.setPosition(outtakeDropDowm);
        rightIntake.setPosition(outtakeDropDowm);
    }
}
