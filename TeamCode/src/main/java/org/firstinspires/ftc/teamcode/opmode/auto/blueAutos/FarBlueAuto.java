package org.firstinspires.ftc.teamcode.opmode.auto.blueAutos;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wrist;
import org.firstinspires.ftc.teamcode.subsystem.Vision.FFVision;
import org.firstinspires.ftc.teamcode.subsystem.Basic.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Basic.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Basic.Slides;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;

@Config
@Autonomous (name = "LeftAutoBlue", group = "RoadRunnerPath")

public class FarBlueAuto extends LinearOpMode {
    Pose2d rightPoseSpike = new Pose2d(-38, 32, Math.toRadians(180));
    Pose2d leftPoseSpike = new Pose2d(-34, 32, Math.toRadians(0));
    // Use reset pose 2 times
    Pose2d centerPoseSpike = new Pose2d(-34, 32 , Math.toRadians(270));
    // Use reset pose 2 times
    Pose2d reset = new Pose2d(-40, 58, Math.toRadians(180));
    Pose2d backdropPose = new Pose2d(47, 60, Math.toRadians(180));
    Pose2d rightScoringPose = new Pose2d(47, 28, Math.toRadians(180));



    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        Drive drive = new Drive(this);
        Wrist wrist = new Wrist(hardwareMap, telemetry);
        SlideV2 slideV2 = new SlideV2(hardwareMap, telemetry);
        IntakeV2 intakeV2 = new IntakeV2(hardwareMap, telemetry);
        Wheel wheel = new Wheel(hardwareMap, telemetry);
        Outtake outtake = new Outtake(hardwareMap, telemetry);
        FFVision ffVision = new FFVision(hardwareMap, telemetry);

        Pose2d startPos = new Pose2d(-30, 62, Math.toRadians(0));
        ElapsedTime timer = new ElapsedTime();

        drive.setPoseEstimate(startPos);

        TrajectorySequence centerTrajectory1 = drive.trajectorySequenceBuilder(startPos)
                .build();

        TrajectorySequence centerTrajectory2 = drive.trajectorySequenceBuilder(centerTrajectory1.end())
                .build();
        TrajectorySequence centerParking = drive.trajectorySequenceBuilder(centerTrajectory2.end())
                .build();

        switch (ffVision.getFinalPosition()) {
            case LEFT:
                break;
            case MIDDLE:

                break;
            case RIGHT:

                break;
        }

    }
}

