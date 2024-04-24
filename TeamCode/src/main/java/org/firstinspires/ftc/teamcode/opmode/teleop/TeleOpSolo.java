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
import org.firstinspires.ftc.teamcode.opmode.command.Intake.IntakeReverse;
import org.firstinspires.ftc.teamcode.opmode.command.Outtake.Hold;
import org.firstinspires.ftc.teamcode.opmode.command.Outtake.Score;
import org.firstinspires.ftc.teamcode.opmode.command.drive.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.opmode.command.drive.SlowDriveCommand;
import org.firstinspires.ftc.teamcode.opmode.command.drone.launch;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideHigh;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideLow;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideMid;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideMoveManual;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideReset;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drive;
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
public class TeleOpSolo extends MatchOpMode {

    private GamepadEx driverGamepad; //Driver 1
    private GamepadEx operatorGamepad; // Driver 2

    private Wrist wrist;
    private SlideV2 slide;
    private IntakeV2 intakeV2;
    private Outtake outtake;
    private Drone drone;
    private Wheel wheel;

    private Drivetrain drivetrain;

    @Override
    public void robotInit() {

        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);

        wrist = new Wrist(hardwareMap, telemetry);
        slide = new SlideV2(hardwareMap, telemetry);
        intakeV2 = new IntakeV2(hardwareMap, telemetry);
        outtake = new Outtake(hardwareMap, telemetry);
        drone = new Drone(hardwareMap, telemetry);
        wheel = new Wheel(hardwareMap, telemetry);


        drivetrain = new Drivetrain(new MecanumDrive(hardwareMap, telemetry, true), telemetry, hardwareMap);
        drivetrain.init();
    }

    @Override
    public void configureButtons() {

        drivetrain.setDefaultCommand(new DefaultDriveCommand(drivetrain, driverGamepad, true));

        //Button recenterIMU = (new GamepadButton(driverGamepad, GamepadKeys.Button.A))
        //.whenPressed(new InstantCommand(drivetrain::reInitializeIMU));

        Button recenterIMU2 = new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_UP)
                .whenPressed(new InstantCommand(drivetrain::reInitializeIMU));

        Button slowMode = (new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_BUMPER))
                .whileHeld(new SlowDriveCommand(drivetrain, driverGamepad, true));

        //slide.setDefaultCommand(new SlideMoveManual(slide, operatorGamepad::getRightY));
        /*Button slideManual = new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_BUMPER)
                .whenHeld(new SlideMoveManual(slide, operatorGamepad::getRightY));
*/
        Button slideReset = new GamepadButton(driverGamepad, GamepadKeys.Button.A)
                .whenPressed(new SlideReset(slide, wrist, outtake, wheel));

        Button slideLow = new GamepadButton(driverGamepad, GamepadKeys.Button.B)
                .whenPressed(new SlideLow(slide, wrist, outtake));

        Button slideMid = new GamepadButton(driverGamepad, GamepadKeys.Button.X)
                .whenPressed(new SlideMid(slide, wrist, outtake));

        Button slideHigh = new GamepadButton(driverGamepad, GamepadKeys.Button.Y)
                .whenPressed(new SlideHigh(slide, wrist, outtake));

        Trigger Intake = new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER)
                .whileActiveContinuous(new IntakeOn(intakeV2, wheel))
                .whenInactive(new IntakeOff(intakeV2, wheel));

        Trigger Outtake = new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.LEFT_TRIGGER)
                .whileActiveContinuous(new IntakeReverse(intakeV2, wheel, false))
                .whenInactive(new IntakeOff(intakeV2, wheel));

        Button Score = new GamepadButton(driverGamepad, GamepadKeys.Button.RIGHT_STICK_BUTTON)
                .whenPressed(new Score(outtake, wheel));

        Button Hold = new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_STICK_BUTTON)
                .whenPressed(new Hold(outtake));

        Button Drone = new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new launch(drone));
    }

    @Override
    public void matchStart() {
    }
}
