package org.firstinspires.ftc.teamcode.opmode.auto.blueAutos;



import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.command.Intake.AutoStack;
import org.firstinspires.ftc.teamcode.opmode.command.Intake.IntakeOff;
import org.firstinspires.ftc.teamcode.opmode.command.Intake.IntakeOn;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wrist;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.DriveConstants;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.Vision.BlueCLoseVision;
import org.firstinspires.ftc.teamcode.subsystem.Vision.TeamMarkerPipeline;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequenceContainerFollowCommand;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.Back;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.Forward;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.LineToConstantHeading;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.LineToLinearHeading;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.LineToSplineHeading;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.Pose2dContainer;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.SplineTo;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.SplineToConstantHeading;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.SplineToLinearHeading;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.StrafeLeft;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.StrafeRight;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceConstraints;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;
import org.firstinspires.ftc.teamcode.util.PoseStorage;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceContainer;

import java.util.function.BiConsumer;

@Autonomous
public class BlueClose extends MatchOpMode {

    // Subsystems
    private IntakeV2 intake;
    private Wrist wrist;
    private Drivetrain drivetrain;
    private SlideV2 slide;
    private BlueCLoseVision vision;
    private Outtake outtake;
    private Wheel wheel;

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
                finalX = BlueCloseConstants.Path.PurplePixel.leftX;
                BlueCloseConstants.Path.PurplePixel.autoPosition = BlueCloseConstants.Path.PurplePixel.autoPosition.lEFT;
                break;
            case MIDDLE:
                finalX = BlueCloseConstants.Path.PurplePixel.leftX;
                BlueCloseConstants.Path.PurplePixel.autoPosition = BlueCloseConstants.Path.PurplePixel.autoPosition.MID;
                break;
            case RIGHT:
                finalX = BlueCloseConstants.Path.PurplePixel.leftX;
                BlueCloseConstants.Path.PurplePixel.autoPosition = BlueCloseConstants.Path.PurplePixel.autoPosition.RIGHT;
                break;
        }
        drivetrain.setPoseEstimate(BlueCloseConstants.Path.start.startPose.getPose());
        PoseStorage.trajectoryPose = BlueCloseConstants.Path.start.startPose.getPose();
        schedule(
                new SequentialCommandGroup(

                        /* Purple Pixel */
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueCloseConstants.Path.PurplePixel.getPurple(finalX))
                        ),

                        new WaitCommand(1),

                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueCloseConstants.Path.getYellow(finalY))
                        ),

                        new WaitCommand(1000),

                        new ParallelCommandGroup(
                                new AutoStack(intake),
                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueCloseConstants.Path.closeCycleStart.startCycle)
                        ),

                        new WaitCommand(1000),

                        new ParallelCommandGroup(
                                new IntakeOff(intake, wheel),
                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueCloseConstants.Path.closeCycleEnd.endCycle)
                        ),

                        new WaitCommand(1000),

                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueCloseConstants.Path.closeCycleStart.startCycle),
                                new AutoStack(intake)
                        ),

                        new WaitCommand(1000),

                        new ParallelCommandGroup(
                                new IntakeOff(intake, wheel),
                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueCloseConstants.Path.closeCycleEnd.endCycle)
                        ),



                        run(() -> PoseStorage.currentPose = drivetrain.getPoseEstimate()),

                        /* Save Pose and end opmode*/

                        run(this::stop)
                )
        );
    }

    @Config
    public static class BlueCloseConstants {

        public static Speed speed;

        public static class Speed {
            public static double baseVel = DriveConstants.MAX_VEL; // value
            public static double baseAccel = DriveConstants.MAX_ACCEL; // value
            public static double turnVel = DriveConstants.MAX_ANG_VEL; // value
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
        }

            public static Path path;

            public static class Path {
                public static Start start;

                public static class Start {
                    public static Pose2dContainer startPose = new Pose2dContainer(17, 63, 90);

                    static TrajectorySequenceContainer start = new TrajectorySequenceContainer(Speed::getBaseConstraints);
                }

                public static PurplePixel purplePixel;

                public static class PurplePixel {
                    public static double leftY = 39,
                            leftX = 24;
                    public static double midY = 32,
                            midX = 12;
                    public static double rightY = 32,
                            rightX = 10;
                    public static double rightHeading = 0;

                    public static double heading = 90;

                    public enum AutoPosition {
                        lEFT,
                        MID,
                        RIGHT
                    }

                    public static AutoPosition autoPosition = AutoPosition.MID;

                    static TrajectorySequenceContainer getPurple(double Y) {
                        switch (autoPosition) {
                            case lEFT:
                                return new TrajectorySequenceContainer(
                                        Speed::getBaseConstraints,
                                        new LineToSplineHeading(leftX, leftY, heading)
                                );
                            case MID:
                                return new TrajectorySequenceContainer(
                                        Speed::getBaseConstraints,
                                        new LineToSplineHeading(midX, midY, heading)
                                );
                            default:
                            case RIGHT:
                                return new TrajectorySequenceContainer(
                                        Speed::getBaseConstraints,
                                        new LineToSplineHeading(rightX, rightY, rightHeading)
                                );
                        }
                    }

                }

                public static double leftY = 44;
                public static double midY = 28;
                public static double rightY = 23;
                public static double X = 55;
                public static double heading = 180;
                public static double endHeading = 0;

                public static TrajectorySequenceContainer getYellow(double Y) {
                    switch (PurplePixel.autoPosition) {
                        case lEFT:
                            return new TrajectorySequenceContainer(
                                    Speed::getBaseConstraints,
                                    new StrafeRight(10),
                                    new SplineToLinearHeading(X, leftY, heading, endHeading)
                            );
                        case MID:
                            return new TrajectorySequenceContainer(
                                    Speed::getBaseConstraints,
                                    new SplineToLinearHeading(X, midY, heading, endHeading)
                            );
                        case RIGHT:
                            return new TrajectorySequenceContainer(
                                    Speed::getBaseConstraints,
                                    new SplineToLinearHeading(X, rightY, heading, endHeading)
                            );
                    }
                    return null;
                }

                public static class closeCycleStart {
                    public static SplineToConstantHeading a = new SplineToConstantHeading(17, 61, 180);
                    public static LineToLinearHeading b = new LineToLinearHeading(-24, 61, 180);
                    public static SplineToConstantHeading c = new SplineToConstantHeading(-54, 35, 180);
                    static TrajectorySequenceContainer startCycle = new TrajectorySequenceContainer(BlueCloseConstants.Speed::getBaseConstraints, a, b, c);
                }

                public static class closeCycleEnd {
                    public static Back a = new Back(2);
                    public static SplineToConstantHeading b = new SplineToConstantHeading(-24, 61, 0);
                    public static LineToLinearHeading c = new LineToLinearHeading(16, 61, 180);
                    public static SplineToConstantHeading d = new SplineToConstantHeading(49, 36, 0);

                    static TrajectorySequenceContainer endCycle = new TrajectorySequenceContainer(BlueCloseConstants.Speed::getBaseConstraints, a, b, c, d);
                }

                public static class farCycleStart {
                    public static SplineToConstantHeading a = new SplineToConstantHeading(17, 11, 180);
                    public static LineToLinearHeading b = new LineToLinearHeading(-24, 11, 180);
                    public static SplineToConstantHeading c = new SplineToConstantHeading(-58, 11, 180);
                    static TrajectorySequenceContainer startCycle = new TrajectorySequenceContainer(BlueCloseConstants.Speed::getBaseConstraints, a, b, c);
                }

                public static class farCycleEnd {
                    public static Back a = new Back(2);
                    public static SplineToConstantHeading b = new SplineToConstantHeading(-24, 11, 0);
                    public static LineToLinearHeading c = new LineToLinearHeading(16, 11, 180);
                    public static SplineToConstantHeading d = new SplineToConstantHeading(49, 36, 0);

                    static TrajectorySequenceContainer endCycle = new TrajectorySequenceContainer(BlueCloseConstants.Speed::getBaseConstraints, a, b, c, d);
                }

                public static class Park {
                    public static LineToConstantHeading a = new LineToConstantHeading(53, 65);
                    static TrajectorySequenceContainer park = new TrajectorySequenceContainer(BlueCloseConstants.Speed::getBaseConstraints, a);
                }
            }

    }
}