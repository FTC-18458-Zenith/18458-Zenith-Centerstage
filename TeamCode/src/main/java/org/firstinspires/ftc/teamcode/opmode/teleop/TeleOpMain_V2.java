package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.opmode.command.Intake.IntakeOn;
import org.firstinspires.ftc.teamcode.opmode.command.Outtake.Score;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideHigh;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideLow;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideMid;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideMoveManual;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideReset;
import org.firstinspires.ftc.teamcode.subsystem.Arm_V2;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.Wrist;
import org.firstinspires.ftc.teamcode.util.GamepadTrigger;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;

public class TeleOpMain_V2 extends MatchOpMode {

    private GamepadEx driverGamepad; //Driver 1
    private GamepadEx operatorGamepad; // Driver 2

    private Wrist wrist;
    private SlideV2 slide;
    private IntakeV2 intake;
    private Outtake outtake;

    Drive drive = new Drive(this);

    @Override
    public void robotInit() {

        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);

        wrist = new Wrist(hardwareMap, telemetry);
        slide = new SlideV2(telemetry, hardwareMap);
        intake = new IntakeV2(telemetry, hardwareMap);
        outtake = new Outtake(hardwareMap, telemetry);

        drive.teleOp();

    }

    @Override
    public void configureButtons() {

        slide.setDefaultCommand(new SlideMoveManual(slide, operatorGamepad::getRightY));

        Button slideReset = new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new SlideReset(slide, wrist, outtake));

        Button slideLow = new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(new SlideLow(slide, wrist));

        Button slideMid = new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(new SlideMid(slide, wrist));

        Button slideHigh = new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_UP)
                .whenPressed(new SlideHigh(slide, wrist));

        Button Intake = new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER)
                .whenHeld(new IntakeOn(intake));

        Button Score = new GamepadButton(operatorGamepad, GamepadKeys.Button.A)
                .whenPressed(new Score(outtake));
    }

    @Override
    public void matchStart() {
    }
}
