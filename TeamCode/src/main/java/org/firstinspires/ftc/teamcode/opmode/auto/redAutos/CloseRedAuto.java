package org.firstinspires.ftc.teamcode.opmode.auto.redAutos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wrist;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Vision.FFVision;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;

@Autonomous
public class CloseRedAuto extends LinearOpMode {
    Double leftCycleScore = -28.0;
    Double rightCycleScore = -44.0;
    Double centerCycleScore = -36.0;

    Pose2d rightSpike = new Pose2d(15, -39, Math.toRadians(45));
    Pose2d centerSpike = new Pose2d(21, -32, Math.toRadians(180 - 45));
    Pose2d leftSpike = new Pose2d(6, -39, Math.toRadians(180 - 45));

    Pose2d rightScore = new Pose2d(49, -44, Math.toRadians(180));
    Pose2d centerScore = new Pose2d(49, -36, Math.toRadians(180));
    Pose2d leftScore = new Pose2d(49, -28, Math.toRadians(180));

    Pose2d rightStartCycle = new Pose2d(30, -25, Math.toRadians(180));
    Pose2d leftStartCycle = new Pose2d(30, -20, Math.toRadians(180));

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        Drive drive = new Drive(this);
        Wrist wrist = new Wrist(hardwareMap, telemetry);
        SlideV2 slideV2 = new SlideV2(hardwareMap, telemetry);
        IntakeV2 intakeV2 = new IntakeV2(hardwareMap, telemetry);
        Wheel wheel = new Wheel(hardwareMap, telemetry);
        Outtake outtake = new Outtake(hardwareMap, telemetry);
        FFVision ffVision = new FFVision(hardwareMap, telemetry);

        Pose2d startPos = new Pose2d(6, -63, Math.toRadians(0));
        ElapsedTime timer = new ElapsedTime();

        drive.setPoseEstimate(startPos);

            TrajectorySequence toSpikeMarks = drive.trajectorySequenceBuilder(startPos)
                    .build();

                drive.followTrajectorySequence(toSpikeMarks);
        }
    }

