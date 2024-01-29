package org.firstinspires.ftc.teamcode.opmode.auto.blueAutos;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.Vision.FFVision;
import org.firstinspires.ftc.teamcode.subsystem.Basic.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Basic.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Basic.Slides;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;

@Config
@Autonomous (name = "LeftAutoBlue", group = "RoadRunnerPath")

public class LeftAutoBlue extends LinearOpMode {
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
    int backingUpBlue = 20;
    Pose2d parkingBlue= new Pose2d(60, 60 , Math.toRadians(90));


    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        Drive drive = new Drive(this);
        Arm arm = new Arm(this);
        Slides slides = new Slides(this);
        Intake intake = new Intake(this);
        FFVision ffVision = new FFVision(hardwareMap, telemetry);

        Pose2d startPos = new Pose2d(17, 63, Math.toRadians(0));
        ElapsedTime timer = new ElapsedTime();

        drive.setPoseEstimate(startPos);

        TrajectorySequence centerTrajectory1 = drive.trajectorySequenceBuilder(startPos)
                .lineToLinearHeading(leftPurplePixelBlueCenter)
                .build();

        TrajectorySequence centerTrajectory2 = drive.trajectorySequenceBuilder(centerTrajectory1.end())
                .lineToLinearHeading(leftBackdropBlueCenter)
                .build();
        TrajectorySequence centerParking = drive.trajectorySequenceBuilder(centerTrajectory2.end())
                .forward(backingUpBlue)
                .lineToLinearHeading(parkingBlue)
                .build();
        switch (ffVision.getFinalPosition()) {
            case LEFT:
                drive.followTrajectorySequence(centerParking);
                break;
            case MIDDLE:
                drive.followTrajectorySequence(centerTrajectory1);
                drive.followTrajectorySequence(centerTrajectory2);
                drive.followTrajectorySequence(centerParking);
                break;
            case RIGHT:
                drive.followTrajectorySequence(centerTrajectory2);
                break;
        }

    }
}

