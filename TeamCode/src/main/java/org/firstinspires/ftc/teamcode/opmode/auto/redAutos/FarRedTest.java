package org.firstinspires.ftc.teamcode.opmode.auto.redAutos;

import static org.firstinspires.ftc.teamcode.opmode.auto.blueAutos.BlueCloseTest.BlueCLoseConstants.Speed.Path.PurplePixel.autoPosition;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.opmode.command.Intake.IntakeReverse;
import org.firstinspires.ftc.teamcode.opmode.command.Outtake.Score;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideHigh;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideMid;
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
import org.firstinspires.ftc.teamcode.subsystem.Vision.TeamMarkerPipeline;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;
import org.firstinspires.ftc.teamcode.util.PoseStorage;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequenceContainerFollowCommand;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.LineToLinearHeading;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.Pose2dContainer;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.StrafeLeft;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.StrafeRight;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceConstraints;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceContainer;

public class FarRedTest extends MatchOpMode {
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
        this.matchStart();

    }

    @Override
    public void disabledPeriodic() {
        vision.setPosition(vision.getPosition());
        vision.periodic();
        telemetry.update();
    }

    @Override
    public void matchStart() {

        TeamMarkerPipeline.FFPosition position = vision.getPosition();

        double finalY = 0;
        double finalX = 0;
        switch (vision.getFinalPosition()) {
            case LEFT:
                finalY = RedCloseTest.RedCloseConstants.Speed.Path.PurplePixel.leftY;
                autoPosition = autoPosition.lEFT;
                break;
            case MIDDLE:
                finalY = RedCloseTest.RedCloseConstants.Speed.Path.PurplePixel.midY;
                autoPosition = autoPosition.MID;
                break;
            case RIGHT:
                finalY = RedCloseTest.RedCloseConstants.Speed.Path.PurplePixel.rightY;
                autoPosition = autoPosition.RIGHT;
                break;
        }
        drivetrain.setPoseEstimate(RedCloseTest.RedCloseConstants.Speed.Path.PurpleLine.startPose.getPose());
        PoseStorage.trajectoryPose = RedCloseTest.RedCloseConstants.Speed.Path.PurpleLine.startPose.getPose();
        schedule(
                new SequentialCommandGroup(
                        /* Purple Line Up */
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, RedCloseTest.RedCloseConstants.Speed.Path.PurpleLine.purpleLineup)
                        ),
                        new WaitCommand(100),

                        /* Purple Pixel */
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, RedCloseTest.RedCloseConstants.Speed.Path.PurplePixel.getPurple(finalY))
                        ),
                        new SequentialCommandGroup(
                                new IntakeReverse(intake, wheel, true)
                        ),

                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, RedCloseTest.RedCloseConstants.Speed.Path.getYellow(finalY))
                        ),

                        new WaitCommand(1000),

                        new SequentialCommandGroup(
                                //new SlideHigh(slide),
                                new WaitCommand(1000),
                                new Score(outtake, wheel),
                                new WaitCommand(200),
                                new SlideMid(slide, wrist),
                                new WaitCommand(100),
                                //new SlideHigh(slide),
                                new WaitCommand(1000),
                                new SlideReset(slide, wrist, outtake, wheel)
                        ),

                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, RedCloseTest.RedCloseConstants.Speed.Path.Park.park)
                        ),


                        run(() -> PoseStorage.currentPose = drivetrain.getPoseEstimate()),

                        /* Save Pose and end opmode*/

                        run(this::stop)
                )
        );


    }

    @Config
    public static class BlueCLoseConstants {

        public static RedCloseTest.RedCloseConstants.Speed speed;
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

            public static RedCloseTest.RedCloseConstants.Speed.Path path;
            public static class Path {
                public static RedCloseTest.RedCloseConstants.Speed.Path.PurpleLine PurpleLineUp;
                public static class PurpleLine {
                    public static Pose2dContainer startPose = new Pose2dContainer(7, -63, 270);
                    public static StrafeRight a = new StrafeRight(20);
                    public static LineToLinearHeading b = new LineToLinearHeading(35, -35, 180);
                    static TrajectorySequenceContainer purpleLineup = new TrajectorySequenceContainer(RedCloseTest.RedCloseConstants.Speed::getBaseConstraints, a, b);
                }

                public static RedCloseTest.RedCloseConstants.Speed.Path.PurplePixel purplePixel;
                public static class PurplePixel {
                    public static double leftY = -30,
                            leftX = -10;
                    public static double midY = -24,
                            midX = -24;
                    public static double rightY = -30,
                            rightX = -32;
                    //public static double X = 40;
                    public static double heading = 180;
                    public enum AutoPosition {
                        lEFT,
                        MID,
                        RIGHT
                    }
                    public static RedCloseTest.RedCloseConstants.Speed.Path.PurplePixel.AutoPosition autoPosition = RedCloseTest.RedCloseConstants.Speed.Path.PurplePixel.AutoPosition.MID;
                    static TrajectorySequenceContainer getPurple(double Y) {
                        switch (autoPosition) {
                            case lEFT:
                                return new TrajectorySequenceContainer(
                                        RedCloseTest.RedCloseConstants.Speed::getBaseConstraints,
                                        new LineToLinearHeading(leftX, leftY, heading)
                                );

                            case MID:
                                return new TrajectorySequenceContainer(
                                        RedCloseTest.RedCloseConstants.Speed::getBaseConstraints,
                                        new LineToLinearHeading(midX, midY, heading)
                                );
                            default:
                            case RIGHT:
                                return new TrajectorySequenceContainer(
                                        RedCloseTest.RedCloseConstants.Speed::getBaseConstraints,
                                        new LineToLinearHeading(rightX, rightY, heading)
                                );
                        }
                    }

                }

                public static double leftY = -28;
                public static double midY = -34;
                public static double rightY = -41;
                public static double X = 49;
                public static double heading = 180;

                public static TrajectorySequenceContainer getYellow (double Y) {
                    switch (autoPosition) {
                        case lEFT:
                            return new TrajectorySequenceContainer(
                                    RedCloseTest.RedCloseConstants.Speed::getBaseConstraints,
                                    new LineToLinearHeading(X, leftY, heading)
                            );
                        case MID:
                            return new TrajectorySequenceContainer(
                                    RedCloseTest.RedCloseConstants.Speed::getBaseConstraints,
                                    new LineToLinearHeading(X, midY, heading)
                            );
                        case RIGHT:
                            return new TrajectorySequenceContainer(
                                    RedCloseTest.RedCloseConstants.Speed::getBaseConstraints,
                                    new LineToLinearHeading(X, rightY, heading)
                            );
                    }
                    return null;
                }

                public static RedCloseTest.RedCloseConstants.Speed.Path.Park park;
                public static class Park {
                    public static StrafeLeft a = new StrafeLeft(20);
                    static TrajectorySequenceContainer park = new TrajectorySequenceContainer(RedCloseTest.RedCloseConstants.Speed::getBaseConstraints, a);
                }
            }
        }


    }
}
