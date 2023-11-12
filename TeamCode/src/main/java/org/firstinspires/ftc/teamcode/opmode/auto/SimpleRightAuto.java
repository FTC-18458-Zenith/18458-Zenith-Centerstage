package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Slides;

@Autonomous
public class SimpleRightAuto extends LinearOpMode {
    private DcMotor leftFront, leftRear, rightRear, rightFront;
    @Override
    public void runOpMode() throws InterruptedException {
        Intake spinner = new Intake(this);
        Drive drive = new Drive(this);
        Arm arm = new Arm(this);
        Slides slides = new Slides(this);

        leftFront = (DcMotor) hardwareMap.get("leftFront");
        leftRear = (DcMotor) hardwareMap.get("leftRear");
        rightFront = (DcMotor) hardwareMap.get("rightFront");
        rightRear = (DcMotor) hardwareMap.get("rightRear");
        drive.stuffForAuto();

        //init
        waitForStart();
        movingMotors(0.8,0.8,0.8,0.8,1000, 10000);
        arm.outtake();
    }
    public void movingMotors(double leftFrontPowers, double rightFrontPowers,
                             double leftRearPowers, double rightRearPowers, long durationOfAction, int targetPosition) {
        leftFront.setPower(leftFrontPowers);
        rightFront.setPower(rightFrontPowers);
        leftRear.setPower(leftRearPowers);
        rightRear.setPower(rightRearPowers);

        leftFront.setTargetPosition(targetPosition);
        leftRear.setTargetPosition(targetPosition);
        rightFront.setTargetPosition(targetPosition);
        rightRear.setTargetPosition(targetPosition);

        sleep(durationOfAction);
    }
}
