package org.firstinspires.ftc.teamcode.opmode.auto.redAutos;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;
import org.openftc.easyopencv.OpenCvWebcam;

@Config
@Autonomous (name = "RightAutoRed", group = "RoadRunnerPath")

public class LeftAutoRed extends LinearOpMode {
    Pose2d purplePixelRedLeft = new Pose2d(-38, -32, Math.toRadians(180));
    //.back(7)
    //.strafeRight(20)
    Pose2d purplePixelRedRight = new Pose2d(-32, -32, Math.toRadians(0));
    //.strafeLeft(10)
    Pose2d purplePixelRedCenter = new Pose2d(-30, -32, Math.toRadians(90));
    //.back(15)
    Pose2d goDownRed = new Pose2d(-56, -20, Math.toRadians(180));
    Pose2d goingToBackdropRed = new Pose2d(-20, -8, Math.toRadians(180));
    Pose2d backdropRed = new Pose2d(47, -28, Math.toRadians(180));

    OpenCvWebcam webcam;
    @Override
    public void runOpMode() throws InterruptedException {


        waitForStart();
        Drive drive = new Drive(this);
        Pose2d startPos = new Pose2d(0, 0, Math.toRadians(0));

        ElapsedTime timer = new ElapsedTime();

        drive.setPoseEstimate(startPos);
        TrajectorySequence Trajectory1 = drive.trajectorySequenceBuilder(startPos)
                .build();

        drive.followTrajectorySequence(Trajectory1);
    }
}

