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
    public static double Spike_Dis = 28;
    public static double Park_Distance = 40;
    public static double right_Strafe = 20.5;
    public static double Cycle_Dis = 110;
    public static double Scoring_Dis = 23;
    public static double Parking = 27.5;

    OpenCvWebcam webcam;
    @Override
    public void runOpMode() throws InterruptedException {


        waitForStart();
        Drive drive = new Drive(this);
        Pose2d startPos = new Pose2d(0, 0, Math.toRadians(0));

        ElapsedTime timer = new ElapsedTime();

        drive.setPoseEstimate(startPos);
        TrajectorySequence Trajectory1 = drive.trajectorySequenceBuilder(startPos)
                .forward(Spike_Dis)
                .build();
        TrajectorySequence Trajectory2 = drive.trajectorySequenceBuilder(Trajectory1.end())
                .turn(Math.toRadians(-90))
                        .forward(Park_Distance)
                                .build();
        TrajectorySequence Trajectory3 = drive.trajectorySequenceBuilder(Trajectory2.end())
                .strafeRight(right_Strafe)
                        .build();

        drive.followTrajectorySequence(Trajectory1);

        drive.followTrajectorySequence(Trajectory2);
    }
}

