package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class SimpleAuto extends LinearOpMode {
    //Dcmotor leftFront is equal to null by default
    private DcMotor leftFront =(DcMotor) hardwareMap.get("leftFront"),
            leftRear = (DcMotor) hardwareMap.get("leftRear"),
            rightRear = (DcMotor) hardwareMap.get("rightRear"),
            rightFront = (DcMotor) hardwareMap.get("rightFront");
    private final DcMotor[] leftDriveTrainMotors = {leftRear, leftFront};
    private final DcMotor[] rightDriveTrainMotors = {rightRear, rightFront};
    
    @Override
    public void runOpMode() throws InterruptedException {


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