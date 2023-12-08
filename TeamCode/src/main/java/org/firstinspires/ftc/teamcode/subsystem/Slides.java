package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Slides {
    public final DcMotor leftSlide, rightSlide;
    public final Servo miniservo;
    public final Gamepad gamepad2;
    public final Gamepad gamepad1;
    public static int HIGH = 1200;
    public static int MID = 1000;
    public static int LOW = 700;
    public static double INTAKE = 195;
    public static int reset = 0;
    int position;
    public static double openingMini = 0.6;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    public Slides(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.gamepad2 = opMode.gamepad2;
        this.gamepad1 = opMode.gamepad1;
        this.telemetry = opMode.telemetry;
        
        leftSlide = (DcMotor) hardwareMap.get("leftSlide");
        rightSlide = (DcMotor) hardwareMap.get("rightSlide");
        miniservo = (Servo) hardwareMap.get("mini-servo");

        leftSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        miniservo.setDirection(Servo.Direction.FORWARD);

        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setTargetPosition(0);
        rightSlide.setTargetPosition(0);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setPower(1);
        rightSlide.setPower(1);
        miniservo.setPosition(1);

        position = leftSlide.getCurrentPosition();
        //No point in other slide as they should be same positions
    }
    public void teleOp() {
        if (gamepad2.dpad_down) moveToIntakeLevel();
        else if (gamepad2.dpad_right) moveMid();
        else if (gamepad2.dpad_left) moveLow();
        else if (gamepad2.dpad_up) moveHigh();
        else if (gamepad2.left_stick_button || gamepad2.right_stick_button) reset();
    }
    public void soloTeleOp() {
        if (gamepad1.dpad_down) moveToIntakeLevel();
        else if (gamepad1.dpad_right) moveMid();
        else if (gamepad1.dpad_left) moveLow();
        else if (gamepad1.dpad_up) moveHigh();
    }
    public void moveHigh() {
        leftSlide.setTargetPosition((int) HIGH);
        rightSlide.setTargetPosition((int) HIGH);
        miniservo.setPosition(openingMini);
    }
    public void moveMid() {
        leftSlide.setTargetPosition((int) MID);
        rightSlide.setTargetPosition((int) MID);
        miniservo.setPosition(openingMini);
    }
    public void moveLow() {
        leftSlide.setTargetPosition((int) LOW);
        rightSlide.setTargetPosition((int) LOW);
        miniservo.setPosition(openingMini);
    }
    public void reset() {
        leftSlide.setTargetPosition(reset);
        rightSlide.setTargetPosition(reset);
        miniservo.setPosition(0);
    }
    public void moveToIntakeLevel() {
        leftSlide.setTargetPosition((int) INTAKE);
        rightSlide.setTargetPosition((int) INTAKE);
        miniservo.setPosition(0);
    }
    public void manualSlideMovement() {
        leftSlide.setPower(gamepad2.left_stick_y);
        rightSlide.setPower(gamepad2.left_stick_y);
        int ticksPerRev = 100;
        if (gamepad2.left_stick_y >= 0.1) leftSlide.setTargetPosition(ticksPerRev++);
        else if (gamepad2.left_stick_y <= -0.1) leftSlide.setTargetPosition(ticksPerRev--);
    }
}