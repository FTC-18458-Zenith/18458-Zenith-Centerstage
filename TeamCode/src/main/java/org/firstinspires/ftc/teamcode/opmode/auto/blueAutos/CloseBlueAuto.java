package org.firstinspires.ftc.teamcode.opmode.auto.blueAutos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wrist;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Vision.FFVision;
import org.firstinspires.ftc.teamcode.subsystem.vision;
import org.firstinspires.ftc.teamcode.subsystem.pipelines.ColorDetectionBlue;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;

@Autonomous (name = "RightAutoBlue", group = "RoadRunnerPath")
public class CloseBlueAuto extends LinearOpMode {
    Double leftCycleScore = 28.0;
    Double rightCycleScore = 44.0;
    Double centerCycleScore = 36.0;

    Pose2d rightSpike = new Pose2d(15, 39, Math.toRadians(45));
    Pose2d centerSpike = new Pose2d(21, 32, Math.toRadians(180 - 45));
    Pose2d leftSpike = new Pose2d(6, 39, Math.toRadians(180 - 45));

    Pose2d rightScore = new Pose2d(49, 44, Math.toRadians(180));
    Pose2d centerScore = new Pose2d(49, 36, Math.toRadians(180));
    Pose2d leftScore = new Pose2d(49, 28, Math.toRadians(180));

    Pose2d rightStartCycle = new Pose2d(30, 25, Math.toRadians(180));
    Pose2d leftStartCycle = new Pose2d(30, 20, Math.toRadians(180));
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

        ColorDetectionBlue colorDetectionBlue = new ColorDetectionBlue();
        vision vision = new vision(this, org.firstinspires.ftc.teamcode.subsystem.vision.AllianceColor.BLUE);
        Pose2d startPos = new Pose2d(17, 63, Math.toRadians(0));

        drive.setPoseEstimate(startPos);

        TrajectorySequence Trajectory1 = drive.trajectorySequenceBuilder(startPos)
                .build();

        TrajectorySequence Trajectory2 = drive.trajectorySequenceBuilder(Trajectory1.end())
                .build();
        //Run Auto


    }
}
