package org.firstinspires.ftc.teamcode.opmode.auto.Other;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wrist;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.Vision.BlueCLoseVision;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;

public class AutoPathPlanning extends MatchOpMode {

    //subsystems
    private IntakeV2 intake;
    private Wrist wrist;
    private Drivetrain drivetrain;
    private SlideV2 slide;
    private BlueCLoseVision vision;
    private Outtake outtake;
    private Wheel wheel;

    public enum AllianceSide  {
        RED,
        BLUE;
    }

    public static AllianceSide allianceSide;

    public AllianceSide getAllianceSide() {

        return null;
    }

    public enum Pose  {
        CLOSE,
        FAR;
    }

    public enum Park  {
        LEFT,
        RIGHT;
    }

    double StartDelay = 0;

    @Override
    public void robotInit() {

        wrist = new Wrist(hardwareMap, telemetry);
        slide = new SlideV2(hardwareMap, telemetry);
        intake = new IntakeV2(hardwareMap, telemetry);
        outtake = new Outtake(hardwareMap, telemetry);
        wheel = new Wheel(hardwareMap, telemetry);

        vision = new BlueCLoseVision(hardwareMap, telemetry);

        drivetrain = new Drivetrain(new MecanumDrive(hardwareMap, telemetry, true), telemetry, hardwareMap);
        drivetrain.init();

        while (!isStarted() & !isStopRequested()) {

            vision.setPosition(vision.getPosition());
            vision.periodic();
            telemetry.update();

            telemetry.addLine( " Red or Blue Alliance");
            telemetry.addLine("A = Red Alliance");
            telemetry.addLine("B = Blue Alliance");

            telemetry.update();

            while (!isStopRequested()) {
                if (gamepad1.a) {
                    while (!isStopRequested() && gamepad1.a) {
                        allianceSide = AutoPathPlanning.AllianceSide.RED;
                    }
                    break;
                }
                if (gamepad1.b) {
                    while (!isStopRequested() && gamepad1.b) {
                        allianceSide = AutoPathPlanning.AllianceSide.BLUE;
                    }
                    break;
                }
            }

            telemetry.clearAll();
            telemetry.addLine("Close or Far Side");
            telemetry.addLine("A = Close Side");
            telemetry.addLine(" B = Far Side");

            telemetry.update();

            while (!isStopRequested()) {
                if (gamepad1.a) {
                    while (!isStopRequested() && gamepad1.a) {
                        Pose pose = AutoPathPlanning.Pose.CLOSE;
                    }
                    break;
                }
                if (gamepad1.b) {
                    while (!isStopRequested() && gamepad1.b) {
                        Pose pose = AutoPathPlanning.Pose.FAR;
                    }
                    break;
                }
            }

            telemetry.clearAll();
            telemetry.addLine("Left or Right Park");
            telemetry.addLine("A = Left Park");
            telemetry.addLine("B = Right Park");

            telemetry.update();

            while (!isStopRequested()) {
                if (gamepad1.a) {
                    while (!isStopRequested() && gamepad1.a) {
                        Park park = Park.LEFT;
                    }
                    break;
                }
                if (gamepad1.b) {
                    while (!isStopRequested() && gamepad1.b) {
                        Park park = Park.RIGHT;
                    }
                    break;
                }
            }

            telemetry.clearAll();
            telemetry.addLine("StartDelay");
            telemetry.addLine("Left Dpad is Increase");
            telemetry.addLine("Right Dpad is Decrease");

            telemetry.update();

            while (!isStopRequested()) {
                if (gamepad1.dpad_left) {
                    while (!isStopRequested() && gamepad1.dpad_left) {
                        StartDelay = StartDelay + 1;
                    }
                }
                if (gamepad1.dpad_right) {
                    while (!isStopRequested() && gamepad1.dpad_right) {
                        StartDelay = StartDelay - 1;
                    }
                }
                if (gamepad1.a) {
                    while (!isStopRequested() && gamepad1.a) {
                        break;
                    }
                }
            }

            telemetry.clearAll();

            telemetry.addLine("Alliance Side");

        }

    }

    @Override
    public void matchStart() {

    }
}
