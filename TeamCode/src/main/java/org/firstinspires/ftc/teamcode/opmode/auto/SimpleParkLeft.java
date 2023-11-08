package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystem.Arm;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Spinner;

@Autonomous
public class SimpleParkLeft extends LinearOpMode {
    private DcMotor leftFront, leftRear, rightRear, rightFront;
    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = (DcMotor) hardwareMap.get("leftFront");
        leftRear = (DcMotor) hardwareMap.get("leftRear");
        rightFront = (DcMotor) hardwareMap.get("rightFront");
        rightRear = (DcMotor) hardwareMap.get("rightRear");

        boolean aprilTagExample1 = true;
        boolean aprilTagExample2 = true;
        boolean aprilTagExample3 = true;

        Spinner spinner = new Spinner(hardwareMap, gamepad2);
        Drive drive = new Drive(this);
        Arm arm = new Arm(hardwareMap, gamepad2);

        //init
        waitForStart();
        while (opModeIsActive()) {
            if (aprilTagExample1) {

            }
        }
    }
}
