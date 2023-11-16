package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.testing.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;


@Autonomous (name = "Park", group = "RoadRunnerPath")

public class Park extends LinearOpMode {

    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d startPos = new Pose2d(0,0, Math.toRadians(0));
        ElapsedTime timer = new ElapsedTime();

        drive.setPoseEstimate(startPos);

        TrajectorySequence Trajectory1 = drive.trajectorySequenceBuilder(startPos)
                .forward(10)
                .build();

        //Run Auto
        drive.followTrajectorySequence(Trajectory1);
    }
}
