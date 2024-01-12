package org.firstinspires.ftc.teamcode.opmode.auto.blueAutos;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Slides;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;

@Config
@Autonomous (name = "LeftAutoBlue", group = "RoadRunnerPath")

public class LeftAutoBlue extends LinearOpMode {
    //TODO: PUT THIS IN MEEPMEEP AND TEST IT
    Pose2d leftPurplePixelBlueLeft = new Pose2d(15, 30, Math.toRadians(0));
    Pose2d leftPurplePixelBlueCenter = new Pose2d(10, 32, Math.toRadians(270));
    //  .turn(Math.toRadians(90))
    //  .back(10)
    Pose2d leftPurplePixelBlueRight = new Pose2d(10, 30, Math.toRadians(180));
    Pose2d leftBackdropBlueRight = new Pose2d(50, 30, Math.toRadians(180));
    Pose2d leftBackDropBlueLeft = new Pose2d(50, 42, Math.toRadians(180));
    Pose2d leftBackdropBlueCenter = new Pose2d(50, 36, Math.toRadians(180));
    //.back(10)
    //.strafeLeft(15)
    Pose2d leftSetUpCycleBlue = new Pose2d(0, 11, Math.toRadians(180));
    Pose2d leftBeginCycleBlue = new Pose2d(-61, 11.5, Math.toRadians(180));


    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        Drive drive = new Drive(this);
        Arm arm = new Arm(this);
        Slides slides = new Slides(this);
        Intake intake = new Intake(this);

        Pose2d startPos = new Pose2d(17, 63, Math.toRadians(0));
        ElapsedTime timer = new ElapsedTime();

        drive.setPoseEstimate(startPos);

        TrajectorySequence Trajectory1 = drive.trajectorySequenceBuilder(startPos)
                .build();

        TrajectorySequence Trajectory2 = drive.trajectorySequenceBuilder(Trajectory1.end())
                .build();
        TrajectorySequence Trajectory3 = drive.trajectorySequenceBuilder(Trajectory2.end())
                .build();
        //Run Auto
        drive.followTrajectorySequence(Trajectory1);
        drive.followTrajectorySequence(Trajectory2);
        Thread.sleep(500);
        //commands go in here
        Thread.sleep(1000);
        Thread.sleep(1500);
        intake.spinnerAutoThing(0.6, 1000);
        drive.followTrajectorySequence(Trajectory3);

    }
}

