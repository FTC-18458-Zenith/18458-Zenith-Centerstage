package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {
    private final DcMotor leftSlide, rightSlide;
    private final Gamepad gamepad2;
    final static double GEAR_RATIO = 1;
    final static double TICKS_PER_REVOLUTION = 1150;
    final static double GEAR_DIAMETER_CENTIMETERS = 3.2;
    private static final double HIGH = 33.33;
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
        if (gamepad2.dpad_up) moveHigh();
        else if (gamepad2.b) moveMid();
        else if (gamepad2.y) moveLow();
        else if (gamepad2.x) moveToPOS4();
        else if (gamepad2.a) moveToIntakeLevel();
        else if (gamepad2.left_stick_y > 0.75) manualSlideMovement(0.75);
        else if (gamepad2.left_stick_y < -0.75) manualSlideMovement(-0.75);
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
    public void manualSlideMovement(double motorPowers) {
        leftSlide.setPower(motorPowers);
        rightSlide.setPower(motorPowers);
    }
}