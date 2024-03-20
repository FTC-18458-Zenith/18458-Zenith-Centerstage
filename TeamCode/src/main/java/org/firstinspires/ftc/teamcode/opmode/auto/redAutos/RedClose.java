package org.firstinspires.ftc.teamcode.opmode.auto.redAutos;

import static org.firstinspires.ftc.teamcode.opmode.auto.blueAutos.BlueClose.BlueCLoseConstants.Speed.Path.PurplePixel.autoPosition;

import android.view.animation.CycleInterpolator;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wrist;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.DriveConstants;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.Vision.FFVision;
import org.firstinspires.ftc.teamcode.subsystem.Vision.TeamMarkerPipeline;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequenceContainerFollowCommand;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.LineToLinearHeading;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.Pose2dContainer;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.SplineToConstantHeading;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.StrafeLeft;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceConstraints;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;
import org.firstinspires.ftc.teamcode.util.PoseStorage;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceContainer;

@Autonomous
public class RedClose extends MatchOpMode {

    // Subsystems
    private IntakeV2 intake;
    private Wrist wrist;
    private Drivetrain drivetrain;
    private SlideV2 slide;
    private FFVision vision;
    private Outtake outtake;
    private Wheel wheel;

    @Override
    public void robotInit() {

        wrist = new Wrist(hardwareMap, telemetry);
        slide = new SlideV2(hardwareMap, telemetry);
        intake = new IntakeV2(hardwareMap, telemetry);
        outtake = new Outtake(hardwareMap, telemetry);
        wheel = new Wheel(hardwareMap, telemetry);

        vision = new FFVision(hardwareMap, telemetry);

        drivetrain = new Drivetrain(new MecanumDrive(hardwareMap, telemetry, true), telemetry, hardwareMap);
        drivetrain.init();
        while (!isStarted() & !isStopRequested()) {
            vision.setPosition(vision.getPosition());
            vision.periodic();
            telemetry.update();
        }
        this.matchStart();
    }
    @Override
    public void matchStart() {

        TeamMarkerPipeline.FFPosition position = vision.getPosition();

        double finalY = 0;
        double finalX = 0;
        switch (vision.getFinalPosition()) {
            case LEFT:
                finalY = RedCloseConstants.Speed.Path.PurplePixel.leftY;
                autoPosition = autoPosition.lEFT;
                break;
            case MIDDLE:
                finalY = RedCloseConstants.Speed.Path.PurplePixel.midY;
                autoPosition = autoPosition.MID;
                break;
            case RIGHT:
                finalY = RedCloseConstants.Speed.Path.PurplePixel.rightY;
                autoPosition = autoPosition.RIGHT;
                break;
        }
        drivetrain.setPoseEstimate(RedCloseConstants.Speed.Path.start.startPose.getPose());
        PoseStorage.trajectoryPose = RedCloseConstants.Speed.Path.start.startPose.getPose();
        schedule(
                new SequentialCommandGroup(
                        /* Purple Pixel */
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, RedCloseConstants.Speed.Path.PurplePixel.getPurple(finalY))
                        ),
//                        new SequentialCommandGroup(
//                                new IntakeReverse(intake, wheel, true)
//                        ),

                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, RedCloseConstants.Speed.Path.getYellow(finalY))
                        ),
                        new ParallelCommandGroup(
                               new TrajectorySequenceContainerFollowCommand(drivetrain, RedCloseConstants.Speed.Path.Park.park)
                        ),

                        new WaitCommand(1000),

                        /*new SequentialCommandGroup(
                                new SlideHigh(slide, wrist, wheel),
                                new WaitCommand(1000),
                                new Score(outtake, wheel),
                                new WaitCommand(200),
                                new SlideMid(slide, wrist),
                                new WaitCommand(100),
                                new SlideHigh(slide, wrist, wheel),
                                new WaitCommand(1000),
                                new SlideReset(slide, wrist, outtake, wheel)
                        ),*/
