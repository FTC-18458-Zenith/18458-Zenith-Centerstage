package org.firstinspires.ftc.teamcode.opmode.testing;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.util.Angle;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


public class LockToTest extends LinearOpMode {
    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
    double xyP = 1;
    double headingP = 1;

    @Override
    public void runOpMode() throws InterruptedException {
    waitForStart();
    while (opModeIsActive()) {
        lockTo(new Pose2d(0, 0, Math.toRadians(0)));
        drive.update();
    }
    }

    public void lockTo (Pose2d targetPos) {
        Pose2d currentPos = drive.getPoseEstimate();
        Pose2d difference = targetPos.minus(currentPos);
        Vector2d xy = difference.vec().rotated(-currentPos.getHeading());

        double heading = Angle.normDelta(targetPos.getHeading()) - Angle.normDelta(currentPos.getHeading());
        drive.setWeightedDrivePower(new Pose2d(xy, heading));
    }
}
