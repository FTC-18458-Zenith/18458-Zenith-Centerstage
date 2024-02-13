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
    private final MotorEx Intake;
    private final ServoEx leftIntake;
    private final ServoEx rightIntake;
    private final Telemetry telemetry;

    private boolean intakeOn;

    public static double Power = 1;
    public static double intakeDropDown = 0.2;
    public static double intakeDropDown1 = 0.1;
    public static double outtakeDropDowm = 0;

    public enum intakePower {
        ON, OFF
    }
    intakePower intakePower;

    public IntakeV2 (Telemetry telemetry, HardwareMap hardwareMap) {

        Intake = new MotorEx(hardwareMap, "Intake");
        leftIntake = new SimpleServo(hardwareMap, "leftIntakeServo", 0, 360);
        rightIntake = new SimpleServo(hardwareMap, "rightIntakeServo", 0 , 360);

        leftIntake.setInverted(true);
        rightIntake.setInverted(false);

        Intake.setInverted(false);

        this.telemetry = telemetry;
        intakePower = intakePower.OFF;
    }

    @Override
    public void periodic() {
        telemetry.addLine("Motor Power");
        telemetry.addData("Motor Output", Intake.getVelocity());
        telemetry.addData("Servo positions", leftIntake.getPosition());
    }

    public void IntakeOn() {
        Intake.set(Power);
        intakeOn = true;
        intakePower = intakePower.ON;

        leftIntake.setPosition(intakeDropDown1);
        rightIntake.setPosition(intakeDropDown);
    }

    public void IntakeReverse() {
        Intake.set(-Power);
        intakeOn = true;
        intakePower = intakePower.ON;

        leftIntake.setPosition(outtakeDropDowm);
        rightIntake.setPosition(outtakeDropDowm);
    }

    public void IntakeOff() {
        Intake.set(-Power);
        intakeOn = false;
        intakePower = intakePower.OFF;

        leftIntake.setPosition(outtakeDropDowm);
        rightIntake.setPosition(outtakeDropDowm);
    }
}
