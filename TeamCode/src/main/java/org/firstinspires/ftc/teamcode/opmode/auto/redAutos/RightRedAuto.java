package org.firstinspires.ftc.teamcode.opmode.auto.redAutos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Slides;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;

@Autonomous
public class RightRedAuto extends LinearOpMode {
    Pose2d rightPurplePixelRedLeft = new Pose2d(-38, 30, Math.toRadians(180));
    //.strafeLeft(10)
    Pose2d rightPurplePixelRedRight = new Pose2d(-31, 30, Math.toRadians(0));
    Pose2d rightPurplePixelRedCenter = new Pose2d(-36, 32, Math.toRadians(270));
    //.back(10)
    Pose2d rightGoDownRed = new Pose2d(-60, 18, Math.toRadians(180));
    Pose2d rightGoingToBackdropRed = new Pose2d(-20,8, Math.toRadians(180));
    Pose2d rightBackdropRed = new Pose2d(50, 41.5, Math.toRadians(180));
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        Intake spinner = new Intake(this);
        Drive drive = new Drive(this);
        Arm arm = new Arm(this);
        Slides slides = new Slides(this);

        Pose2d startPos = new Pose2d(6, -63, Math.toRadians(0));
        ElapsedTime timer = new ElapsedTime();

        drive.setPoseEstimate(startPos);

            TrajectorySequence toSpikeMarks = drive.trajectorySequenceBuilder(startPos)
                    .build();

                drive.followTrajectorySequence(toSpikeMarks);
        }
    }

