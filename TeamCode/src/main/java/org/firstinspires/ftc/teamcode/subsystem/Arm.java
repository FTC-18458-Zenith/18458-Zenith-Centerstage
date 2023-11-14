package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class Arm {
    private final CRServo leftArmServo, rightArmServo;
    private final Gamepad gamepad2;
    private final Gamepad gamepad1;
    private static volatile double OUTTAKE = 1;
    private static volatile double INTAKE = 0.24;

    //TODO:EDIT THESE VALUES TO CONSTRAINTS OF SERVO
    private static double GEAR_RATIO = 0;
    private static double READINGS_PER_REVOLUTION = 0;
    private static double DEGREES_OF_FREEDOM = 355;
    private static double GOAL_DEGREES = 0;
    private HardwareMap hardwareMap;
    public Arm(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.gamepad2 = opMode.gamepad2;
        this.gamepad1 = opMode.gamepad1;

        leftArmServo = (CRServo) hardwareMap.get("LeftArmServo");
        rightArmServo = (CRServo) hardwareMap.get("RightArmServo");

        leftArmServo.setPower(INTAKE - 0.52);
        rightArmServo.setPower(INTAKE);
        leftArmServo.setDirection(DcMotorSimple.Direction.FORWARD);
        rightArmServo.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    public void soloTeleOp() {
        if (gamepad1.triangle) outtake();
        else if (gamepad1.square) intake();
    }
    public void teleOp() {
        //DEGREES = (GEAR_RATIO * READINGS_PER_REVOLUTION) / (DEGREES_OF_FREEDOM) * DEGREES I WISH, DO NOT DO YET
        if (gamepad2.triangle) outtake();
        else if (gamepad2.square) intake();
    }

    public void setPosition(double position) {
        leftArmServo.setPower(position);
        rightArmServo.setPower(position);
    }
    public void outtake() {
        leftArmServo.setPower(-OUTTAKE);
        rightArmServo.setPower(OUTTAKE);
    }
    public void intake() {
        leftArmServo.setPower(-INTAKE);
        rightArmServo.setPower(INTAKE);
    }
    public void armAutoOuttake(long durationOfAction) throws InterruptedException {
        leftArmServo.setPower(-OUTTAKE);
        rightArmServo.setPower(OUTTAKE);
        Thread.sleep(durationOfAction);
        leftArmServo.setPower(-INTAKE);
        rightArmServo.setPower(INTAKE);
    }
    public void armAutoIntake(long durationOfAction) throws InterruptedException {
        leftArmServo.setPower(-INTAKE);
        rightArmServo.setPower(INTAKE);
        Thread.sleep(durationOfAction);
        leftArmServo.setPower(-OUTTAKE);
        rightArmServo.setPower(OUTTAKE);
    }

}
