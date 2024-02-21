package org.firstinspires.ftc.teamcode.opmode.auto.redAutos;

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
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Vision.FFVision;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;
import org.openftc.easyopencv.OpenCvWebcam;

@Config
@Autonomous (name = "RightAutoRed", group = "RoadRunnerPath")

public class FarRedAuto extends LinearOpMode {
    Pose2d LeftPose = new Pose2d(-47, -35, Math.toRadians(270));
    Pose2d MidPose = new Pose2d(-35, -32, Math.toRadians(90));
    Pose2d RightPose = new Pose2d(-34, -34, Math.toRadians(180));

    Pose2d ResetPose = new Pose2d(-35, -60, Math.toRadians(180));
    Pose2d scorePose = new Pose2d(47, -39, Math.toRadians(180));

    OpenCvWebcam webcam;
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

        Pose2d startPos = new Pose2d(0, 0, Math.toRadians(0));

        ElapsedTime timer = new ElapsedTime();

        drive.setPoseEstimate(startPos);
        TrajectorySequence Trajectory1 = drive.trajectorySequenceBuilder(startPos)
                .build();

        drive.followTrajectorySequence(Trajectory1);
    }
}

