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
    Pose2d purplePixelRed = new Pose2d(10, -32, Math.toRadians(180));
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

        TrajectorySequence Trajectory1 = drive.trajectorySequenceBuilder(startPos)
                .lineToLinearHeading(purplePixelRed)
                .build();

            TrajectorySequence Trajectory2 = drive.trajectorySequenceBuilder(Trajectory1.end())
                    .lineToLinearHeading(backdropRed)
                    .build();

            TrajectorySequence Trajectory3 = drive.trajectorySequenceBuilder(Trajectory2.end())
                    .lineToLinearHeading(setUpCycle)
                    .build();
            TrajectorySequence Trajectory4 = drive.trajectorySequenceBuilder(Trajectory3.end())
                    .lineToLinearHeading(beginCycleRed)
                    .build();
            TrajectorySequence Trajectory5 = drive.trajectorySequenceBuilder(Trajectory4.end())
                    .lineToLinearHeading(setUpCycle)
                    .build();
            TrajectorySequence Trajectory6 = drive.trajectorySequenceBuilder(Trajectory5.end())
                    .lineToLinearHeading(backdropRed)
                    .build();

                drive.followTrajectorySequence(Trajectory1);
                drive.followTrajectorySequence(Trajectory2);
                drive.followTrajectorySequence(Trajectory3);
                drive.followTrajectorySequence(Trajectory4);
                drive.followTrajectorySequence(Trajectory5);
                drive.followTrajectorySequence(Trajectory6);

        }
    }

