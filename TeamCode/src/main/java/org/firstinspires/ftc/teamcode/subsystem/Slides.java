package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {
    private final DcMotor leftSlide, rightSlide;
    private final Gamepad gamepad2;
    private final Gamepad gamepad1;
    final static double GEAR_RATIO = 1;
    final static double TICKS_PER_REVOLUTION = 145.1;
    //could be RPM
    final static double GEAR_DIAMETER_CENTIMETERS = 9.6;
    static final double HIGH = 1200;
    //max is 33
    static final double MID = 1000;
    static final double LOW = 700;
    static final double POSITION4 = 600;
    static final int INTAKE = 0;
    final double CENTIMETER_TO_TICKS = (TICKS_PER_REVOLUTION * GEAR_RATIO) / (GEAR_DIAMETER_CENTIMETERS * Math.PI);
    private HardwareMap hardwareMap;
    private static final double Isisah = 42.1052631579;

    public Slides(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.gamepad2 = opMode.gamepad2;
        this.gamepad1 = opMode.gamepad1;
        
        leftSlide = (DcMotor) hardwareMap.get("leftSlide");
        rightSlide = (DcMotor) hardwareMap.get("rightSlide");

        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSlide.setDirection(DcMotorSimple.Direction.FORWARD);

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
    }
    public void teleOp() {
        //Depends on number of ticks per revolution
        if (gamepad2.dpad_down) moveToIntakeLevel();
        else if (gamepad2.dpad_right) moveMid();
        else if (gamepad2.dpad_left) moveLow();
        //else if (gamepad2.dpad_right) moveToPOS4();
        else if (gamepad2.dpad_up) moveHigh();
    }
    public void soloTeleOp() {
        if (gamepad1.dpad_down) moveToIntakeLevel();
        else if (gamepad1.dpad_right) moveMid();
        else if (gamepad1.dpad_left) moveLow();
        else if (gamepad1.dpad_up) moveHigh();
    }
    public void moveSlides(double centimeters) {
//        Centimeters = (GEAR_RATIO * TICKS_PER_REVOLUTION) / (GEAR_DIAMETER_CENTIMETERS * Math.PI);
        leftSlide.setTargetPosition((int) (centimeters * CENTIMETER_TO_TICKS));
        rightSlide.setTargetPosition((int) (centimeters * CENTIMETER_TO_TICKS));
    }
    public void moveHigh() {
        leftSlide.setTargetPosition((int) HIGH);
        rightSlide.setTargetPosition((int) HIGH);
    }
    public void moveMid() {
        leftSlide.setTargetPosition((int) MID);
        rightSlide.setTargetPosition((int) MID);
    }
    public void moveLow() {
        leftSlide.setTargetPosition((int) LOW);
        rightSlide.setTargetPosition((int) LOW);
    }
    public void moveToIntakeLevel() {
        leftSlide.setTargetPosition(INTAKE);
        rightSlide.setTargetPosition(INTAKE);
    }
    public void manualSlideMovement() {
        leftSlide.setPower(gamepad2.left_stick_y);
        rightSlide.setPower(gamepad2.left_stick_y);
        int ticksPerRev = 100;
        if (gamepad2.left_stick_y >= 0.1) leftSlide.setTargetPosition(ticksPerRev++);
        else if (gamepad2.left_stick_y <= -0.1) leftSlide.setTargetPosition(ticksPerRev--);
    }
}