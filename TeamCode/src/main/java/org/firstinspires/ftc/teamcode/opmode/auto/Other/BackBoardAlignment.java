package org.firstinspires.ftc.teamcode.opmode.auto.Other;

import static org.firstinspires.ftc.teamcode.subsystem.CommandBased.AutoDistance.error;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auto.blueAutos.BlueClose;
import org.firstinspires.ftc.teamcode.opmode.command.Sensor.AutoSensor;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.AutoDistance;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.DriveConstants;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;
import org.firstinspires.ftc.teamcode.util.PoseStorage;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequenceContainerFollowCommand;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.Back;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.Forward;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.Pose2dContainer;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceConstraints;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceContainer;

@Autonomous
public class BackBoardAlignment extends MatchOpMode {

    private Drivetrain drivetrain;
    private AutoDistance Sensor;

    @Override
    public void robotInit() {

        drivetrain = new Drivetrain(new MecanumDrive(hardwareMap, telemetry, true), telemetry, hardwareMap);
        drivetrain.init();

        Sensor = new AutoDistance(telemetry, hardwareMap);

        this.matchStart();

    }

    @Override
    public void matchStart() {

        drivetrain.setPoseEstimate(Constants.Start.startPose.getPose());
        PoseStorage.trajectoryPose = Constants.Start.startPose.getPose();

        schedule(
                new SequentialCommandGroup(



                        new WaitCommand(100),

                        new ParallelCommandGroup(
                                new AutoSensor(Sensor),
                                new TrajectorySequenceContainerFollowCommand(drivetrain, Constants.VariableDistance.Variable)
                        ),

                        run(() -> PoseStorage.currentPose = drivetrain.getPoseEstimate()),

                        run(this::stop)


                )
        );





    }

    @Config
    public static class Constants {

        public static BlueClose.BlueCloseConstants.Speed speed;

        public static class Speed {
            public static double baseVel = DriveConstants.MAX_VEL; // value
            public static double baseAccel = DriveConstants.MAX_ACCEL; // value
            public static double turnVel = DriveConstants.MAX_ANG_VEL; // value
            public static double turnAccel = DriveConstants.MAX_ANG_ACCEL; // value


            static TrajectorySequenceConstraints getBaseConstraints() {
                return new TrajectorySequenceConstraints(baseVel, baseAccel, turnVel, turnAccel);
            }
        }

        public static Start start;

        public static class Start {
            public static Pose2dContainer startPose = new Pose2dContainer(0, 0, 0);

            static TrajectorySequenceContainer start = new TrajectorySequenceContainer(Constants.Speed::getBaseConstraints);
        }



        public static class VariableDistance {

            public static Back a = new Back(error);

            static TrajectorySequenceContainer Variable = new TrajectorySequenceContainer(Speed::getBaseConstraints, a);

        }
    }
}
