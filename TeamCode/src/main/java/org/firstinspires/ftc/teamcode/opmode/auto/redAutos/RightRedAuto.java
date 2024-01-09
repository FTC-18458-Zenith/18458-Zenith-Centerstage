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
    Pose2d purplePixelRedLeft = new Pose2d(10, -32, Math.toRadians(180));
    Pose2d purplePixelRedCenter = new Pose2d(10, -32, Math.toRadians(90));
    Pose2d purplePixelRedRight = new Pose2d(10, -32, Math.toRadians(0));
    Pose2d backdropRed = new Pose2d(50, -32, Math.toRadians(180));
    Pose2d setUpCycle = new Pose2d(0, -13, Math.toRadians(180));
    Pose2d beginCycleRed = new Pose2d(-61, -11.5, Math.toRadians(180));
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        Intake spinner = new Intake(this);
        Drive drive = new Drive(this);
        Arm arm = new Arm(this);
        Slides slides = new Slides(this);

        Pose2d startPos = new Pose2d(0, 0, Math.toRadians(0));
        ElapsedTime timer = new ElapsedTime();

        drive.setPoseEstimate(startPos);

        TrajectorySequence toSpikeMarks = drive.trajectorySequenceBuilder(startPos)
                .lineToLinearHeading(purplePixelRedLeft)
                .build();

            TrajectorySequence toBackDrop = drive.trajectorySequenceBuilder(toSpikeMarks.end())
                    .lineToLinearHeading(backdropRed)
                    .build();

            TrajectorySequence startOfCycle = drive.trajectorySequenceBuilder(toBackDrop.end())
                    .lineToLinearHeading(setUpCycle)
                    .build();
            TrajectorySequence Trajectory4 = drive.trajectorySequenceBuilder(startOfCycle.end())
                    .lineToLinearHeading(beginCycleRed)
                    .build();
            TrajectorySequence Trajectory5 = drive.trajectorySequenceBuilder(Trajectory4.end())
                    .lineToLinearHeading(setUpCycle)
                    .build();
            TrajectorySequence Trajectory6 = drive.trajectorySequenceBuilder(Trajectory5.end())
                    .lineToLinearHeading(backdropRed)
                    .build();

                drive.followTrajectorySequence(toSpikeMarks);
                drive.followTrajectorySequence(toBackDrop);
                drive.followTrajectorySequence(startOfCycle);
                drive.followTrajectorySequence(Trajectory4);
                drive.followTrajectorySequence(Trajectory5);
                drive.followTrajectorySequence(Trajectory6);
        }
    }

