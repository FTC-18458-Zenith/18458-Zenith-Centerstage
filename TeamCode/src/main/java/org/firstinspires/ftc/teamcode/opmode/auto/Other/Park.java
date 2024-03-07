package org.firstinspires.ftc.teamcode.opmode.auto.Other;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drive;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;

@Config

public class Park extends LinearOpMode {
    public static double Park_Distance = 30;
    public static double Pixel_Dis = 20;

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
            drive.movingMotors(0.1, 10000, 1000);
        }
    }

