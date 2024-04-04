package org.firstinspires.ftc.teamcode.opmode.auto.Other;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.opmode.auto.blueAutos.BlueClose;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wrist;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.DriveConstants;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.Vision.BlueCLoseVision;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;
import org.firstinspires.ftc.teamcode.util.PoseStorage;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequenceContainerFollowCommand;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.Forward;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.Pose2dContainer;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceConstraints;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceContainer;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.Turn;

@Autonomous
public class AutoPathPlanning extends MatchOpMode {

    //subsystems
    private IntakeV2 intake;
    private Wrist wrist;
    private Drivetrain drivetrain;
    private SlideV2 slide;
    private BlueCLoseVision vision;
    private Outtake outtake;
    private Wheel wheel;
    private ConfigurableAuto configurableAuto;

    public enum AllianceSide  {
        RED,
        BLUE;
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
        configurableAuto = new ConfigurableAuto(telemetry, gamepad1);

//        drivetrain = new Drivetrain(new MecanumDrive(hardwareMap, telemetry, true), telemetry, hardwareMap);
//        drivetrain.init();

        while (!isStarted() & !isStopRequested()) {

            telemetry.clearAll();
            telemetry.addLine("Truss movement");
            telemetry.addLine("UP = UP TRUSS");
            telemetry.addLine("D-PAD DOWN = DOWN TRUSS");

            telemetry.update();
            telemetry.addData("Path traveled", configurableAuto.position());

        }
    }
    @Override
    public void matchStart() {
        double finalY = 0;
        double finalX = 0;
        switch (configurableAuto.position()) {
            case CLOSE:
                finalX = BlueClose.BlueCloseConstants.Path.PurplePixel.leftX;
                AutoPathPlanning.Constants.Path.Testing.autoPosition = AutoPathPlanning.Constants.Path.Testing.autoPosition.CLOSE;
                break;
            case FAR:
                finalX = BlueClose.BlueCloseConstants.Path.PurplePixel.leftX;
                AutoPathPlanning.Constants.Path.Testing.autoPosition = AutoPathPlanning.Constants.Path.Testing.autoPosition.FAR;
                break;
        }
        drivetrain.setPoseEstimate(AutoPathPlanning.Constants.Path.start.startPose.getPose());
        PoseStorage.trajectoryPose = AutoPathPlanning.Constants.Path.start.startPose.getPose();
        schedule(
                new SequentialCommandGroup(

                        /* Purple Pixel */
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(drivetrain, AutoPathPlanning.Constants.Path.Testing.testing(finalX))
                        ),

                        new WaitCommand(1),

                        run(() -> PoseStorage.currentPose = drivetrain.getPoseEstimate()),

                        /* Save Pose and end opmode*/

                        run(this::stop)
                )
        );
    }
    public static class Constants {

        public static AutoPathPlanning.Constants.Speed speed;

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

        public static AutoPathPlanning.Constants.Path path;

        public static class Path {
            public static AutoPathPlanning.Constants.Path.Start start;

            public static class Start {
                public static Pose2dContainer startPose = new Pose2dContainer(17, 63, 90);

                static TrajectorySequenceContainer start = new TrajectorySequenceContainer(AutoPathPlanning.Constants.Speed::getBaseConstraints);
            }

            public static AutoPathPlanning.Constants.Path.Testing testing;

            public static class Testing {

                public static double heading = 90;

                public enum AutoPosition {
                    FAR,
                    CLOSE
                }
                public static AutoPathPlanning.Constants.Path.Testing.AutoPosition autoPosition = AutoPosition.FAR;

                static TrajectorySequenceContainer testing(double Y) {
                    switch (autoPosition) {
                        case FAR:
                            return new TrajectorySequenceContainer(
                                    AutoPathPlanning.Constants.Speed::getBaseConstraints,
                                    new Forward(10)
                            );
                        default:
                        case CLOSE:
                            return new TrajectorySequenceContainer(
                                    AutoPathPlanning.Constants.Speed::getBaseConstraints,
                                    new Turn(180)
                            );
                    }
                }
            }
        }
    }
}
