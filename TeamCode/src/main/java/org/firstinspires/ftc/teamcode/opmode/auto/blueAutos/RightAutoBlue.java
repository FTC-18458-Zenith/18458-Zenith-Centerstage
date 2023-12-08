package org.firstinspires.ftc.teamcode.opmode.auto.blueAutos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Slides;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;

@Autonomous (name = "RightAutoBlue", group = "RoadRunnerPath")
public class RightAutoBlue extends LinearOpMode {
    public static double Park_Distance = 80;
    public static double Spike_Dis = 29;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        Drive drive = new Drive(this);
        Arm arm = new Arm(this);
        Slides slides = new Slides(this);

        Pose2d startPos = new Pose2d(-34, 68, Math.toRadians(90));
        ElapsedTime timer = new ElapsedTime();

        drive.setPoseEstimate(startPos);

        TrajectorySequence Trajectory1 = drive.trajectorySequenceBuilder(startPos)
                .forward(Spike_Dis)
                .build();

        TrajectorySequence Trajectory2 = drive.trajectorySequenceBuilder(Trajectory1.end())
                .turn(Math.toRadians(90))
                .forward(Park_Distance)
                .build();
        //Run Auto
        drive.followTrajectorySequence(Trajectory1);
        drive.followTrajectorySequence(Trajectory2);
        Thread.sleep(500);
        slides.moveLow();
        Thread.sleep(1000);
        arm.outtake();
        Thread.sleep(1500);
        arm.intake();
        slides.reset();
    }
}
