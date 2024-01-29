package org.firstinspires.ftc.teamcode.subsystem.Basic;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
@Config
public class HangingMech {
    private final DcMotor leftSlide, rightSlide;
    private final Gamepad gamepad1;
    private final int hanging = 1000;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    int position;

    public HangingMech(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.gamepad1 = opMode.gamepad1;

        leftSlide = (DcMotor) hardwareMap.get("leftSlide");
        rightSlide = (DcMotor) hardwareMap.get("rightSlide");

        leftSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setTargetPosition(0);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlide.setPower(0.5);

        rightSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setTargetPosition(0);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setPower(0.5);

        position = leftSlide.getCurrentPosition();
    }
    public void teleOp() throws InterruptedException{
        if (gamepad1.left_bumper) {
            hanging();
            Thread.sleep(500);
            clamping();
        }
    }
    public void soloTeleOp() {
        if (gamepad1.dpad_up && gamepad1.dpad_down)
            hanging();
    }
    public void hanging() {
        leftSlide.setTargetPosition(hanging);
        rightSlide.setTargetPosition(hanging);
    }
    public void clamping() {
        rightSlide.setPower(0.25);
        leftSlide.setPower(0.25);
        rightSlide.setTargetPosition(0);
        leftSlide.setTargetPosition(0);
    }
}
