package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Intake;
import org.firstinspires.ftc.teamcode.subsystem.Slides;

@Autonomous
public class SimpleLeftAuto extends LinearOpMode {
    private DcMotor leftFront, leftRear, rightRear, rightFront;
    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = (DcMotor) hardwareMap.get("leftFront");
        leftRear = (DcMotor) hardwareMap.get("leftRear");
        rightFront = (DcMotor) hardwareMap.get("rightFront");
        rightRear = (DcMotor) hardwareMap.get("rightRear");


        Intake spinner = new Intake(hardwareMap, gamepad2);
        Drive drive = new Drive(this);
        Arm arm = new Arm(hardwareMap, gamepad2);
        Slides slides = new Slides(hardwareMap, gamepad2);

        //init
        waitForStart();
        movingMotors(1,1,1,1,1000);
        slides.moveLow();

    }
    public void movingMotors(double leftFrontPowers, double rightFrontPowers,
                             double leftRearPowers, double rightRearPowers, long durationOfAction  ) {
        leftFront.setPower(leftFrontPowers);
        rightFront.setPower(rightFrontPowers);
        leftRear.setPower(leftRearPowers);
        rightRear.setPower(rightRearPowers);
        sleep(durationOfAction);
    }
}
