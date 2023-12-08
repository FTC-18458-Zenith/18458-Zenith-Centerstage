package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;

@Config
@Autonomous (name = "RightAutoRed", group = "RoadRunnerPath")

public class RightAutoRed extends LinearOpMode {
    public static double Park_Distance = 30;
    public static double Pixel_Dis = 20;
    OpenCvWebcam webcam;
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        Drive drive = new Drive(this);
        Pose2d startPos = new Pose2d(0, 0, Math.toRadians(0));

        ElapsedTime timer = new ElapsedTime();

        drive.setPoseEstimate(startPos);
        TrajectorySequence Trajectory1 = drive.trajectorySequenceBuilder(startPos)
                .forward(Pixel_Dis)
                .build();
        TrajectorySequence Trajectory2 = drive.trajectorySequenceBuilder(Trajectory1.end())
                .turn(Math.toRadians(-90))
                        .forward(Park_Distance)
                                .build();

        drive.followTrajectorySequence(Trajectory1);

        drive.followTrajectorySequence(Trajectory2);
    }
}

