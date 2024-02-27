package org.firstinspires.ftc.teamcode.opmode.auto.Other;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;

@Autonomous
public class testingAuto extends LinearOpMode {

    public final Gamepad gamepad1;
    public final Drive drive;
    public TrajectorySequence trajectorySequence;
    public testingAuto(Gamepad gamepad1, Drive drive) {
        this.gamepad1 = gamepad1;
        this.drive = drive;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        Drive drive = new Drive(this);
        testing();
        }
    public void testing() {
        Pose2d startPos = new Pose2d(0, 0, Math.toRadians(0));
        drive.setPoseEstimate(startPos);
        TrajectorySequence Trajectory1 = drive.trajectorySequenceBuilder(startPos)
                .forward(10)
                .build();
        TrajectorySequence Trajectory2 = drive.trajectorySequenceBuilder(Trajectory1.end())
                .turn(100)
                .build();
        if (gamepad1.b) {
            drive.followTrajectorySequence(Trajectory1);
        }
        else if (gamepad1.a) {
            drive.followTrajectorySequence(Trajectory1);
        }
    }
}