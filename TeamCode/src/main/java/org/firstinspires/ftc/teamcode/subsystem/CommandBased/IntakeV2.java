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

    public static double Power = 0.6;
    public static double intakeDropDown = 0.47;
    public static double outtakeDropDown = 0.6;
    //Value can be lower than this, such as 0.6 or smth


    // Auto Stack
    public static double Cycle1_1Pixel = 0.51;
    public static double Cycle1_2Pixel = 0.5;
    public static double Cycle2_3Pixel = 0.49;
    public static double Cycle2_4Pixel = 0.485;



    public enum intakePower {
        ON, OFF
    }
    intakePower intakePower;

    public IntakeV2 (HardwareMap hardwareMap, Telemetry telemetry) {

        Intake = new MotorEx(hardwareMap, "Intake");
        leftIntake = new SimpleServo(hardwareMap, "leftIntakeServo", 0, 360);
        rightIntake = new SimpleServo(hardwareMap, "rightIntakeServo", 0 , 360);

        leftIntake.setInverted(true);
        rightIntake.setInverted(false);

        Intake.setInverted(false);

        leftIntake.setPosition(outtakeDropDown);
        rightIntake.setPosition(outtakeDropDown);

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

        leftIntake.setPosition(intakeDropDown);
        rightIntake.setPosition(intakeDropDown);
    }

    public void IntakeReverse() {
        Intake.set(-Power);
        intakeOn = true;
        intakePower = intakePower.ON;

        leftIntake.setPosition(outtakeDropDown);
        rightIntake.setPosition(outtakeDropDown);
    }

    public void IntakeReverseAuto() {
        Intake.set(-0.3);
        intakeOn = true;
        intakePower = intakePower.ON;

        leftIntake.setPosition(outtakeDropDown);
        rightIntake.setPosition(outtakeDropDown);
    }

    public void IntakeOff() {
        Intake.set(0);
        intakeOn = false;
        intakePower = intakePower.OFF;

        leftIntake.setPosition(outtakeDropDown);
        rightIntake.setPosition(outtakeDropDown);
    }

    public void autoStack() {
        Intake.set(Power);
        intakeOn = true;
        intakePower = intakePower.ON;
    }

    public void firstCycle1() {
        leftIntake.setPosition(Cycle1_1Pixel);
        leftIntake.setPosition(Cycle1_1Pixel);
    }

    public void firstCycle2() {
        leftIntake.setPosition(Cycle1_2Pixel);
        leftIntake.setPosition(Cycle1_2Pixel);
    }

    public void secondCycle1() {
        leftIntake.setPosition(Cycle2_3Pixel);
        leftIntake.setPosition(Cycle2_3Pixel);
    }

    public void secondCycle2() {
        leftIntake.setPosition(Cycle2_4Pixel);
        leftIntake.setPosition(Cycle2_4Pixel);
    }
}