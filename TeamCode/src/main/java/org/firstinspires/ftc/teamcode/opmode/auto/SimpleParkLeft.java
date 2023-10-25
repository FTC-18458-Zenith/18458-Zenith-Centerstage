package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystem.Drive;

@Autonomous
public class SimpleParkLeft extends LinearOpMode {
    private DcMotor leftFront, leftRear, rightRear, rightFront;
    private final DcMotor[] leftDriveTrainMotors = {leftRear, leftFront};
    private final DcMotor[] rightDriveTrainMotors = {rightRear, rightFront};
    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = (DcMotor) hardwareMap.get("leftFront");
        leftRear = (DcMotor) hardwareMap.get("leftRear");
        rightFront = (DcMotor) hardwareMap.get("rightFront");
        rightRear = (DcMotor) hardwareMap.get("rightRear");
        // init
        waitForStart();

    }
}
