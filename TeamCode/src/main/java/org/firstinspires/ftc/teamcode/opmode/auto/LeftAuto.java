package org.firstinspires.ftc.teamcode.opmode.auto;

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
public class LeftAuto extends LinearOpMode {
    public static double Park_Distance = 35;
    public static double Pixel_Dis = -83;
    public static double randomization2 = -17;
    public static int colorSensor;
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
                .forward(Pixel_Dis)
                .build();

            TrajectorySequence Trajectory2_1 = drive.trajectorySequenceBuilder(Trajectory1.end())
                    .turn(Math.toRadians(-90))
                    .forward(Park_Distance)
                    .build();

            TrajectorySequence Trajectory3_1 = drive.trajectorySequenceBuilder(Trajectory2_1.end())
                    .forward(83)
                    .build();

                drive.followTrajectorySequence(Trajectory1);
                spinner.spinnerAutoThing(-0.5, 250);

                drive.followTrajectorySequence(Trajectory2_1);

                drive.followTrajectorySequence(Trajectory3_1);
                slides.moveLow();
                arm.armAutoOuttake(250);

        }
    }

