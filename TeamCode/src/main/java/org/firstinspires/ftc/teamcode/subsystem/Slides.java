package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {
    private final DcMotor leftSlide, rightSlide;
    private final Gamepad gamepad2;
    final static double GEAR_RATIO = 1;
    final static double TICKS_PER_REVOLUTION = 145.1;
    //could be RPM
    final static double GEAR_DIAMETER_CENTIMETERS = 4;
    private static final double HIGH = 1200;
    //max is 33
    private static final double MID = 25.75;
    private static final double LOW = 19;
    private static final double POSITION4 = 12.375;
    private static final int INTAKE = 0;
    final double CENTIMETER_TO_TICKS = (TICKS_PER_REVOLUTION * GEAR_RATIO) / (GEAR_DIAMETER_CENTIMETERS * Math.PI);

    public Slides(HardwareMap hardwareMap, Gamepad gamepad2) {
        leftSlide = (DcMotor) hardwareMap.get("leftSlide");
        rightSlide = (DcMotor) hardwareMap.get("rightSlide");
        this.gamepad2 = gamepad2;

        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        rightSlide.setDirection(DcMotorSimple.Direction.FORWARD);

        //TODO:Test for the other mode Float
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
        if (gamepad2.dpad_up) moveToIntakeLevel();
        else if (gamepad2.cross) moveMid();
        else if (gamepad2.triangle) moveLow();
        else if (gamepad2.square) moveToPOS4();
        else if (gamepad2.circle) moveHigh();
        else if (gamepad2.left_stick_y > 0.75 || gamepad2.left_stick_y < -0.75) manualSlideMovement();
    }
    public void moveSlides(double centimeters) {
        //Centimeters = (GEAR_RATIO * TICKS_PER_REVOLUTION) / (GEAR_DIAMETER_CENTIMETERS * Math.PI)
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
    public void moveToPOS4() {
        leftSlide.setTargetPosition((int) POSITION4);
        rightSlide.setTargetPosition((int) POSITION4);
    }
    public void moveToIntakeLevel() {
        leftSlide.setTargetPosition(INTAKE);
        rightSlide.setTargetPosition(INTAKE);
    }
    public void manualSlideMovement() {
        leftSlide.setPower(gamepad2.left_stick_y);
        rightSlide.setPower(gamepad2.left_stick_y);
    }
}