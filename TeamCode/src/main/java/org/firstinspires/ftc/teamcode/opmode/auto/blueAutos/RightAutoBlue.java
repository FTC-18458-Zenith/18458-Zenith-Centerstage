package org.firstinspires.ftc.teamcode.opmode.auto.blueAutos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Slides;
import org.firstinspires.ftc.teamcode.subsystem.Vision;
import org.firstinspires.ftc.teamcode.subsystem.pipelines.ColorDetectionBlue;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.TrajectorySequence;

@Autonomous (name = "RightAutoBlue", group = "RoadRunnerPath")
public class RightAutoBlue extends LinearOpMode {
    Pose2d purplePixelBlueLeft = new Pose2d(-38, 30, Math.toRadians(180));
    //.back(10)
    Pose2d purplePixelBlueRight = new Pose2d(-31, 30, Math.toRadians(0));
    Pose2d purplePixelBlueCenter = new Pose2d(-30, 32, Math.toRadians(270));
    //.back(10)
    Pose2d goDownBlue = new Pose2d(-34, 60, Math.toRadians(180));
    Pose2d goingToBackdrop = new Pose2d(0,8, Math.toRadians(180));
    Pose2d backDrop = new Pose2d(50, 41.5, Math.toRadians(180));
    ColorDetectionBlue colorDetectionBlue = new ColorDetectionBlue();

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        Drive drive = new Drive(this);
        Arm arm = new Arm(this);
        Slides slides = new Slides(this);
        ColorDetectionBlue colorDetectionBlue = new ColorDetectionBlue();
        Vision vision = new Vision(this, Vision.AllianceColor.BLUE);
        Pose2d startPos = new Pose2d(-30, 64, Math.toRadians(90));

        drive.setPoseEstimate(startPos);

        TrajectorySequence Trajectory1 = drive.trajectorySequenceBuilder(startPos)
                .lineToLinearHeading(purplePixelBlueLeft)
                .build();

        TrajectorySequence Trajectory2 = drive.trajectorySequenceBuilder(Trajectory1.end())
                .lineToLinearHeading(goDownBlue)
                .build();
        //Run Auto


    }
}
