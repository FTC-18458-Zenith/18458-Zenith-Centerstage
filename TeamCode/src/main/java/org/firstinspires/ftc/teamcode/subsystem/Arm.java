package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.R;

@Config
public class Arm {
    //TODO: PROGRAM THE ARM, WHEN THE AXON MOVE IN THE SAME DIRECTION, IT MOVES, BUT IN THE OPPOSITE DIRECTION THE CLAW ROTATES.
    public final CRServo leftArmServo, rightArmServo;
    public final Gamepad gamepad2;
    public final Gamepad gamepad1;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    double position;
    public static volatile double SAME_DIRECTION = 1;
    public static volatile double OPP_DIRECTION = -1;
    public Arm(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.gamepad2 = opMode.gamepad2;
        this.gamepad1 = opMode.gamepad1;
        this.telemetry = opMode.telemetry;

        leftArmServo = (CRServo) hardwareMap.get("LeftArmServo");
        rightArmServo = (CRServo) hardwareMap.get("RightArmServo");

        leftArmServo.setDirection(DcMotorSimple.Direction.FORWARD);
        rightArmServo.setDirection(DcMotorSimple.Direction.FORWARD);

    }
    public void teleOp() throws InterruptedException {
        //DEGREES = (GEAR_RATIO * READINGS_PER_REVOLUTION) / (DEGREES_OF_FREEDOM) * DEGREES I WISH, DO NOT DO YET
        if (gamepad2.triangle) rotating();
        else if (gamepad2.square) moving();
    }
    public void soloTeleOp() throws InterruptedException {
        if (gamepad1.triangle) moving();
        else if (gamepad1.square) rotating();
    }
    public void setPosition(double position) {
        leftArmServo.setPower(position);
        rightArmServo.setPower(position);
    }
    public void rotating() {
        leftArmServo.setPower(OPP_DIRECTION);
        rightArmServo.setPower(OPP_DIRECTION);
    }
    public void moving() {
        leftArmServo.setPower(SAME_DIRECTION);
        rightArmServo.setPower(SAME_DIRECTION);
    }
}
