package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.command.Intake.IntakeOff;
import org.firstinspires.ftc.teamcode.opmode.command.Intake.IntakeOn;
import org.firstinspires.ftc.teamcode.opmode.command.Outtake.Score;
import org.firstinspires.ftc.teamcode.opmode.command.drive.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.opmode.command.drive.SlowDriveCommand;
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
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.GamepadTrigger;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;

@Config
@TeleOp
public class TeleOpMain_V2 extends MatchOpMode {

    private GamepadEx driverGamepad; //Driver 1
    private GamepadEx operatorGamepad; // Driver 2

    private Wrist wrist;
    private SlideV2 slide;
    private IntakeV2 intakeV2;
    private Outtake outtake;
    private Drone drone;
    private Wheel wheel;
    private Drivetrain drivetrain;

    //Drive drive = new Drive(this);

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

        drivetrain = new Drivetrain(new MecanumDrive(hardwareMap, telemetry, true), telemetry, hardwareMap);
        drivetrain.init();

    }

    @Override
    public void configureButtons() {

        drivetrain.setDefaultCommand(new DefaultDriveCommand(drivetrain, driverGamepad, true));

        Button recenterIMU = (new GamepadButton(driverGamepad, GamepadKeys.Button.A))
                .whenPressed(new InstantCommand(drivetrain::reInitializeIMU));

        Button recenterIMU2 = (new GamepadButton(driverGamepad, GamepadKeys.Button.START))
                .whenPressed(new InstantCommand(drivetrain::reInitializeIMU));

        Button slowMode = (new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_BUMPER))
                .whileHeld(new SlowDriveCommand(drivetrain, driverGamepad, true));

        slide.setDefaultCommand(new SlideMoveManual(slide, operatorGamepad::getRightY));

        Button slideReset = new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new SlideReset(slide, wrist, outtake));

        Button slideLow = new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(new SlideLow(slide, wrist));

        Button slideMid = new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(new SlideMid(slide, wrist));

        Button slideHigh = new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_UP)
                .whenPressed(new SlideHigh(slide, wrist, wheel));

        Trigger Intake = new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER)
                .whileActiveContinuous(new IntakeOn(intakeV2, wheel))
                .whenInactive(new IntakeOff(intakeV2, wheel));



        Button Score = new GamepadButton(operatorGamepad, GamepadKeys.Button.A)
                .whenPressed(new Score(outtake, wheel));

        Button Drone = new GamepadButton(operatorGamepad, GamepadKeys.Button.B)
                .whenPressed(new launch(drone));
    }

    @Override
    public void matchStart() {

        //drive.teleOp();
    }
}
