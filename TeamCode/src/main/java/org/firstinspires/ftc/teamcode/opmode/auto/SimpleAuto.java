package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class SimpleAuto extends LinearOpMode {
    //Dcmotor leftFront is equal to null by default
    DcMotor leftFront, leftRear, rightRear, rightFront;
    DcMotor[] leftDriveTrainMotors = {leftRear, leftFront};
    DcMotor[] rightDriveTrainMotors = {rightRear, rightFront};
    @Override
    public void runOpMode() throws InterruptedException {

        leftFront = (DcMotor) hardwareMap.get("leftFront");
        leftRear = (DcMotor) hardwareMap.get("leftRear");
        rightFront = (DcMotor) hardwareMap.get("rightFront");
        rightRear = (DcMotor) hardwareMap.get("rightRear");
        // init
        waitForStart();
        setPowers(1,1, 1000);
        setPowers(0,0, 1000);
        setPowers(0.5,-0.5, 1000);
        setPowers(0,0, 500);
        setPowers(-0.25, -0.25, 3000);
        setPowers(0,0,2000);

    }
    void setPowers(double leftPower, double rightPower, long sleepMili) {

        for (DcMotor motor : leftDriveTrainMotors) {
            motor.setPower(leftPower);
        }
        for (DcMotor motor : rightDriveTrainMotors) {
            motor.setPower(rightPower);
        }
        sleep(sleepMili);
    }
}