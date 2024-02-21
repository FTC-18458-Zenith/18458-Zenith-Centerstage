package org.firstinspires.ftc.teamcode.opmode.auto.blueAutos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Basic.Arm;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wrist;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Basic.Slides;
import org.firstinspires.ftc.teamcode.subsystem.Vision.FFVision;
import org.firstinspires.ftc.teamcode.subsystem.vision;
import org.firstinspires.ftc.teamcode.subsystem.pipelines.ColorDetectionBlue;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;

@Autonomous (name = "RightAutoBlue", group = "RoadRunnerPath")
public class CloseBlueAuto extends LinearOpMode {
    Pose2d scorePose = new Pose2d(47, 39, Math.toRadians(180));
    Pose2d resetPose = new Pose2d(35, 35, Math.toRadians(180));
    Pose2d rightPose = new Pose2d(32, 30, Math.toRadians(180));
    Pose2d leftPose = new Pose2d(10, 30, Math.toRadians(180));
    Pose2d midPose = new Pose2d(24, 24, Math.toRadians(180));
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