//                        new ParallelCommandGroup(
//                                new TrajectorySequenceContainerFollowCommand(drivetrain, RedCloseConstants.Speed.Path.Cycle.cycle())
//                        ),
//                       new ParallelCommandGroup(
//                               new TrajectorySequenceContainerFollowCommand(drivetrain, RedCloseConstants.Speed.Path.Score.score())
//                       ),
                       new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, RedCloseConstants.Speed.Path.Park.park)
                        ),
                        run(() -> PoseStorage.currentPose = drivetrain.getPoseEstimate()),


                        run(this::stop)
                )
        );
    }
    @Config
    public static class RedCloseConstants {

        public static Speed speed;
        public static class Speed {
            public static double baseVel = DriveConstants.MAX_VEL; // value
            public static double baseAccel = DriveConstants.MAX_ACCEL; // value
            public static double turnVel = DriveConstants.MAX_VEL; // value
            public static double turnAccel = DriveConstants.MAX_ANG_ACCEL; // value

            static TrajectorySequenceConstraints getDropConstraints() {
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

            static TrajectorySequenceConstraints getBaseConstraints() {
                return new TrajectorySequenceConstraints(baseVel, baseAccel, turnVel, turnAccel);
            }

            public static Path path;
            public static class Path {
                public static Start start;
                public static class Start {
                    public static Pose2dContainer startPose = new Pose2dContainer(7, -63, 90);

                    static TrajectorySequenceContainer Start = new TrajectorySequenceContainer(Speed::getBaseConstraints);
                }
                public static PurplePixel purplePixel;
                public static class PurplePixel {
                    public static double leftY = -39,
                            leftX = 6,
                            headingLeft = 135;
                    public static double midY = -32,
                            midX = 21,
                            headingMid = 135;
                    public static double rightY = -39,
                            rightX = 15,
                            headingRight = 45;
                    //public static double X = 40;
                    public static double heading = 90;
                    public enum AutoPosition {
                        lEFT,
                        MID,
                        RIGHT
                    }
                    public static AutoPosition autoPosition = AutoPosition.RIGHT;
                    static TrajectorySequenceContainer getPurple(double Y) {
                        switch (autoPosition) {
                            case lEFT:
                                return new TrajectorySequenceContainer(
                                        Speed::getBaseConstraints,
                                        new LineToLinearHeading(leftX, leftY, headingLeft)
                                );

                            case MID:
                                return new TrajectorySequenceContainer(
                                        Speed::getBaseConstraints,
                                        new LineToLinearHeading(midX, midY, headingMid)
                                );
                            default:
                            case RIGHT:
                                return new TrajectorySequenceContainer(
                                        Speed::getBaseConstraints,
                                        new LineToLinearHeading(rightX, rightY, headingRight)
                                );
                        }
                    }

                }
                public static double leftY = -28;
                public static double midY = -36;
                public static double rightY = -44;
                public static double X = 51;
                public static double heading = 180;

                public static TrajectorySequenceContainer getYellow (double Y) {
                    switch (autoPosition) {
                        case lEFT:
                            return new TrajectorySequenceContainer(
                                    Speed::getBaseConstraints,
                                    new LineToLinearHeading(X, leftY, heading)
                            );
                        case MID:
                            return new TrajectorySequenceContainer(
                                    Speed::getBaseConstraints,
                                    new LineToLinearHeading(X, midY, heading)
                            );
                        case RIGHT:
                            return new TrajectorySequenceContainer(
                                    Speed::getBaseConstraints,
                                    new LineToLinearHeading(X, rightY, heading)
                            );
                    }
                    return null;
                }
                public static Cycle cycle;
                public static class Cycle{
                    public static double leftY = -20,
                    leftX = 30;
                    public static double rightY = -25,
                    rightX = 30;
                    public static double heading = 180;
                    static TrajectorySequenceContainer cycle() {
                        switch (autoPosition) {
                            case lEFT:
                                return new TrajectorySequenceContainer(
                                        Speed::getBaseConstraints,
                                        new LineToLinearHeading(leftX, leftY, heading),
                                        new SplineToConstantHeading(-59, -11, Math.toRadians(180))
                                );
                            default:
                            case RIGHT:
                                return new TrajectorySequenceContainer(
                                        Speed::getBaseConstraints,
                                        new LineToLinearHeading(rightX, rightY, heading),
                                        new SplineToConstantHeading(-59, -11, Math.toRadians(180))
                                );
                        }
                    }
                }
                public static Score score;
                public static class Score {
                    public static double rightY = -44;
                    public static double midY = -36;
                    public static double leftY = -28;
                    static TrajectorySequenceContainer score() {
                        switch (autoPosition) {
                            case lEFT:
                                return new TrajectorySequenceContainer(
                                  Speed::getBaseConstraints,
                                  new LineToLinearHeading(0, -14, Math.toRadians(180)),
                                        new SplineToConstantHeading(51, leftY, Math.toRadians(180))
                                );
                            case MID:
                                return new TrajectorySequenceContainer(
                                        Speed::getBaseConstraints,
                                        new LineToLinearHeading(0, -14, Math.toRadians(180)),
                                        new SplineToConstantHeading(51, midY, Math.toRadians(180))
                                );
                            case RIGHT:
                                return new TrajectorySequenceContainer(
                                        Speed::getBaseConstraints,
                                        new LineToLinearHeading(0, -14, Math.toRadians(180)),
                                        new SplineToConstantHeading(51, rightY, Math.toRadians(180))
                                );
                        }
                        return null;
                    }
                }
                public static Park park;
                public static class Park {
//                    public static SplineToConstantHeading a = new SplineToConstantHeading(60,-60, Math.toRadians(0));
                    public static StrafeLeft a = new StrafeLeft(20);
                    static TrajectorySequenceContainer park = new TrajectorySequenceContainer(Speed::getBaseConstraints, a);
                }
            }
        }
    }
}