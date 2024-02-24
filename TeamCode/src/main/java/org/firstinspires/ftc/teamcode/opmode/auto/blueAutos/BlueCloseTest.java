package org.firstinspires.ftc.teamcode.opmode.auto.blueAutos;

import static org.firstinspires.ftc.teamcode.opmode.auto.blueAutos.BlueCloseTest.BlueCLoseConstants.Speed.Path.PurplePixel.autoPosition;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmode.command.Intake.IntakeReverse;
import org.firstinspires.ftc.teamcode.opmode.command.Outtake.Score;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideAuto;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideHigh;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideMid;
import org.firstinspires.ftc.teamcode.opmode.command.slides.SlideReset;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Drone;
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
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.StrafeLeft;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceConstraints;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;
import org.firstinspires.ftc.teamcode.util.PoseStorage;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceContainer;

@Autonomous
public class BlueCloseTest extends MatchOpMode {

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

    /*@Override
    public void disabledPeriodic() {
        vision.setPosition(vision.getPosition());
        vision.periodic();
        telemetry.update();
    }*/

    @Override
    public void matchStart() {

        TeamMarkerPipeline.FFPosition position = vision.getPosition();

        double finalY = 0;
        double finalX = 0;
        switch (vision.getFinalPosition()) {
            case LEFT:
                finalY = BlueCLoseConstants.Speed.Path.PurplePixel.leftY;
                autoPosition = autoPosition.lEFT;
                break;
            case MIDDLE:
                finalY = BlueCLoseConstants.Speed.Path.PurplePixel.midY;
                autoPosition = autoPosition.MID;
                break;
            case RIGHT:
                finalY = BlueCLoseConstants.Speed.Path.PurplePixel.rightY;
                autoPosition = autoPosition.RIGHT;
                break;
        }
        drivetrain.setPoseEstimate(BlueCLoseConstants.Speed.Path.PurpleLine.startPose.getPose());
        PoseStorage.trajectoryPose = BlueCLoseConstants.Speed.Path.PurpleLine.startPose.getPose();
        schedule(
                new SequentialCommandGroup(
                        /* Purple Line Up */
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueCLoseConstants.Speed.Path.PurpleLine.purpleLineup)
                        ),
                        new WaitCommand(100),

                        /* Purple Pixel */
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueCLoseConstants.Speed.Path.PurplePixel.getPurple(finalY))
                        ),
                        new SequentialCommandGroup(
                                new IntakeReverse(intake, wheel, true)
                        ),

                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueCLoseConstants.Speed.Path.getYellow(finalY))
                        ),

                        new WaitCommand(1000),

                        new SequentialCommandGroup(
                                new SlideHigh(slide, wrist, wheel),
                                new WaitCommand(1000),
                                new Score(outtake, wheel),
                                new WaitCommand(1000),
                                new SlideAuto(slide, wrist, wheel)
                                //new WaitCommand(100),
                                //new SlideHigh(slide, wrist, wheel),
                                //new WaitCommand(1000),
                                //new SlideReset(slide, wrist, outtake, wheel)
                        ),

                        new WaitCommand(1000),

                        new SlideReset(slide, wrist, outtake, wheel),

                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, BlueCLoseConstants.Speed.Path.getPark(finalY))
                        ),


                        run(() -> PoseStorage.currentPose = drivetrain.getPoseEstimate()),

                        /* Save Pose and end opmode*/

                        run(this::stop)
                )
        );


    }

    @Config
    public static class BlueCLoseConstants {

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
                public static PurpleLine PurpleLineUp;

                public static class PurpleLine {
                    public static Pose2dContainer startPose = new Pose2dContainer(17, 63, 270);
                    public static StrafeLeft a = new StrafeLeft(20);
                    public static LineToLinearHeading b = new LineToLinearHeading(35, 35, 180);
                    static TrajectorySequenceContainer purpleLineup = new TrajectorySequenceContainer(Speed::getBaseConstraints, a, b);
                }

                public static PurplePixel purplePixel;

                public static class PurplePixel {
                    public static double leftY = 30,
                            leftX = 48;
                    public static double midY = 4,
                            midX = 24;
                    public static double rightY = 30,
                            rightX = 16;
                    //public static double X = 40;
                    public static double heading = 180;

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
                                        new LineToLinearHeading(leftX, leftY, heading)
                                );

                            case MID:
                                return new TrajectorySequenceContainer(
                                        Speed::getBaseConstraints,
                                        new LineToLinearHeading(midX, midY, heading)
                                );
                            default:
                            case RIGHT:
                                return new TrajectorySequenceContainer(
                                        Speed::getBaseConstraints,
                                        new LineToLinearHeading(rightX, rightY, heading)
                                );
                        }
                    }

                }

                public static double leftY = 40;
                public static double midY = 34;
                public static double rightY = 16;
                public static double X = 72;
                public static double heading = 180;

                public static TrajectorySequenceContainer getYellow(double Y) {
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

                public static double leftPark = 40;
                public static double rightPark = 34;
                public static double midPark = 16;

                public static TrajectorySequenceContainer getPark(double Y) {
                    switch (autoPosition) {
                        case lEFT:
                            return new TrajectorySequenceContainer(
                                    Speed::getBaseConstraints,
                                    new StrafeLeft(leftPark)

                            );
                        case MID:
                            return new TrajectorySequenceContainer(
                                    Speed::getBaseConstraints,
                                    new StrafeLeft(midPark)
                            );
                        case RIGHT:
                            return new TrajectorySequenceContainer(
                                    Speed::getBaseConstraints,
                                    new StrafeLeft(rightPark)
                            );
                    }
                    return null;
                }
            }


        }
    }
}
