package org.firstinspires.ftc.teamcode.opmode.auto.blueAutos;

import static org.firstinspires.ftc.teamcode.opmode.auto.blueAutos.BlueCloseTest.BlueAucienceConstants.Path.PurplePixel.box;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.command.Intake.IntakeReverse;
import org.firstinspires.ftc.teamcode.opmode.command.Outtake.Score;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideHigh;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideReset;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wrist;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.DriveConstants;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.Vision.FFVision;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;
import org.firstinspires.ftc.teamcode.util.PoseStorage;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequenceContainerFollowCommand;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.LineToLinearHeading;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.Pose2dContainer;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.SplineToConstantHeading;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.StrafeLeft;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceConstraints;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceContainer;

@Autonomous
public class BlueCloseTest extends MatchOpMode {

    private SlideV2 slide;
    private Wrist wrist;
    private Drivetrain drive;
    private FFVision vision;
    private IntakeV2 intake;
    private Wheel wheel;
    private Outtake outtake;
    private Drivetrain drivetrain;

    @Config
    public static class BlueAucienceConstants {

        public static Speed speed;

        public static class Speed {
            public static double baseVel = DriveConstants.MAX_VEL; // value
            public static double baseAccel = DriveConstants.MAX_ACCEL; // value
            public static double turnVel = DriveConstants.MAX_VEL; // value
            public static double turnAccel = DriveConstants.MAX_ANG_ACCEL; // value
            public static double slowVel = baseVel * 0.8; // value
            public static double slowAccel = baseAccel * 0.8; // value
            public static double slowTurnVel = turnVel * 0.8; // value
            public static double slowTurnAccel = turnAccel * 0.8; // value

            static TrajectorySequenceConstraints getDriveConstraints() {
                return new TrajectorySequenceConstraints(
                        (s, a, b, c) -> {
                            if (s > 18) {
                                return baseVel * 0.4;
                            } else {
                                return baseVel;
                            }

                        },
                        (s, a, b, c) -> baseAccel,
                        turnVel,
                        turnAccel
                );
            }
        }


            public static Path path;

            public static class Path {

                public static Purple purple;

                public static class Purple {
                    public static Pose2dContainer startPose = new Pose2dContainer(17, 63, Math.toRadians(270));
                    public static StrafeLeft a = new StrafeLeft(20);
                    public static LineToLinearHeading b = new LineToLinearHeading(35, 35, Math.toRadians(180));
                    static TrajectorySequenceContainer purple = new TrajectorySequenceContainer(Speed::getDriveConstraints, a, b);
                }

                public static PurplePixel Ppixel;

                public static class PurplePixel {
                    public static double leftX = 32,
                                        leftY = 30;
                    public static double midX = 24,
                                        midY = 24;
                    public static double rightX = 10,
                                        rightY = 30;
                    public static double heading = 180;
                    public static PurplePixel.AutoPosition AutoPosition;

                    public enum AutoPosition {
                        LEFT,
                        MID,
                        RIGHT
                    }

                    public static AutoPosition box = AutoPosition.MID;
                    static TrajectorySequenceContainer PurplePixel(double x, double y) {
                        switch (box) {

                            case LEFT:
                                return new TrajectorySequenceContainer(
                                        Speed::getDriveConstraints,
                                        new LineToLinearHeading(leftX, leftY, heading)
                                );

                            case MID:
                                return new TrajectorySequenceContainer(
                                        Speed::getDriveConstraints,
                                        new LineToLinearHeading(midX, midY, heading)
                                );

                            default:
                            case RIGHT:
                                return new TrajectorySequenceContainer(
                                        Speed::getDriveConstraints,
                                        new LineToLinearHeading(rightX, rightY, heading)
                                );
                        }
                    }

                }

                public static YellowPixel yPixel;

                public static class YellowPixel {
                    public static double leftX = 49,
                            leftY = 40;
                    public static double midX = 49,
                            midY = 34;
                    public static double rightX = 49,
                            rightY = 28;
                    public static double heading = 180;

                static TrajectorySequenceContainer YellowPixel(double y) {
                    {
                        switch (box) {

                            case LEFT:
                                return new TrajectorySequenceContainer(
                                        Speed::getDriveConstraints,
                                        new LineToLinearHeading(leftX, leftY, heading)
                                );

                            case MID:
                                return new TrajectorySequenceContainer(
                                        Speed::getDriveConstraints,
                                        new LineToLinearHeading(midX, midY, heading)
                                );

                            default:
                            case RIGHT:
                                return new TrajectorySequenceContainer(
                                        Speed::getDriveConstraints,
                                        new LineToLinearHeading(rightX, rightY, heading)
                                );
                        }
                    }
                }
                }

                public static Park park;

                public static class Park {
                    public static StrafeLeft a = new StrafeLeft(10);
                    public static SplineToConstantHeading b = new SplineToConstantHeading(60, 12, 0);
                    static TrajectorySequenceContainer park = new TrajectorySequenceContainer(Speed::getDriveConstraints, a, b);
                }

            }

    }

    @Override
    public void robotInit() {

        wrist = new Wrist(hardwareMap, telemetry);
        slide = new SlideV2(hardwareMap, telemetry);
        intake = new IntakeV2(hardwareMap, telemetry);
        outtake = new Outtake(hardwareMap, telemetry);
        wheel = new Wheel(hardwareMap, telemetry);

        drivetrain = new Drivetrain(new MecanumDrive(hardwareMap, telemetry, true), telemetry, hardwareMap);
        drivetrain.init();

        vision = new FFVision(hardwareMap, telemetry);

        while (!isStarted() && !isStopRequested()) {
            vision.getPosition(); //May or may not work
        }

        this.matchStart();
    }

    @Override
    public void matchStart() {
        double finalX = 0;
        double finalY = 0;

        switch (vision.getFinalPosition()) {
            case LEFT:
                BlueAucienceConstants.Path.PurplePixel.AutoPosition = BlueAucienceConstants.Path.PurplePixel.AutoPosition.LEFT;
                break;
            case MIDDLE:
                BlueAucienceConstants.Path.PurplePixel.AutoPosition = BlueAucienceConstants.Path.PurplePixel.AutoPosition.MID;
               break;
            case RIGHT:
                BlueAucienceConstants.Path.PurplePixel.AutoPosition = BlueAucienceConstants.Path.PurplePixel.AutoPosition.RIGHT;
                break;
        }

        drivetrain.setPoseEstimate(BlueAucienceConstants.Path.Purple.startPose.getPose());
        PoseStorage.trajectoryPose = BlueAucienceConstants.Path.Purple.startPose.getPose();
        schedule(
                new SequentialCommandGroup(

                        //Line Up to Purple Pixel
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueAucienceConstants.Path.Purple.purple)
                        ),

                        //Drop Purple Pixel
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueAucienceConstants.Path.PurplePixel.PurplePixel(finalX, finalY)),
                                new IntakeReverse(intake, wheel, true)
                        ),
                            new WaitCommand(100),

                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueAucienceConstants.Path.YellowPixel.YellowPixel(finalY)),
                                new SlideHigh(slide, wrist, wheel),
                                new WaitCommand(200),
                                new Score(outtake, wheel)
                        ),

                        new WaitCommand(200),
                        new SlideReset(slide, wrist, outtake, wheel),

                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueAucienceConstants.Path.Park.park)
                        )
                ),

                run(() -> PoseStorage.currentPose = drivetrain.getPoseEstimate()),

                run(this::stop)
        );

    }
}
