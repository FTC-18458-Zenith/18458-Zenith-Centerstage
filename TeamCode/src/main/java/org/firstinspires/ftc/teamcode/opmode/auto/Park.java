package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.testing.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;

@Config
@Autonomous (name = "Park", group = "RoadRunnerPath")

public class Park extends LinearOpMode {


    public static double Park_Distance = 30;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        Drive drive = new Drive(this);

            Pose2d startPos = new Pose2d(0, 0, Math.toRadians(0));
            ElapsedTime timer = new ElapsedTime();

            drive.setPoseEstimate(startPos);

            TrajectorySequence Trajectory1 = drive.trajectorySequenceBuilder(startPos)
                    .forward(Park_Distance)
                    .build();

            //Run Auto
            drive.followTrajectorySequence(Trajectory1);
        }
    }

