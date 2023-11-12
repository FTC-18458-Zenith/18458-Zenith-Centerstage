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
        leftFront = (DcMotor) hardwareMap.get("leftFront");
        leftRear = (DcMotor) hardwareMap.get("leftRear");
        rightFront = (DcMotor) hardwareMap.get("rightFront");
        rightRear = (DcMotor) hardwareMap.get("rightRear");

        Intake spinner = new Intake(this);
        Drive drive = new Drive(this);
        Arm arm = new Arm(this);
        Slides slides = new Slides(this);

        //init

        waitForStart();
        movingMotors(0.8,0.8,0.8,0.8,1000);
        arm.outtake();
    }
    public void movingMotors(double leftFrontPowers, double rightFrontPowers,
                             double leftRearPowers, double rightRearPowers, long durationOfAction) {
        leftFront.setPower(leftFrontPowers);
        rightFront.setPower(rightFrontPowers);
        leftRear.setPower(leftRearPowers);
        rightRear.setPower(rightRearPowers);
        sleep(durationOfAction);
    }
    public void targetPosition(int targetPosition) {
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);
        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);

        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(0);
        rightFront.setTargetPosition(0);
        rightRear.setTargetPosition(0);
        leftRear.setTargetPosition(0);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setPower(1);
        rightFront.setPower(1);
        leftRear.setPower(1);
        rightRear.setPower(1);
    }
}
