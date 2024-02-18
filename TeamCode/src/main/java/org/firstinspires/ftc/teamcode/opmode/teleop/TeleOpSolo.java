package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.opmode.command.Intake.IntakeOff;
import org.firstinspires.ftc.teamcode.opmode.command.Intake.IntakeOn;
import org.firstinspires.ftc.teamcode.opmode.command.Outtake.Score;
import org.firstinspires.ftc.teamcode.opmode.command.drone.launch;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideHigh;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideLow;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideMid;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideMoveManual;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideReset;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Drone;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wrist;
import org.firstinspires.ftc.teamcode.util.GamepadTrigger;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;

public class TeleOpSolo extends MatchOpMode {

    private GamepadEx driverGamepad; //Driver 1
    private GamepadEx operatorGamepad; // Driver 2

    private Wrist wrist;
    private SlideV2 slide;
    private IntakeV2 intakeV2;
    private Outtake outtake;
    private Drone drone;
    private Wheel wheel;

    Drive drive = new Drive(this);

    @Override
    public void robotInit() {

        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);

        wrist = new Wrist(hardwareMap, telemetry);
        slide = new SlideV2(telemetry, hardwareMap);
        intakeV2 = new IntakeV2(telemetry, hardwareMap);
        outtake = new Outtake(hardwareMap, telemetry);
        drone = new Drone(hardwareMap, telemetry);
        wheel = new Wheel(hardwareMap, telemetry);
    }

    @Override
    public void configureButtons() {

        //slide.setDefaultCommand(new SlideMoveManual(slide, operatorGamepad::getRightY));
        Button slideManual = new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_BUMPER)
                .whenHeld(new SlideMoveManual(slide, operatorGamepad::getRightY));

        Button slideReset = new GamepadButton(driverGamepad, GamepadKeys.Button.A)
                .whenPressed(new SlideReset(slide, wrist, outtake, wheel));

        Button slideLow = new GamepadButton(driverGamepad, GamepadKeys.Button.B)
                .whenPressed(new SlideLow(slide, wrist));

        Button slideMid = new GamepadButton(driverGamepad, GamepadKeys.Button.X)
                .whenPressed(new SlideMid(slide, wrist));

        Button slideHigh = new GamepadButton(driverGamepad, GamepadKeys.Button.Y)
                .whenPressed(new SlideHigh(slide, wrist, wheel));

        Button Intake = new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER)
                .whenHeld(new IntakeOn(intakeV2, wheel));

        Button Outtake = new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.LEFT_TRIGGER)
                .whenHeld(new IntakeOff(intakeV2, wheel));

        Button Score = new GamepadButton(driverGamepad, GamepadKeys.Button.RIGHT_STICK_BUTTON)
                .whenPressed(new Score(outtake, wheel));

        Button Drone = new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new launch(drone));
    }

    @Override
    public void matchStart() {

        drive.teleOp();

    }
}
